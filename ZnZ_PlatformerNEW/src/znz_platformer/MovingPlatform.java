package znz_platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Zach & Zach
 */
public class MovingPlatform extends Platform{
    
    private double xMoveDist;
    private double yMoveDist;
    private double speed;
    
    private double stepsMovedx;
    private double stepsMovedy;
    
    private boolean movingRight;
    private boolean movingUp;
    
    public MovingPlatform(double xPos, double yPos, double width, double height, double xMoveDist, double yMoveDist, double speed) {
        super(xPos, yPos, width, height);
        this.xMoveDist = xMoveDist;
        this.yMoveDist = yMoveDist;
        this.speed = speed;
        movingRight = true;
        movingUp = true;
        PFType = MOVING;
    }
    
    @Override
    public void teleport() {
        super.teleport();
        movingRight = true;
        movingUp = true;
        stepsMovedx = 0;
        stepsMovedy = 0;
    }
    
    @Override
    public void Scroll() {
        if (movingRight) {
            if (stepsMovedx < xMoveDist) {
                super.currentXpos += speed;
                if (super.playerIsOnTop) {
                    Board.player.moveHorizontal(speed);
                }
                stepsMovedx += speed;
            }
            else {
                movingRight = false;
                stepsMovedx = 0;
            }
        }
        else {
            if (stepsMovedx < xMoveDist) {
                super.currentXpos -= speed;
                if (super.playerIsOnTop) {
                    Board.player.moveHorizontal(speed * -1);
                }
                stepsMovedx += speed;
            }
            else  {
                movingRight = true;
                stepsMovedx = 0;
            }
        }
        
        if (movingUp) {
            if (stepsMovedy < yMoveDist) {
                super.currentYpos += speed;
                if (super.playerIsOnTop) {
                    Board.player.moveVertical(speed);
                }
                stepsMovedy += speed;
            }
            else {
                movingUp = false;
                stepsMovedy = 0;
            }
        }
        else {
            if (stepsMovedy < yMoveDist) {
                super.currentYpos -= speed;
                if (super.playerIsOnTop) {
                    Board.player.moveVertical(speed * -1);
                }
                stepsMovedy += speed;
            }
            else  {
                movingUp = true;
                stepsMovedy = 0;
            }
        }
        bounds = new Rectangle((int)super.currentXpos, (int)super.currentYpos, getBounds().width, getBounds().height);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }
}
