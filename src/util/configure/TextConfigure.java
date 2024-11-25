package util.configure;

import screen.Screen;

import javax.swing.*;
import java.awt.*;

public class TextConfigure {
    //Location
    private int x = 0;
    private int y = 0;

    //Width and height of gridbox
    private int width = 0;
    private int height = 0;

    //Spacing
    private int north = 0;
    private int west = 0;
    private int south = 0;
    private int east = 0;

    //Text on label
    private String text = "";

    //Color of text
    private Color textColor = new Color(255, 255, 255);

    //Font
    private String font = "Century Gothic";

    //Style - EX Bold
    private int style = Font.PLAIN;

    //Size of text
    private int size = 1;

    private boolean visible = true;

    //Color of Button
    private Color backgroundColor;

    public TextConfigure setX(int x) {
        this.x = x;
        return this;
    }

    public TextConfigure setY(int y) {
        this.y = y;
        return this;
    }

    public TextConfigure setWidth(int width) {
        this.width = width;
        return this;
    }

    public TextConfigure setHeight(int height) {
        this.height = height;
        return this;
    }

    public TextConfigure setNorth(int north) {
        this.north = north;
        return this;
    }

    public TextConfigure setWest(int west) {
        this.west = west;
        return this;
    }

    public TextConfigure setSouth(int south) {
        this.south = south;
        return this;
    }

    public TextConfigure setEast(int east) {
        this.east = east;
        return this;
    }

    public TextConfigure setText(String text) {
        this.text = text;
        return this;
    }

    public TextConfigure setTextColor(Color color) {
        this.textColor = color;
        return this;
    }

    public TextConfigure setTextColor(int red, int green, int blue) {
        int r = Math.min(255, Math.max(0, red));
        int g = Math.min(255, Math.max(0, green));
        int b = Math.min(255, Math.max(0, blue));
        this.textColor = new Color(r, g, b);
        return this;
    }

    public TextConfigure setFont(String font) {
        this.font = font;
        return this;
    }

    public TextConfigure setStyle(int style) {
        this.style = style;
        return this;
    }

    public TextConfigure setSize(int size) {
        this.size = size;
        return this;
    }

    public TextConfigure setInvisible() {
        this.visible = false;
        return this;
    }

    public TextConfigure setBackgroundColor(int red, int green, int blue) {
        int r = Math.min(255, Math.max(0, red));
        int g = Math.min(255, Math.max(0, green));
        int b = Math.min(255, Math.max(0, blue));

        this.backgroundColor = new Color(r, g, b);

        return this;
    }

    public TextConfigure setBackgroundColor(Color color) {
        this.backgroundColor = color;
        return this;
    }

    public void confirm(JLabel label, Screen screen, JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = y;

        gbc.ipadx = width;
        gbc.ipady = height;

        gbc.insets = new Insets(north, west, south, east);

        gbc.anchor = GridBagConstraints.CENTER;

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        label.setText(text);
        label.setForeground(textColor);
        label.setFont(new Font(font, style, size));
        screen.getLayout().setConstraints(label, gbc);
        panel.add(label);
        label.setVisible(visible);

        if(backgroundColor != null) {
            label.setOpaque(true);
            label.setBackground(backgroundColor);
        }
    }

    public static TextConfigure configure() {
        return new TextConfigure();
    }
}
