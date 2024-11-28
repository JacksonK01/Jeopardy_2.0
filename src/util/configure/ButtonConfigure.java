package util.configure;

import screen.Screen;

import javax.swing.*;
import java.awt.*;

//Quick way to build buttons
public class ButtonConfigure {
    //Location
    private int x = 0;
    private int y = 0;

    //Size of button
    private int width = 0;
    private int height = 0;

    //Spacing
    private int north = 0;
    private int west = 0;
    private int south = 0;
    private int east = 0;

    //Color of Button
    private Color backgroundColor;
    private Color foregroundColor;

    //Text on button
    private String text;

    boolean visible = true;

    public ButtonConfigure setX(int x) {
        this.x = x;
        return this;
    }

    public ButtonConfigure setY(int y) {
        this.y = y;
        return this;
    }

    public ButtonConfigure setWidth(int width) {
        this.width = width;
        return this;
    }

    public ButtonConfigure setHeight(int height) {
        this.height = height;
        return this;
    }

    public ButtonConfigure setNorth(int north) {
        this.north = north;
        return this;
    }

    public ButtonConfigure setWest(int west) {
        this.west = west;
        return this;
    }

    public ButtonConfigure setSouth(int south) {
        this.south = south;
        return this;
    }

    public ButtonConfigure setEast(int east) {
        this.east = east;
        return this;
    }

    public ButtonConfigure setInvisible() {
        this.visible = true;
        return this;
    }

    public ButtonConfigure setBackgroundColor(int red, int green, int blue) {
        int r = Math.min(255, Math.max(0, red));
        int g = Math.min(255, Math.max(0, green));
        int b = Math.min(255, Math.max(0, blue));

        this.backgroundColor = new Color(r, g, b);

        return this;
    }

    public ButtonConfigure setForegroundColor(int red, int green, int blue) {
        int r = Math.min(255, Math.max(0, red));
        int g = Math.min(255, Math.max(0, green));
        int b = Math.min(255, Math.max(0, blue));

        this.foregroundColor = new Color(r, g, b);

        return this;
    }

    public ButtonConfigure setBackgroundColor(Color color) {
        this.backgroundColor = color;
        return this;
    }

    public ButtonConfigure setForegroundColor(Color color) {
        this.foregroundColor = color;
        return this;
    }

    public ButtonConfigure setText(String text) {
        this.text = text;
        return this;
    }

    public void finish(JButton button, Screen screen, JPanel panel) {
        finish(button, panel, screen.getLayout());
    }

    public void finish(JButton button, Screen screen, JPanel panel, int gridBagAlignment) {
        finish(button, panel, screen.getLayout(), gridBagAlignment);
    }

    public void finish(JButton button, JPanel panel, GridBagLayout layout) {
        finish(button, panel, layout, GridBagConstraints.CENTER);
    }

    public void finish(JButton button, JPanel panel, GridBagLayout layout, int gridBagAlignment) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = y;

        gbc.ipadx = width;
        gbc.ipady = height;

        gbc.insets = new Insets(north, west, south, east);

        gbc.anchor = gridBagAlignment;

        layout.setConstraints(button, gbc);
        panel.add(button);
        button.setVisible(visible);

        if(backgroundColor != null) {
            button.setBackground(backgroundColor);
        }
        if(foregroundColor != null) {
            button.setForeground(foregroundColor);
        }
        if(text != null) {
            button.setText(text);
        }
    }

    public static ButtonConfigure configure() {
        return new ButtonConfigure();
    }
}
