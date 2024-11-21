import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Game extends JFrame implements ActionListener {
    int playedBoards = 0;
    int pointsValue = 0;
    Scanner input = new Scanner(System.in);
    double timer = 600; //add a timer one day
    JLayeredPane startMenu = new JLayeredPane();
    JLayeredPane boardMenu = new JLayeredPane();
    JLayeredPane questionMenu = new JLayeredPane();
    JLayeredPane rewardPointsMenu = new JLayeredPane();
    JLayeredPane winningScreen = new JLayeredPane();
    JLayeredPane playerScreen = new JLayeredPane();
    JLayeredPane loadScreen = new JLayeredPane();
    JLayeredPane createQuestionScreen = new JLayeredPane();

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout layout = new GridBagLayout();

    ImageIcon img = new ImageIcon(getClass().getResource("/startmenu_background.jpg"));
    JLabel imageLabel = new JLabel(img);
    ImageIcon img0 = new ImageIcon(getClass().getResource("/background_questions.jpg"));
    JLabel imageLabel0 = new JLabel(img0);
    ImageIcon img1 = new ImageIcon(getClass().getResource("/board.jpg"));
    JLabel imageLabel1 = new JLabel(img1);

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    JLabel titleText = new JLabel();
    JLabel subjectOneLabel = new JLabel();
    JLabel subjectTwoLabel = new JLabel();
    JLabel subjectThreeLabel = new JLabel();
    JLabel subjectFourLabel = new JLabel();
    JLabel subjectFiveLabel = new JLabel();
    JLabel question = new JLabel();
    JLabel answer = new JLabel();
    JLabel POneScore = new JLabel();
    JLabel PTwoScore = new JLabel();
    JLabel PThreeScore = new JLabel();
    JLabel PFourScore = new JLabel();
    JLabel PFiveScore = new JLabel();

    JLabel Winner = new JLabel();

    //Start Menu Buttons
    JButton startButton = new JButton("Start");
    JButton loadButton = new JButton("Load");
    JButton customButton = new JButton("Custom");

    //Subject 1 buttons
    JButton subOne_first = new JButton("$100");
    JButton subOne_second = new JButton("$200");
    JButton subOne_third = new JButton("$300");
    JButton subOne_fourth = new JButton("$400");
    JButton subOne_fifth = new JButton("$500");

    JButton subTwo_first = new JButton("$100");
    JButton subTwo_second = new JButton("$200");
    JButton subTwo_third = new JButton("$300");
    JButton subTwo_fourth = new JButton("$400");
    JButton subTwo_fifth = new JButton("$500");

    JButton subThree_first = new JButton("$100");
    JButton subThree_second = new JButton("$200");
    JButton subThree_third = new JButton("$300");
    JButton subThree_fourth = new JButton("$400");
    JButton subThree_fifth = new JButton("$500");

    JButton subFour_first = new JButton("$100");
    JButton subFour_second = new JButton("$200");
    JButton subFour_third = new JButton("$300");
    JButton subFour_fourth = new JButton("$400");
    JButton subFour_fifth = new JButton("$500");

    JButton subFive_first = new JButton("$100");
    JButton subFive_second = new JButton("$200");
    JButton subFive_third = new JButton("$300");
    JButton subFive_fourth = new JButton("$400");
    JButton subFive_fifth = new JButton("$500");


    JButton answerButton = new JButton("Show Answer");
    JButton nextButton = new JButton("Next =>");

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

    public void startMenuRegister(JLabel image) {
        JPanel background = new JPanel();
        image.setBounds(0, 0, 960, 720);
        image.setVisible(true);
        background.add(image);
        background.setBackground(new Color(255, 255, 255));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);
        registerButton(startButton, 0, 1, 150, 40, 0, 0, 20, 0, info);
        registerButton(loadButton, 0, 3, 60, 30, 0, 0, 10, 0, info);
        registerButton(customButton, 0, 2, 60, 30, 0, 0, 10, 0, info);
        registerText(titleText, "Jeopardy", 255,255,0, "Century Gothic", Font.BOLD, 70, 0, 0, 16, 16, 0, 0, 300, 5, info);
        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        startMenu.add(background, JLayeredPane.DEFAULT_LAYER);
        startMenu.add(info, JLayeredPane.PALETTE_LAYER);
        startMenu.setBounds(0, 0, 960, 720);

    }

    public void boardMenuRegister(JLabel image) {
        JPanel background = new JPanel();
        image.setBounds(0, 0, 960, 720);
        image.setVisible(true);
        background.add(image);
        background.setBackground(new Color(255, 255, 255));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);


        registerText(subjectOneLabel, JeopardyBoard.subjectOne.title, 255,255,0, "Century Gothic", Font.BOLD, 16, 0, 0, 75, 25, 0, 0, 25, 0, info);
        subjectOneLabel = boarderBlue(subjectOneLabel);
        registerText(subjectTwoLabel, JeopardyBoard.subjectTwo.title, 255,255,0, "Century Gothic", Font.BOLD, 16, 1, 0, 75, 25, 0, 0, 25, 0, info);
        subjectTwoLabel = boarderBlue(subjectTwoLabel);
        registerText(subjectThreeLabel, JeopardyBoard.subjectThree.title, 255,255,0, "Century Gothic", Font.BOLD, 16, 2, 0, 75, 25, 0, 0, 25, 0, info);
        subjectThreeLabel = boarderBlue(subjectThreeLabel);
        registerText(subjectFourLabel, JeopardyBoard.subjectFour.title, 255,255,0, "Century Gothic", Font.BOLD, 16, 3, 0, 75, 25, 0, 0, 25, 0, info);
        subjectFourLabel = boarderBlue(subjectFourLabel);
        registerText(subjectFiveLabel, JeopardyBoard.subjectFive.title, 255,255,0, "Century Gothic", Font.BOLD, 16, 4, 0, 75, 25, 0, 0, 25, 0, info);
        subjectFiveLabel = boarderBlue(subjectFiveLabel);

        int height = 65;
        int width = 90;

        registerButton(subOne_first, 0, 1, width, height, 0, 0, 5, 0, info);
        subOne_first = makeItBlue(subOne_first);
        registerButton(subOne_second, 0, 2, width, height, 0, 0, 5, 0, info);
        subOne_second = makeItBlue(subOne_second);
        registerButton(subOne_third, 0, 3, width, height, 0, 0, 5, 0, info);
        subOne_third = makeItBlue(subOne_third);
        registerButton(subOne_fourth, 0, 4, width, height, 0, 0, 5, 0, info);
        subOne_fourth = makeItBlue(subOne_fourth);
        registerButton(subOne_fifth, 0, 5, width, height, 0, 0, 5, 0, info);
        subOne_fifth = makeItBlue(subOne_fifth);

        registerButton(subTwo_first, 1, 1, width, height, 0, 0, 5, 0, info);
        subTwo_first = makeItBlue(subTwo_first);
        registerButton(subTwo_second, 1, 2, width, height, 0, 0, 5, 0, info);
        subTwo_second = makeItBlue(subTwo_second);
        registerButton(subTwo_third, 1, 3, width, height, 0, 0, 5, 0, info);
        subTwo_third = makeItBlue(subTwo_third);
        registerButton(subTwo_fourth, 1, 4, width, height, 0, 0, 5, 0, info);
        subTwo_fourth = makeItBlue(subTwo_fourth);
        registerButton(subTwo_fifth, 1, 5, width, height, 0, 0, 5, 0, info);
        subTwo_fifth = makeItBlue(subTwo_fifth);

        registerButton(subThree_first, 2, 1, width, height, 0, 0, 5, 0, info);
        subThree_first = makeItBlue(subThree_first);
        registerButton(subThree_second, 2, 2, width, height, 0, 0, 5, 0, info);
        subThree_second = makeItBlue(subThree_second);
        registerButton(subThree_third, 2, 3, width, height, 0, 0, 5, 0, info);
        subThree_third = makeItBlue(subThree_third);
        registerButton(subThree_fourth, 2, 4, width, height, 0, 0, 5, 0, info);
        subThree_fourth = makeItBlue(subThree_fourth);
        registerButton(subThree_fifth, 2, 5, width, height, 0, 0, 5, 0, info);
        subThree_fifth = makeItBlue(subThree_fifth);

        registerButton(subFour_first, 3, 1, width, height, 0, 0, 5, 0, info);
        subFour_first = makeItBlue(subFour_first);
        registerButton(subFour_second, 3, 2, width, height, 0, 0, 5, 0, info);
        subFour_second = makeItBlue(subFour_second);
        registerButton(subFour_third, 3, 3, width, height, 0, 0, 5, 0, info);
        subFour_third = makeItBlue(subFour_third);
        registerButton(subFour_fourth, 3, 4, width, height, 0, 0, 5, 0, info);
        subFour_fourth = makeItBlue(subFour_fourth);
        registerButton(subFour_fifth, 3, 5, width, height, 0, 0, 5, 0, info);
        subFour_fifth = makeItBlue(subFour_fifth);

        registerButton(subFive_first, 4, 1, width, height, 0, 0, 5, 0, info);
        subFive_first = makeItBlue(subFive_first);
        registerButton(subFive_second, 4, 2, width, height, 0, 0, 5, 0, info);
        subFive_second = makeItBlue(subFive_second);
        registerButton(subFive_third, 4, 3, width, height, 0, 0, 5, 0, info);
        subFive_third = makeItBlue(subFive_third);
        registerButton(subFive_fourth, 4, 4, width, height, 0, 0, 5, 0, info);
        subFive_fourth = makeItBlue(subFive_fourth);
        registerButton(subFive_fifth, 4, 5, width, height, 0, 0, 5, 0, info);
        subFive_fifth = makeItBlue(subFive_fifth);

        registerText(POneScore, Players.PlayerOne.name+": \n$"+Players.PlayerOne.points, 0,0,0, "Century Gothic", Font.BOLD, 12, 0, 6, 75, 50, 0, 0, 50, 0, info);
        POneScore = boarderBlue(POneScore);
        registerText(PTwoScore, Players.PlayerTwo.name+": \n$"+Players.PlayerTwo.points, 0,0,0, "Century Gothic", Font.BOLD, 12, 1, 6, 75, 50, 0, 0, 50, 0, info);
        PTwoScore = boarderBlue(PTwoScore);
        registerText(PThreeScore, Players.PlayerThree.name+": \n$"+Players.PlayerThree.points, 0,0,0, "Century Gothic", Font.BOLD, 12, 2, 6, 75, 50, 0, 0, 50, 0, info);
        PThreeScore = boarderBlue(PThreeScore);
        registerText(PFourScore, Players.PlayerFour.name+": \n$"+Players.PlayerFour.points, 0,0,0, "Century Gothic", Font.BOLD, 12, 3, 6, 75, 50, 0, 0, 50, 0, info);
        PFourScore = boarderBlue(PFourScore);
        registerText(PFiveScore, Players.PlayerFive.name+": \n$"+Players.PlayerFive.points, 0,0,0, "Century Gothic", Font.BOLD, 12, 4, 6, 75, 50, 0, 0, 50, 0, info);
        PFiveScore = boarderBlue(PFiveScore);

        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        boardMenu.add(background, JLayeredPane.DEFAULT_LAYER);
        boardMenu.add(info, JLayeredPane.PALETTE_LAYER);
        boardMenu.setBounds(0, 0, 960, 720);

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

    public void questionMenuRegister(JLabel image) {
        JPanel background = new JPanel();
        image.setBounds(0, 0, 960, 720);
        image.setVisible(true);
        background.add(image);
        background.setBackground(new Color(0, 0, 0));
        background.setBounds(0, 0, 960, 720);

        JPanel info = new JPanel();
        info.setLayout(layout);
        registerText(question, "", 255, 255, 255, "Century Gothic", Font.BOLD, 20, 0, 0, 100, 47, 0, 0, 0, 0, info);
        registerText(answer, "", 255, 255, 255, "Century Gothic", Font.BOLD, 20, 0, 0, 100, 47, 0, 0, 0, 0, info);
        registerButton(answerButton, 0, 1, 75, 50, 50, 0, 0, 0, info);
        registerButton(nextButton, 0, 1, 50, 25, 75, 0, 0, 0, info);

        answer.setVisible(false);
        info.setBounds(0, 0, 960, 720);
        info.setOpaque(false);

        questionMenu.add(background, JLayeredPane.DEFAULT_LAYER);
        questionMenu.add(info, JLayeredPane.PALETTE_LAYER);
        questionMenu.setBounds(0, 0, 960, 720);

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

    public void questionInput(String inputQuestion, String inputAnswer) {
        question.setText(inputQuestion);
        answer.setText(inputAnswer);
    }

    public JButton makeItBlue(JButton button) {
        button.setBackground(new Color(4, 16, 84));
        button.setForeground(new Color(255,255,0));
        return button;
    }

    public JLabel boarderBlue(JLabel label) {
        label.setOpaque(true);
        label.setBackground(new Color(4, 16, 84));
        label.setForeground(new Color(255,255,0));
        return label;
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

    public Game() {
        setTitle("JEOPARDY");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((int) screenWidth/4, (int) screenHeight/10, 960, 720);
        setLayout(null);

        winningScreenRegister();
        createQuestionRegister();
        loadScreenRegister();
        startMenuRegister(imageLabel);
        playerScreenRegister();
        setContentPane(startMenu);
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
            boardMenuRegister(imageLabel1);
            questionMenuRegister(imageLabel0);
            rewardPointsMenuRegister();
            setContentPane(boardMenu);
        }
        if(e.getSource()==loadButton) {
            setContentPane(loadScreen);
        }
        if(e.getSource()==exit) {
            setContentPane(startMenu);
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
            new JeopardyBoard();
            subjectCounter = 1;
            customQuestionsText.setText("Enter Your Custom Jeopardy Board: ("+subjectCounter+"/5)");
            setContentPane(startMenu);
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
        if(e.getSource()==startButton) {
            setContentPane(playerScreen);
        }
        if (e.getSource()==subOne_first) {
            subOne_first.setVisible(false);
            questionInput(JeopardyBoard.subjectOne.questions.get(0), JeopardyBoard.subjectOne.answers.get(0));
            this.pointsValue = 100;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subTwo_first) {
            subTwo_first.setVisible(false);
            questionInput(JeopardyBoard.subjectTwo.questions.get(0), JeopardyBoard.subjectTwo.answers.get(0));
            this.pointsValue = 100;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subThree_first) {
            subThree_first.setVisible(false);
            questionInput(JeopardyBoard.subjectThree.questions.get(0), JeopardyBoard.subjectThree.answers.get(0));
            this.pointsValue = 100;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFour_first) {
            subFour_first.setVisible(false);
            questionInput(JeopardyBoard.subjectFour.questions.get(0), JeopardyBoard.subjectFour.answers.get(0));
            this.pointsValue = 100;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFive_first) {
            subFive_first.setVisible(false);
            questionInput(JeopardyBoard.subjectFive.questions.get(0), JeopardyBoard.subjectFive.answers.get(0));
            this.pointsValue = 100;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subOne_second) {
            subOne_second.setVisible(false);
            questionInput(JeopardyBoard.subjectOne.questions.get(1), JeopardyBoard.subjectOne.answers.get(1));
            this.pointsValue = 200;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subTwo_second) {
            subTwo_second.setVisible(false);
            questionInput(JeopardyBoard.subjectTwo.questions.get(1), JeopardyBoard.subjectTwo.answers.get(1));
            this.pointsValue = 200;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subThree_second) {
            subThree_second.setVisible(false);
            questionInput(JeopardyBoard.subjectThree.questions.get(1), JeopardyBoard.subjectThree.answers.get(1));
            this.pointsValue = 200;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFour_second) {
            subFour_second.setVisible(false);
            questionInput(JeopardyBoard.subjectFour.questions.get(1), JeopardyBoard.subjectFour.answers.get(1));
            this.pointsValue = 200;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFive_second) {
            subFive_second.setVisible(false);
            questionInput(JeopardyBoard.subjectFive.questions.get(1), JeopardyBoard.subjectFive.answers.get(1));
            this.pointsValue = 200;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subOne_third) {
            subOne_third.setVisible(false);
            questionInput(JeopardyBoard.subjectOne.questions.get(2), JeopardyBoard.subjectOne.answers.get(2));
            this.pointsValue = 300;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subTwo_third) {
            subTwo_third.setVisible(false);
            questionInput(JeopardyBoard.subjectTwo.questions.get(2), JeopardyBoard.subjectTwo.answers.get(2));
            this.pointsValue = 300;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subThree_third) {
            subThree_third.setVisible(false);
            questionInput(JeopardyBoard.subjectThree.questions.get(2), JeopardyBoard.subjectThree.answers.get(2));
            this.pointsValue = 300;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFour_third) {
            subFour_third.setVisible(false);
            questionInput(JeopardyBoard.subjectFour.questions.get(2), JeopardyBoard.subjectFour.answers.get(2));
            this.pointsValue = 300;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFive_third) {
            subFive_third.setVisible(false);
            questionInput(JeopardyBoard.subjectFive.questions.get(2), JeopardyBoard.subjectFive.answers.get(2));
            this.pointsValue = 300;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subOne_fourth) {
            subOne_fourth.setVisible(false);
            questionInput(JeopardyBoard.subjectOne.questions.get(3), JeopardyBoard.subjectOne.answers.get(3));
            this.pointsValue = 400;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subTwo_fourth) {
            subTwo_fourth.setVisible(false);
            questionInput(JeopardyBoard.subjectTwo.questions.get(3), JeopardyBoard.subjectTwo.answers.get(3));
            this.pointsValue = 400;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subThree_fourth) {
            subThree_fourth.setVisible(false);
            questionInput(JeopardyBoard.subjectThree.questions.get(3), JeopardyBoard.subjectThree.answers.get(3));
            this.pointsValue = 400;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFour_fourth) {
            subFour_fourth.setVisible(false);
            questionInput(JeopardyBoard.subjectFour.questions.get(3), JeopardyBoard.subjectFour.answers.get(3));
            this.pointsValue = 400;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFive_fourth) {
            subFive_fourth.setVisible(false);
            questionInput(JeopardyBoard.subjectFive.questions.get(3), JeopardyBoard.subjectFive.answers.get(3));
            this.pointsValue = 400;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subOne_fifth) {
            subOne_fifth.setVisible(false);
            questionInput(JeopardyBoard.subjectOne.questions.get(4), JeopardyBoard.subjectOne.answers.get(4));
            this.pointsValue = 500;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subTwo_fifth) {
            subTwo_fifth.setVisible(false);
            questionInput(JeopardyBoard.subjectTwo.questions.get(4), JeopardyBoard.subjectTwo.answers.get(4));
            this.pointsValue = 500;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subThree_fifth) {
            subThree_fifth.setVisible(false);
            questionInput(JeopardyBoard.subjectThree.questions.get(4), JeopardyBoard.subjectThree.answers.get(4));
            this.pointsValue = 500;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFour_fifth) {
            subFour_fifth.setVisible(false);
            questionInput(JeopardyBoard.subjectFour.questions.get(4), JeopardyBoard.subjectFour.answers.get(4));
            this.pointsValue = 500;
            setContentPane(questionMenu);
        }
        if (e.getSource()==subFive_fifth) {
            subFive_fifth.setVisible(false);
            questionInput(JeopardyBoard.subjectFive.questions.get(4), JeopardyBoard.subjectFive.answers.get(4));
            this.pointsValue = 500;
            setContentPane(questionMenu);
        }
        if (e.getSource()==answerButton) {
            question.setVisible(false);
            answer.setVisible(true);
            answerButton.setVisible(false);
            nextButton.setVisible(true);
        }
        if (e.getSource() == nextButton) {
            question.setVisible(true);
            answer.setVisible(false);
            answerButton.setVisible(true);
            nextButton.setVisible(false);
            setContentPane(rewardPointsMenu);
        }
        if (e.getSource() == returnButton) {
            editButton.setBackground(null);
            POneScore.setText(Players.PlayerOne.name+": \n$"+Players.PlayerOne.points);
            PTwoScore.setText(Players.PlayerTwo.name+": \n$"+Players.PlayerTwo.points);
            PThreeScore.setText(Players.PlayerThree.name+": \n$"+Players.PlayerThree.points);
            PFourScore.setText(Players.PlayerFour.name+": \n$"+Players.PlayerFour.points);
            PFiveScore.setText(Players.PlayerFive.name+": \n$"+Players.PlayerFive.points);

            setContentPane(boardMenu);
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
        if (e.getSource() == customButton) {
            setContentPane(createQuestionScreen);
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
}
