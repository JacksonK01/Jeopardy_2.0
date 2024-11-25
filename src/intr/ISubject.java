package intr;

import java.util.List;

public interface ISubject {
    String getTitle();
    List<IQuestion> getQuestions();

    void setQuestions(List<IQuestion> questions);

    default void addQuestion(IQuestion question) {
        getQuestions().add(question);
    }
}
