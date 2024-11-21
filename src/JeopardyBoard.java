import java.io.FileWriter;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;


//I wanted to use dictionaries to store questions with their answers, there just wasn't a good way of doing that

public class JeopardyBoard {
    public static Subject subjectOne;
    public static Subject subjectTwo;
    public static Subject subjectThree;
    public static Subject subjectFour;
    public static Subject subjectFive;

    public JeopardyBoard() {
        createDefaultBoard();
        String file = "default.txt";
        if (!file.contains(".txt")) {
            file = file + ".txt";
        }
        try {
            File openFile = new File(Main.dirPath + "/JeopardyGameBoards/" + file);
            if (openFile.exists()) {
                System.out.println("Loading " + file);
            } else {
                System.out.println(file + " does not exist\nLoading default.txt");
                openFile.delete();
                openFile = new File(Main.dirPath + "/JeopardyGameBoards/" + "default.txt");
            }
            Scanner readFile = new Scanner(openFile);

            this.subjectOne = createSubject(readFile);

            this.subjectTwo = createSubject(readFile);

            this.subjectThree = createSubject(readFile);

            this.subjectFour = createSubject(readFile);

            this.subjectFive = createSubject(readFile);


        } catch (Exception e) {
            System.out.println("Error loading Jeopardy Boards");
        }
    }
    public void writeToFile(String customFile) {
        try {
            FileWriter newFile = new FileWriter(System.getProperty("user.dir")+"/JeopardyGameBoards/"+customFile);
            writeListToFile(newFile, subjectOne.title, subjectOne.questions, subjectOne.answers);
            writeListToFile(newFile, subjectTwo.title, subjectTwo.questions, subjectTwo.answers);
            writeListToFile(newFile, subjectThree.title, subjectThree.questions, subjectThree.answers);
            writeListToFile(newFile, subjectFour.title, subjectFour.questions, subjectFour.answers);
            writeListToFile(newFile, subjectFive.title, subjectFive.questions, subjectFive.answers);
            newFile.close();
            System.out.println(System.getProperty("user.dir")+"/JeopardyGameBoards/" + customFile + " Successfully Created");



        } catch (IOException e) {
            System.out.print("\nAn error has occured when trying to generate custom question board\n");
            e.printStackTrace();

        }
    }
    public Boolean load(String customFile) {
        String file = customFile.toLowerCase().strip();
        if (!file.contains(".txt")) {
            file = file + ".txt";
        }
        try {
            File openFile = new File(Main.dirPath + "/JeopardyGameBoards/" + file);
            if (openFile.exists()) {
                System.out.println("Loading " + file);
            } else {
                System.out.println(file + " does not exist\nLoading default.txt");
                openFile.delete();
                openFile = new File(Main.dirPath + "/JeopardyGameBoards/" + "default.txt");
            }
            Scanner readFile = new Scanner(openFile);

            this.subjectOne = createSubject(readFile);

            this.subjectTwo = createSubject(readFile);

            this.subjectThree = createSubject(readFile);

            this.subjectFour = createSubject(readFile);

            this.subjectFive = createSubject(readFile);

            return true;


        } catch (Exception e) {
            System.out.println("Error loading Jeopardy Boards");
            return false;
        }
    }

    public Subject createSubject(Scanner readFile) {
        String lineOfData = readFile.nextLine();
        String title = lineOfData;

        lineOfData = readFile.nextLine();
        List<String> questionList = List.of(lineOfData.split("\\|"));

        lineOfData = readFile.nextLine();
        List<String> questionAnswer = List.of(lineOfData.split("\\|"));

        return new Subject(title, questionList, questionAnswer);
    }

    public List<String> readFileData(String titleSubject, String questions, String answers) {
        return List.of(titleSubject, questions.split("|").toString(), answers.split("|").toString());
    }

