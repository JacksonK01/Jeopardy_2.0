package menu;

import impl.JeopardySubject;
import intr.IQuestion;
import intr.ISubject;
import screen.Screen;
import util.JBackgroundPanel;
import util.JButtonListener;
import util.configure.ButtonConfigure;
import util.configure.TextConfigure;
import util.configure.TextFieldConfigure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsCreatorMenu extends AbstractMenu {
    private final List<ISubject> subjects;

    private final JPanel createSubjectPanel;
    private final JPanel resetAndExit;

    private List<IQuestion> questions;

    //All panels except background and resetAndExit
    private final List<JPanel> allPanels;

    public QuestionsCreatorMenu(Screen screen) {
        super(screen);
        subjects = new ArrayList<>();
        questions = new ArrayList<>();
        allPanels = new ArrayList<>();

        createSubjectPanel = new JPanel();
        resetAndExit = new JPanel();
        setupCreateSubject();
        setupExitAndReset();

        allPanels.add(createSubjectPanel);
        allPanels.add(foreground);
    }

    @Override
    public void onReset() {

    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("load_menu.jpg");
    }

    @Override
    public JPanel addForeground() {
        JPanel panel = new JPanel();

        JLabel createSubject = new JLabel();

        JButtonListener addSubject = new JButtonListener("Add Subject", (e) -> {
            foreground.setVisible(false);
            createSubjectPanel.setVisible(true);
        });

        ButtonConfigure.configure().setWidth(100).setHeight(15).setY(0).setX(0)
                .finish(addSubject, screen, panel);

        return panel;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void tick() {
        createSubjectPanel.setBounds(0, 0, width, height);
    }

    private void setupCreateSubject() {
        createSubjectPanel.setOpaque(false);
        createSubjectPanel.setVisible(false);
        createSubjectPanel.setLayout(screen.getLayout());
        createSubjectPanel.setBounds(0, 0, width, height);
        pane.add(createSubjectPanel, JLayeredPane.MODAL_LAYER);

        JTextField enterTitle = new JTextField();

        JLabel enterLabel = new JLabel();

        JButtonListener confirm = new JButtonListener("Confirm", (e) -> {
            subjects.add(new JeopardySubject(enterTitle.getText()));
            setPanelVisible(foreground);
        });

        TextConfigure.configure().setStyle(Font.BOLD).setY(0).setSouth(50).setSize(50).setText("Subject Name: ").confirm(enterLabel, screen, createSubjectPanel);


        TextFieldConfigure.configure().setColumn(25).setWidth(100).setHeight(25).setText("Enter Subject Name")
                .setX(0).setY(1).setSize(15).confirm(enterTitle, screen, createSubjectPanel);

        ButtonConfigure.configure().setY(2).setWidth(30).setHeight(10).setNorth(50).finish(confirm, screen, createSubjectPanel);

    }

    private void setupExitAndReset() {
        resetAndExit.setOpaque(false);
        resetAndExit.setVisible(true);
        resetAndExit.setLayout(screen.getLayout());
        resetAndExit.setBounds(0, 0, width, height);
        pane.add(resetAndExit, JLayeredPane.POPUP_LAYER);

        JButtonListener exit = new JButtonListener((e) -> {
            screen.setContentPane(screen.getStartMenu());
        });

        JButtonListener reset = new JButtonListener((e) -> {
            subjects.clear();
            questions.clear();
        });

        ButtonConfigure.configure().setX(0).setText("Exit").setSouth(600).finish(exit, screen, resetAndExit);
        ButtonConfigure.configure().setX(1).setText("Reset").setEast(750).setWest(5).setSouth(600).finish(reset, screen, resetAndExit);
    }

    private void setPanelVisible(JPanel panel) {
        allPanels.forEach((p) -> {
            p.setVisible(p == panel);
        });
    }
}
