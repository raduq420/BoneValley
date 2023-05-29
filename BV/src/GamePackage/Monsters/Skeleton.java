package GamePackage.Monsters;

import GamePackage.Monsters.Monster;
import GamePackage.Player;
import GamePackage.SpellBook;

import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class Skeleton extends Monster {
    private boolean hasAttacked = false;
    public Skeleton(int x, int y, int i, int j, int z, int speed, float scale,
                    BufferedImage[] front, BufferedImage[] back, BufferedImage[] left, BufferedImage[] right, int health) {
        super(x, y, i, j, z, speed, scale, front, back, left, right, health);
        type = "Skeleton";
    }
    @Override
    public void intelligence(Player dude)
    {
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

        if(hasAttacked == false)
            if(abs((texture.getX() - texture.getWidth()/2) - (dude.texture.getX() + dude.texture.getWidth()/2)) < 70 && abs((texture.getY() - texture.getHeight()/2) - (dude.texture.getY() + dude.texture.getHeight()/2)) < 70 && hasAttacked == false)
            {
                SpellBook.getInstance().castSpell("Slash" , this);
                hasAttacked = true;
            }

    }
    public void setHasAttacked()
    {
        if(hasAttacked == false)
            hasAttacked = true;
        else hasAttacked = false;
    }
}
