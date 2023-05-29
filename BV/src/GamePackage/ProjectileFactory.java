package GamePackage;

import GraphicalPackage.Assets;

public class ProjectileFactory {
    public ProjectileFactory()
    {

    }
    public static Projectile buildProjectile(String args, int x, int y, int xD, int yD)
    {
        switch(args)
        {
            case "Fireball":
                return new Projectile(x, y, 0, 0, 5, 5,
                        (float)2, Assets.getTextureLine(0, 4), Assets.getTextureLine(1,4),
                        Assets.getTextureLine(2,4 ), Assets.getTextureLine(3,4), xD, yD);
            case "Throw":
                return new Projectile(x, y, 0, 0, 5, 4,
                        (float)2, Assets.getTextureLine(0, 6), Assets.getTextureLine(0,6),
                        Assets.getTextureLine(0, 6), Assets.getTextureLine(0, 6), xD, yD);
            case "Slash":

                return new Projectile(x, y, 0, 0, 7, 0, (float)2,
                        Assets.getTextureLine(0, 7), Assets.getTextureLine(1,7),
                        Assets.getTextureLine(2, 7), Assets.getTextureLine(3, 7), xD, yD);
            case "Frostbolt":
                return new Projectile(x, y, 0, 0, 5, 5,
                        (float)2, Assets.getTextureLine(0, 9), Assets.getTextureLine(1,9),
                        Assets.getTextureLine(0,9 ), Assets.getTextureLine(3,9), xD, yD);
        }
        return null;
    }
}
