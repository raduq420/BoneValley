package GamePackage;

import GamePackage.GUI.Intermission;
import GamePackage.Spells.Spell;
import GraphicalPackage.Assets;

import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.List;

public class Player extends MovEntity {
    public int health, mana;
    private String spell;
    private String[] spellMenu;
    private int manaBuffer = 20;
    private int spellCD = 0;
    private int currentSpell;
    private int spellCnt;
    private static final Player singlepawn = new Player(0,0,2, (float)2.5,
            Assets.getTextureLine(0,2), Assets.getTextureLine(1,2),
            Assets.getTextureLine(2,2), Assets.getTextureLine(3,2));
    public static Player getInstance()
    {
        return singlepawn;
    }
    public Player(int i, int j, int z, float scale,
                  BufferedImage[] front, BufferedImage[] back,BufferedImage[] left,BufferedImage[] right)
    {
        super(300,200,i , j, z, 4,scale, front, back, left, right);
        health = 100;
        mana = 100;
        spell = "Frostbolt";
        spellMenu = new String[1];
        spellMenu[0] = spell;
    }
    public void addSpell(String x)
    {
        String[] y = new String[spellMenu.length + 1];
        for(int i = 0 ; i < spellMenu.length ; i++)
            y[i] = spellMenu[i];
        y[spellMenu.length] = x;
        spellMenu = y;
    }
    public void switchSpell()
    {
        int z = 0;
        for(int i = 0 ; i < spellMenu.length ; i++)
        {
            if(spell == spellMenu[i])
                z = i;
        }
        if(z == spellMenu.length - 1)
            spell = spellMenu[0];
        else spell = spellMenu[z+1];
    }
    public String getSpell()
    {
        return spell;
    }
    public int getSpellCD()
    {
        return spellCD;
    }
    public void setSpellCD(int x)
    {
        spellCD = x;
    }
    public void tickCalc() throws SQLException, ClassNotFoundException {
        if(mana >= 75)
            setSpeed(4);
        if(mana < 75)
            setSpeed(3);
        if(mana < 50)
            setSpeed(2);
        if(mana < 25)
            setSpeed(2);
        if(mana <= 0)
            setSpeed(1);

        if(manaBuffer == 0)
        {
            if(mana != 100)
             mana += 1;

            if(spellCD != 0)
                spellCD--;

            manaBuffer = 20;
        }
        else manaBuffer -= 1;

       if(health <= 0)
       {
            Intermission.getInstance().loadIntermission(5);
            GameMain.getInstance().setGameState("intermission");
        }
    }
}
