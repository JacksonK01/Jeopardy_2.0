public class Main {
    public static String dirPath = System.getProperty("user.dir");
    public static JeopardyBoard jeopardyBoard;

    public static void main(String[] args) {
        StartUp.load();
        Players players = new Players();
        jeopardyBoard = new JeopardyBoard();
        Game frame = new Game();
        frame.setVisible(true);
    }
}