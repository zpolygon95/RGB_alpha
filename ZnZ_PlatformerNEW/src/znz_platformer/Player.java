package znz_platformer;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Zach & Zac
 */
public class Player {
    /**
     * The path to the resources file with the player image
     */
    private final String playerImagePath = "/znz_platformer/resources/PLAYER_IMAGE.jpg";//player image path should be equal to "<package>/resources/<filename>.<filetype>" with the images saved in a seperate package called resources
	
    /**
     * The xPosition of the player. Determines its place on the game field
     */
    private double xPosition;
    /**
     * The yPosition of the player. Determines its place on the game field.
     */
    private double yPosition;
    /**
     * the rectangle that the player can move freely in
     */
    public Rectangle moveBounds;
    /**
     * The xVelocity determines how much xPosition will be changed by, in pixels
     */
    private double xVelocity;
    /**
     * The yVelocity determines how much yPosition will be changed by, in pixels
     */
    private double yVelocity;
    /**
     * One of the bounds parameters, height gets the distance from the top of the player to the bottom of the player
     */
    private int height;
    /**
     * One of the bounds parameters, width gets the distance from the left of the player to the right of the player
     */
    private int width;
    /**
     * The actual image of the player. This is defined in the constructor
     */
    private Image playerImage;
    /**
     * The value that determines weather or not the player image is visible
     */
    private Boolean visible;
    /**
     * The value that determines if the player is standing on a platform or not
     */
    private Boolean arial = true;
        
