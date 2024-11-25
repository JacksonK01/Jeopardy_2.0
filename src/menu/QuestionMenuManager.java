package menu;

import impl.JeopardyQuestion;
import intr.IQuestion;
import screen.Screen;
import util.JBackgroundPanel;
import util.JButtonListener;
import util.configure.ButtonConfigure;
import util.configure.TextConfigure;

import javax.swing.*;
import java.awt.*;

public class QuestionMenuManager extends AbstractMenu {
    private JButtonListener answerButton;
    private JButtonListener nextButton;

    private JLabel question;
    private JLabel answer;

    private IQuestion currentQuestion;

    public QuestionMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("background_questions.jpg");
    }

    @Override
    public JPanel addForeground() {
        JPanel fg = new JPanel();

        question = new JLabel();
        answer = new JLabel();
        currentQuestion = new JeopardyQuestion("Default question", "Default answer", 0);

        TextConfigure.configure().setText(currentQuestion.getQuestion()).setBackgroundColor(Screen.JEOPARDY_BLUE).setSize(20).setWidth(100).setHeight(47).confirm(question, screen, fg);
        TextConfigure.configure().setText(currentQuestion.getAnswer()).setInvisible().setBackgroundColor(Screen.JEOPARDY_BLUE).setSize(20).setWidth(100).setHeight(47).confirm(answer, screen, fg);

        answerButton = new JButtonListener("Show Answer", (e) -> {
            answerButton.setVisible(false);
            nextButton.setVisible(true);
            question.setVisible(false);
            answer.setVisible(true);
        });

        nextButton = new JButtonListener("Next =>", (e) -> {
            answerButton.setVisible(true);
            nextButton.setVisible(false);
            question.setVisible(true);
            answer.setVisible(false);
            screen.setContentPane(screen.getRewardMenu().getPane());
        });

        ButtonConfigure.configure().setY(1).setWidth(75).setHeight(50).setNorth(50).finish(answerButton, screen, fg);
        ButtonConfigure.configure().setY(1).setWidth(50).setHeight(25).setNorth(75).finish(nextButton, screen, fg);

        return fg;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void tick() {
        question.setText(currentQuestion.getQuestion());
        answer.setText(currentQuestion.getAnswer());
    }

    public void setQuestion(IQuestion q) {
        this.currentQuestion = q;
    }
}
