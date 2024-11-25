package impl;

import intr.IQuestion;
import intr.IQuestionFactory;

public class JeopardyQuestionFactory implements IQuestionFactory<IQuestion> {
    @Override
    public IQuestion create(String question, String answer, int value) {
        return new JeopardyQuestion(question, answer, value);
    }

    public static JeopardyQuestionFactory getFactory() {
        return new JeopardyQuestionFactory();
    }
}
