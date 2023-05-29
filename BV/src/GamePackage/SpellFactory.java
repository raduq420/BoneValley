package GamePackage;

import GamePackage.Monsters.Skeleton;
import GamePackage.Monsters.Thrower;
import GamePackage.Spells.*;

import java.sql.Time;

public class SpellFactory {
    public static Spell buildSpell(String args, MovEntity caster, int x, int y, int damage)
    {
        switch(args)
        {
            case "Fireball":
                return new Fireball(caster.texture.getX() + caster.texture.getWidth()/2,
                        caster.texture.getY() + caster.texture.getHeight()/2,
                        args, damage, x, y, caster.texture);
            case "Throw":
                return new Throw(caster.texture.getX() + caster.texture.getWidth()/2,
                        caster.texture.getY() + caster.texture.getHeight()/2,
                        args, damage, 0, 0, (Thrower) caster);
            case "Slash":
                int l = 0, k = 0;
                if(caster.getFacing() == 0)
                {
                    l = caster.texture.getX() + caster.texture.getWidth()/2 - 10;
                    k = caster.texture.getY() - 40;
                }
                if(caster.getFacing() == 1)
                {
                    l = caster.texture.getX() + caster.texture.getWidth()/2 + 10;
                    k = caster.texture.getY() + caster.texture.getHeight();
                }
                if(caster.getFacing() == 2)
                {
                    l = caster.texture.getX() + caster.texture.getWidth() + 20;
                    k = caster.texture.getY() + caster.texture.getHeight() / 2;
                }
                if(caster.getFacing() == 3)
                {
                    l = caster.texture.getX() - 30;
                    k = caster.texture.getY() + caster.texture.getHeight() / 2;
                }
                return new Slash(l, k, args, damage, 0, 0, (Skeleton) caster);
            case "Frostbolt":
                return new Frostbolt(caster.texture.getX() + caster.texture.getWidth()/2,
                        caster.texture.getY() + caster.texture.getHeight()/2,
                        args, damage, x, y, caster.texture);
            case "Timestop":
                return new Timestop(caster.texture.getX() + caster.texture.getWidth()/2,
                        caster.texture.getY() + caster.texture.getHeight()/2,
                        args, damage, x, y);
        }
        return null;
    }
}
