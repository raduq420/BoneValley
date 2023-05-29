package GamePackage.Spells;

import GamePackage.Monsters.Monster;
import GamePackage.Player;
import GamePackage.Projectile;
import GamePackage.ProjectileFactory;

public abstract class Spell {
    private String spelltype;
    protected Projectile proj;
    private int damage;
    public Spell(int x, int y, String proj, int damage, int xD, int yD) {
        this.damage = damage;
        this.spelltype = proj;
        this.proj = ProjectileFactory.buildProjectile(proj, x, y, xD, yD);
    }
    public int getDamage()
    {
        return damage;
    }

    public String getSpelltype() {
        return spelltype;
    }

    public Projectile getProj() {
        return proj;
    }

    abstract public boolean spellCheck();
    protected void SpellEffect(Monster target)
    {
        target.health -= damage;
    }
    protected void SpellEffect(Player target)
    {
        target.health -= damage;
    }

}
