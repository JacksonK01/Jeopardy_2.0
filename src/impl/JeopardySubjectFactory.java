package impl;

import intr.ISubject;
import intr.ISubjectFactory;

public class JeopardySubjectFactory implements ISubjectFactory<ISubject> {

    @Override
    public ISubject create(String title) {
        return new JeopardySubject(title);
    }

    public static JeopardySubjectFactory getFactory() {
        return new JeopardySubjectFactory();
    }
}
