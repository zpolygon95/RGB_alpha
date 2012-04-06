/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package znz_platformer;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.Timer;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 12010365
 */
public class Enemy {
    private String playerImagePath = "/znz_platformer/resources/ENEMY_IMAGE.jpg";
    private Image playerImage;
    private int height;
    private int width;
    private double originalXpos;
    private double originalYpos;
    private double xPosition;
    private double yPosition;
    private double xVelocity;
    private double yVelocity;
    private Boolean visible;
    private double xInitial;
    private double yInitial;
    public Boolean reachedEnd = false;
    public Boolean test = false;
    Timer timer;
    private double finalX;
    private double finalY;
    private double speed;
    private int stepsMoved;
    public final double SLOW = 0.1;
    public final double MEDIUM = 0.5;
    public final double FAST = 1.0;
    
    public Enemy(int x, int y, double moveX, double moveY, double moveSpeed) {
	ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagePath));
	playerImage = ii.getImage();
	height = playerImage.getHeight(null);
	width = playerImage.getWidth(null);
	xPosition = x;
        originalXpos = x;
	yPosition = y;
        originalYpos = y;
        xInitial = x;
        yInitial = y;
	xVelocity = 0;
	yVelocity = 0;
	visible = true;
        finalX = moveX;
        finalY = moveY;
        speed = moveSpeed;
        stepsMoved = 0;
    }
    
    public void move() {
        if(test == false) {
        stepsMoved++;
        if(reachedEnd == false) {
            if(stepsMoved != finalX) {
                xPosition = xPosition + speed;
            }
            /*if(stepsMoved != finalY) {
                xPosition = xPosition + speed;
            }
            if(stepsMoved == finalY && stepsMoved == finalX) {
                reachedEnd = true;
                xPosition = xPosition - speed;
                yPosition = yPosition - speed;
            }*/
            if(stepsMoved == finalX) {
                reachedEnd = true;
                stepsMoved = 0;
            }
        }
        if(reachedEnd == true) {
            if(stepsMoved != finalX) {
                xPosition = xPosition - speed;
            }
            /*if(stepsMoved != finalY) {
                xPosition = xPosition + speed;
            }
            if(stepsMoved == finalY && stepsMoved == finalX) {
                reachedEnd = true;
                xPosition = xPosition - speed;
                yPosition = yPosition - speed;
            }*/
            if(stepsMoved == finalX) {
                reachedEnd = false;
                stepsMoved = 0;
            }
        }
        }
        /*
        if(reachedEnd == true) {
            if(stepsMoved != finalX) {
                xPosition = xPosition - speed;
            }
            /*if(stepsMoved != finalY) {
                xPosition = xPosition + speed;
            }
            if(stepsMoved == finalY && stepsMoved == finalX) {
                reachedEnd = true;
                xPosition = xPosition - speed;
                yPosition = yPosition - speed;
            }
            if(stepsMoved == finalX) {
                reachedEnd = false;
            }
        }
        */
    }
    
    public void teleport() {
        xPosition = originalXpos;
        yPosition = originalYpos;
        reachedEnd = false;
        stepsMoved = 0;
    }
    
    public double getX() {
	return xPosition;
    }
    /**
     * Returns the Y coordinate of the top left corner of the player
     * @return double yPosition
     */
    public double getY() {
	return yPosition;
    }
    /**
     * returns the player image file, for drawing
     * @return Image playerImage
     */
    public Image getImage() {
	return playerImage;
    }
    /**
     * Sets if the player is visible or not
     * @param Boolean Visible - if true: player is visible; if false: player is not visible
     */
    public void setVisible(Boolean Visible) {
	this.visible = Visible;
    }
    /**
     * Returns if the player is visible or not
     * @return Boolean visible
     */
    public Boolean isVisible() {
	return visible;
    }
    /**
     * Returns the bounds of the player
     * @return Rectangle bounds - the bounds of the player
     */
    public Rectangle getBounds() {
    	return new Rectangle((int)xPosition, (int)yPosition, width, height);
    }
    
    public void paint(Graphics g) {
        if(this.reachedEnd == false) {
            playerImagePath = "/znz_platformer/resources/ENEMY_IMAGE.jpg";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagePath));
            this.playerImage = ii.getImage();
            g.drawImage(this.getImage(), (int)this.getX(), (int)this.getY(), null);
        }
        if(this.reachedEnd == true) {
            playerImagePath = "/znz_platformer/resources/ENEMY_IMAGE_LEFT.jpg";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagePath));
            this.playerImage = ii.getImage();
            g.drawImage(this.getImage(), (int)this.getX(), (int)this.getY(), null);
        }
    }

    void rePos(double dx, double dy) {
        xPosition += dx;
        yPosition += dy;
    }
}
