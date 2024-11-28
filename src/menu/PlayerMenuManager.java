package menu;

import impl.JeopardyPlayer;
import intr.IBoard;
import intr.IPlayer;
import intr.ISubject;
import screen.Screen;
import util.JBackgroundPanel;
import util.JButtonListener;
import util.configure.ButtonConfigure;
import util.configure.TextConfigure;
import util.configure.TextFieldConfigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayerMenuManager extends AbstractMenu {
    private static final int MAX_PLAYERS = 15;
    private static final int MAX_PER_ROW = 5;
    private static final int MIN_PLAYERS = 2;

    private List<JTextField> textFields;
    private final JPanel addPlayers;
    //y value
    private int row = 0;
    //x value
    private int column = 0;

    int amountVisible = 0;

    public PlayerMenuManager(Screen screen) {
        super(screen);

        addPlayers = new JPanel();
        addPlayers.setOpaque(false);
        addPlayers.setLayout(screen.getLayout());


        JLabel title = new JLabel();

        JButtonListener back = new JButtonListener("<= Back", (e) -> {
            screen.setContentPane(screen.getStartMenu());
        });

        JButtonListener reset = new JButtonListener("Reset", (e) -> {
            this.onReset();
        });

        JButtonListener addPlayer = new JButtonListener("Add Player", (e) -> {
            if(!canAddPlayer()) {
                return;
            }

            boolean search = true;
            int i = 0;
            while(search && i < textFields.size()) {
                if(!textFields.get(i).isVisible()) {
                    textFields.get(i).setVisible(true);
                    search = false;
                    amountVisible++;
                }
                i++;
            }
        });

        JButtonListener removePlayer = new JButtonListener("Remove Player", (e) -> {
            if(!canRemovePlayer()) {
                return;
            }

            boolean search = true;
            System.out.println(textFields.size());
            int i = textFields.size() - 1;
            while(search && i >= 0) {
                if(textFields.get(i).isVisible()) {
                    textFields.get(i).setVisible(false);
                    search = false;
                    amountVisible--;
                }
                i--;
            }
        });
        JButtonListener confirm = new JButtonListener("Confirm Names", this::confirmPlayers);

        int north = 400;

        TextConfigure.configure().setText("Enter Player Names").setBackgroundColor(Screen.JEOPARDY_BLUE).setX(1).setSize(30).setWidth(100).setSouth(0).confirm(title, screen, addPlayers);

        ButtonConfigure.configure().setX(0).setWidth(30).setHeight(10).setY(0).finish(back, screen, addPlayers);
        ButtonConfigure.configure().setX(2).setWidth(30).setHeight(10).setY(0).finish(reset, screen, addPlayers);
        ButtonConfigure.configure().setX(0).setWidth(60).setHeight(20).setY(7).setNorth(north).finish(removePlayer, screen, addPlayers);
        ButtonConfigure.configure().setX(1).setWidth(200).setHeight(30).setY(7).setNorth(north).finish(confirm, screen, addPlayers);
        ButtonConfigure.configure().setX(2).setWidth(60).setHeight(20).setY(7).setNorth(north).finish(addPlayer, screen, addPlayers);

        addPlayers.setBounds(0, 0, width, height);

        this.pane.add(addPlayers, JLayeredPane.MODAL_LAYER);

        //Foreground
        textFields = new ArrayList<>();
        createDefaultTextFields(foreground);
    }

    @Override
    public void onReset() {
        column = 0;
        row = 0;
        amountVisible = 0;
        textFields.clear();
        foreground.removeAll();
        createDefaultTextFields(foreground);
    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("player_select1.jpg");
    }

    @Override
    public JPanel addForeground() {
        return new JPanel();
    }

    private void confirmPlayers(ActionEvent e) {
        IBoard<ISubject> jeopardyBoard = screen.getJeopardyBoard();
        jeopardyBoard.clearAllPlayers();
        List<IPlayer> players = jeopardyBoard.getPlayers();

        textFields.forEach(text -> {
            if(text.isVisible()) {
                players.add(new JeopardyPlayer(text.getText()));
            }
        });

        if(!screen.getFileToOpen().equals(Screen.DEFAULT_FILE_TO_OPEN)) {
            String fileToOpen = screen.getFileToOpen();
            jeopardyBoard.setSubjects(fileToOpen);
            System.out.println("Successfully reading " + fileToOpen);
        }

        screen.setContentPane(screen.getJeopardyBoardMenu());
    }

    @Override
    public void draw(Graphics g) {
//        if(title != null) {
//            g.setColor(Color.red);
//            g.fillRect(title.getX(), title.getY(), 500, 500);
//            System.out.println("Trying to draw red");
//        }
//        System.out.println("Trying to draw red");
    }

    @Override
    public void tick() {
        addPlayers.setBounds(0, 0, width, height);
    }

    private void createDefaultTextFields(JPanel panel) {
        for(int i = 0; i < MAX_PLAYERS; i++) {
            playerTextFieldCreator(i + 1, column, row, panel);
        }
    }

    //n is both the y value and number for player
    private void playerTextFieldCreator(int n, int x, int y, JPanel panel) {
        JTextField enterPlayer = new JTextField();
        TextFieldConfigure.configure().setText("Enter Player " + (n) + " Here").setColumn(20).setWidth(100).setX(x)
                .setNorth(10).setEast(0).setSouth(10).setWest(35).setSize(15).setHeight(20).setY(y).confirm(enterPlayer, screen, panel);
        textFields.add(enterPlayer);
        if(textFields.size() > MAX_PER_ROW) {
            enterPlayer.setVisible(false);
        } else {
            amountVisible++;
        }
        incrementRowAndColumn();
    }

    private boolean canAddPlayer() {
        return amountVisible < MAX_PLAYERS;
    }

    private boolean canRemovePlayer() {
        return amountVisible > MIN_PLAYERS;
    }


    private void incrementRowAndColumn() {
        if (!canAddPlayer()) {
            row = 4;
            column = 2;
            return;
        }
        row++;
        if (row >= MAX_PER_ROW) {
            column++;
            row = 0;
        }
    }
}
