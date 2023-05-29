package GraphicalPackage;

import GamePackage.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private int x,y;
    private float scale;
    private BufferedImage texture;
    public int getHeight()
    {
        return texture.getHeight();
    }
    public int getWidth()
    {
        return texture.getWidth();
    }
    public Sprite(int x,int y, BufferedImage texture, float scale)
    {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.scale = scale;
    }
    public Sprite()
    {
        this.x = 0;
        this.y = 0;
        this.texture = null;
    }
    public void Draw(Graphics g,int pX,int pY)
    {
        int imgWidth = (int) (texture.getWidth() * scale);
        int imgHeight = (int) (texture.getHeight() * scale);
        int xS = this.x - pX + 435;
        int yS = this.y - pY + 350;
        g.drawImage(texture, xS, yS, imgWidth, imgHeight, null);
    }
    public void Draw(Graphics g)
    {
        g.drawImage(texture, x, y, texture.getWidth(), texture.getHeight(), null);
    }
    public void setPos(int xNew, int yNew)
    {
        x = xNew;
        y = yNew;
    }
    public void LoadTexture(BufferedImage text)
    {
        texture = text;
    }

    public float getScale() {
        return scale;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public boolean collision(Sprite z)
    {
        int lowX = this.getX() + (int)(texture.getWidth() * scale);
        int lowY = this.getY() + (int)(texture.getHeight() * scale);
        int zlowX = z.getX() + (int)(z.texture.getWidth() * z.getScale());
        int zlowY = z.getY() + (int)(z.texture.getHeight() * z.getScale());

        if(z.getX() <= lowX && this.getX() <= zlowX)
            if(z.getY() <= lowY && this.getY() <= zlowY)
                return true;

        return false;
    }
    public boolean collisionEntity(Sprite z)
    {
        int y = this.getY() + (int)((texture.getHeight() * scale)*0.8);
        int lowX = this.getX() + (int)(texture.getWidth() * scale);
        int lowY = y + (int)((texture.getHeight() * scale)*0.2);
        int zlowX = z.getX() + (int)(z.texture.getWidth() * z.getScale());
        int zlowY = z.getY() + (int)(z.texture.getHeight() * z.getScale());

        if(lowX >= z.getX() && lowY >= z.getY())
            if(zlowX >= this.getX() && zlowY >= y)
                return true;

        return false;
    }
}
