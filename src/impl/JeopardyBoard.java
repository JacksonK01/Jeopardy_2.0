package impl;

import intr.IBoard;
import intr.IPlayer;
import intr.ISubject;
import screen.Screen;
import util.JsonHandler;

import java.util.ArrayList;
import java.util.List;

//Manages the questions and players, the whole board. DOES not do GUI. Purely logic.
//Gets initialized after user presses "Start"
public class JeopardyBoard implements IBoard<ISubject> {
    private List<ISubject> subjects;
    private final List<IPlayer> players;

    public JeopardyBoard() {
        subjects = createDefaultSubjects();
        JsonHandler.exportJsonToFile(JsonHandler.serializeBoard(this), Screen.DEFAULT_FILE_TO_OPEN);

        players = new ArrayList<>();
    }

    private List<ISubject> createDefaultSubjects() {
        List<ISubject> defaultSubjects = new ArrayList<>(5);

        JeopardySubject science = new JeopardySubject("Science");
        science.addQuestion(new JeopardyQuestion("What is the chemical symbol for water?", "H2O", 100));
        science.addQuestion(new JeopardyQuestion("Who developed the theory of relativity?", "Albert Einstein", 200));
        science.addQuestion(new JeopardyQuestion( "What is the powerhouse of the cell?", "Mitochondria", 300));
        science.addQuestion(new JeopardyQuestion("What gas do plants absorb during photosynthesis?", "Carbon dioxide", 400));
        science.addQuestion(new JeopardyQuestion( "In physics, what is the unit of measurement for force?", "Newtons", 500));

        JeopardySubject history = new JeopardySubject("History");
        history.addQuestion(new JeopardyQuestion("Who was the first President of the United States?", "George Washington", 100));
        history.addQuestion(new JeopardyQuestion("In what year did World War II end?", "1945", 200));
        history.addQuestion(new JeopardyQuestion("Who was the leader of the Soviet Union during the Cuban Missile Crisis?", "Nikita Khrushchev", 300));
        history.addQuestion(new JeopardyQuestion("What ancient civilization built the pyramids in Egypt?", "Ancient Egyptians", 400));
        history.addQuestion(new JeopardyQuestion("The Renaissance period originated in which country?", "Italy", 500));

        JeopardySubject literature = new JeopardySubject("Literature");
        literature.addQuestion(new JeopardyQuestion("Who wrote \"Romeo and Juliet\"?", "William Shakespeare", 100));
        literature.addQuestion(new JeopardyQuestion("What is the title of J.K. Rowling's first Harry Potter book?", "Harry Potter and the Philosopher's Stone (or Sorcerer's Stone in the U.S.)", 200));
        literature.addQuestion(new JeopardyQuestion("Who wrote \"To Kill a Mockingbird\"?", "Harper Lee", 300));
        literature.addQuestion(new JeopardyQuestion("What dystopian novel by George Orwell explores themes of totalitarianism?", "1984", 400));
        literature.addQuestion(new JeopardyQuestion("Which Shakespeare play features the character Macbeth?", "Macbeth", 500));

        JeopardySubject geography = new JeopardySubject("Geography");
        geography.addQuestion(new JeopardyQuestion("What is the capital city of France?", "Paris", 100));
        geography.addQuestion(new JeopardyQuestion("Which river is the longest in the world?", "Nile", 200));
        geography.addQuestion(new JeopardyQuestion("In which continent is the Sahara Desert located?", "Africa", 300));
        geography.addQuestion(new JeopardyQuestion("What is the largest ocean on Earth?", "Pacific Ocean", 400));
        geography.addQuestion(new JeopardyQuestion("Name the seven continents.", "Africa, Antarctica, Asia, Europe, North America, Australia, South America", 500));

        JeopardySubject technology = new JeopardySubject("Technology");
        technology.addQuestion(new JeopardyQuestion("Who is the co-founder of Microsoft?", "Bill Gates", 100));
        technology.addQuestion(new JeopardyQuestion("What does the acronym \"HTML\" stand for?", "Hypertext Markup Language", 200));
        technology.addQuestion(new JeopardyQuestion("In computing, what does CPU stand for?", "Central Processing Unit", 300));
        technology.addQuestion(new JeopardyQuestion("Who developed the theory of general relativity?", "Albert Einstein", 400));
        technology.addQuestion(new JeopardyQuestion("What is the programming language created by Guido van Rossum?", "Python", 500));

        defaultSubjects.add(science);
        defaultSubjects.add(history);
        defaultSubjects.add(literature);
        defaultSubjects.add(geography);
        defaultSubjects.add(technology);

        return defaultSubjects;
    }

    @Override
    public List<ISubject> getSubjects() {
        return subjects;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public void setSubjects(String fileToOpen) {
        String json = JsonHandler.readJsonFromFile(fileToOpen);
        if(json == null) {
            System.out.println("Error reading " + fileToOpen + ". Using default instead");
            subjects = createDefaultSubjects();
            JsonHandler.exportJsonToFile(JsonHandler.serializeBoard(this), "default");
        } else {
            subjects = JsonHandler.deSerializeSubjects(fileToOpen, JeopardySubjectFactory.getFactory(), JeopardyQuestionFactory.getFactory());
        }
    }

    @Override
    public void clearAllPlayers() {
        players.clear();
    }
}
