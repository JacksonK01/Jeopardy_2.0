package screen;

public class Main {
    public static String dirPath = System.getProperty("user.dir");
    public static JeopardyBoardOld jeopardyBoard = new JeopardyBoardOld();

    public static void main(String[] args) {
        StartUp.load();
        Players players = new Players();
        Screen frame = new Screen();

//        JeopardyBoard jeopardyBoard1 = new JeopardyBoard();
//
//        String jeoJson = JsonHandler.readJsonFromFile("default");
//
//        System.out.println(jeoJson);
//
//        JsonHandler.exportJsonToFile(JsonHandler.serializeBoard(jeopardyBoard1), "default");
    }
}