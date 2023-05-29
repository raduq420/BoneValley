package GamePackage.Spells;

import GamePackage.Fawn;
import GamePackage.GameMain;
import GamePackage.Monsters.Monster;
import GraphicalPackage.Sprite;

public class Timestop extends Spell {
    private Sprite caster;
    private int manaCost;
    private int timer;
    public Timestop(int x, int y, String proj, int damage, int xD, int yD) {
        super(x, y, proj, damage, xD, yD);
        timer = 300;
        GameMain.getInstance().setTimeStopped(true);
    }

    @Override
    public boolean spellCheck() {
        System.out.println(timer);
        if (timer == 0) {
            SpellEffect();
            return true;
        }
        else {
            timer--;
            return false;
        }
    }
    public void SpellEffect()
    {
        GameMain.getInstance().setTimeStopped(false);
    }
}