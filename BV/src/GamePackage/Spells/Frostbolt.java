package GamePackage.Spells;

import GamePackage.Monsters.Monster;
import GamePackage.StatusEffect;
import GraphicalPackage.Sprite;

public class Frostbolt extends Spell{
    private Sprite caster;
    private int manaCost;
    public Frostbolt(int x, int y, String proj, int damage, int xD, int yD, Sprite caster) {
        super(x, y, proj, damage, xD, yD);
        this.caster = caster;
        this.manaCost = 5;
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
        if(target.getSpeed() != 0)
        {
            target.setSpeed(target.getSpeed() - 1);
            target.addStatus(new StatusEffect("Freeze"));
        }
    }
}
