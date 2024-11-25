package intr;

public interface ISubjectFactory<T extends ISubject> {
    T create(String title);
}
