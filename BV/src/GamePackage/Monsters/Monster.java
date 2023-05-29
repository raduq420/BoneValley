package GamePackage.Monsters;

import GamePackage.MovEntity;
import GamePackage.OverflowException;
import GamePackage.Player;
import GamePackage.StatusEffect;

import java.awt.image.BufferedImage;

public abstract class Monster extends MovEntity {
    public Monster(int x, int y, int i, int j, int z, int speed, float scale, int health) {
        super(x, y, i, j, z, speed, scale);
    }

    public Monster(int x, int y, int i, int j, int z, int speed, float scale,
                   BufferedImage[] front, BufferedImage[] back, BufferedImage[] left, BufferedImage[] right, int health) {
        super(x, y, i, j, z, speed, scale, front, back, left, right);
        this.health = health;
    }
    protected String type;
    public int health;

    private StatusEffect[] stats = new StatusEffect[100];
    private int statusCnt = 0;

    abstract public void intelligence(Player dude) throws OverflowException;
    public boolean isdead()
    {
        return health <= 0;
    }
    public void addStatus(StatusEffect z)
    {
        stats[statusCnt] = z;
        statusCnt++;
    }
    public void tickStatuses()
    {
        for(int i = 0 ; i < statusCnt ; i++)
            if(stats[i].checkBuffer())
            {
                stats[i].statusActivate(this);
                stats[i] = stats[statusCnt - 1];
                statusCnt--;
                i--;
            }
    }
    public String getType()
    {
        return type;
    }
}
