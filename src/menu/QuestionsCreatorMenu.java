package menu;

import impl.JeopardyQuestion;
import impl.JeopardyQuestionFactory;
import impl.JeopardySubject;
import impl.JeopardySubjectFactory;
import intr.IQuestion;
import intr.ISubject;
import screen.Screen;
import util.JBackgroundPanel;
import util.JButtonListener;
import util.JsonHandler;
import util.Pair;
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
    private final JPanel topButtons;
    private final JPanel subjectAddQuestion;
    private final JPanel subjectEdit;
    private final JPanel openFile;
    private final JPanel confirmBoard;

    private List<IQuestion> questions;

    //All panels except background and resetAndExit
    private final List<JPanel> allPanels;

    private final List<JLabel> allSubjectLabels;

    private final List<Pair<ISubject, JLabel>> allSubjectsAndLabels;

    private boolean hasDisplayedError = false;
    private boolean hasEditDisplayedError = false;

    //Used for the edit screen
    private int editIndex = 0;

    public QuestionsCreatorMenu(Screen screen) {
        super(screen);
        subjects = new ArrayList<>();
        questions = new ArrayList<>();
        allPanels = new ArrayList<>();
        allSubjectLabels = new ArrayList<>();
        allSubjectsAndLabels = new ArrayList<>();

        createSubjectPanel = new JPanel();
        initPanel(createSubjectPanel, false, JLayeredPane.MODAL_LAYER);

        topButtons = new JPanel();
        initPanel(topButtons, true, JLayeredPane.DRAG_LAYER);

        subjectAddQuestion = new JPanel();
        initPanel(subjectAddQuestion, false, JLayeredPane.POPUP_LAYER);

        subjectEdit = new JPanel();
        initPanel(subjectEdit, false, JLayeredPane.POPUP_LAYER);

        openFile = new JPanel();
        initPanel(openFile, false, JLayeredPane.POPUP_LAYER);

        confirmBoard = new JPanel();
        initPanel(confirmBoard, false, JLayeredPane.POPUP_LAYER);

        setupCreateSubject();
        setupTopButtons();
        setupConfirmBoard();
        setupOpen();

        allPanels.add(createSubjectPanel);
        allPanels.add(foreground);
        allPanels.add(subjectAddQuestion);
        allPanels.add(subjectEdit);
        allPanels.add(openFile);
        allPanels.add(confirmBoard);
    }

    @Override
    public void onReset() {
        subjects.clear();
        questions.clear();
        allSubjectsAndLabels.clear();
        allSubjectLabels.clear();
        foreground.removeAll();
        screen.setFileToOpen("default.json");
        setupForeground();
    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("load_menu.jpg");
    }

    @Override
    public JPanel addForeground() {
        foreground = new JPanel();
        setupForeground();

        return foreground;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void tick() {
        allPanels.forEach((panel) -> {
            panel.setBounds(0, 0, width, height);
        });
    }

    private void setupOpen() {
        JLabel enter = new JLabel();
        TextConfigure.configure().setText("Enter Jeopardy Board To Open:").setSize(30).setStyle(Font.BOLD).setWidth(100).setSouth(25).confirm(enter, screen, openFile);

        JLabel currentLoadedFile = new JLabel();

        TextConfigure.configure().setText("").setY(3).setStyle(Font.BOLD).setSize(15).setWidth(100).setNorth(100).confirm(currentLoadedFile, screen, openFile);

        JTextField enterName = new JTextField();

        TextFieldConfigure.configure().setText(screen.getFileToOpen()).setSize(15).setY(1)
                .setHeight(25).setWidth(500).setStyle(Font.BOLD)
                .setNorth(10).setEast(10).setSouth(10).setWest(10).confirm(enterName, screen, openFile);

        JButtonListener open = new JButtonListener((e) -> {
            screen.setFileToOpen(enterName.getText());
            currentLoadedFile.setText(screen.getFileToOpen() + " Loaded");

            String board = JsonHandler.readJsonFromFile(screen.getFileToOpen());
            assert board != null;
            List<ISubject> subjectsIO = JsonHandler.deSerializeSubjects(board, JeopardySubjectFactory.getFactory(), JeopardyQuestionFactory.getFactory());

            subjectsIO.forEach((s) -> {
                subjects.add(s);
                onSubjectCreated(s);
            });
        });

        JButtonListener back = new JButtonListener((e) -> {
            setPanelVisible(foreground);
            currentLoadedFile.setText("");
        });


        ButtonConfigure.configure().setX(0).setY(1).setWidth(90).setHeight(25).setNorth(125).setText("Open").setWest(10).finish(open, screen, openFile);
        ButtonConfigure.configure().setX(0).setY(2).setWidth(60).setHeight(25).setNorth(25).setText("Back").setWest(10).finish(back, screen, openFile);

    }

    private void setupCreateSubject() {
        JTextField enterTitle = new JTextField();

        JLabel enterLabel = new JLabel();

        JButtonListener confirm = new JButtonListener("Confirm", (e) -> {
            ISubject createdSubject = new JeopardySubject(enterTitle.getText());
            subjects.add(createdSubject);
            onSubjectCreated(createdSubject);
            setPanelVisible(foreground);
        });

        JButtonListener back = new JButtonListener("Back", (e) -> {
            setPanelVisible(foreground);
        });

        TextConfigure.configure().setStyle(Font.BOLD).setY(0).setSouth(50).setSize(50).setText("Subject Name: ").confirm(enterLabel, screen, createSubjectPanel);

        TextFieldConfigure.configure().setColumn(25).setWidth(100).setHeight(25).setText("Enter Subject Name")
                .setX(0).setY(1).setSize(15).confirm(enterTitle, screen, createSubjectPanel);

        ButtonConfigure.configure().setY(1).setWidth(40).setHeight(20).setNorth(175).finish(confirm, screen, createSubjectPanel);
        ButtonConfigure.configure().setY(2).setWidth(30).setHeight(10).setNorth(25).finish(back, screen, createSubjectPanel);
    }

    private void setupForeground() {
        JLabel createSubject = new JLabel();

        JButtonListener addSubject = new JButtonListener("Add Subject", (e) -> {
            setPanelVisible(createSubjectPanel);
        });

        ButtonConfigure.configure().setWidth(100).setHeight(15).setY(0).setX(1)
                .finish(addSubject, screen, foreground);

        TextConfigure.configure().setText("Created Subjects:").setSize(30).setSize(30).setStyle(Font.BOLD).setEast(50).confirm(createSubject, screen, foreground);
    }

    private void setupTopButtons() {
        JButtonListener exit = new JButtonListener((e) -> {
            screen.setContentPane(screen.getStartMenu());
        });

        JButtonListener reset = new JButtonListener((e) -> {
            onReset();
        });

        JButtonListener confirm = new JButtonListener((e) -> {
            setPanelVisible(confirmBoard);
        });

        JButtonListener open = new JButtonListener((e) -> {
            setPanelVisible(openFile);
        });

        ButtonConfigure.configure().setX(0).setText("Exit").setSouth(600).finish(exit, screen, topButtons);
        ButtonConfigure.configure().setX(1).setText("Reset").setSouth(600).finish(reset, screen, topButtons);
        ButtonConfigure.configure().setX(2).setText("Open").setWest(25).setSouth(600).finish(open, screen, topButtons);
        ButtonConfigure.configure().setX(3).setText("Confirm").setSouth(600).setEast(650).finish(confirm, screen, topButtons);

    }

    private void setupConfirmBoard() {
        JLabel enter = new JLabel();
        TextConfigure.configure().setText("Enter Jeopardy Board Name:").setSize(30).setStyle(Font.BOLD).setWidth(100).setSouth(25).confirm(enter, screen, confirmBoard);

        JLabel currentLoadedFile = new JLabel();

        TextConfigure.configure().setText("").setY(3).setStyle(Font.BOLD).setSize(15).setWidth(100).setNorth(100).confirm(currentLoadedFile, screen, confirmBoard);

        JTextField enterName = new JTextField();

        TextFieldConfigure.configure().setText(screen.getFileToOpen()).setSize(15).setY(1)
                .setHeight(25).setWidth(500).setStyle(Font.BOLD)
                .setNorth(10).setEast(10).setSouth(10).setWest(10).confirm(enterName, screen, confirmBoard);

        JButtonListener confirm = new JButtonListener((e) -> {
            screen.setFileToOpen(enterName.getText());
            currentLoadedFile.setText(screen.getFileToOpen() + " Saved");
            JsonHandler.exportJsonToFile(JsonHandler.serializeSubjects(this.subjects), screen.getFileToOpen());
        });

        JButtonListener back = new JButtonListener((e) -> {
            setPanelVisible(foreground);
            currentLoadedFile.setText("");
        });


        ButtonConfigure.configure().setX(0).setY(1).setWidth(90).setHeight(25).setNorth(125).setText("Save").setWest(10).finish(confirm, screen, confirmBoard);
        ButtonConfigure.configure().setX(0).setY(2).setWidth(60).setHeight(25).setNorth(25).setText("Back").setWest(10).finish(back, screen, confirmBoard);

    }

    private void setPanelVisible(JPanel panel) {
        refreshSubjectQuestionSizes();
        allPanels.forEach((p) -> {
            p.setVisible(p == panel);
        });
    }

    private void initPanel(JPanel panel, boolean visible, Integer layer) {
        panel.setOpaque(false);
        panel.setVisible(visible);
        panel.setLayout(screen.getLayout());
        panel.setBounds(0, 0, width, height);
        pane.add(panel, layer);
    }

    private void onSubjectCreated(ISubject subjectCreated) {
        JLabel newSubject = new JLabel();

        JButtonListener addQuestion = new JButtonListener("Add Question", (e) -> {
            onCreateQuestion(subjectCreated);
            setPanelVisible(subjectAddQuestion);
        });

        JButtonListener edit = new JButtonListener("Edit", (e) -> {
            setPanelVisible(subjectEdit);
            onSubjectEdit(subjectCreated);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonPanel.add(addQuestion);
        buttonPanel.add(edit);

        int y = subjects.size();

        gbc.gridx = 1;
        gbc.gridy = y;

        gbc.fill = GridBagConstraints.NONE;

        foreground.add(buttonPanel, gbc);

        TextConfigure.configure().setX(0).setY(y).setText(subjectCreated.getTitle() + " Qs: " + subjectCreated.getQuestions().size()).setSize(20).confirm(newSubject, screen, foreground, GridBagConstraints.WEST);
        allSubjectLabels.add(newSubject);

        JButtonListener delete = new JButtonListener("Delete", (e) -> {
            subjects.remove(subjectCreated);
            foreground.remove(newSubject);
            foreground.remove(buttonPanel);

            foreground.remove((Component) e.getSource());
            allSubjectLabels.remove(newSubject);
            subjects.remove(subjectCreated);
        });

        buttonPanel.add(delete);
    }

    private void refreshSubjectQuestionSizes() {
        for(int i = 0; i < subjects.size(); i++) {
            ISubject s = subjects.get(i);
            allSubjectLabels.get(i).setText(s.getTitle() + " Qs: " + s.getQuestions().size());
        }
    }

    private void onCreateQuestion(ISubject subjectToAddTo) {
        topButtons.setVisible(false);

        JLabel subject = new JLabel();
        JLabel question = new JLabel();
        JLabel answer = new JLabel();
        JLabel value = new JLabel();

        JTextField questionField = new JTextField();
        JTextField answerField = new JTextField();
        JTextField valueField = new JTextField();

        JButtonListener confirm = new JButtonListener("Confirm", (e) -> {
            try {
                int qValue = Integer.parseInt(valueField.getText());
                setPanelVisible(foreground);
                subjectToAddTo.addQuestion(new JeopardyQuestion(questionField.getText(), answerField.getText(), qValue));
                refreshSubjectQuestionSizes();
                topButtons.setVisible(true);
                subjectAddQuestion.removeAll();
                hasDisplayedError = false;
            } catch(RuntimeException exception) {
                if(hasDisplayedError) {
                    return;
                }
                hasDisplayedError = true;
                JLabel error = new JLabel();
                TextConfigure.configure().setText("        Value has to be an Integer").setTextColor(Color.red).setY(3).setX(2).setSize(10).setStyle(Font.BOLD).confirm(error, screen, subjectAddQuestion);
            }
        });

        JButtonListener back = new JButtonListener("Back", (e) -> {
            setPanelVisible(foreground);
            topButtons.setVisible(true);
            subjectAddQuestion.removeAll();
        });

        TextConfigure.configure().setText("For \"" + subjectToAddTo.getTitle() + "\":").setY(0).setSize(25).setStyle(Font.BOLD).confirm(subject, screen, subjectAddQuestion);
        TextConfigure.configure().setText("Question: ").setY(1).setSize(25).setStyle(Font.BOLD).confirm(question, screen, subjectAddQuestion);
        TextConfigure.configure().setText("Answer: ").setY(2).setSize(25).setStyle(Font.BOLD).setWidth(20).setHeight(20).confirm(answer, screen, subjectAddQuestion);
        TextConfigure.configure().setText("Value: $").setY(3).setSize(25).setStyle(Font.BOLD).setWidth(20).setHeight(20).confirm(value, screen, subjectAddQuestion);

        TextFieldConfigure.configure().setX(1).setSize(10).setHeight(30).setWidth(200).setY(1).confirm(questionField, screen, subjectAddQuestion);
        TextFieldConfigure.configure().setX(1).setSize(10).setHeight(30).setWidth(200).setY(2).confirm(answerField, screen, subjectAddQuestion);
        TextFieldConfigure.configure().setText("0").setX(1).setSize(10).setHeight(30).setWidth(200).setY(3).confirm(valueField, screen, subjectAddQuestion);

        ButtonConfigure.configure().setY(4).finish(back, screen, subjectAddQuestion);
        ButtonConfigure.configure().setX(1).setY(4).finish(confirm, screen, subjectAddQuestion);
    }

    //Use a page-like system
    private void onSubjectEdit(ISubject subjectToEdit) {
        topButtons.setVisible(false);

        int total = subjectToEdit.getQuestions().size();

        if(total == 0) {
            exitEdit();
            return;
        }

        IQuestion currentQuestion = subjectToEdit.getQuestions().get(editIndex);

        JLabel subject = new JLabel();
        JLabel question = new JLabel();
        JLabel answer = new JLabel();
        JLabel value = new JLabel();
        JLabel pageTracker = new JLabel();

        JLabel old = new JLabel();
        JLabel toBeNew = new JLabel();

        JTextField questionField = new JTextField();
        JTextField answerField = new JTextField();
        JTextField valueField = new JTextField();

        JButtonListener exit = new JButtonListener("Exit Edit", (e) -> {
            saveEdit(questionField, answerField, valueField, subjectToEdit, 0);
            if(!hasEditDisplayedError) {
                exitEdit();
            }
        });

        JButtonListener back = new JButtonListener("Back", (e) -> {
            if(0 == editIndex) {
                return;
            }
            if(0 < editIndex) {
                saveEdit(questionField, answerField, valueField, subjectToEdit, -1);
            }
        });

        JButtonListener next = new JButtonListener("Next", (e) -> {
            if(editIndex >= subjectToEdit.getQuestions().size()) {
                return;
            }
            if((editIndex + 1) < subjectToEdit.getQuestions().size()) {
                saveEdit(questionField, answerField, valueField, subjectToEdit, 1);
            }
        });

        JButtonListener delete = new JButtonListener("Delete", (e) -> {
            subjectToEdit.getQuestions().remove(currentQuestion);
            refreshSubjectQuestionSizes();
            exitEdit();
        });

        TextConfigure.configure().setText("For \"" + subjectToEdit.getTitle() + "\":").setY(0).setSize(25).setStyle(Font.BOLD).confirm(subject, screen, subjectEdit);

        TextConfigure.configure().setText("Page: " + (editIndex + 1) + "/" + total).setY(0).setX(1).setSize(25).setStyle(Font.BOLD).setWidth(20).setHeight(20).confirm(pageTracker, screen, subjectEdit);

        TextConfigure.configure().setText("Old: ").setY(1).setSize(25).setStyle(Font.BOLD).confirm(old, screen, subjectEdit);
        TextConfigure.configure().setText("New: ").setY(1).setX(1).setSize(25).setStyle(Font.BOLD).confirm(toBeNew, screen, subjectEdit);

        TextConfigure.configure().setText("\"" + currentQuestion.getQuestion() + "\"   ").setY(2).setSize(25).setStyle(Font.BOLD).confirm(question, screen, subjectEdit);
        TextConfigure.configure().setText("\"" + currentQuestion.getAnswer() + "\"   ").setY(3).setSize(25).setStyle(Font.BOLD).setWidth(20).setHeight(20).confirm(answer, screen, subjectEdit);
        TextConfigure.configure().setText("\"" + currentQuestion.getValue() + "\"   ").setY(4).setSize(25).setStyle(Font.BOLD).setWidth(20).setHeight(20).confirm(value, screen, subjectEdit);

        TextFieldConfigure.configure().setText(currentQuestion.getQuestion()).setX(1).setSize(10).setHeight(30).setWidth(200).setY(2).confirm(questionField, screen, subjectEdit);
        TextFieldConfigure.configure().setText(currentQuestion.getAnswer()).setX(1).setSize(10).setHeight(30).setWidth(200).setY(3).confirm(answerField, screen, subjectEdit);
        TextFieldConfigure.configure().setText("" + currentQuestion.getValue()).setX(1).setSize(10).setHeight(30).setWidth(200).setY(4).confirm(valueField, screen, subjectEdit);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonPanel.add(back);
        buttonPanel.add(next);

        gbc.gridx = 0;
        gbc.gridy = 5;

        gbc.fill = GridBagConstraints.NONE;

        subjectEdit.add(buttonPanel, gbc);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel2.setOpaque(false);
        buttonPanel2.setVisible(true);

        GridBagConstraints gbc2 = new GridBagConstraints();

        buttonPanel2.add(delete);
        buttonPanel2.add(exit);

        gbc2.gridx = 1;
        gbc2.gridy = 5;

        gbc2.fill = GridBagConstraints.NONE;

        subjectEdit.add(buttonPanel2, gbc2);
    }

    private void exitEdit() {
        subjectEdit.removeAll();
        setPanelVisible(foreground);
        topButtons.setVisible(true);
        editIndex = 0;
    }

    private void saveEdit(JTextField questionField, JTextField answerField, JTextField valueField, ISubject subjectToEdit, int addToIndex) {
        List<IQuestion> questions = subjectToEdit.getQuestions();

        try {
            int qValue = Integer.parseInt(valueField.getText());
            questions.set(editIndex, new JeopardyQuestion(questionField.getText(), answerField.getText(), qValue));
            subjectEdit.removeAll();
            hasEditDisplayedError = false;
            editIndex += addToIndex;

            onSubjectEdit(subjectToEdit);
        } catch(RuntimeException exception) {
            if(hasEditDisplayedError) {
                return;
            }
            hasEditDisplayedError = true;
            JLabel error = new JLabel();
            TextConfigure.configure().setText("        Value has to be an Integer").setTextColor(Color.red).setY(4).setX(2).setSize(10).setStyle(Font.BOLD).confirm(error, screen, subjectEdit);
        }
    }
}
