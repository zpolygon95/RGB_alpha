package znz_platformer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Zach & Zac
 */
public class Board extends JPanel{
    /**
     * the instance of the player object
     */
    public static Player player = new Player();
    
    /**
     * the instance of the structures object
     */
    public static Structures structs = new Structures();
    
    /**
     * height of the board
     */
    public int board_height;
    /**
     * width of the board
     */
    public int board_width;
    
    /**
     * the state that the game is in
     */
    public int gameState;
    /**
     * pauses the game when gameState == PAUSE_SCREEN
     */
    public final int PAUSE_SCREEN = 0;
    /**
     * plays the normal game when gameState == GAME_PLAY
     */
    public final int GAME_PLAY = 1;
    /**
     * views that levels cut scenes when gameState == CUTSCENE
     */
    public final int CUTSCENE = 2;
    /**
     * pauses the game when gameState == PAUSE_SCREEN
     */
    public final int END_GAME = 3;
    /**
     * unused / debug
     */
    public String debugString = "";
    /**
     * the current level
     */
    public static int level = 0;
    /**
     * the timer instance
     */
    Timer timer;
    /**
     * the default font
     */
    Font myFont = new Font("Bradley Hand ITC", Font.PLAIN, 48);
    
    Ghost ghostzor = new Ghost(player.getX(), player.getY());
    /**
     * class that checks for keyboard-related events
     */
    private class KeyEventListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {//    <<<unused>>>
        }
        /**
         * checks for specific buttons, then pushes the key event to player
         * @param e the key event
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {//janky pause screen
                if (gameState == PAUSE_SCREEN) {//toggle for the pause screen
                    gameState = GAME_PLAY;
                }
                else {
                    gameState = PAUSE_SCREEN;
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_Q) {//force quit
                if (gameState == PAUSE_SCREEN || gameState == END_GAME) {
                    System.exit(0);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE){//advances past cutscenes
                if (level < 5){
                    gameState = GAME_PLAY;
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_R) {//restart game/level
                if (level == structs.numLevels) {//if on the final level, restart game
                    newLevel(0);
                    timer.scheduleAtFixedRate(new ScheduleTask(), 150, 10);
                }
                else {//otherwise restart the current level
                    newLevel(level);
                }
                if(!ghostzor.isVisible()) {
                player.keyPressed(e);//passes the key event to the player
                }
            }
            player.keyPressed(e);//passes the key event to the player
        }
        /**
         * passes the key release event to the player
         * @param e the key event
         */
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
    /**
     * constructor for the game board
     */
    public Board() {
        addKeyListener(new KeyEventListener());
	setFocusable(true);
	setBackground(Color.white);
	setDoubleBuffered(true);
	setSize(500, 500);
	
	//gameState = GAME_PLAY;
	
	timer = new Timer();
	timer.scheduleAtFixedRate(new ScheduleTask(), 150, 10);
        newLevel(level);
    }
    /**
     * sets arbitrary values
     */
    @Override
    public void addNotify() {
    	super.addNotify();
	board_height = getHeight();
	board_width = getWidth();
    }
    /**
     * paints or passes graphics to everything
     * @param g the graphics argument
     */
    @Override
    public void paint(Graphics g) {
    //basically, this is the MAIN for paint
        super.paint(g);//herp
	switch (gameState) {//paints certain things based on the game mode
            case PAUSE_SCREEN://herp de derp
                setBackground(Color.black);
                g.setColor(Color.white);
               	g.drawString("Paused.\n\nPress escape to continue\n\nor...\n\nPress \"L\" for the level selector\n\nor...\n\nPress \"Q\" to quit",10 ,10 );
		break;
            case GAME_PLAY://passes the structs class paint, paints the player, ect
                setBackground(Color.white);
                if (player.isVisible() == false) {
                    ghostzor.paint(g);
                }
                if(player.isVisible()) {
                    g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), this);
                    //g.drawRect(player.moveBounds.x, player.moveBounds.y, player.moveBounds.width, player.moveBounds.height);
                }
                structs.paintStructures(g);
                g.setColor(Color.red);
                g.setFont(myFont);
                g.drawString(player.getStars() + "/" + structs.levelDat[level][2] + " stars", 1000, 30);
                break;
            case CUTSCENE://self explanitory
                setBackground(Color.black);
                structs.paintCS(g);
                break;
            case END_GAME:
                setBackground(Color.black);
                g.setColor(Color.white);
                g.setFont(myFont);
                g.drawString("It's over! you are worthy of the #winwinwin", 100, 100);
               break;
	}
    }
    /**
     * checks collisions between the player and other objects
     */
    public void checkCollisions() {
        Rectangle p = player.getBounds();//convience variable
        player.setArial(true);//default arial to true
        for(int i = 0; i < structs.levelDat[level][1]; i++) {//checks for collisions in all of the platforms
            Platform r = structs.platforms[level][i];//convience
            if (r.PFType == r.REGULAR) {//checks for regular platforms
                if (Math.abs(r.getMinY() - p.getMaxY()) < 6 && p.getMaxX() > r.getMinX() && p.getMinX() < r.getMaxX()) {
                    player.setArial(false);//bottom of player hits top of platform
                }
                else if (Math.abs(r.getMaxX() - p.getMinX()) < 6 && p.getMaxY() > r.getMinY() && p.getMinY() < r.getMaxY()) {
                    player.horizCollide(2);//right of player hits left of platform
                }
                else if (Math.abs(r.getMinX() - p.getMaxX()) < 6 && p.getMaxY() > r.getMinY() && p.getMinY() < r.getMaxY()) {
                    player.horizCollide(1);//left of player hits right of platfrom
                }
                else if (Math.abs(r.getMaxY() - p.getMinY()) < 6 && p.getMaxX() > r.getMinX() && p.getMinX() < r.getMaxX()) {
                    player.vertCollide();//top of player hits bottom of platform
                }
            }
            else if (r.PFType == r.INTANGIBLE) {//checks for intangible platforms
                //go straight thru dat bitch >:3
            }
            else if (r.PFType == r.JUMP_PAD) {//checks for intangible platforms
                if (p.intersects(r.getBounds())) {//if player hits jump pad
                    player.superJump();//obv
                }
            }
            else if (r.PFType == r.MOVING) {
                if (Math.abs(r.getMinY() - p.getMaxY()) < 6 && p.getMaxX() > r.getMinX() && p.getMinX() < r.getMaxX()) {
                    player.setArial(false);//bottom of player hits top of platform
                    r.playerIsOnTop = true;
                }
                else if (Math.abs(r.getMaxX() - p.getMinX()) < 6 && p.getMaxY() > r.getMinY() && p.getMinY() < r.getMaxY()) {
                    player.horizCollide(2);//right of player hits left of platform
                    r.playerIsOnTop = false;
                }
                else if (Math.abs(r.getMinX() - p.getMaxX()) < 6 && p.getMaxY() > r.getMinY() && p.getMinY() < r.getMaxY()) {
                    player.horizCollide(1);//left of player hits right of platfrom
                    r.playerIsOnTop = false;
                }
                else if (Math.abs(r.getMaxY() - p.getMinY()) < 6 && p.getMaxX() > r.getMinX() && p.getMinX() < r.getMaxX()) {
                    player.vertCollide();//top of player hits bottom of platform
                    r.playerIsOnTop = false;
                }
                else {
                    r.playerIsOnTop = false;
                }
            }
        }
        
        for (int i = 0; i < structs.levelDat[level][2]; i++) {//checks for collisions between the player and stars
            Rectangle star = structs.stars[level][i].getBounds();//convienance
            
            if (!structs.stars[level][i].isCollected()) {//if the star is not collected
                if (star.intersects(p)) {//and player hits star
                    player.starCollected();//stuff to collect the star
                    structs.stars[level][i].collect();
                }
            }
        }
        for (int i = 0; i < structs.levelDat[level][3]; i++) {//checks for collisions between the player and enemies
            Rectangle enemy = structs.enemies[level][i].getBounds();//convienance
            
                if (enemy.intersects(p)) {//zorr code that I havn't figured out
                    
                    player.setVisible(false);
                    player.stop();
                    ghostzor.setVisible(true);
            }
        }
    }
    /**
     * checks if all of the stars have been collected
     */
    public void checkComplete() {
        if (player.getStars() == structs.levelDat[level][2]) {
            newLevel(level + 1);//next level
        }
    }
    /**
     * stars a new level based on an integer input
     * @param l the level to start
     */
    public void newLevel(int l) {
        level = l;
        if (level == structs.numLevels) {//checks if all levels have been completed
            endGame();
        }
        else {//perform new level preperations
            player.teleport(new Point(structs.InitialPlayerPos[level][0], structs.InitialPlayerPos[level][1]));
            for (int i = 0; i < structs.levelDat[level][1]; i++) {
                structs.platforms[level][i].teleport();
            }
            if (structs.levelDat[level][2] > 0) {
                for (int i = 0; i < structs.levelDat[level][2]; i++) {
                    structs.stars[level][i].reset();
                    structs.stars[level][i].teleport();
                }
            }
            if (structs.levelDat[level][3] > 0) {
                for (int i = 0; i < structs.levelDat[level][3]; i++) {
                    structs.enemies[level][i].teleport();
                }
            }
            if (structs.levelDat[level][4] > 0) {
                for (int i = 0; i < structs.levelDat[level][5]; i++) {
                    structs.levelText[level][i].teleport();
                }
            }
            player.newLevel();
            gameState = CUTSCENE;
        }
    }
    /**
     * ends the game
     */
    public void endGame() {
        gameState = END_GAME;
    }
    
    class ScheduleTask extends TimerTask {//class that contains the timers run method
        /**
         * called every time the timer ticks
         */
        @Override
        public void run() {//called every time the timer ticks
            if (gameState == END_GAME) {
                this.cancel();
            }
            if (gameState == GAME_PLAY) {
                player.move();
                checkCollisions();
                checkComplete();
                if(!player.isVisible()) {
                    ghostzor.die();
                    if(ghostzor.hasDied()) {
                        newLevel(level);
                    }
                }
            }
            try {
                for (int i = 0; i < structs.levelDat[level][3]; i++) {//moves all enemies
                    structs.enemies[level][i].move(); 
                }
                for (int i = 0; i < structs.levelDat[level][1]; i++) {
                    structs.platforms[level][i].Scroll();
                }
            }
            catch(ArrayIndexOutOfBoundsException e) {
                
            }
            repaint();
        }
    }
}
