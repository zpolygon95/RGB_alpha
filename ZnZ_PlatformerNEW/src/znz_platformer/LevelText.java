package znz_platformer;
    import java.awt.Color;
    import java.awt.Font;
    import java.awt.Graphics;
/**
 *
 * @author Zac & Zach
 */
public class LevelText {
    public static Font DEFAULT = new Font("Bradley Hand ITC", Font.PLAIN, 30);
    
    private String text;
    private double originalXPos;
    private double originalYPos;
    private double currentXPos;
    private double currentYPos;
    private Font myFont;
    private Color textColor;
    
    public LevelText(String text, double x, double y) {
        this.text = text;
        originalXPos = x;
        originalYPos = y;
        currentXPos = x;
        currentYPos = y;
        myFont = DEFAULT;
        textColor = Color.black;
    }
    public LevelText(Font f, String text, double x, double y) {
        this.text = text;
        originalXPos = x;
        originalYPos = y;
        currentXPos = x;
        currentYPos = y;
        myFont = f;
        textColor = Color.black;
    }
    public LevelText(Color c, String text, double x, double y) {
        this.text = text;
        originalXPos = x;
        originalYPos = y;
        currentXPos = x;
        currentYPos = y;
        myFont = DEFAULT;
        textColor = c;
    }
    public LevelText(Color c, Font f, String text, double x, double y) {
        this.text = text;
        originalXPos = x;
        originalYPos = y;
        currentXPos = x;
        currentYPos = y;
        myFont = f;
        textColor = c;
    }
    
    public void move (double dx, double dy) {
        currentXPos += dx;
        currentYPos += dy;
    }
    
    public void teleport () {
        currentXPos = originalXPos;
        currentYPos = originalYPos;
    }
    
    public void paint(Graphics g) {
        g.setFont(myFont);
        g.setColor(textColor);
        g.drawString(text, (int)currentXPos, (int)currentYPos);
    }
}
