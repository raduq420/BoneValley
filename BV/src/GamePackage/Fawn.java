package GamePackage;

import GamePackage.Monsters.Monster;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class Fawn {
    private static final Fawn singlepawn = new Fawn();
    private Monster[] enemies;
    private int monsterCnt = 0;
    private int waveCount = 1;
    private MonsterFactory factory = new MonsterFactory();
    private boolean isFawnLoaded = false;
    private Fawn()
    {
        enemies = new Monster[20];
    }
    public static Fawn getInstance()
    {
        return singlepawn;
    }
    public int getMonsterCnt()
    {
        return monsterCnt;
    }
    public String getTypeI(int i)
    {
        return enemies[i].getType();
    }
    public int getXI(int i)
    {
        return enemies[i].texture.getX();
    }
    public int getYI(int i)
    {
        return enemies[i].texture.getY();
    }
    public void setFawnLoaded(boolean x)
    {
        isFawnLoaded = x;
    }

    public boolean isFawnLoaded() {
        return isFawnLoaded;
    }

    public void setMonsterCnt(int x)
    {
        monsterCnt = x;
    }
    public void tickStatus()
    {
        for(int i = 0 ; i < monsterCnt ; i++)
            enemies[i].tickStatuses();
    }
    public void buildMonster(String args, int x, int y)
    {
        switch(args)
        {
            case "Skeleton":
                enemies[monsterCnt] = factory.buildMonster(args, x, y);
                monsterCnt++;
                break;
            case "Thrower":
                enemies[monsterCnt] = factory.buildMonster(args, x, y);
                monsterCnt++;
                break;
            case "Necro":
                enemies[monsterCnt] = factory.buildMonster(args, x, y);
                monsterCnt++;
        }
    }
    public void loadFawn()
    {
        //buildMonster("Skeleton", 100, 200);
        //buildMonster("Thrower", 100, 280);
        //buildMonster("Necro", 200, 350);
    }
    public void Draw(Graphics g)
    {
        Player dude = Player.getInstance();
        for(int i = 0 ; i < monsterCnt ; i++)
            enemies[i].Draw(g);
    }
    public Monster contains(String args)
    {
        for(int i = 0 ; i < monsterCnt ; i++)
        {
            if(args == enemies[i].getType())
                return enemies[i];
        }
        return null;
    }
    public void Intelligence() throws OverflowException {
        Player dude = Player.getInstance();
        for(int i = 0 ; i < monsterCnt ; i++) {
            if(enemies[i].isdead() == true)
            {
                Monster aux = enemies[i];
                enemies[i] = enemies[monsterCnt - 1];
                enemies[monsterCnt - 1] = aux;
                monsterCnt--;
            }
            else
                enemies[i].intelligence(dude);
        }
    }
    public boolean checkCollision(MovEntity thing)
    {
        for(int i = 0 ; i < monsterCnt ; i++)
            if(thing != enemies[i] && thing.texture.collisionEntity(enemies[i].texture))
                return true;

        return false;
    }
    public Monster checkCollisionRet(MovEntity thing)
    {
        for(int i = 0 ; i < monsterCnt ; i++)
            if(thing != enemies[i] && thing.texture.collisionEntity(enemies[i].texture))
                return enemies[i];

        return null;
    }
    public void damageSurrounding(Monster target, int damage)
    {
        for(int i = 0 ; i < monsterCnt ; i++)
            if(abs(target.texture.getX() - enemies[i].texture.getX()) < 100 && abs(target.texture.getY() - enemies[i].texture.getY()) < 100)
                enemies[i].health -= damage;
    }
    public void loadWave()
    {
        monsterCnt = 0;
        int[] x;
        int[] y;
        x = new int[]{871, 966, 1041, 402, 338, 389, 564, 756};
        y = new int[]{243, 652, 373, 652, 424, 195, 314, 756, 336};
        int z = waveCount;
        if(z>9)
            z = 9;
        for(int i = 0 ; i < z ; i++)
        {
            if(random()*100 < 33)
                buildMonster("Skeleton", x[i], y[i]);
            else if(random()*100 < 66)
                buildMonster("Thrower", x[i], y[i]);
            else if(random()*100 <= 100)
                buildMonster("Necro", x[i], y[i]);
        }
        waveCount++;
        isFawnLoaded = true;
    }
    public void checkWave()
    {
        if(monsterCnt == 0)
        {
            isFawnLoaded = false;
        }
    }
    public int calculateScore()
    {
        int a = 0;
        for(int i = 0 ; i < waveCount ; i++)
            a += i;
        return a;
    }
}
