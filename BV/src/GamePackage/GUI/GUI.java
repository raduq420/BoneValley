package GamePackage.GUI;

import GamePackage.GameMain;
import GamePackage.KeyHandler;

import java.awt.*;
import java.security.Key;
import java.sql.SQLException;

public class GUI {
    private static final GUI singlepawn = new GUI();
    private String[] options;
    private int x;
    private int[] y;
    private int selected;
    private int selY;
    public static GUI getSinglepawn()
    {
        return singlepawn;
    }
    public GUI() {
        options = new String[2];
        selected = 0;
        options[0] = "Start";
        options[1] = "Exit";
        y = new int[options.length];
        x = 200;
        for (int i = 0; i < options.length; i++)
            y[i] = 100 + 100 * i;

        selY = y[0];
    }
    public void drawGUI(Graphics g)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 26));
        g.setColor(Color.WHITE);
        for(int i = 0 ; i < options.length ; i++)
            g.drawString(options[i], x, y[i]);
        g.drawString(">", x - 40, selY);
    }
    public void activateGUI() throws SQLException, ClassNotFoundException {
        switch(selected)
        {
            case 0:
                GameMain.getInstance().setGameState("intermission");
                Intermission.getInstance().loadIntermission(1);
                break;
            case 1:
                GameMain.getInstance().setRunState(false);
                break;
        }
        KeyHandler.jReleased = false;
    }
    public void setSelected(int x)
    {
        if(x == 1)
        {
            if(selected == 0)
                selected = options.length - 1;
            else selected--;
            KeyHandler.upPressed = false;
        }
        else if(x == -1)
        {
            if(selected == options.length - 1)
                selected = 0;
            else selected++;
            KeyHandler.downPressed = false;
        }
        selY = y[selected];
    }

}
