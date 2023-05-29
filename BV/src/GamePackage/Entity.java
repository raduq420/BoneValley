package GamePackage;

import GraphicalPackage.Assets;
import GraphicalPackage.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public Sprite texture;
    public Entity(int x, int y, int i, int j, int z, float scale)
    {
        texture = new Sprite(x,y, Assets.getTexture(i,j,z), scale);
    }
    public Entity()
    {
        texture = null;
    }
    public void LoadTexture(BufferedImage text)
    {
        texture.LoadTexture(text);
    }
    public void Draw(Graphics g)
    {
        Player dude = Player.getInstance();
        texture.Draw(g, dude.texture.getX(), dude.texture.getY());
    }
    public void Draw2(Graphics g)
    {
        texture.Draw(g, texture.getX(), texture.getY());
    }
}
