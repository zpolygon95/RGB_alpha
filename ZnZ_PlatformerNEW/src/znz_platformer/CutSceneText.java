package znz_platformer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * 
 * @author Zac & Zach
 */
public class CutSceneText {
    private String text;
    private int xPos;
    private int yPos;
    private Font myFont;
    private Color textColor;
    
    public CutSceneText(String text, int x, int y) {
        this.text = text;
        xPos = x;
        yPos = y;
        myFont = new Font("Bradley Hand ITC", Font.PLAIN, 48);
        textColor = Color.white;
    }
    
    public CutSceneText(Font f, String text, int x, int y) {
        this.text = text;
        xPos = x;
        yPos = y;
        myFont = f;
        textColor = Color.white;
    }
    
    public CutSceneText(Color c, Font f, String text, int x, int y) {
        this.text = text;
        xPos = x;
        yPos = y;
        myFont = f;
        textColor = c;
    }
    
    public void paint(Graphics g) {
        g.setColor(textColor);
        g.setFont(myFont);
        g.drawString(text, xPos, yPos);
    }
}