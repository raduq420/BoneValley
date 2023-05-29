package GamePackage;

import GamePackage.Monsters.Monster;
import GraphicalPackage.Assets;
import GraphicalPackage.ImageLoader;
import GraphicalPackage.Sprite;
import GamePackage.GUI.*;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


public class GameMain implements Runnable{
    private static final GameMain instance = new GameMain("Bone Valley",1000, 800);
    private Player dude;
    private String gameState = "gui";
    private GameWindow window;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    private Habitat abys = new Habitat();
    private Fawn syba = Fawn.getInstance();
    private SpellBook buk = SpellBook.getInstance();
    private Sprite bGround = new Sprite(0,0, ImageLoader.LoadImage("/textures/nword.png"), 4);
    private Sprite HUD = new Sprite(200, 625, ImageLoader.LoadImage("/textures/HUD.png"), 1);
    private int currentLevel = 2;
    private boolean isTimeStopped = false;
    private Graphics        g;
    public static GameMain getInstance()
    {
        return instance;
    }
    public void setGameState(String x)
    {
        gameState = x;
    }
    public void setRunState(boolean x)
    {
        runState = x;
    }
    public void setLevel(int x)
    {
        currentLevel ++;
    }

    public boolean isTimeStopped() {
        return isTimeStopped;
    }

    public void setTimeStopped(boolean x)
    {
        isTimeStopped = x;
    }
    public static int getLevel(){
        return instance.currentLevel;
    }
    private GameMain(String title, int width, int height)
    {
        window = new GameWindow(title, width, height);
        runState = false;
        currentLevel = 1;
    }
    private void Update() throws SQLException, ClassNotFoundException, OverflowException {
        switch(gameState) {
            case "game":
                int x = 0, y = 0;
                //Calculam directia in care se misca player-ul
                if (window.keyH.upPressed == true) y -= 1;
                if (window.keyH.downPressed == true) y += 1;
                if (window.keyH.leftPressed == true) x -= 1;
                if (window.keyH.rightPressed == true) x += 1;
                //Creem o abilitate cand player-ul apasa J si are mai multa mana de 0
                if (window.keyH.jReleased == true && dude.getSpellCD() == 0 && dude.mana > 0) {
                    buk.castSpell(dude.getSpell(), dude);
                    window.keyH.jReleased = false;
                }
                if(window.keyH.Rpressed == true)
                {
                    //Schimbam abilitatea selectata atunci cand player-ul apasa R
                    dude.switchSpell();
                    window.keyH.Rpressed = false;
                }
                //Incarcam monstri in database si ii incarcam in database cand parasim o incapere si intram in alta
                if (syba.isFawnLoaded() == false) {
                    if(currentLevel != 3)
                    {
                        MonsterLoader.SaveMonsters(currentLevel, abys.map.getCurrentMap());
                        MonsterLoader.LoadMonsters(currentLevel, abys.map.getCurrentMap());
                    }
                    //Incarcam un nou val de inamici pentru nivelul 3
                    else syba.loadWave();
                }
                //Verificam daca player-ul a invins valul curent de inamici in nivelul 3
                if(currentLevel == 3)
                    syba.checkWave();
                //Calculam viteza player-ulu in functie de mana
                //Verificam daca player-ul are mai mult de 0 HP
                dude.tickCalc();
                //Miscam player-ul
                dude.Move(x, y);
                if(isTimeStopped == false)
                {
                    //Actionam inteligenta artificiala a inamicilor
                    syba.Intelligence();
                    //Scadem din efectele status a inamicilor
                    syba.tickStatus();
                }
                //Verificam daca player-ul a atins o usa
                abys.doorCollision(currentLevel);
                //Verificam abilitatile
                buk.checkSpells();
                //System.out.println(dude.texture.getX() + " " + dude.texture.getY());
                break;
            case "gui":
                //Parcurgem meniul principal
                if (window.keyH.upPressed == true)
                    GUI.getSinglepawn().setSelected(1);
                else if (window.keyH.downPressed == true)
                    GUI.getSinglepawn().setSelected(-1);
                else if (window.keyH.jReleased == true)
                    GUI.getSinglepawn().activateGUI();
                break;
            case "intermission":
                //Scadem din timer-ul pentru intermisii
                Intermission.getInstance().tickIntermission();
        }
    }
    private void Draw()
    {
        bs = window.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            try
            {
                window.getCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, window.getWidth(), window.getHeight());
        //Desenam fundalul negru
        bGround.Draw(g,460, 400);
        switch(gameState) {
            case "game":
                //Desenam tile-urile
                abys.DrawHabitat(g);
                //Desenam inamicii
                syba.Draw(g);
                //Desenam player-ul
                dude.Draw(g);
                //Desenam toate abilitatile active
                buk.drawSpells(g);
                //abys.DrawHabitat2nd(g, dude);

                //Desenam HUD-ul
                g.setFont(new Font("Arial", Font.PLAIN, 26));
                HUD.Draw(g);
                g.setColor(Color.RED);
                g.drawString(String.valueOf(dude.health), 230, 705);
                g.setColor(Color.BLUE);
                g.drawString(String.valueOf(dude.mana), 230, 735);
                g.setColor(Color.GREEN);
                g.drawString(String.valueOf(dude.getSpellCD()), 430, 705);
                g.drawString(String.valueOf(dude.getSpell()), 430, 735);
                break;
            case "gui":
                //Desenam meniul principal
                GUI.getSinglepawn().drawGUI(g);
                break;
            case "intermission":
                //Desenam ecranul de intermisie
                Intermission.getInstance().drawIntermission(g);
        }
        bs.show();

        g.dispose();
    }
    public void initGame() throws IOException {
        Assets.Init();
        dude = Player.getInstance();
        window.initWindow();
        syba.loadFawn();
        MonsterLoader.initDB();
        runState = true;

    }
    public void run()
    {
        try {
            initGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long oldTime = System.nanoTime();
        long curentTime;



        final int framesPerSecond   = 60;
        final double timeFrame      = 1000000000 / framesPerSecond;
        while (runState == true)
        {
            //Verificam daca harta este incarcata
            if(abys.map.getMapState() == false)
            {
                //Daca nu este, o incarcam
                try {
                    abys.map = new Mapz(currentLevel);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                abys.map.setCurrentMap(0);
            }
            //Verificam daca tile-urile sunt incarcate
            if(abys.isHabitatLoaded() == false)
                //Daca nu, le incarcam
                abys.LoadHabitat();

            curentTime = System.nanoTime();

            if((curentTime - oldTime) > timeFrame)
            {
                try {
                    Update();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (OverflowException e) {
                    Fawn.getInstance().setMonsterCnt(19);
                }
                Draw();
                oldTime = curentTime;
            }
        }
        System.exit(0);
    }


}
