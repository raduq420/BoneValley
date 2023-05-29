package GamePackage;

import GamePackage.Monsters.Monster;
import GamePackage.Monsters.Necro;
import GamePackage.Monsters.Skeleton;
import GamePackage.Monsters.Thrower;
import GraphicalPackage.Assets;

public class MonsterFactory implements Cloneable {
    public MonsterFactory()
    {

    }
    public static Monster buildMonster(String args, int x, int y) {
        switch (args) {
            case "Skeleton":
                return new Skeleton(x, y, 0, 0, 3, 2, (float) 2.5,
                        Assets.getTextureLine(0, 3), Assets.getTextureLine(1, 3),
                        Assets.getTextureLine(2, 3), Assets.getTextureLine(3, 3), 20);
            case "Thrower":
                return new Thrower(x, y, 0, 0, 4, 1, (float) 2.5,
                        Assets.getTextureLine(0, 5), Assets.getTextureLine(1, 5),
                        Assets.getTextureLine(2, 5), Assets.getTextureLine(3, 5), 20);
            case "Necro":
                return new Necro(x, y, 0, 0, 8, 0, (float) 3,
                        Assets.getTextureLine(0, 8), Assets.getTextureLine(1, 8),
                        Assets.getTextureLine(2, 8), Assets.getTextureLine(3, 8), 50);
        }
        return null;
    }
}
