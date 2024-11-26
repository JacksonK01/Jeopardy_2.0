package intr;

import java.util.List;

public interface IBoard<T extends ISubject> {
    List<T> getSubjects();
    List<IPlayer> getPlayers();
    void setSubjects(String file);
    void clearAllPlayers();
}
