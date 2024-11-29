package screen;

import impl.JeopardyBoard;
import intr.IBoard;
import intr.ISubject;
import menu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Screen extends JFrame implements Runnable {
    public final static Color JEOPARDY_BLUE = new Color(4, 16, 84);
    public final static Color JEOPARDY_YELLOW = new Color(255,255,0);
    public final static String DEFAULT_FILE_TO_OPEN = "default.json";
    public final static int FPS = 60;

    private String fileToOpen = DEFAULT_FILE_TO_OPEN;

    private int fpsCount = 0;

    private AbstractMenu currentMenu;
    private final AbstractMenu startMenu;
    private final AbstractMenu jeopardyBoardMenu;
    private final AbstractMenu questionMenu;
    private final AbstractMenu playerMenu;
    private final AbstractMenu rewardMenu;
    private final AbstractMenu loadMenu;
    private final AbstractMenu questionCreator;
    private final AbstractMenu winningMenu;

    private final IBoard<ISubject> jeopardyBoard;

    private final List<AbstractMenu> allMenus = new ArrayList<>();

    private final GridBagLayout layout = new GridBagLayout();

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    public int screenWidth = screenSize.width;
    public int screenHeight = screenSize.height;

    public Screen() {
        //The order of these methods are very important
        setTitle("JEOPARDY");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((int) screenWidth / 4, (int) screenHeight / 10, 960, 720);
        setLayout(null);

        jeopardyBoard = new JeopardyBoard();

        startMenu = new StartMenuManager(this);
        jeopardyBoardMenu = new BoardMenuManager(this);
        questionMenu = new QuestionMenuManager(this);
        playerMenu = new PlayerMenuManager(this);
        rewardMenu = new RewardMenuManager(this);
        loadMenu = new LoadMenuManager(this);
        questionCreator = new QuestionsCreatorMenu(this);
        winningMenu = new WinningMenu(this);

        setContentPane(startMenu);
    }

    public void setContentPane(AbstractMenu menu) {
        if(currentMenu != null) {
            currentMenu.onRemove();
        }
        menu.onSetActive();
        currentMenu = menu;
        setContentPane(menu.getPane());
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

    public AbstractMenu getLoadMenu() {
        return loadMenu;
    }

    public AbstractMenu getQuestionCreator() {
        return questionCreator;
    }

    public AbstractMenu getWinningMenu() {
        return winningMenu;
    }

    public IBoard<ISubject> getJeopardyBoard() {
        return jeopardyBoard;
    }

    public String getFileToOpen() {
        return fileToOpen;
    }

    public void setFileToOpen(String fileToOpen) {
        String json = ".json";
        if(!fileToOpen.contains(json)) {
            fileToOpen += json;
        }

        this.fileToOpen = fileToOpen.toLowerCase().strip().replaceAll(" ", "_");
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
    }
}
