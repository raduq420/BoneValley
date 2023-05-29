package GamePackage.Monsters;

import GamePackage.Fawn;
import GamePackage.OverflowException;
import GamePackage.Player;

import java.awt.image.BufferedImage;

import static java.lang.Math.random;

public class Necro extends Monster{
    private int cooldown = 300;
    public Necro(int x, int y, int i, int j, int z, int speed, float scale, BufferedImage[] front, BufferedImage[] back, BufferedImage[] left, BufferedImage[] right, int health) {
        super(x, y, i, j, z, speed, scale, front, back, left, right, health);
        type = "Necro";
    }

    @Override
    public void intelligence(Player dude) throws OverflowException {

        int x = 0, y = 0;
        if (this.texture.getX() - dude.texture.getX() > 10)
            x = -1;
        else if (this.texture.getX() - dude.texture.getX() < -10)
            x = 1;

        if (this.texture.getY() - dude.texture.getY() > 10)
            y = -1;
        else if (this.texture.getY() - dude.texture.getY() < -10)
            y = 1;
        Move(x, y);

        if(cooldown < 0)
        {
                int l = 0, k = 0;
                if (this.getFacing() == 0) {
                    l = this.texture.getX() + this.texture.getWidth() / 2 - 10;
                    k = this.texture.getY() - 70;
                }
                if (this.getFacing() == 1) {
                    l = this.texture.getX() + this.texture.getWidth() / 2 + 10;
                    k = this.texture.getY() + this.texture.getHeight();
                }
                if (this.getFacing() == 2) {
                    l = this.texture.getX() + this.texture.getWidth() + 40;
                    k = this.texture.getY() + this.texture.getHeight() / 2;
                }
                if (this.getFacing() == 3) {
                    l = this.texture.getX() - 50;
                    k = this.texture.getY() + this.texture.getHeight() / 2;
                }

                if (random() * 100 < 80) {
                    Fawn.getInstance().buildMonster("Skeleton", l, k);
                } else {
                    Fawn.getInstance().buildMonster("Thrower", l, k);
                }
                cooldown = 300;
                if(Fawn.getInstance().getMonsterCnt() >= 20)
                    throw new OverflowException();

        }
        if(cooldown == 30)
            this.setBuffer(20);
        if(cooldown == 170)
            this.setBuffer(20);
        cooldown -= 1;

    }
}
