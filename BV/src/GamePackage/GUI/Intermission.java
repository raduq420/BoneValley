package GamePackage.GUI;

import GamePackage.Fawn;
import GamePackage.GameMain;
import GamePackage.KeyHandler;
import GamePackage.Player;

import java.awt.*;
import java.sql.*;

public class Intermission {
    private static final Intermission singlepawn = new Intermission();
    private int buffer;
    private String[] text;
    public static Intermission getInstance()
    {
        return singlepawn;
    }
    public void loadIntermission(int x) throws SQLException, ClassNotFoundException {
        buffer = 300;
        Player.getInstance().health = 100;
        Player.getInstance().mana = 100;
        switch (x) {
            case 1:
                text = new String[1];
                text[0] = "Level 1";
                break;
            case 2:
                text = new String[2];
                text[0] = "Level 2";
                text[1] = "New ability: Fireball, unlocked";
                break;
            case 3:
                text = new String[2];
                text[0] = "Level 3";
                text[1] = "New ability: Timestop, unlocked";
                break;
            case 5:
                Class.forName("org.sqlite.JDBC");
                Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Rduku\\Desktop\\juegospao\\BV\\res\\scor.db");

                String selectSql = "SELECT id FROM scor ORDER BY id DESC LIMIT 1";
                Statement statement2 = c.createStatement();
                ResultSet resultSet2 = statement2.executeQuery(selectSql);
                int z = 0;

                if (resultSet2.next())
                    z = resultSet2.getInt("id") + 1;

                Statement statement3 = c.createStatement();
                ResultSet resultSet3 = statement2.executeQuery(selectSql);

                statement3.executeUpdate("INSERT INTO scor (id, Kills) VALUES ('" + z + "','" + Fawn.getInstance().calculateScore() + "')");

                Statement statement = c.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM scor");
                int cnt = 0;
                String[] y = new String[100];
                while(resultSet.next())
                {
                    y[cnt] = String.valueOf(resultSet.getInt("id")) + " " + String.valueOf(resultSet.getInt("Kills") + "Kills");
                    cnt++;
                }
                text = new String[cnt+1];
                text[0] = "GAME OVER, SCOREBOARD: ";
                for(int i = 1 ; i < text.length ; i++)
                    text[i] = y[i-1];
                buffer = 2000;
        }
        GameMain.getInstance().setGameState("intermission");
    }
    public void tickIntermission()
    {
        if(buffer == 0)
            if(text[0] != "GAME OVER, SCOREBOARD: ")
                GameMain.getInstance().setGameState("game");
            else
                GameMain.getInstance().setGameState("gui");
        else buffer--;
    }
    public void drawIntermission(Graphics g)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 26));
        g.setColor(Color.WHITE);
        int x = 300;
        int y = 300;
        g.drawString(text[0], x, y);
        x -= 60;
        y += 30;
        for(int i = 1 ; i < text.length ; i++)
        {
            g.drawString(text[i], x, y);
            y += 30;
        }
    }
}
