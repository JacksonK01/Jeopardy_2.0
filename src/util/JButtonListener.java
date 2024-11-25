package util;

import javax.swing.*;
import java.awt.event.ActionListener;

//JButton but stores an action
public class JButtonListener extends JButton {
    private final ActionListener buttonAction;

    public JButtonListener() {
        this(null, null, null);
    }

    public JButtonListener(ActionListener buttonAction) {
        this(null, null, buttonAction);
    }

    public JButtonListener(Icon icon, ActionListener buttonAction) {
        this(null, icon, buttonAction);
    }

    public JButtonListener(String text, ActionListener buttonAction) {
        this(text, null, buttonAction);
    }

    public JButtonListener(String text, Icon icon, ActionListener buttonAction) {
        super(text, icon);
        addActionListener(buttonAction);
        this.buttonAction = buttonAction;
    }

    public ActionListener buttonAction() {
        return buttonAction;
    }
}
