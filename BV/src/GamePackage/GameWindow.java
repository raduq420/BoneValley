package GamePackage;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame frame ;
    public KeyHandler keyH = new KeyHandler();
    private String title;
    private int width;
    private int height;
    private Canvas canvas;
    public GameWindow(String wTitle, int wWidth, int wHeight){
        title = wTitle;
        height = wHeight;
        width = wWidth;
        frame = null;
    }
    public void initWindow()
    {
        if(frame != null)
            return;
        else
        {
            frame = new JFrame(title);
            frame.setSize(width,height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            canvas = new Canvas();
            canvas.setSize(new Dimension(width,height));
            canvas.setMaximumSize(new Dimension(width,height));
            canvas.setMinimumSize(new Dimension(width,height));

            frame.add(canvas);
            frame.addKeyListener(keyH);
            frame.pack();
        }
    }

    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }
    public Canvas getCanvas()
    {
        return canvas;
    }
}
