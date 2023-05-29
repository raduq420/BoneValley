package GamePackage;

import GraphicalPackage.Assets;
import GraphicalPackage.Sprite;

import java.awt.image.BufferedImage;

public class MovEntity extends Entity{
    private int speed;
    private float buffer;
    private int facing;
    private int currentPosture;
    private BufferedImage[] front;
    private BufferedImage[] back;
    private BufferedImage[] left;
    private BufferedImage[] right;

    public int getFacing() {
        return facing;
    }
    public int getSpeed()
    {
        return speed;
    }
    public MovEntity(int x, int y, int i, int j, int z, int speed, float scale)
    {
        super(x,y, i, j , z, scale);
        this.speed = speed;
        facing = 0;
        currentPosture = 0;
    }
    public MovEntity(int x, int y,int i, int j, int z,int speed, float scale,
                     BufferedImage[] front, BufferedImage[] back,BufferedImage[] left,BufferedImage[] right)
    {
        super(x,y, i, j , z, scale);
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
        this.speed = speed;
        facing = 0;
        buffer = 0;
        currentPosture = 0;
    }
    public void setSpeed(int s)
    {
        speed = s;
    }
    public void setBuffer(float num)
    {
        buffer = num;
    }
    public boolean collisionCheck()
    {
        int x = texture.getX() / 48;
        int y = texture.getY() / 48;
        for(int i = x - 1 ; i < x + 3; i++)
            for(int j = y - 1; j < y + 3; j++)
            {
                  if(j >= 0 && j < Habitat.spritez.length)
                      if(i >= 0 && i < Habitat.spritez[j].length)
                          if(Habitat.collisionMap[j][i] == 0)
                          {
                            if(texture.collisionEntity(Habitat.spritez[j][i]))
                               return true;
                          }
            }

        Fawn syba = Fawn.getInstance();
        if(syba.checkCollision(this))
            return true;

        Player dude = Player.getInstance();
        if(dude != this && dude.texture.collisionEntity(this.texture))
            return true;

        return false;
    }

