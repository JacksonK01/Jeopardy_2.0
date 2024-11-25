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
    private List<JTextField> textFields;

    public PlayerMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("player_select.jpg");
    }

    @Override
    public JPanel addForeground() {
        JPanel panel = new JPanel();

        textFields = new ArrayList<>();

        int numPlayers = 5;

        JLabel title = new JLabel();

        TextConfigure.configure().setText("Enter Player Names").setBackgroundColor(Screen.JEOPARDY_BLUE).setSize(30).setWidth(100).setSouth(50).confirm(title, screen, panel);

        for(int i = 0; i < numPlayers; i++) {
            JTextField enterPlayer = new JTextField();
            TextFieldConfigure.configure().setText("Enter Player " + (i + 1) + " Here").setColumn(25)
                    .setNorth(10).setEast(10).setSouth(10).setWest(10).setSize(20).setHeight(20).setY(i + 1).confirm(enterPlayer, screen, panel);
            textFields.add(enterPlayer);
        }

        JButtonListener confirm = new JButtonListener("Confirm Names", this::confirmPlayers);
        ButtonConfigure.configure().setY(6).setWidth(100).setHeight(30).setNorth(10).finish(confirm, screen, panel);

        return panel;
    }

    private void confirmPlayers(ActionEvent e) {
        IBoard<ISubject> jeopardyBoard = screen.getJeopardyBoard();
        List<IPlayer> players = jeopardyBoard.getPlayers();

        textFields.forEach(text -> {
            players.add(new JeopardyPlayer(text.getText()));
        });
        
        screen.setContentPane(screen.getJeopardyBoardMenu().getPane());
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void tick() {

    }
}