    public void createDefaultBoard() {
        String titleOne = "Science";
        List<String> subOneQuestions = List.of("What is the chemical symbol for water?", "Who developed the theory of relativity?",
                "What is the powerhouse of the cell?", "What gas do plants absorb during photosynthesis?",
                "In physics, what is the unit of measurement for force?");
        List<String> subOneAnswers = List.of("H2O", "Albert Einstein", "Mitochondria", "Carbon dioxide", "Newton");

        String titleTwo = "History";
        List<String> subTwoQuestions = List.of("Who was the first President of the United States?", "In what year did World War II end?",
                "Who was the leader of the Soviet Union during the Cuban Missile Crisis?", "What ancient civilization built the pyramids in Egypt?",
                "The Renaissance period originated in which country?");
        List<String> subTwoAnswers = List.of("George Washington", "1945", "Nikita Khrushchev", "Ancient Egyptians", "Italy");

        String titleThree = "Literature";
        List<String> subThreeQuestions = List.of("Who wrote \"Romeo and Juliet\"?", "What is the title of J.K. Rowling's first Harry Potter book?",
                "Who wrote \"To Kill a Mockingbird\"?", "What dystopian novel by George Orwell explores themes of totalitarianism?",
                "Which Shakespeare play features the character Macbeth?");
        List<String> subThreeAnswers = List.of("William Shakespeare", "Harry Potter and the Philosopher's Stone (or Sorcerer's Stone in the U.S.)", "Harper Lee", "1984", "Macbeth");

        String titleFour = "Geography";
        List<String> subFourQuestions = List.of("What is the capital city of France?", "Which river is the longest in the world?",
                "In which continent is the Sahara Desert located?", "What is the largest ocean on Earth?",
                "Name the seven continents.");
        List<String> subFourAnswers = List.of("Paris", "Nile", "Africa", "Pacific Ocean", "Africa, Antarctica, Asia, Europe, North America, Australia, South America");

        String titleFive = "Technology";
        List<String> subFiveQuestions = List.of("Who is the co-founder of Microsoft?", "What does the acronym \"HTML\" stand for?",
                "In computing, what does CPU stand for?", "Who developed the theory of general relativity?",
                "What is the programming language created by Guido van Rossum?");
        List<String> subFiveAnswers = List.of("Bill Gates", "Hypertext Markup Language", "Central Processing Unit", "Albert Einstein", "Python");

        try {
            FileWriter newFile = new FileWriter(System.getProperty("user.dir")+"/JeopardyGameBoards/default.txt");
            writeListToFile(newFile, titleOne, subOneQuestions, subOneAnswers);
            writeListToFile(newFile, titleTwo, subTwoQuestions, subTwoAnswers);
            writeListToFile(newFile, titleThree, subThreeQuestions, subThreeAnswers);
            writeListToFile(newFile, titleFour, subFourQuestions, subFourAnswers);
            writeListToFile(newFile, titleFive, subFiveQuestions, subFiveAnswers);
            newFile.close();
            System.out.println(System.getProperty("user.dir")+"/JeopardyGameBoards/default.txt Successfully Created");



        } catch (IOException e) {
            System.out.print("\nAn error has occured when trying to generate default question board\n");
            e.printStackTrace();

        }


    }

    public void writeListToFile(FileWriter file, String title, List<String> questions, List<String> answers) {
        int counter = 0;
        try {
            file.write(title + "\n");
            for(String item : questions) {
                file.write(item + "|");
            }
            file.write("\n");
            for(String item : answers) {
                file.write(item + "|");
            }
            file.write("\n");
            file.flush();
        }
        catch (IOException e) {
            System.out.print("An error has occured when trying to generate default question board");
            e.printStackTrace();
        }
    }

    class Subject {
        String title;
        List<String> questions = List.of();
        List<String> answers = List.of();

        public Subject(String title, List<String> questions, List<String> answers) {
            this.title = title;
            this.questions = questions;
            this.answers = answers;
        }
    }
}
