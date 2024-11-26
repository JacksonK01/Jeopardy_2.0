package menu;

import screen.Screen;
import util.JBackgroundPanel;
import util.JButtonListener;

import javax.swing.*;
import java.awt.*;

public class RewardMenuManager extends AbstractMenu {
    public RewardMenuManager(Screen screen) {
        super(screen);
    }

    @Override
    public void onReset() {

    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("reward_menu.jpg");
    }

    @Override
    public JPanel addForeground() {
        JPanel jPanel = new JPanel();

        JButtonListener button = new JButtonListener("Board", (e) -> {
           screen.setContentPane(screen.getJeopardyBoardMenu());
        });

        jPanel.add(button);

        return jPanel;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void tick() {

    }
}
