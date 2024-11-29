package util;

import com.google.gson.*;
import intr.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonHandler {
    private static final String TITLE = "subject_title";
    private static final String SUBJECT = "subject_questions";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private static final String VALUE = "value";

    private static final String DIRECTORY = System.getProperty("user.dir");

    public static <T extends ISubject> String serializeBoard(IBoard<T> board) {
        return serializeSubjects(board.getSubjects());
    }

    public static <T extends ISubject> String serializeSubjects(List<T> subjects) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray entireJson = new JsonArray();

        for(T s : subjects) {
            JsonObject jsonSubject = new JsonObject();
            jsonSubject.addProperty(TITLE, s.getTitle());
            JsonArray questionJson = new JsonArray();

            List<IQuestion> questions = s.getQuestions();
            for(IQuestion q : questions) {
                JsonObject questionObject = new JsonObject();
                questionObject.addProperty(QUESTION, q.getQuestion());
                questionObject.addProperty(ANSWER, q.getAnswer());
                questionObject.addProperty(VALUE, q.getValue());
                questionJson.add(questionObject);
            }

            jsonSubject.add(SUBJECT, questionJson);
            entireJson.add(jsonSubject);
        }


        return gson.toJson(entireJson);
    }

    public static <T extends ISubject, Q extends IQuestion> List<T> deSerializeSubjects(@NotNull String json, @NotNull ISubjectFactory<T> subjectFactory, @NotNull IQuestionFactory<Q> questionFactory) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

        List<T> subjects = new ArrayList<>(jsonArray.size());

        for(JsonElement e : jsonArray) {
            JsonObject jsonObject = e.getAsJsonObject();

            T subject = subjectFactory.create(jsonObject.get(TITLE).getAsString());

            JsonArray questions = jsonObject.getAsJsonArray(SUBJECT);
            for(JsonElement q : questions) {
                JsonObject qObject = q.getAsJsonObject();
                String question = qObject.get(QUESTION).getAsString();
                String answer = qObject.get(ANSWER).getAsString();
                int value = qObject.get(VALUE).getAsInt();
                subject.addQuestion(questionFactory.create(question, answer, value));
            }

            subjects.add(subject);
        }

        return subjects;
    }

    public static void exportJsonToFile(@NotNull String json, @NotNull String fileName) {
        String file = fileName;

        if(!file.contains(".json")) {
            file += ".json";
        }

        //Makes sure the directory exists
        File directory = new File(DIRECTORY + "/SavedBoards");
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        try(FileWriter fileWriter = new FileWriter(directory.getAbsolutePath() + "/" + file)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static String readJsonFromFile(String fileName) {
        String file = fileName;

        if(!file.contains(".json")) {
            file += ".json";
        }

        String filePath = DIRECTORY + "/SavedBoards/" + file;

        try {
            File openFile = new File(filePath);
            Scanner scanner = new Scanner(openFile);
            StringBuilder jsonBuilder = new StringBuilder();
            while(scanner.hasNextLine()) {
                jsonBuilder.append(scanner.nextLine()).append("\n");
            }
            return jsonBuilder.toString();
        } catch (Exception e) {
            System.out.println("Error loading json file");
        }

        return null;
    }
}
