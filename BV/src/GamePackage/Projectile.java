package GamePackage;

import GamePackage.Monsters.Monster;
import GraphicalPackage.Sprite;

import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class Projectile extends MovEntity {
    private int x, y;
    private int targetAxis = 0;
    private int turnCounter = 0;
    private int targetX, targetY;
    private int oldX, oldY;
    private boolean movedOnce = false;
    public int getXD()
    {
        return x;
    }
    public int getYD()
    {
        return y;
    }
    public Projectile(int x, int y, int i, int j, int z, int speed, float scale,
                      BufferedImage[] front, BufferedImage[] back, BufferedImage[] left, BufferedImage[] right, int xD, int yD) {
        super(x, y, i, j, z, speed, scale, front, back, left, right);
        this.x = xD;
        this.y = yD;
    }
    public Monster collisionCheckRet()
    {
        Fawn syba = Fawn.getInstance();
        Monster target = syba.checkCollisionRet(this);
        if(target != null)
            return target;
        else return null;
    }
    public void Move(Sprite caster)
    {
        movedOnce = true;
        oldX = texture.getX();
        oldY = texture.getY();
        super.MoveWithEx(x, y, caster);
    }
    public void blankMove()
    {
        texture.setX(texture.getX() + getSpeed()*x);
        texture.setY(texture.getY() + getSpeed()*y);
    }
    public int homingMove()
    {
        Player dude = Player.getInstance();
        if(targetAxis == 0)
        {
            if(abs(texture.getX() - dude.texture.getX()) >= abs(texture.getY() - dude.texture.getY())) {
                if (texture.getX() - dude.texture.getX() > 0) {
                    targetAxis = -1;
                    targetX = dude.texture.getX();
                } else {
                    targetAxis = 1;
                    targetX = dude.texture.getX();
                }
                turnCounter++;
            }
            else
                {
                    if (texture.getY() - dude.texture.getY() > 0) {
                        targetAxis = -2;
                    } else {
                        targetAxis = 2;
                    }
                    targetY = dude.texture.getY();
                    turnCounter++;
                }

        }
        if(turnCounter >= 2)
        {
            if(abs(targetAxis) == 1)
            {
                MoveWithNoCollision(targetAxis, 0);
                if(collisionCheckRetSprite(null) == null)
                    return 0;
                else if(collisionCheckRetSprite(null) != dude.texture)
                    return 1;
                else
                    return 2;

            }
            else {
                MoveWithNoCollision(0, targetAxis/2);
                if(collisionCheckRetSprite(null) == null)
                    return 0;
                else if(collisionCheckRetSprite(null) != dude.texture)
                    return 1;
                else
                    return 2;
            }
        }
        else if(abs(targetAxis) == 1)
        {
            if(abs(texture.getX() - targetX) <= 10)
                targetAxis = 0;
            else {
                MoveWithNoCollision(targetAxis, 0);
                if(texture.collision(dude.texture) == true)
                    return 2;
            }
        }
        else if(abs(targetAxis) == 2)
        {
            if(abs(texture.getY() - targetY) <= 10)
                targetAxis = 0;
            else {
                MoveWithNoCollision(0, targetAxis/2);
                if(texture.collision(dude.texture) == true)
                    return 2;
            }
        }
        return 0;
    }
    public boolean hasMoved()
    {
        if(movedOnce == true)
        {
            if(oldX == texture.getX() && oldY == texture.getY())
                return false;
        }
        return true;
    }
}