    public Sprite collisionCheckRetSprite(Sprite target)
    {
        int x = texture.getX() / 48;
        int y = texture.getY() / 48;
        for(int i = x - 1 ; i < x + 3; i++)
            for(int j = y - 1; j < y + 3; j++)
            {
                if(j >= 0 && j < Habitat.spritez.length)
                    if(i >= 0 && i < Habitat.spritez[j].length)
                        if(Habitat.collisionMap[j][i] == 0)
                        {
                            if(texture.collisionEntity(Habitat.spritez[j][i]))
                                if(target != Habitat.spritez[j][i])
                                    return Habitat.spritez[j][i];
                        }
            }

        Fawn syba = Fawn.getInstance();
        Sprite check = null;
        if(syba.checkCollisionRet(this) != null)
            check = syba.checkCollisionRet(this).texture;
        if(check != null)
            if(check != target)
                return check;

        Player dude = Player.getInstance();
        if(dude != this && dude.texture.collisionEntity(this.texture) && target != dude.texture)
            return dude.texture;

        return null;
    }
    public void MoveWithEx(int x, int y, Sprite target)
    {
        int oldSpeed = speed;
        if(x != 0 && y != 0 && speed > 1)
        {
            speed = (int)(speed * 0.75);
        }
        texture.setX(texture.getX() + x*speed);
        Sprite check = this.collisionCheckRetSprite(target);
        if(check != null)
            if(check != target)
                texture.setX(texture.getX() - x*speed);
        texture.setY(texture.getY() + y*speed);
        check = this.collisionCheckRetSprite(target);
        if(check != null)
            if(check != target)
            texture.setY(texture.getY() - y*speed);
        speed = oldSpeed;
        if(x == 0 && y == 0 )
        {
            switch(facing)
            {
                case 0:
                    texture.LoadTexture(back[0]);
                    break;
                case 1:
                    texture.LoadTexture(front[0]);
                    break;
                case 2:
                    texture.LoadTexture(right[0]);
                    break;
                case 3:
                    texture.LoadTexture(left[0]);
                    break;
            }
            return;
        }
        if(y < 0)
        {
            if(facing == 0)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == back.length) currentPosture = 0;
                    texture.LoadTexture(back[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 0;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(back[currentPosture]);
            }
        }
        else if (y > 0)
        {
            if(facing == 1)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == front.length) currentPosture = 0;
                    texture.LoadTexture(front[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 1;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(front[currentPosture]);
            }
        }
        else if (x > 0)
        {
            if(facing == 2)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == right.length) currentPosture = 0;
                    texture.LoadTexture(right[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 2;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(right[currentPosture]);
            }
        }
        else {
            if(facing == 3)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == right.length) currentPosture = 0;
                    buffer = 0;
                    texture.LoadTexture(left[currentPosture]);
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 3;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(left[currentPosture]);
            }
        }
    }
    public void MoveWithNoCollision(int x, int y)
    {
        texture.setX(texture.getX() + x*speed);
        texture.setY(texture.getY() + y*speed);
        if(x == 0 && y == 0 )
        {
            switch(facing)
            {
                case 0:
                    texture.LoadTexture(back[0]);
                    break;
                case 1:
                    texture.LoadTexture(front[0]);
                    break;
                case 2:
                    texture.LoadTexture(right[0]);
                    break;
                case 3:
                    texture.LoadTexture(left[0]);
                    break;
            }
            return;
        }
        if(y < 0)
        {
            if(facing == 0)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == back.length) currentPosture = 0;
                    texture.LoadTexture(back[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 0;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(back[currentPosture]);
            }
        }
        else if (y > 0)
        {
            if(facing == 1)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == front.length) currentPosture = 0;
                    texture.LoadTexture(front[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 1;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(front[currentPosture]);
            }
        }
        else if (x > 0)
        {
            if(facing == 2)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == right.length) currentPosture = 0;
                    texture.LoadTexture(right[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 2;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(right[currentPosture]);
            }
        }
        else {
            if(facing == 3)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == right.length) currentPosture = 0;
                    buffer = 0;
                    texture.LoadTexture(left[currentPosture]);
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 3;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(left[currentPosture]);
            }
        }
    }
    public void Move(int x, int y)
    {
        int oldSpeed = speed;
        if(x != 0 && y != 0 && speed > 1)
            speed = (int)(speed * 0.75);
        texture.setX(texture.getX() + x*speed);
        if(this.collisionCheck() == true)
            texture.setX(texture.getX() - x*speed);
        texture.setY(texture.getY() + y*speed);
        if(this.collisionCheck() == true)
            texture.setY(texture.getY() - y*speed);
        speed = oldSpeed;
        if(x == 0 && y == 0 )
        {
            switch(facing)
            {
                case 0:
                    texture.LoadTexture(back[0]);
                    break;
                case 1:
                    texture.LoadTexture(front[0]);
                    break;
                case 2:
                    texture.LoadTexture(right[0]);
                    break;
                case 3:
                    texture.LoadTexture(left[0]);
                    break;
            }
            return;
        }
        if(y < 0)
        {
            if(facing == 0)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == back.length) currentPosture = 0;
                    texture.LoadTexture(back[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 0;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(back[currentPosture]);
            }
        }
        else if (y > 0)
        {
            if(facing == 1)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == front.length) currentPosture = 0;
                    texture.LoadTexture(front[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 1;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(front[currentPosture]);
            }
        }
        else if (x > 0)
        {
            if(facing == 2)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == right.length) currentPosture = 0;
                    texture.LoadTexture(right[currentPosture]);
                    buffer = 0;
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 2;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(right[currentPosture]);
            }
        }
        else {
            if(facing == 3)
            {
                if(buffer >= 20)
                {
                    if(currentPosture == right.length) currentPosture = 0;
                    buffer = 0;
                    texture.LoadTexture(left[currentPosture]);
                    currentPosture++;
                }
                else {
                    buffer += speed;
                }
            }
            else {
                facing = 3;
                buffer = 0;
                currentPosture = 0;
                texture.LoadTexture(left[currentPosture]);
            }
        }
    }
}
