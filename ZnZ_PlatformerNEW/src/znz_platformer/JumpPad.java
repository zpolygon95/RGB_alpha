/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package znz_platformer;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Samuel
 */
public class JumpPad extends Platform{
    public JumpPad(double xPos, double yPos, double width, double height) {
        super(xPos, yPos, width, height);
        PFType = JUMP_PAD;
    }
    
    @Override
    public void paint(Graphics g) {
            g.setColor(Color.green);
            g.fillRect(getBounds().x,
                   getBounds().y,
                   getBounds().width,
                   getBounds().height);
        }
}
