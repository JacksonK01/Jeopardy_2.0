package impl;

import intr.IQuestion;

//Possibly add timer
public class JeopardyQuestion implements IQuestion {
    private final String question;
    private final String answer;
    private final int value;

    public JeopardyQuestion(String question, String answer, int value) {
        this.question = question;
        this.answer = answer;
        this.value = value;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String toString() {
        return getQuestion() + " " + getAnswer() + " Value: " + getValue();
    }
}
