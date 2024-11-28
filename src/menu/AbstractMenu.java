package menu;

import intr.IBackgroundForeground;
import intr.IDrawable;
import intr.ITickable;
import screen.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenu implements IBackgroundForeground, ITickable, IDrawable {
    protected JLayeredPane pane;
    protected JPanel background;
    protected JPanel foreground;
    protected GridBagLayout layout;
    protected Screen screen;

    protected int width = 0;
    protected int height = 0;

    public AbstractMenu(Screen screen) {
        this.screen = screen;

        priorityInit();

        this.pane = new JLayeredPane();
        this.layout = screen.getLayout();
        this.width = screen.getWidth();
        this.height = screen.getHeight();
        this.background = addBackground();
        this.foreground = addForeground();

        enableLayout();

        screen.getAllMenus().add(this);

        pane.setBounds(0, 0, width, height);
        background.setBounds(0, 0, width, height);
        foreground.setBounds(0, 0, width, height);

        foreground.setOpaque(false);

        this.pane.add(background, JLayeredPane.DEFAULT_LAYER);
        this.pane.add(foreground, JLayeredPane.PALETTE_LAYER);
    }

    protected void enableLayout() {
        this.foreground.setLayout(screen.getLayout());
    }

    //Meant to be overrided.
    protected void priorityInit() {}

    public JLayeredPane getPane() {
        return this.pane;
    }

    public void update() {
        this.width = screen.getWidth();
        this.height = screen.getHeight();

        this.foreground.setBounds(0, 0, width, height);
        this.background.setBounds(0, 0, width, height);
        this.pane.setBounds(0, 0, width, height);
        tick();
    }

    //Gets called when the Screen has this menu active
    //Meant to be overrided
    public void onSetActive() {}

    //Gets called when the Screen isn't active pane anymore
    //Meant to be overrided
    public void OnRemove() {}

    //Called when everything should be reset to init state
    public abstract void onReset();
}