    private Boolean colliding = false;
    /**
     * A value that tells if the left arrow key, or the "a" key is pressed down. Used in smooth keyboard input.
     */
    private Boolean leftIsPressed = false;
    /**
     * A value that tells if the right arrow key, or the "d" key is pressed down. Used in smooth keyboard input.
     */
    private Boolean rightIsPressed = false;
    /**
     * A value that tells if the right arrow key, the "w" key or the space bar is pressed down. Used in smooth keyboard input.
     */
    private Boolean upIsPressed = false;
    /**
     * the count for the amount of stars the player has collected
     */
    private int starsCollected;
    /**
     * Constructor for the player that sets its values to the defaults.
     */
    Ghost ghost;
    public Player() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagePath));
	playerImage = ii.getImage();
	height = playerImage.getHeight(null);
	width = playerImage.getWidth(null);
	xPosition = 250;
	yPosition = 350;
	xVelocity = 0;
	yVelocity = 0;
        starsCollected = 0;
        moveBounds = new Rectangle((int)xPosition - 200, (int)yPosition - 200, 400, 400);
	visible = true;
    }
    /**
     * constructor for the player that sets all its values at default, except for the (x, y) start position
     * @param x Specifies the initial X coordinate
     * @param y Specifies the initial Y coordinate
     */
    public Player(int x, int y) {
	ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagePath));
	playerImage = ii.getImage();
	height = playerImage.getHeight(null);
	width = playerImage.getWidth(null);
	xPosition = x;
	yPosition = y;
	xVelocity = 0;
	yVelocity = 0;
	visible = true;
    }
    
    public void stop() {
        xVelocity = 0;
        yVelocity = 0;
    }
    
    /**
     * Method that changes the xPosition and yPosition based on several variables and equations
     */
    public void move() {
        if (rightIsPressed) {
            right();//changes the xvelocity
        }
        if (leftIsPressed) {
            left();//changes the xvelocity
        }
        if (upIsPressed) {
            jump();//changes the yvelocity
        }
        
//        if (moveBounds.contains(getCenter())) {//checks if the player is within the free-movement bounds
            xPosition += xVelocity;//player moves independantly of the level
            yPosition += yVelocity;//............
//        }
//        else {
//            if (Math.abs(moveBounds.getMaxX() - getCenter().x) < 6 && xVelocity < 0) {//checks if the player is moving away from the boundary
//                xPosition += xVelocity;//moves independantly
//            }
//            if (Math.abs(moveBounds.getMinX() - getCenter().x) < 6 && xVelocity > 0) {//...other bound
//                xPosition += xVelocity;//...
//            }
//            else {
//                for (int i = 0; i < Board.structs.levelDat[Board.level][1]; i++) {//moves the level around the player
//                    Board.structs.platforms[Board.level][i].move(-1 * xVelocity, 0);
//                }
//                for (int i = 0; i < Board.structs.levelDat[Board.level][3]; i++) {//...other bound
//                    Board.structs.enemies[Board.level][i].rePos(-1 * xVelocity, 0);
//                }
//                for (int i = 0; i < Board.structs.levelDat[Board.level][2]; i++) {
//                    Board.structs.stars[Board.level][i].move(-1 * xVelocity, 0);
//                }
//            }
////=============================================================================================================================================            
//            if (Math.abs(moveBounds.getMinY() - getCenter().y) < 6 && yVelocity > 0) {//checks if the player is moving away from the boundary
//                yPosition += yVelocity;//moves independantly
//            }
//            else if (Math.abs(moveBounds.getMaxY() - getCenter().y) < 6 && yVelocity < 0) {//...other bound
//                yPosition += yVelocity;//...
//            }
//            else {
//                for (int i = 0; i < Board.structs.levelDat[Board.level][1]; i++) {//moves the level around the player
//                    Board.structs.platforms[Board.level][i].move(0, -1 * yVelocity);
//                }
//                for (int i = 0; i < Board.structs.levelDat[Board.level][3]; i++) {//...other bounds
//                    Board.structs.enemies[Board.level][i].rePos(0, -1 * yVelocity);
//                }
//                for (int i = 0; i < Board.structs.levelDat[Board.level][2]; i++) {
//                    Board.structs.stars[Board.level][i].move(0, -1 * yVelocity);
//                }
//            }
//        }
        double cameraSpeedx = 1;
        double cameraSpeedy = 1;
        //COUTESY OF JHON MAMISH
        if (Math.abs(getCenter().x - moveBounds.getCenterX()) > 100) {
            cameraSpeedx = Math.abs(getCenter().x - moveBounds.getCenterX())/100;
        }
        /*else if (Math.abs(getCenter().x - moveBounds.getCenterX()) > 200) {
            cameraSpeedx += 2;
        }*/
        //COUTESY OF JHON MAMISH
        if (Math.abs(getCenter().y - moveBounds.getCenterY()) > 100)
            cameraSpeedy = Math.abs(getCenter().y - moveBounds.getCenterY())/100;
            
        /*else if (Math.abs(getCenter().y - moveBounds.getCenterY()) > 200) {
            cameraSpeedy += 2;
        }*/
        
        if (getCenter().x < moveBounds.getCenterX()) {
            moveHorizontal(cameraSpeedx);
            for (int i = 0; i < Board.structs.levelDat[Board.level][1]; i++) {//moves the level around the player
                Board.structs.platforms[Board.level][i].move(cameraSpeedx, 0);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][3]; i++) {//...other bound
                Board.structs.enemies[Board.level][i].rePos(cameraSpeedx, 0);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][2]; i++) {
                Board.structs.stars[Board.level][i].move(cameraSpeedx, 0);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][5]; i++) {
                Board.structs.levelText[Board.level][i].move(cameraSpeedx, 0);
            }
        }
        else if (getCenter().x > moveBounds.getCenterX()) {
            moveHorizontal(-cameraSpeedx);
            for (int i = 0; i < Board.structs.levelDat[Board.level][1]; i++) {//moves the level around the player
                Board.structs.platforms[Board.level][i].move(-cameraSpeedx, 0);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][3]; i++) {//...other bound
                Board.structs.enemies[Board.level][i].rePos(-cameraSpeedx, 0);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][2]; i++) {
                Board.structs.stars[Board.level][i].move(-cameraSpeedx, 0);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][5]; i++) {
                Board.structs.levelText[Board.level][i].move(-cameraSpeedx, 0);
            }
        }
        
        if (getCenter().y < moveBounds.getCenterY()) {
            moveVertical(cameraSpeedy);
            for (int i = 0; i < Board.structs.levelDat[Board.level][1]; i++) {//moves the level around the player
                Board.structs.platforms[Board.level][i].move(0, cameraSpeedy);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][3]; i++) {//...other bound
                Board.structs.enemies[Board.level][i].rePos(0, cameraSpeedy);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][2]; i++) {
                Board.structs.stars[Board.level][i].move(0, cameraSpeedy);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][5]; i++) {
                Board.structs.levelText[Board.level][i].move(0, cameraSpeedy);
            }
        }
        else if (getCenter().y > moveBounds.getCenterY()) {
            moveVertical(-cameraSpeedy);
            for (int i = 0; i < Board.structs.levelDat[Board.level][1]; i++) {//moves the level around the player
                Board.structs.platforms[Board.level][i].move(0, -cameraSpeedy);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][3]; i++) {//...other bound
                Board.structs.enemies[Board.level][i].rePos(0, -cameraSpeedy);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][2]; i++) {
                Board.structs.stars[Board.level][i].move(0, -cameraSpeedy);
            }
            for (int i = 0; i < Board.structs.levelDat[Board.level][5]; i++) {
                Board.structs.levelText[Board.level][i].move(0, -cameraSpeedy);
            }
        }
                
	xVelocity -= xVelocity * 0.1;//friction in the x axis
		
	if (arial) {//checks if the player is not touching a platform
            if (yVelocity < 3.5) {//checks if the player is traveling at the maximum fall speed
                yVelocity += 0.2;//friction in the y axis
            }
        }
	else {//the player is in contant with the platform
            yVelocity = 0;//set movement in the y axis to nothing
        }
    }
    /**
     * Method that gives the effect of jumping by decreasing the yVelocity
     */
    public void jump() {
        if (!arial) {//checks if the player is not contacting a platform
            yVelocity = -6.5;
            arial = true;//ensures that the player is no longer in contact with a platform
            //colliding = false;    <<<depreciated>>>
	}
    }
    /**
     * method called when the player comes in contact with a jump pad
     */
    public void superJump() {
        yVelocity = -10;//jumps super high
        arial = true;//ensures that the player is no longer in contact with a platform
    }
    /**
     * Method that moves the player right by increasing the xVelocity
     */
    public void right() {
	xVelocity += 0.5;
        //colliding = false;    <<<depreiated>>>
        //moveHorizontal(-1);    <<<depreciated>>>
    }
    /**
     * method that moves the player left by decreasing the xVelocity
     */
    public void left() {
	xVelocity -= 0.5;
        //colliding = false;    <<<depreciated>>>
        //moveHorizontal(1);    <<<depreciated>>>
    }
    /**
     * method that is called when the player horizontally collides with a wall
     * @param int direction the side of the wall the player hit: 1 for right, 2 for left
     */
    
    void colliding(boolean colliding) {//    <<<unused>>>
        this.colliding = colliding;
    }
    /**
     * method that is called when the player collides with a platform horizontally
     * @param direction the direction that the collision happened in
     */
    void horizCollide(int direction) {
        xVelocity = 0;//stops the player
        
        if (direction == 1) {//bounces off the wall
            xVelocity -= 2;
        }
        else if (direction == 2) {//in the other direction
            xVelocity += 2;
        }
    }
    /**
     * Method that is called when the player collides with the underside of a platform
     */
    void vertCollide() {
        yVelocity = 0.5;//stops the player
    }
    /**
     * Gives a bouncing effect when landing on platforms; appends the yPosition by the specified amount
     * @param amount the amount to change the yPosition by, in pixels
     */
    public void moveVertical(double amount) {//    <<<unused>>>
        yPosition += amount;
    }
    /**
     * Gives a bouncing effect when colliding horizontally with a platform; appends the yPosition by the specified amount
     * @param amount the amount to change the yPosition by, in pixels
     */
    public void moveHorizontal(double amount) {//    <<<unused>>>
        xPosition += amount;
    }
    /**
     * Returns the X coordinate of the top left corner of the player
     * @return double xPosition
     */
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
    
    public Point getCenter() {
        return new Point((int)getBounds().getCenterX(), (int)getBounds().getCenterY());
    }
    /**
     * tells the player that if it is in the air or not
     * @param Arial - if the player is in the air: it is true; if the player is not in the air: it is false 
     */
    public void setArial(Boolean Arial) {
	this.arial = Arial;
    }
    /**
     * Checks if the player is in the air or not
     * @return arial - true if the player is in the air, false if not
     */
    public Boolean isArial() {
	return arial;
    }
    
    /**
     * moves the player to the specified coordinates
     * @param x - the X coordinate to teleport to
     * @param y - the Y coordinate to teleport to
     */
    public void teleport(Point p) {
        xPosition = p.x;
        yPosition = p.y;
    }
    
    /**
     * Gets the number of stars the player has collected so far
     * @return stars collected
     */
    public int getStars() {
        return starsCollected;
    }
    
    /**
     * Adds one to the number of stars collected. called when the player collects a star
     */
    public void starCollected() {
        starsCollected ++;
    }
    
    public void newLevel() {
        starsCollected = 0;
        xVelocity = yVelocity = 0;
    }
    
    /**
     * Specifies user input, namely when a key is pressed
     * @param e the KeyEvent
     */
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
	
	if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) {
            upIsPressed = true;
	}
	else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightIsPressed = true;
            leftIsPressed = false;
	}
	else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            leftIsPressed = true;
            rightIsPressed = false;
	}
    }
    /**
     * Specifies user input, namely when a key is released
     * @param e the KeyEvent
     */
    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) {
            upIsPressed = false;
        }
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightIsPressed = false;
        }
        else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            leftIsPressed = false;
        }
    }
}			

