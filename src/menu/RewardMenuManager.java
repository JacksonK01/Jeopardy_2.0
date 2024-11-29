package menu;

import intr.IBoard;
import intr.IPlayer;
import intr.IQuestion;
import intr.ISubject;
import screen.Screen;
import util.JBackgroundPanel;
import util.JButtonListener;
import util.configure.ButtonConfigure;
import util.configure.TextConfigure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RewardMenuManager extends AbstractMenu implements Consumer<IQuestion> {
    //5 seconds
    public static final int INTERVAL = 2;

    private IQuestion currentQuestion;
    private boolean editMode = false;
    private int timer = 0;

    private List<JButton> allButtons;

    public RewardMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public void onReset() {

    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("reward_menu.jpg");
    }

    @Override
    public void onSetActive() {
        IBoard<ISubject> board = screen.getJeopardyBoard();
        List<ISubject> subjects = board.getSubjects();
        List<IPlayer> players = board.getPlayers();

        allButtons = new ArrayList<>();

        int spot = Math.floorDiv(subjects.size(), 2);

        JButtonListener edit = new JButtonListener("Edit", (e) -> {
            editMode = !editMode;
            if(editMode) {
                ((JButton) e.getSource()).setBackground(Color.red);
            } else {
                ((JButton) e.getSource()).setBackground(Color.white);
            }
            timer = INTERVAL * Screen.FPS;
        });

        ButtonConfigure.configure().setX(spot - 1).setSouth(100).finish(edit, screen, foreground);

        JLabel topTitle = new JLabel();

        TextConfigure.configure().setX(spot).setSouth(100).setText("Who Answered Correctly").setSize(20).setStyle(Font.BOLD)
                .setBackgroundColor(Screen.JEOPARDY_BLUE).confirm(topTitle, screen, foreground);

        JButtonListener next = new JButtonListener("Next", (e) -> {
            if(screen.getJeopardyBoardMenu() instanceof BoardMenuManager menu) {
                if(menu.isBoardEmpty()) {
                    screen.setContentPane(screen.getWinningMenu());
                    return;
                }
            }
            screen.setContentPane(screen.getJeopardyBoardMenu());
        });

        ButtonConfigure.configure().setX(spot + 1).setSouth(100).finish(next, screen, foreground);

        int maxPlayersForColumn = subjects.size();
        int x = 0;
        int yOffset = 1;

        for(int i = 0; i < players.size(); i++) {
            IPlayer player = players.get(i);
            int y = subjects.get(i % maxPlayersForColumn).getQuestions().size() + yOffset;

            JButtonListener button = new JButtonListener((e) -> {
                if(currentQuestion != null) {
                    int value = currentQuestion.getValue();
                    if(editMode) {
                        ((JButton) e.getSource()).setBackground(Color.RED);
                        player.addScore(-value);
                    } else {
                        ((JButton) e.getSource()).setBackground(Color.YELLOW);
                        player.addScore(value);
                    }
                    timer = INTERVAL * Screen.FPS;
                }
            });

            allButtons.add(button);

            ButtonConfigure.configure().setX(x).setY(y).setText(player.getNames())
                    .setWidth(75).setHeight(25).finish(button, screen, foreground);

            x++;
            if(x >= maxPlayersForColumn) {
                x = 0;
                yOffset++;
            }
        }

        allButtons.add(edit);
        allButtons.add(next);
    }

    @Override
    public void onRemove() {
        foreground.removeAll();
    }

    @Override
    public JPanel addForeground() {
        return new JPanel();
    }

    @Override
    public void tick() {
        if(allButtons == null) {
            return;
        }

        if(timer > 0) {
            timer--;
        }

        if(timer <= 0) {
            allButtons.forEach((e) -> {
                e.setBackground(Color.white);
            });
            editMode = false;
        }

    }

    @Override
    public void accept(IQuestion iQuestion) {
        this.currentQuestion = iQuestion;
    }
}
