package GamePackage.Monsters;

import GamePackage.Monsters.Monster;
import GamePackage.Player;
import GamePackage.SpellBook;

import java.awt.image.BufferedImage;

public class Thrower extends Monster {
    private boolean hasThrown = false;
    private int buffer = 0;
    public Thrower(int x, int y, int i, int j, int z, int speed, float scale, BufferedImage[] front, BufferedImage[] back, BufferedImage[] left, BufferedImage[] right, int health) {
        super(x, y, i, j, z, speed, scale, front, back, left, right, health);
        type = "Thrower";
    }
    @Override
    public void intelligence(Player dude)
    {
        if(hasThrown == false)
        {
            if(buffer > 300)
            {
                SpellBook.getInstance().castSpell("Throw", this);
                hasThrown = true;
                buffer = 0;
            }
            else buffer += 5;

        }
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
    }
    public void setHasThrown()
    {
        if(hasThrown == false)
            hasThrown = true;
        else
            hasThrown = false;
    }
}
