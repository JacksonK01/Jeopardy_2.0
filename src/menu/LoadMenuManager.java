package menu;

import screen.Screen;
import util.JBackgroundPanel;
import util.JButtonListener;
import util.configure.ButtonConfigure;
import util.configure.TextConfigure;
import util.configure.TextFieldConfigure;

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
        return new JBackgroundPanel("load.jpg");
    }

    @Override
    public JPanel addForeground() {
        JPanel panel = new JPanel();

        JLabel enter = new JLabel();
        TextConfigure.configure().setText("Enter Jeopardy Board File:").setSize(30).setStyle(Font.BOLD).setWidth(100).setSouth(50).confirm(enter, screen, panel);

        JLabel currentLoadedFile = new JLabel();

        TextConfigure.configure().setText(screen.getFileToOpen() + " Currently Loaded").setY(3).setStyle(Font.BOLD).setSize(15).setWidth(100).setNorth(100).confirm(currentLoadedFile, screen, panel);

        JTextField enterSubject = new JTextField();

        TextFieldConfigure.configure().setText(screen.getFileToOpen()).setSize(15).setY(1)
                .setHeight(25).setWidth(500).setStyle(Font.BOLD)
                .setNorth(10).setEast(10).setSouth(10).setWest(10).confirm(enterSubject, screen, panel);

        JButtonListener confirm = new JButtonListener((e) -> {
            screen.setFileToOpen(enterSubject.getText());
            currentLoadedFile.setText(screen.getFileToOpen() + " Currently Loaded");
        });

        ButtonConfigure.configure().setX(1).setY(1).setWidth(60).setHeight(25).setText("Confirm").setWest(10).finish(confirm, screen, panel);

        JButtonListener exit = new JButtonListener((e) -> {
            screen.setContentPane(screen.getStartMenu());
        });

        ButtonConfigure.configure().setX(0).setY(2).setWidth(60).setHeight(25).setText("Exit").setWest(10).finish(exit, screen, panel);

        return panel;
    }

    @Override
    public void tick() {

    }
}
