package util.configure;

import screen.Screen;

import javax.swing.*;
import java.awt.*;

public class TextFieldConfigure {
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
    private Color textColor = new Color(0, 0, 0);

    //Font
    private String font = "Century Gothic";

    //Style - EX Bold
    private int style = Font.PLAIN;

    //Size of text
    private int size = 1;

    //Column
    private int column = 1;

    private boolean visible = true;

    //Color
    private Color background;

    public TextFieldConfigure setX(int x) {
        this.x = x;
        return this;
    }

    public TextFieldConfigure setY(int y) {
        this.y = y;
        return this;
    }

    public TextFieldConfigure setWidth(int width) {
        this.width = width;
        return this;
    }

    public TextFieldConfigure setHeight(int height) {
        this.height = height;
        return this;
    }

    public TextFieldConfigure setNorth(int north) {
        this.north = north;
        return this;
    }

    public TextFieldConfigure setWest(int west) {
        this.west = west;
        return this;
    }

    public TextFieldConfigure setSouth(int south) {
        this.south = south;
        return this;
    }

    public TextFieldConfigure setEast(int east) {
        this.east = east;
        return this;
    }

    public TextFieldConfigure setText(String text) {
        this.text = text;
        return this;
    }

    public TextFieldConfigure setTextColor(Color color) {
        this.textColor = color;
        return this;
    }

    public TextFieldConfigure setTextColor(int red, int green, int blue) {
        int r = Math.min(255, Math.max(0, red));
        int g = Math.min(255, Math.max(0, green));
        int b = Math.min(255, Math.max(0, blue));
        this.textColor = new Color(r, g, b);
        return this;
    }

    public TextFieldConfigure setBackgroundColor(Color color) {
        this.background = color;
        return this;
    }

    public TextFieldConfigure setBackgroundColor(int red, int green, int blue) {
        int r = Math.min(255, Math.max(0, red));
        int g = Math.min(255, Math.max(0, green));
        int b = Math.min(255, Math.max(0, blue));
        this.background = new Color(r, g, b);
        return this;
    }

    public TextFieldConfigure setFont(String font) {
        this.font = font;
        return this;
    }

    public TextFieldConfigure setStyle(int style) {
        this.style = style;
        return this;
    }

    public TextFieldConfigure setSize(int size) {
        this.size = size;
        return this;
    }

    public TextFieldConfigure setInvisible() {
        this.visible = false;
        return this;
    }

    public TextFieldConfigure setColumn(int column) {
        this.column = column;
        return this;
    }

    public void confirm(JTextField textField, Screen screen, JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = y;

        gbc.ipadx = width;
        gbc.ipady = height;

        gbc.insets = new Insets(north, west, south, east);

        gbc.anchor = GridBagConstraints.CENTER;

        textField.setHorizontalAlignment(SwingConstants.CENTER);

        textField.setText(text);
        textField.setForeground(textColor);
        textField.setFont(new Font(font, style, size));
        screen.getLayout().setConstraints(textField, gbc);
        panel.add(textField);
        textField.setVisible(visible);
        textField.setColumns(column);

        if(background != null) {
            textField.setBackground(background);
        }
    }

    public static TextFieldConfigure configure() {
        return new TextFieldConfigure();
    }
}
