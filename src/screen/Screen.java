package screen;

import impl.JeopardyBoard;
import intr.IBoard;
import intr.ISubject;
import menu.*;
import intr.ITickable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Screen extends JFrame implements ActionListener, Runnable {
    public final static Color JEOPARDY_BLUE = new Color(4, 16, 84);
    public final static Color JEOPARDY_YELLOW = new Color(255,255,0);
    private final static int FPS = 60;

    private int fpsCount = 0;

    int playedBoards = 0;
    int pointsValue = 0;
    Scanner input = new Scanner(System.in);
    double timer = 600; //add a timer one day

    private final Thread thread;

    private final AbstractMenu startMenu;
    private final AbstractMenu jeopardyBoardMenu;
    private final AbstractMenu questionMenu;
    private final AbstractMenu playerMenu;
    private final AbstractMenu rewardMenu;

    private final IBoard<ISubject> jeopardyBoard;

    private final List<AbstractMenu> allMenus = new ArrayList<>();

    public JLayeredPane rewardPointsMenu = new JLayeredPane();
    public JLayeredPane winningScreen = new JLayeredPane();
    public JLayeredPane playerScreen = new JLayeredPane();
    public JLayeredPane loadScreen = new JLayeredPane();
    public JLayeredPane createQuestionScreen = new JLayeredPane();

    public GridBagConstraints gbc = new GridBagConstraints();
    private final GridBagLayout layout = new GridBagLayout();

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    public int screenWidth = screenSize.width;
    public int screenHeight = screenSize.height;

    JLabel question = new JLabel();
    JLabel answer = new JLabel();
    JLabel POneScore = new JLabel();
    JLabel PTwoScore = new JLabel();
    JLabel PThreeScore = new JLabel();
    JLabel PFourScore = new JLabel();
    JLabel PFiveScore = new JLabel();

    JLabel Winner = new JLabel();

    JButton playerOneButton = new JButton(Players.PlayerOne.name);
    JButton playerTwoButton = new JButton(Players.PlayerTwo.name);
    JButton playerThreeButton = new JButton(Players.PlayerThree.name);
    JButton playerFourButton = new JButton(Players.PlayerFour.name);
    JButton playerFiveButton = new JButton(Players.PlayerFive.name);
    JLabel promptReward = new JLabel();
    JButton editButton = new JButton("Edit");
    JButton returnButton = new JButton("Next =>");

    JTextField playerOneEnter;
    JTextField playerTwoEnter;
    JTextField playerThreeEnter;
    JTextField playerFourEnter;
    JTextField playerFiveEnter;

    JButton confirm;

    JTextField enterLoadFile;
    JLabel loadScreenText = new JLabel();
    JLabel statusOfFileLoaded = new JLabel();
    JButton loadConfirmButton = new JButton("Confirm");
    JButton exit = new JButton("Exit");

    JButton exit0 = new JButton("Exit");
    JButton nextSubject = new JButton("Next =>");
    JLabel customQuestionsText = new JLabel();
    JLabel customFileStatus = new JLabel();

    JTextField enterSubject;
    JTextField enterQuestion1;
    JTextField enterQuestion2;
    JTextField enterQuestion3;
    JTextField enterQuestion4;
    JTextField enterQuestion5;
    JTextField enterAnswer1;
    JTextField enterAnswer2;
    JTextField enterAnswer3;
    JTextField enterAnswer4;
    JTextField enterAnswer5;
    int subjectCounter = 1;

    public Screen() {
        //The order of these methods are very important
        setTitle("JEOPARDY");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((int) screenWidth / 4, (int) screenHeight / 10, 960, 720);
        setLayout(null);

        winningScreenRegister();
        createQuestionRegister();
        loadScreenRegister();
        playerScreenRegister();

        jeopardyBoard = new JeopardyBoard();

        startMenu = new StartMenuManager(this);
        jeopardyBoardMenu = new BoardMenuManager(this);
        questionMenu = new QuestionMenuManager(this);
        playerMenu = new PlayerMenuManager(this);
        rewardMenu = new RewardMenuManager(this);

        setContentPane(startMenu.getPane());

        //Order specifically here
        setVisible(true);

        thread = new Thread(this);
        thread.start();
    }


    public void createQuestionRegister() {
        JPanel background = new JPanel();
        background.setBackground(new Color(4, 16, 84));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        enterSubject = new JTextField("Enter Subject Here", 21);
        enterSubject.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 2;
        info.add(enterSubject, gbc);
        enterQuestion1 = new JTextField("Enter Question Here", 21);
        enterQuestion1.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 3;
        info.add(enterQuestion1, gbc);
        enterQuestion2 = new JTextField("Enter Question Here", 21);
        enterQuestion2.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 4;
        info.add(enterQuestion2, gbc);
        enterQuestion3 = new JTextField("Enter Question Here", 21);
        enterQuestion3.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 5;
        info.add(enterQuestion3, gbc);
        enterQuestion4 = new JTextField("Enter Question Here", 21);
        enterQuestion4.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 6;
        info.add(enterQuestion4, gbc);
        enterQuestion5 = new JTextField("Enter Question Here", 21);
        enterQuestion5.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 7;
        info.add(enterQuestion5, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        enterAnswer1 = new JTextField("Enter Answer Here", 21);
        enterAnswer1.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 3;
        info.add(enterAnswer1, gbc);
        enterAnswer2 = new JTextField("Enter Answer Here", 21);
        enterAnswer2.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 4;
        info.add(enterAnswer2, gbc);
        enterAnswer3 = new JTextField("Enter Answer Here", 21);
        enterAnswer3.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 5;
        info.add(enterAnswer3, gbc);
        enterAnswer4 = new JTextField("Enter Answer Here", 21);
        enterAnswer4.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 6;
        info.add(enterAnswer4, gbc);
        enterAnswer5 = new JTextField("Enter Answer Here", 21);
        enterAnswer5.setFont(new Font("Century Gothic", Font.BOLD, 10));
        gbc.gridy = 7;
        info.add(enterAnswer5, gbc);

        registerText(customQuestionsText, "Enter Your Custom Jeopardy Board: ("+subjectCounter+"/5)", 255, 255, 255, "Century Gothic", Font.BOLD, 15, 0, 0, 100, 0, 0, 0, 10, 0, info);
        registerText(customFileStatus, "", 255, 255, 255, "Century Gothic", Font.BOLD, 15, 0, 8, 100, 0, 0, 0, 10, 0, info);
        customFileStatus.setVisible(false);
        registerButton(nextSubject, 1, 0, 100, 30, 0, 0, 0, 0, info);
        registerButton(exit0, 0, 9, 60, 30, 5, 0, 0, 0, info);

        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        createQuestionScreen.add(background, JLayeredPane.DEFAULT_LAYER);
        createQuestionScreen.add(info, JLayeredPane.PALETTE_LAYER);
        createQuestionScreen.setBounds(0, 0, 960, 720);
    }

    public void loadScreenRegister() {
        JPanel background = new JPanel();
        background.setBackground(new Color(4, 16, 84));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);
        enterLoadFile = new JTextField("default.txt", 30);
        enterLoadFile.setFont(new Font("Century Gothic", Font.BOLD, 15));

        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 1;
        info.add(enterLoadFile, gbc);

        registerText(loadScreenText, "Enter Jeopardy Board File:", 255, 255, 255, "Century Gothic", Font.BOLD, 30, 0, 0, 100, 0, 0, 0, 50, 0, info);
        registerText(statusOfFileLoaded, "default.txt Currently Loaded", 255, 255, 255, "Century Gothic", Font.BOLD, 14, 0, 3, 100, 0, 100, 0, 0, 0, info);

        registerButton(loadConfirmButton, 1, 1, 100, 30, 10, 0, 0, 0, info);
        registerButton(exit, 0, 2, 100, 30, 10, 0, 0, 0, info);
        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        loadScreen.add(background, JLayeredPane.DEFAULT_LAYER);
        loadScreen.add(info, JLayeredPane.PALETTE_LAYER);
        loadScreen.setBounds(0, 0, 960, 720);
    }

    public void playerScreenRegister() {
        JPanel background = new JPanel();
        background.setBackground(new Color(4, 16, 84));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);
        playerOneEnter = new JTextField("Enter Player 1 here", 25);
        playerTwoEnter = new JTextField("Enter Player 2 here", 25);
        playerThreeEnter = new JTextField("Enter Player 3 here", 25);
        playerFourEnter = new JTextField("Enter Player 4 here", 25);
        playerFiveEnter = new JTextField("Enter Player 5 here", 25);
        playerOneEnter.setFont(new Font("Century Gothic", Font.BOLD, 20));
        playerTwoEnter.setFont(new Font("Century Gothic", Font.BOLD, 20));
        playerThreeEnter.setFont(new Font("Century Gothic", Font.BOLD, 20));
        playerFourEnter.setFont(new Font("Century Gothic", Font.BOLD, 20));
        playerFiveEnter.setFont(new Font("Century Gothic", Font.BOLD, 20));

        gbc.gridx = 0;

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 1;
        info.add(playerOneEnter, gbc);
        gbc.gridy = 2;
        info.add(playerTwoEnter, gbc);
        gbc.gridy = 3;
        info.add(playerThreeEnter, gbc);
        gbc.gridy = 4;
        info.add(playerFourEnter, gbc);
        gbc.gridy = 5;
        info.add(playerFiveEnter, gbc);

        JLabel playerScreenText = new JLabel();
        registerText(playerScreenText, "Enter Player Names:", 255, 255, 255, "Century Gothic", Font.BOLD, 30, 0, 0, 100, 0, 0, 0, 50, 0, info);

        confirm = new JButton("Confirm Names");
        registerButton(confirm, 0, 6, 100, 30, 10, 0, 0, 0, info);
        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        playerScreen.add(background, JLayeredPane.DEFAULT_LAYER);
        playerScreen.add(info, JLayeredPane.PALETTE_LAYER);
        playerScreen.setBounds(0, 0, 960, 720);
    }

    public void rewardPointsMenuRegister() {
        JPanel background = new JPanel();
        background.setBackground(new Color(4, 16, 84));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);

        registerText(promptReward, "Who Answered Correctly?", 255,255,255, "Century Gothic", Font.BOLD, 20, 2, 0, 1, 1, 0, 0, 250, 0, info);

        registerButton(playerOneButton, 0, 1, 50, 25, 10, 0, 10, 0, info);
        registerButton(playerTwoButton, 1, 1, 50, 25, 10, 0, 10, 0, info);
        registerButton(playerThreeButton, 2, 1, 150, 25, 10, 0, 10, 0, info);
        registerButton(playerFourButton, 3, 1, 50, 25, 10, 0, 10, 0, info);
        registerButton(playerFiveButton, 4, 1, 50, 25, 10, 0, 10, 0, info);

        playerOneButton.setText(Players.PlayerOne.name);
        playerTwoButton.setText(Players.PlayerTwo.name);
        playerThreeButton.setText(Players.PlayerThree.name);
        playerFourButton.setText(Players.PlayerFour.name);
        playerFiveButton.setText(Players.PlayerFive.name);

        registerButton(editButton, 0, 0, 25, 10, 0, 0, 500, 0, info);
        registerButton(returnButton, 4, 0, 25, 10, 0, 0, 500, 0, info);

        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        rewardPointsMenu.add(background, JLayeredPane.DEFAULT_LAYER);
        rewardPointsMenu.add(info, JLayeredPane.PALETTE_LAYER);
        rewardPointsMenu.setBounds(0, 0, 960, 720);

    }

    public void winningScreenRegister() {
        JPanel background = new JPanel();
        background.setBackground(new Color(4, 16, 84));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);

        registerText(Winner, "null", 255, 255, 255, "Century Gothic", Font.BOLD, 40, 0, 0, 100, 47, 0, 0, 0, 0, info);

        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        winningScreen.add(background, JLayeredPane.DEFAULT_LAYER);
        winningScreen.add(info, JLayeredPane.PALETTE_LAYER);
        winningScreen.setBounds(0, 0, 960, 720);

    }

    public void findWinner() {
        List<Players.Player> placements = List.of(Players.PlayerOne, Players.PlayerTwo, Players.PlayerThree, Players.PlayerFour, Players.PlayerFive);
        int winnerIndex = 0;
        for (int index = 0; index < placements.size(); index = index + 1) {
            if (placements.get(index).points >= placements.get(winnerIndex).points) {
                winnerIndex = index;
                System.out.println("Updating winnerIndex to " + winnerIndex);
            }
            else {
                System.out.println("Not updated yet");
            }
        }

        Winner.setText("Winner Winner: "+placements.get(winnerIndex).name);
    }

    //Creates buttons with ease
    public void registerButton(JButton button, int x, int y, int width, int height, int up, int left, int down, int right, JPanel panel) {
        gbc.gridx = x;
        gbc.gridy = y;

        gbc.ipadx = width;
        gbc.ipady = height;

        gbc.insets = new Insets(up, left, down, right);

        gbc.anchor = GridBagConstraints.CENTER;

        button.addActionListener(this);
        layout.setConstraints(button, gbc);
        panel.add(button);
        button.setVisible(true);
    }

    public void registerText(JLabel label, String text, int r, int g, int b, String font, int style, int size, int x, int y, int width, int height, int up, int left, int down, int right, JPanel panel) {
        gbc.gridx = x;
        gbc.gridy = y;

        gbc.ipadx = width;
        gbc.ipady = height;

        gbc.insets = new Insets(up, left, down, right);

        gbc.anchor = GridBagConstraints.CENTER;

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        label.setText(text);
        label.setForeground(new Color(r, g, b));
        label.setFont(new Font(font, style, size));
        layout.setConstraints(label, gbc);
        panel.add(label);
        label.setVisible(true);
    }

    public void addPoints(Players.Player player) {
        if (editButton.getBackground().equals(Color.YELLOW)) {
            player.points = player.points - pointsValue;
        }
        else {
            player.points = player.points + pointsValue;
        }
    }

    public void questionChosen() {
        playedBoards = playedBoards + 1;
        if (playedBoards >= 25) {
            setContentPane(winningScreen);
            findWinner();
        }
    }

    public void resetText() {
        enterSubject.setText("Enter Subject Here");
        enterQuestion1.setText("Enter Question Here");
        enterAnswer1.setText("Enter Answer Here");
        enterQuestion2.setText("Enter Question Here");
        enterAnswer2.setText("Enter Answer Here");
        enterQuestion3.setText("Enter Question Here");
        enterAnswer3.setText("Enter Answer Here");
        enterQuestion4.setText("Enter Question Here");
        enterAnswer4.setText("Enter Answer Here");
        enterQuestion5.setText("Enter Question Here");
        enterAnswer5.setText("Enter Answer Here");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==confirm) {
            Players.PlayerOne.name = playerOneEnter.getText();
            Players.PlayerTwo.name = playerTwoEnter.getText();
            Players.PlayerThree.name = playerThreeEnter.getText();
            Players.PlayerFour.name = playerFourEnter.getText();
            Players.PlayerFive.name = playerFiveEnter.getText();
            rewardPointsMenuRegister();
            setContentPane(jeopardyBoardMenu.getPane());
        }
        if(e.getSource()==exit) {
            setContentPane(startMenu.getPane());
        }
        if(e.getSource()==exit0) {
            enterQuestion1.setVisible(true);
            enterAnswer1.setVisible(true);
            enterQuestion2.setVisible(true);
            enterAnswer2.setVisible(true);
            enterQuestion3.setVisible(true);
            enterAnswer3.setVisible(true);
            enterQuestion4.setVisible(true);
            enterAnswer4.setVisible(true);
            enterQuestion5.setVisible(true);
            enterAnswer5.setVisible(true);
            enterSubject.setVisible(true);
            customFileStatus.setVisible(false);
            resetText();
            new JeopardyBoardOld();
            subjectCounter = 1;
            customQuestionsText.setText("Enter Your Custom Jeopardy Board: ("+subjectCounter+"/5)");
            setContentPane(startMenu.getPane());
        }
        if(e.getSource() == loadConfirmButton) {
            Boolean fileLoadStatus = Main.jeopardyBoard.load(enterLoadFile.getText());
            if (fileLoadStatus) {
                statusOfFileLoaded.setText("File Found, Successfully Loaded");
            }
            else {
                statusOfFileLoaded.setText("File not found");
            }
        }
        if (e.getSource() == returnButton) {
            editButton.setBackground(null);
            POneScore.setText(Players.PlayerOne.name+": \n$"+Players.PlayerOne.points);
            PTwoScore.setText(Players.PlayerTwo.name+": \n$"+Players.PlayerTwo.points);
            PThreeScore.setText(Players.PlayerThree.name+": \n$"+Players.PlayerThree.points);
            PFourScore.setText(Players.PlayerFour.name+": \n$"+Players.PlayerFour.points);
            PFiveScore.setText(Players.PlayerFive.name+": \n$"+Players.PlayerFive.points);

            setContentPane(jeopardyBoardMenu.getPane());
            questionChosen();
        }
        if (e.getSource() == editButton) {
            if (editButton.getBackground().equals(Color.YELLOW)) {
                editButton.setBackground(null);
            }
            else {
                editButton.setBackground(Color.YELLOW);
            }
        }
        if (e.getSource() == playerOneButton) {
            addPoints(Players.PlayerOne);
        }
        if (e.getSource() == playerTwoButton) {
            addPoints(Players.PlayerTwo);
        }
        if (e.getSource() == playerThreeButton) {
            addPoints(Players.PlayerThree);
        }
        if (e.getSource() == playerFourButton) {
            addPoints(Players.PlayerFour);
        }
        if (e.getSource() == playerFiveButton) {
            addPoints(Players.PlayerFive);
        }
        if (e.getSource() == nextSubject) {
            if (subjectCounter == 0) {
                subjectCounter = 1;
                nextSubject.setVisible(false);
                String filename = enterSubject.getText().toLowerCase().strip();
                if (!filename.contains(".txt")) {
                    filename = filename + ".txt";
                }

                customFileStatus.setText("File "+filename+" Created");
                customFileStatus.setVisible(true);
                customQuestionsText.setText("Completed");
                Main.jeopardyBoard.writeToFile(filename);

            }
            else if (subjectCounter == 1) {
                subjectCounter = subjectCounter + 1;
                Main.jeopardyBoard.subjectOne.title = enterSubject.getText();
                Main.jeopardyBoard.subjectOne.questions = List.of(enterQuestion1.getText(), enterQuestion2.getText(),
                        enterQuestion3.getText(), enterQuestion4.getText(), enterQuestion5.getText());
                Main.jeopardyBoard.subjectOne.answers = List.of(enterAnswer1.getText(), enterAnswer2.getText(),
                        enterAnswer3.getText(), enterAnswer4.getText(), enterAnswer5.getText());
                resetText();
                customQuestionsText.setText("Enter Your Custom Jeopardy Board: ("+subjectCounter+"/5)");
            }
            else if (subjectCounter == 2) {
                subjectCounter = subjectCounter + 1;
                Main.jeopardyBoard.subjectTwo.title = enterSubject.getText();
                Main.jeopardyBoard.subjectTwo.questions = List.of(enterQuestion1.getText(), enterQuestion2.getText(),
                        enterQuestion3.getText(), enterQuestion4.getText(), enterQuestion5.getText());
                Main.jeopardyBoard.subjectTwo.answers = List.of(enterAnswer1.getText(), enterAnswer2.getText(),
                        enterAnswer3.getText(), enterAnswer4.getText(), enterAnswer5.getText());
                resetText();
                customQuestionsText.setText("Enter Your Custom Jeopardy Board: ("+subjectCounter+"/5)");
            }
            else if (subjectCounter == 3) {
                subjectCounter = subjectCounter + 1;
                Main.jeopardyBoard.subjectThree.title = enterSubject.getText();
                Main.jeopardyBoard.subjectThree.questions = List.of(enterQuestion1.getText(), enterQuestion2.getText(),
                        enterQuestion3.getText(), enterQuestion4.getText(), enterQuestion5.getText());
                Main.jeopardyBoard.subjectThree.answers = List.of(enterAnswer1.getText(), enterAnswer2.getText(),
                        enterAnswer3.getText(), enterAnswer4.getText(), enterAnswer5.getText());
                resetText();
                customQuestionsText.setText("Enter Your Custom Jeopardy Board: ("+subjectCounter+"/5)");
            }
            else if (subjectCounter == 4) {
                subjectCounter = subjectCounter + 1;
                Main.jeopardyBoard.subjectFour.title = enterSubject.getText();
                Main.jeopardyBoard.subjectFour.questions = List.of(enterQuestion1.getText(), enterQuestion2.getText(),
                        enterQuestion3.getText(), enterQuestion4.getText(), enterQuestion5.getText());
                Main.jeopardyBoard.subjectFour.answers = List.of(enterAnswer1.getText(), enterAnswer2.getText(),
                        enterAnswer3.getText(), enterAnswer4.getText(), enterAnswer5.getText());
                resetText();
                customQuestionsText.setText("Enter Your Custom Jeopardy Board: ("+subjectCounter+"/5)");
            }
            else {
                Main.jeopardyBoard.subjectFive.title = enterSubject.getText();
                Main.jeopardyBoard.subjectFive.questions = List.of(enterQuestion1.getText(), enterQuestion2.getText(),
                        enterQuestion3.getText(), enterQuestion4.getText(), enterQuestion5.getText());
                Main.jeopardyBoard.subjectFive.answers = List.of(enterAnswer1.getText(), enterAnswer2.getText(),
                        enterAnswer3.getText(), enterAnswer4.getText(), enterAnswer5.getText());
                resetText();
                enterQuestion1.setVisible(false);
                enterAnswer1.setVisible(false);
                enterQuestion2.setVisible(false);
                enterAnswer2.setVisible(false);
                enterQuestion3.setVisible(false);
                enterAnswer3.setVisible(false);
                enterQuestion4.setVisible(false);
                enterAnswer4.setVisible(false);
                enterQuestion5.setVisible(false);
                enterAnswer5.setVisible(false);
                nextSubject.setText("Confirm");
                customQuestionsText.setText("Completed 5/5 Subjects. Now Enter File Name");
                enterSubject.setText("Enter File Name Here (NO SPACES)");
                subjectCounter = 0;
            }
        }
    }

    public GridBagLayout getLayout() {
        return this.layout;
    }

    public List<AbstractMenu> getAllMenus() {
        return allMenus;
    }

    public AbstractMenu getStartMenu() {
        return startMenu;
    }

    public AbstractMenu getJeopardyBoardMenu() {
        return jeopardyBoardMenu;
    }

    public AbstractMenu getQuestionMenu() {
        return questionMenu;
    }

    public AbstractMenu getPlayerMenu() {
        return playerMenu;
    }

    public AbstractMenu getRewardMenu() {
        return rewardMenu;
    }

    public IBoard<ISubject> getJeopardyBoard() {
        return jeopardyBoard;
    }

    @Override
    public void run() {
        long drawInterval = 1000000000 / FPS;
        long nextDrawTime = System.nanoTime() + drawInterval;

        long fpsTimer = System.nanoTime();
        int frameCount = 0;

        long milliSecondConvert = 1000000;

        long oneSecond = 1000000000;

        while (isVisible()) {
            long currentTime = System.nanoTime();
            update();
            repaint();

            frameCount++;


            if (currentTime - fpsTimer >= oneSecond) {
                fpsCount = frameCount;
                frameCount = 0;
                fpsTimer = currentTime;
            }

            try {
                long remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / milliSecondConvert;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep(remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException ignored) {
                System.out.println("Thread interrupted");
            }
        }
    }

    private void update() {
        validate();
        allMenus.forEach(AbstractMenu::update);
        //System.out.println("FPS: " + fpsCount);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        allMenus.forEach(ITickable::tick);
        g.dispose();
    }
}
