import screen.Screen;

public class Main {

    public static void main(String[] args) {
        Screen frame = new Screen();
        frame.setVisible(true);

        Thread thread = new Thread(frame);
        thread.start();

//        JeopardyBoard jeopardyBoard1 = new JeopardyBoard();
//
//        String jeoJson = JsonHandler.readJsonFromFile("default");
//
//        System.out.println(jeoJson);
//
//        JsonHandler.exportJsonToFile(JsonHandler.serializeBoard(jeopardyBoard1), "default");
    }
}