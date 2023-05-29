package GamePackage.Spells;

import GamePackage.Monsters.Skeleton;
import GamePackage.Player;

public class Slash extends Spell {
    private Skeleton caster;
    private int buffer = 0;
    private boolean hasHit = false;
    public Slash(int x,int y, String proj, int damage, int xD, int yD, Skeleton caster) {
        super(x, y, proj, damage, xD, yD);

        this.caster = caster;
        this.proj.Move(this.proj.getXD(),this.proj.getYD());
    }

    @Override
    public boolean spellCheck() {
        while(buffer < 50)
        {
            if(proj.texture.collisionEntity(Player.getInstance().texture) == true && hasHit == false)
            {
                SpellEffect(Player.getInstance());
                hasHit = true;
                return false;

            }
            else {
                buffer += 1;
                return false;
            }
        }
        caster.setHasAttacked();
        return true;
    }
}
