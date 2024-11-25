package intr;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface IBoard<T extends ISubject> {
    List<T> getSubjects();
    List<IPlayer> getPlayers();
}
