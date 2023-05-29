package GraphicalPackage;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Assets
{
    private static BufferedImage[] caveTextures = new BufferedImage[223];
    private static BufferedImage[][] playerTextures = new BufferedImage[4][4];
    private static BufferedImage doorTexture = ImageLoader.LoadImage("/textures/door.png");
    private static BufferedImage[][] skeleton1 = new BufferedImage[4][2];
    private static BufferedImage[][] fireball = new BufferedImage[4][4];
    private static BufferedImage[] bone = new BufferedImage[4];
    private static BufferedImage[][] skeleton2 = new BufferedImage[4][2];
    private static BufferedImage[][] sword = new BufferedImage[4][1];
    private static BufferedImage[][] necro = new BufferedImage[4][2];
    private static BufferedImage[] frostbolt = new BufferedImage[1];
    public static void Init()
    {


        SpriteSheet caveSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/caves3.png"));
        SpriteSheet caveExtendSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/cavesextend.png"));
        for(int i = 0 ; i < caveTextures.length ; i++)
                caveTextures[i] = caveSheet.crop(i%16,i/16);


        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/player.png"));
        for(int i = 0 ; i < playerTextures.length; i++)
            for(int j = 0 ; j < playerTextures[i].length; j++)
                playerTextures[i][j] = playerSheet.crop(i, j, 24, 38, 8, 10);

        SpriteSheet skelly = new SpriteSheet(ImageLoader.LoadImage("/textures/skel1-D.png"));
        skeleton1[0][0] = skelly.crop(0, 0, 18, 27, 0, 0);
        skeleton1[0][1] = skelly.crop(0,1, 18, 27, 0, 0);
        skelly = new SpriteSheet(ImageLoader.LoadImage("/textures/skel1-U.png"));
        skeleton1[1][0] = skelly.crop(0, 0, 17, 27, 0, 0);
        skeleton1[1][1] = skelly.crop(0,1, 17, 27, 0, 0);
        skelly = new SpriteSheet(ImageLoader.LoadImage("/textures/skel1-L.png"));
        skeleton1[2][0] = skelly.crop(0, 0, 15, 27, 0, 0);
        skeleton1[2][1] = skelly.crop(0,1, 15, 27, 0, 0);
        skelly = new SpriteSheet(ImageLoader.LoadImage("/textures/skel1-R.png"));
        skeleton1[3][0] = skelly.crop(0, 0, 15, 27, 0, 0);
        skeleton1[3][1] = skelly.crop(0,1, 15, 27, 0, 0);

        SpriteSheet fireb = new SpriteSheet(ImageLoader.LoadImage("/textures/fireball.png"));
        fireball[0][0] = fireb.crop(0,0, 17, 19, 0, 0);
        fireball[0][1]= fireb.crop(0,1, 17, 19, 0, 0);
        fireball[0][2] = fireb.crop(0,2, 17, 19, 0, 0);
        fireball[0][3] = fireb.crop(0,3, 17, 19, -1, 0);

        fireb = new SpriteSheet(ImageLoader.LoadImage("/textures/fireball-U.png"));
        fireball[1][0] = fireb.crop(0,0, 17, 19, 0, 0);
        fireball[1][1]= fireb.crop(0,1, 17, 19, 0, 0);
        fireball[1][2] = fireb.crop(0,2, 17, 19, 0, 0);
        fireball[1][3] = fireb.crop(0,3, 17, 19, -1, 0);

        fireb = new SpriteSheet(ImageLoader.LoadImage("/textures/fireball-L.png"));
        fireball[2][0] = fireb.crop(0,0, 19, 17, 0, 0);
        fireball[2][1]= fireb.crop(1,0, 19, 17, 0, 0);
        fireball[2][2] = fireb.crop(2,0, 19, 17, 0, 0);
        fireball[2][3] = fireb.crop(3,0, 19, 17, 0, -1);

        fireb = new SpriteSheet(ImageLoader.LoadImage("/textures/fireball-R.png"));
        fireball[3][0] = fireb.crop(0,0, 19, 17, 0, 0);
        fireball[3][1]= fireb.crop(1,0, 19, 17, 0, 0);
        fireball[3][2] = fireb.crop(2,0, 19, 17, 0, 0);
        fireball[3][3] = fireb.crop(3,0, 19, 17, 0, -1);

        SpriteSheet boneb = new SpriteSheet(ImageLoader.LoadImage("/textures/bone.png"));
        bone[0] = boneb.crop(0, 0, 18, 17);
        bone[1] = boneb.crop(18, 0, 18, 17);
        bone[2] = boneb.crop(36, 0, 15, 17);
        bone[3] = boneb.crop(51, 0, 15, 17);

        SpriteSheet skeletB = new SpriteSheet(ImageLoader.LoadImage("/textures/skeletB-D.png"));
        skeleton2[0][0] = skeletB.crop(0, 0, 18, 29, 0, 0);
        skeleton2[0][1] = skeletB.crop(0, 1, 18, 29, 0, 0);

        skeletB = new SpriteSheet(ImageLoader.LoadImage("/textures/skeletB-U.png"));
        skeleton2[1][0] = skeletB.crop(0, 0, 19, 29, 0, 0);
        skeleton2[1][1] = skeletB.crop(0, 1, 19, 29, 0, 0);

        skeletB = new SpriteSheet(ImageLoader.LoadImage("/textures/skeletB-L.png"));
        skeleton2[2][0] = skeletB.crop(0, 0, 15, 28, 0, 0);
        skeleton2[2][1] = skeletB.crop(0, 1, 15, 28, 1, 0);

        skeletB = new SpriteSheet(ImageLoader.LoadImage("/textures/skeletB-R.png"));
        skeleton2[3][0] = skeletB.crop(0, 0, 15, 28, 0, 0);
        skeleton2[3][1] = skeletB.crop(0, 1, 15, 28, 1, 0);

        sword[0][0] = ImageLoader.LoadImage("/textures/sword-D.png");
        sword[1][0] = ImageLoader.LoadImage("/textures/sword-U.png");
        sword[2][0] = ImageLoader.LoadImage("/textures/sword-L.png");
        sword[3][0] = ImageLoader.LoadImage("/textures/sword-R.png");

        SpriteSheet necroS = new SpriteSheet(ImageLoader.LoadImage("/textures/necro.png"));
        necro[0][0] = necroS.crop(0, 2, 16, 26, 0, 0);
        necro[0][1] = necroS.crop(0, 1, 16, 26, 0, 0);
        necro[1][0] = necroS.crop(0, 0, 16, 26, 0, 0);
        necro[1][1] = necroS.crop(0, 5, 16, 26, 0, 0);
        necro[2][0] = necroS.crop(0, 4, 16, 26, 0, 0);
        necro[2][1] = necroS.crop(0, 3, 16, 26, 0, 0);
        necroS = new SpriteSheet(ImageLoader.LoadImage("/textures/necro2.png"));
        necro[3][0] = necroS.crop(0,0 , 16, 26, 0 ,0);
        necro[3][1] = necroS.crop(0,1 , 16, 26, 0 ,0);

        frostbolt[0] = ImageLoader.LoadImage("/textures/frostBolt.png");

    }
    public static BufferedImage getTexture(int i, int j,int z)
    {
        switch(z)
        {
            case 1:
                return caveTextures[i];
            case 2:
                return playerTextures[i][j];
            case 3:
                return doorTexture;
            case 4:
                return skeleton1[i][j];
            case 5:
                return fireball[i][j];
            case 6:
                return skeleton2[i][j];
            case 7:
                return sword[i][j];
            case 8:
                return necro[i][j];
        }
        return null;
    }
    public static BufferedImage[] getTextureLine(int i,int z)
    {
        switch(z) {
            case 1:
                //return caveTextures[i];
            case 2:
                return playerTextures[i];
            case 3:
                return skeleton1[i];
            case 4:
                return fireball[i];
            case 5:
                return skeleton2[i];
            case 6:
                return bone;
            case 7:
                return sword[i];
            case 8:
                return necro[i];
            case 9:
                return frostbolt;
        }
        return null;
    }
}
