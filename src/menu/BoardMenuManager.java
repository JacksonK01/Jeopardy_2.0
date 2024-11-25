package menu;

import impl.JeopardyBoard;
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
import java.awt.event.ActionListener;
import java.util.List;

public class BoardMenuManager extends AbstractMenu {
    int maxPlayersForColumn = 0;

    public BoardMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("board.jpg");
    }

    @Override
    public JPanel addForeground() {
        JPanel fg = new JPanel();

        List<ISubject> subjects = screen.getJeopardyBoard().getSubjects();

        maxPlayersForColumn = subjects.size();

        Color blue = Screen.JEOPARDY_BLUE;
        Color yellow = Screen.JEOPARDY_YELLOW;

        for(int i = 0; i < subjects.size(); i++) {
            ISubject s = subjects.get(i);

            JLabel subjectTitle = new JLabel();
            TextConfigure.configure().setX(i).setText(s.getTitle()).setTextColor(yellow)
                    .setStyle(Font.BOLD).setSize(16).setWidth(75).setHeight(25)
                    .setSouth(25).setBackgroundColor(blue).confirm(subjectTitle, screen, fg);
            subjectTitle.setBackground(blue);

            List<IQuestion> questions = s.getQuestions();
            for(int j = 0; j < questions.size(); j++) {
                IQuestion q = questions.get(j);
                JButton button = new JButton();

                ActionListener action = (e) -> {
                    QuestionMenuManager menu = (QuestionMenuManager) screen.getQuestionMenu();
                    button.setVisible(false);
                    menu.setQuestion(q);
                    screen.setContentPane(menu.getPane());
                };

                button.addActionListener(action);

                ButtonConfigure.configure().setX(i).setY(j + 1).setText("$" + q.getValue())
                        .setWidth(90).setHeight(65)
                        .setSouth(5).setBackgroundColor(blue)
                        .setForegroundColor(yellow).finish(button, screen, fg);
            }
        }

        return fg;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void tick() {
        List<IPlayer> players = screen.getJeopardyBoard().getPlayers();

    }
}
