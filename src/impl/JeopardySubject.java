package impl;

import intr.IQuestion;
import intr.ISubject;

import java.util.ArrayList;
import java.util.List;

public class JeopardySubject implements ISubject {
    private final String title;
    private List<IQuestion> questions;

    public JeopardySubject(String title) {
        this.title = title;
        questions = new ArrayList<>();
    }

    public JeopardySubject(String title, List<IQuestion> questions) {
        this.title = title;
        this.questions = questions;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<IQuestion> getQuestions() {
        return questions;
    }

    @Override
    public void setQuestions(List<IQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder(title);
        for(IQuestion q : questions) {
            toReturn.append(": ").append("Question: ").append(q.getQuestion()).append(" ").append("Answer: ").append(q.getAnswer());
        }

        return toReturn.toString();
    }
}
