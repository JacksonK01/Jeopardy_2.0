package menu;

import screen.Screen;
import util.configure.ButtonConfigure;
import util.JBackgroundPanel;
import util.JButtonListener;
import util.configure.TextConfigure;

import javax.swing.*;
import java.awt.*;

public class StartMenuManager extends AbstractMenu {

    public StartMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public void onReset() {

    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("startmenu_background.jpg");
    }

    @Override
    public JPanel addForeground() {
        JPanel newForeground = new JPanel();

        JButtonListener startButton = new JButtonListener("Start", (e) -> screen.setContentPane(screen.getPlayerMenu()));
        JButtonListener loadButton = new JButtonListener("Load", (e) -> screen.setContentPane(screen.loadScreen));
        JButtonListener customButton = new JButtonListener("Custom", (e) -> screen.setContentPane(screen.createQuestionScreen));

        ButtonConfigure.configure().setY(1).setWidth(150).setHeight(40).setSouth(20).finish(startButton, screen, newForeground);
        ButtonConfigure.configure().setY(3).setWidth(60).setHeight(30).setSouth(10).finish(loadButton, screen, newForeground);
        ButtonConfigure.configure().setY(2).setWidth(60).setHeight(30).setSouth(10).finish(customButton, screen, newForeground);

        JLabel titleText = new JLabel();
        TextConfigure.configure().setText("Jeopardy")
                .setTextColor(255, 255, 0).setStyle(Font.BOLD).setSize(70)
                .setWidth(16).setHeight(16).setSouth(5).confirm(titleText, screen, newForeground);

        return newForeground;
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {

    }
}
