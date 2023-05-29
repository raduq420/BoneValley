package GamePackage;

import GamePackage.Spells.Spell;

import java.awt.*;

public class SpellBook {
    private static final SpellBook singlepawn = new SpellBook();
    private Spell[] book;
    private int spellCnt;

    public static SpellBook getInstance()
    {
        return singlepawn;
    }
    private SpellBook() {
        book = new Spell[100];
        spellCnt = 0;
    }
    public void setSpellCnt(int x)
    {
        spellCnt = x;
    }
    public void castSpell(String args, MovEntity caster)
    {
        int x = 0 , y = 0;
        switch (caster.getFacing())
        {
            case 0:
                x = 0;
                y = -1;
                break;
            case 1:
                x = 0;
                y = 1;
                break;
            case 2:
                x = 1;
                y = 0;
                break;
            case 3:
                x = -1;
                y = 0;
                break;
        }
        switch(args)
        {
            case "Fireball":
                book[spellCnt] = SpellFactory.buildSpell(args, caster, x, y, 20);
                Player.getInstance().mana -= 25;
                Player.getInstance().setSpellCD(4);
                spellCnt++;
                break;
            case "Throw":
                book[spellCnt] = SpellFactory.buildSpell(args, caster, x, y, 5);
                spellCnt++;
                break;
            case "Slash":
                book[spellCnt] = SpellFactory.buildSpell(args, caster, x, y, 10);
                spellCnt++;
                break;
            case "Frostbolt":
                book[spellCnt] = SpellFactory.buildSpell(args, caster, x, y, 10);
                spellCnt++;
                Player.getInstance().mana -= 5;
                Player.getInstance().setSpellCD(2);
                break;
            case "Timestop":
                book[spellCnt] = SpellFactory.buildSpell(args, caster, x, y, 0);
                spellCnt++;
                Player.getInstance().mana -= 40;
        }
    }
    public void drawSpells(Graphics g)
    {
        Player dude = Player.getInstance();
        for(int i = 0 ; i < spellCnt ; i++)
            if(book[i].getSpelltype() != "Timestop")
                book[i].getProj().texture.Draw(g, dude.texture.getX(), dude.texture.getY());
    }
    public void checkSpells()
    {
        for(int i = 0 ; i < spellCnt ; i ++)
        {
            if(GameMain.getInstance().isTimeStopped() == false || book[i].getSpelltype() == "Timestop")
                if(book[i].spellCheck() == true)
                {
                Spell aux = book[spellCnt-1];
                book[spellCnt-1] = book[i];
                book[i] = aux;
                spellCnt--;
                }
        }
    }
}
