package menu;

import intr.IPlayer;
import screen.Screen;
import util.JBackgroundPanel;
import util.configure.TextConfigure;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WinningMenu extends AbstractMenu {
    private JLabel placements;
    private JLabel firstL;
    private JLabel secondL;
    private JLabel thirdL;
    private boolean hasInit = false;

    public WinningMenu(Screen screen) {
        super(screen);

        placements = new JLabel();
        firstL = new JLabel();
        secondL = new JLabel();
        thirdL = new JLabel();
    }

    @Override
    public void onSetActive() {
        if(hasInit) {
            return;
        }
        hasInit = true;

        List<IPlayer> players = screen.getJeopardyBoard().getPlayers();

        IPlayer first = findHighestScore(players);
        players.remove(first);

        IPlayer second = findHighestScore(players);
        players.remove(second);

        IPlayer third = findHighestScore(players);
        players.remove(third);

        TextConfigure.configure().setBackgroundColor(Screen.JEOPARDY_BLUE).setText("Placements:").setSize(50).confirm(placements, screen, foreground);
        TextConfigure.configure().setBackgroundColor(Screen.JEOPARDY_BLUE).setText("1: " + first.getNames()).setY(1).setSize(25).confirm(firstL, screen, foreground);
        TextConfigure.configure().setBackgroundColor(Screen.JEOPARDY_BLUE).setText("2: " + second.getNames()).setY(2).setSize(25).confirm(secondL, screen, foreground);
        TextConfigure.configure().setBackgroundColor(Screen.JEOPARDY_BLUE).setText("3: " + third.getNames()).setY(3).setSize(25).confirm(thirdL, screen, foreground);
    }

    @Override
    public void onReset() {

    }

    @Override
    public JPanel addBackground() {
        return new JBackgroundPanel("player_select.jpg");
    }

    @Override
    public JPanel addForeground() {
        return new JPanel();
    }

    @Override
    public void tick() {

    }

    private IPlayer findHighestScore(List<IPlayer> players) {
        IPlayer first = players.get(0);
        for (IPlayer player : players) {
            if (player.getScore() > first.getScore()) {
                first = player;
            }
        }

        return first;
    }
}
