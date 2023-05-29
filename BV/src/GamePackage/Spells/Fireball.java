package GamePackage.Spells;

import GamePackage.Fawn;
import GamePackage.Monsters.Monster;
import GraphicalPackage.Sprite;

public class Fireball extends Spell {
    private Sprite caster;
    private int manaCost;
    public Fireball(int x, int y, String proj, int damage, int xD, int yD, Sprite caster) {
        super(x, y, proj, damage, xD, yD);
        this.caster = caster;
        manaCost = 25;
    }

    @Override
    public boolean spellCheck() {
        if (proj.hasMoved() == false) {
            proj.blankMove();
            Monster target = proj.collisionCheckRet();
            if (target != null)
                SpellEffect(target);

            return true;
        }
        else {
            proj.Move(caster);
            return false;
        }
    }
    @Override
    public void SpellEffect(Monster target)
    {
        super.SpellEffect(target);
        Fawn.getInstance().damageSurrounding(target, getDamage());
    }
}
