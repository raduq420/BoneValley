package GamePackage;

import GraphicalPackage.Assets;
import GraphicalPackage.Sprite;

public class Door {
    private int x, y;
    public int nxtLevel;
    public int nxtX, nxtY;

    public Door(int x, int y, int nxtLevel, int nxtX, int nxtY) {
        this.x = x;
        this.y = y;
        this.nxtLevel = nxtLevel;
        this.nxtX = nxtX;
        this.nxtY = nxtY;
    }
    public Sprite createDoor()
    {
        return new Sprite(y * 48, x * 48, Assets.getTexture(0,0,3),3);
    }
}
