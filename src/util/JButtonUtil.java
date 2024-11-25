package util;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JButtonUtil {
    public static JButton registerWithListener(ActionListener e) {
        JButton jButton = new JButton();
        jButton.addActionListener(e);
        return jButton;
    }
}
