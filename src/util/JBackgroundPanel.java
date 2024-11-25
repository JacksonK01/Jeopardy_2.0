package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class JBackgroundPanel extends JPanel {
    private BufferedImage background;

    public JBackgroundPanel(URL input) {
        try {
            background = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JBackgroundPanel(String url) {
        this(Objects.requireNonNull(JBackgroundPanel.class.getResource("/" + url)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
