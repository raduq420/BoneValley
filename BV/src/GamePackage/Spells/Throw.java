package GamePackage.Spells;

import GamePackage.Player;
import GamePackage.Monsters.Thrower;

public class Throw extends Spell {
    private Thrower monsterCaster;
    public Throw(int x, int y, String proj, int damage, int xD, int yD, Thrower caster) {
        super(x, y, proj, damage, xD, yD);
        this.monsterCaster = caster;
    }

    @Override
    public boolean spellCheck() {
        switch(proj.homingMove())
        {
            case 2:
                monsterCaster.setHasThrown();
                SpellEffect(Player.getInstance());
                return true;
            case 1:
                monsterCaster.setHasThrown();
                return true;
            case 0:
                return false;
        }
        return false;
    }
    public void SpellEffect(Player target)
    {
        super.SpellEffect(target);
    }
}
