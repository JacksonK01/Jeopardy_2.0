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
    //Holds a button and it's action
    protected List<JButton> buttons;
    protected Screen screen;

    protected int width = 0;
    protected int height = 0;

    public AbstractMenu(Screen screen) {
        this.screen = screen;

        priorityInit();

        this.pane = new JLayeredPane();
        this.buttons = new ArrayList<>();
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

        background.setBackground(Color.BLUE);
        foreground.setOpaque(false);

        for(int i = 0; i < foreground.getComponentCount(); i++) {
            Component element = foreground.getComponent(i);
            if(element instanceof JButton jButton) {
                buttons.add(jButton);
            }
        }

        for(int i = 0; i < background.getComponentCount(); i++) {
            Component element = background.getComponent(i);
            if(element instanceof JButton jButton) {
                buttons.add(jButton);
            }
        }

        this.pane.add(background, JLayeredPane.DEFAULT_LAYER);
        this.pane.add(foreground, JLayeredPane.PALETTE_LAYER);
    }

    protected void enableLayout() {
        this.foreground.setLayout(this.layout);
    }

    //Meant to be overrided.
    protected void priorityInit() {}

    public JLayeredPane getPane() {
        return this.pane;
    }

    public List<JButton> getButtons() {
        return this.buttons;
    }

    public void update() {
        this.width = screen.getWidth();
        this.height = screen.getHeight();

        this.foreground.setBounds(0, 0, width, height);
        this.background.setBounds(0, 0, width, height);
        this.pane.setBounds(0, 0, width, height);
        tick();
    }
}
