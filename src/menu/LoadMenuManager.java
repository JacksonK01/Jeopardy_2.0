package menu;

import screen.Screen;

import javax.swing.*;
import java.awt.*;

public class LoadMenuManager extends AbstractMenu {
    public LoadMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public void onReset() {

    }

    @Override
    public JPanel addBackground() {
        JPanel background = new JPanel();
        background.setBackground(new Color(4, 16, 84));
        return background;
    }

    @Override
    public JPanel addForeground() {
        JPanel foreground = new JPanel();

        JTextField enterSubject = new JTextField();

        return foreground;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void tick() {

    }
}
