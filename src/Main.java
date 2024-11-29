import screen.Screen;

public class Main {
    public static void main(String[] args) {
        Screen frame = new Screen();
        frame.setVisible(true);

        Thread thread = new Thread(frame);
        thread.start();
    }
}