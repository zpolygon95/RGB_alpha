package znz_platformer;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Graphics;
/**
 *
 * @author 12010365
 */
public class Ghost {
    private final String playerImagePath = "/znz_platformer/resources/GHOST_IMAGE.jpg";//player image path should be equal to "<package>/resources/<filename>.<filetype>" with the images saved in a seperate package called resources

    private double xPosition;

    private double yPosition;

    private double xVelocity;

    private double yVelocity;

    private int height;

    private int width;
    private double initX;
    private double initY;
    private Image playerImage;

    private Boolean visible;
    
    public Ghost(double x, double  y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagePath));
	playerImage = ii.getImage();
	height = playerImage.getHeight(null);
	width = playerImage.getWidth(null);
        
	xPosition = x;
	yPosition = y;
        initX = x;
        initY = y;
	xVelocity = 0;
	yVelocity = 0;
	visible = false;
    }
    
    public void die() {
        if(yPosition != 765) {
            yPosition += 1;
        }
    }
    
    public boolean hasDied() {
        if(yPosition == 765) {
            this.setVisible(false);
            Board.player.setVisible(true);
            this.xPosition = initX;
            this.yPosition = initY;
            return true;
        }
        else {
            return false;
        }
    }
    
    public Image getImage() {
	return playerImage;
    }
    public void teleport(Point p) {
        xPosition = p.x;
        yPosition = p.y;
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
    public void paint(Graphics g) {
        if(this.visible) {
            g.drawImage(this.getImage(), (int)this.getX(), (int)this.getY(), null);  
        }
    }
}
