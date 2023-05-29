package GamePackage;

import GamePackage.GUI.Intermission;
import GraphicalPackage.Assets;
import GraphicalPackage.Sprite;

import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

public class Habitat {
    private static LinkedList<Integer> collisionCodes = new LinkedList<>(Arrays.asList(1,2,17,18,83,90,150,151,152,134,135,136,201,202,203,217,218,219,43,44,33,34));
    private boolean isHabitatLoaded;
    public static Sprite[][] spritez;
    public static Sprite[] doorz;
    public static int[][] collisionMap;
    public Mapz map = new Mapz();

    public Habitat()
    {
        spritez = null;
        isHabitatLoaded = false;
    }

    public boolean isHabitatLoaded() {
        return isHabitatLoaded;
    }

    public void LoadHabitat()
    {
        int posx = 0;
        int posy = 0;
        int k = map.getCurrentMap();
        spritez = new Sprite[map.dataz[k].length][map.dataz[k][0].length];
        collisionMap = new int[map.dataz[k].length][map.dataz[k][0].length];
        for(int i = 0 ; i < map.dataz[k].length; i++) {
            for (int j = 0; j < map.dataz[k][0].length; j++) {
                spritez[i][j] = new Sprite(posx, posy, Assets.getTexture(map.dataz[k][i][j] - 1, 0, 1), 3);
                if(collisionCodes.contains(map.dataz[k][i][j]))
                    collisionMap[i][j] = 1;
                else collisionMap[i][j] = 0;
                posx += 48;
            }
            posx = 0;
            posy += 48;
        }
        doorz = new Sprite[map.doorzz[k].length];
        for(int i = 0 ; i < doorz.length ; i++)
        {
            doorz[i] = map.doorzz[k][i].createDoor();
        }
        isHabitatLoaded = true;
    }
    public void doorCollision(int currentLevel) throws SQLException, ClassNotFoundException {
        Player dude = Player.getInstance();
        for(int i = 0 ; i < doorz.length ; i++)
        {
            if(dude.texture.collisionEntity(doorz[i]))
            {
                if(map.doorzz[map.getCurrentMap()][i].nxtLevel != 66)
                {
                    dude.texture.setX(map.doorzz[map.getCurrentMap()][i].nxtX);
                    dude.texture.setY(map.doorzz[map.getCurrentMap()][i].nxtY);
                    MonsterLoader.SaveMonsters(GameMain.getLevel(), map.getCurrentMap());
                    map.setCurrentMap(map.doorzz[map.getCurrentMap()][i].nxtLevel);
                    isHabitatLoaded = false;
                    SpellBook.getInstance().setSpellCnt(0);
                    Fawn.getInstance().setFawnLoaded(false);
                }
                else
                {
                    GameMain.getInstance().setLevel(currentLevel+1);
                    dude.texture.setX(map.doorzz[map.getCurrentMap()][i].nxtX);
                    dude.texture.setY(map.doorzz[map.getCurrentMap()][i].nxtY);
                    MonsterLoader.SaveMonsters(GameMain.getLevel(), map.getCurrentMap());
                    SpellBook.getInstance().setSpellCnt(0);
                    Fawn.getInstance().setFawnLoaded(false);
                    isHabitatLoaded = false;
                    map.setMapLoaded(false);
                    Intermission.getInstance().loadIntermission(currentLevel+1);
                    if(currentLevel == 1)
                        Player.getInstance().addSpell("Fireball");
                    if(currentLevel == 2)
                        Player.getInstance().addSpell("Timestop");
                    GameMain.getInstance().setGameState("intermission");
                }
            }
        }
    }
    public void DrawHabitat(Graphics g)
    {
        Player dude = Player.getInstance();
        int k = map.getCurrentMap();
        for(int i = 0 ; i < spritez.length; i++)
            for(int j = 0 ;j < spritez[i].length; j++)
                spritez[i][j].Draw(g, dude.texture.getX(), dude.texture.getY());


    }
    public void DrawHabitat2nd(Graphics g, Player dude)
    {
        for(int i = 0 ; i < doorz.length ; i++)
        {
            doorz[i].Draw(g, dude.texture.getX(), dude.texture.getY());
        }
    }

}
