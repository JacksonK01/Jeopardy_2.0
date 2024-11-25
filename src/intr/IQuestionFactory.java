package intr;

public interface IQuestionFactory<T extends IQuestion> {
    T create(String question, String answer, int value);
}
