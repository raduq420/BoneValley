package GamePackage;

import GamePackage.Monsters.Monster;

public class StatusEffect {
    private String StatusType;
    private int buffer;

    public StatusEffect(String StatusType)
    {
        this.StatusType = StatusType;
        switch (StatusType)
        {
            case "Freeze":
                buffer = 200;
                break;
        }
    }
    public boolean checkBuffer()
    {
        if(buffer > 0 )
        {
            buffer -= 1;
            return false;
        }
        return true;
    }
    public void statusActivate(Monster target)
    {
        target.setSpeed(target.getSpeed() + 1);
    }


}
