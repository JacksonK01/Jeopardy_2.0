package menu;

import impl.JeopardyPlayer;
import intr.IBoard;
import intr.IPlayer;
import intr.IQuestion;
import intr.ISubject;
import screen.Screen;
import util.JBackgroundPanel;
import util.Pair;
import util.configure.ButtonConfigure;
import util.configure.TextConfigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BoardMenuManager extends AbstractMenu {
    private boolean hasInit = false;
    private List<JLabel> playerLabels;
    private List<JButton> allBoardButtons;
    private int totalBoardButtons = 0;
    private boolean hasSetWinning = false;

    public BoardMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("board.jpg");
    }

    @Override
    public JPanel addForeground() {
        return new JPanel();
    }

    @Override
    public void tick() {
        List<IPlayer> players = screen.getJeopardyBoard().getPlayers();

        if(players == null || playerLabels == null) {
            return;
        }

        for(int i = 0; i < players.size(); i++) {
            JLabel l = playerLabels.get(i);
            IPlayer p = players.get(i);

            l.setText(p.getNames() + ": $" + p.getScore());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSetActive() {
        if(hasInit) {
            return;
        }

        allBoardButtons = new ArrayList<>();

        List<ISubject> subjects = screen.getJeopardyBoard().getSubjects();

        Color blue = Screen.JEOPARDY_BLUE;
        Color yellow = Screen.JEOPARDY_YELLOW;

        for(int i = 0; i < subjects.size(); i++) {
            ISubject s = subjects.get(i);

            JLabel subjectTitle = new JLabel();
            TextConfigure.configure().setX(i).setText(s.getTitle()).setTextColor(yellow)
                    .setStyle(Font.BOLD).setSize(16).setWidth(75).setHeight(25)
                    .setSouth(25).setBackgroundColor(blue).confirm(subjectTitle, screen, foreground);
            subjectTitle.setBackground(blue);

            List<IQuestion> questions = s.getQuestions();
            for(int j = 0; j < questions.size(); j++) {
                IQuestion q = questions.get(j);
                JButton button = new JButton();

                ActionListener action = (e) -> {
                    totalBoardButtons--;
                    AbstractMenu menu = screen.getQuestionMenu();
                    button.setVisible(false);

                    //Has to be raw, won't compile otherwise
                    if(menu instanceof Consumer setQuestionInMenu) {
                        setQuestionInMenu.accept(q);
                    }

                    screen.setContentPane(menu);
                };

                button.addActionListener(action);

                allBoardButtons.add(button);

                totalBoardButtons++;

                ButtonConfigure.configure().setX(i).setY(j + 1).setText("$" + q.getValue())
                        .setWidth(90).setHeight(65)
                        .setSouth(5).setBackgroundColor(blue)
                        .setForegroundColor(yellow).finish(button, screen, foreground);
            }
        }

        int maxPlayersForColumn = subjects.size();

        List<IPlayer> players = screen.getJeopardyBoard().getPlayers();

        playerLabels = new ArrayList<>();

        int x = 0;
        int yOffset = 1;

        for(int i = 0; i < players.size(); i++) {
            IPlayer player = players.get(i);
            int y = subjects.get(i % maxPlayersForColumn).getQuestions().size() + yOffset;
            JLabel label = new JLabel();

            TextConfigure.configure().setX(x).setY(y).setText(player.getNames() + ": $" + player.getScore())
                    .setTextColor(yellow).setStyle(Font.BOLD).setSize(16).setWidth(75).setHeight(25)
                    .setBackgroundColor(blue).confirm(label, screen, foreground);

            playerLabels.add(label);

            x++;
            if(x >= maxPlayersForColumn) {
                x = 0;
                yOffset++;
            }
        }

        foreground.setOpaque(false);
        foreground.setLayout(screen.getLayout());
        foreground.setBounds(0, 0, width, height);

        hasInit = true;
    }

    @Override
    public void onReset() {
        hasInit = false;
        foreground.removeAll();
    }

    public boolean isBoardEmpty() {
        return totalBoardButtons <= 0;
    }

}
