package znz_platformer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Zach & Zac
 */
public class Structures {
    Platform[][] platforms = {
        {new Platform(215, -510, 220, 10), new Platform(215, -510, 10, 940), new Platform(425, -510, 10, 260), new Platform(425, -250, 375, 25), new Platform(775, -250, 25, 250), new Platform(425, -25, 375, 25), new Platform(425, -125, 10, 560), new IntangiblePlatform(425, -225, 10, 100), new Platform(215, 425, 220, 10), new Platform(225, 350, 75, 10), new Platform(325, 175, 75, 10), new Platform(325, 250, 75, 10), new MovingPlatform(225, 100, 75, 10, 125, 0, 0.5), new Platform(300, 25, 75, 10), new MovingPlatform(225, -25, 75, 10, 125, 0, 0.75), new MovingPlatform(500, -125, 75, 10, 0, 100, 0.75), new MovingPlatform(250, -250, 50, 10, 0, 125, .5), new IntangiblePlatform(350, -125, 50, 10), new Platform(300, -250, 50, 10), new JumpPad(350, -285, 50, 10), new Platform(225, -400, 75, 10), new Platform(275, -457, 75, 10)},//each of these brackets represents the structures in a level
        {/*the level structures go in here*/},
        {},
        {},
        {}
    };
    
    Star[][] stars = {
        {new Star(700, -200), new Star(300, 0), new Star(228, -430), new Star(360, -150)},//each of these brackets represents the tokens in a particular level
        {/*the star instances go in here*/},
        {},
        {},
        {}
    };
    
    Enemy[][] enemies = {
        {new Enemy(100, 350, 400, 0, 0.5)},//enemies are defined as instances of the enemy class. enemies per level are stored here
        {/*instances of the enemy class go here*/},
        {},
        {},
        {}
    };
    
    int[][] InitialPlayerPos = {
        {400, 400},//represents the starting position of a level
        {/*double x, double y... you know the drill*/},
        {},
        {},
        {}
    };
    
    public CutSceneText[][] cutSceneText = {
        {new CutSceneText("Tester level", 50, 50)},
        {},
        {},
        {},
        {}
    };
    
    public LevelText[][] levelText = {
        {new LevelText(Color.red, new Font("Helvetica", Font.BOLD, 20), "CAUTION!", 500, 0), new LevelText(Color.blue, "use arrow keys to move", 350, 500), new LevelText(Color.orange, new Font("Helvetica", Font.PLAIN, 18), "collect stars to win!", 225, 275)},
        {},
        {},
        {},
        {},
    };
    
    public int[][] levelDat = {
        {0, 22, 4, 1, 1, 3},//misc. level data goes here
        {/*int levelType(TALL or LONG), int numPlatforms, int numStars, int numEnemies, int numCutScenes, int numLevelTexts*/},
        {},
        {},
        {}
    };
    int numLevels = 1;
    
    private static Image PLAYER_IMAGE, STAR_IMAGE, SENTRY_IMAGE, SPIKE_IMAGE;
    private String playerImagePath = "";
    private String starImagePath = "/znz_platformer/resources/STAR_IMAGE.jpg";
    private String sentryImagePath = "";
    private String spikeImagePath = "";
    
    public static Rectangle boardBounds = new Rectangle(0, 0, Board.WIDTH, Board.HEIGHT);
    /*
     * level is defined by the structures(double originalXPos, double originalYPos, double width, double height, double currentXPos, double currentYPos),
     *                     the star(double xPos, double yPos, boolean Collected?)
     *                     the enemies(int enemyType, double originalXPos, double originalYPos, double currentXPos, double currentYPos)
     *                     the playerStartPosition(double originalXPos, double originalYPos)
     *                     misc level data specified above in the levelDat[][] array
     */
    
    public Structures() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagePath));
        PLAYER_IMAGE = ii.getImage();
        ii = new ImageIcon(this.getClass().getResource(starImagePath));
        STAR_IMAGE = ii.getImage();
        ii = new ImageIcon(this.getClass().getResource(sentryImagePath));
        SENTRY_IMAGE = ii.getImage();
        ii = new ImageIcon(this.getClass().getResource(spikeImagePath));
        SPIKE_IMAGE = ii.getImage();
    }
    
    public static Image getPlayerImage() {
        return PLAYER_IMAGE;
    }
    
    public static Image getStarImage() {
        return STAR_IMAGE;
    }
    
    public static Image getSentryImage() {
        return SENTRY_IMAGE;
    }
    
    public static Image getSpikeImage() {
        return SPIKE_IMAGE;
    }
    
    public void paintStructures(Graphics g) {
        try {
            for (int i = 0; i < levelDat[Board.level][1]; i++) {
                platforms[Board.level][i].paint(g);
            }
            
            if (levelDat[Board.level][2] != 0) {
                for (int i = 0; i < levelDat[Board.level][2]; i++) {
                    stars[Board.level][i].paint(g);
                }
            }
        
            if (levelDat[Board.level][3] != 0) {
                for (int i = 0; i < levelDat[Board.level][3]; i++) {
                    enemies[Board.level][i].paint(g);
                }
            }
            
            if (levelDat[Board.level][5] != 0) {
                for (int i = 0; i < levelDat[Board.level][5]; i++) {
                    levelText[Board.level][i].paint(g);
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
            
        }
    }
    
    public void paintCS(Graphics g) {
        if (levelDat[Board.level][4] != 0) {
            for (int i = 0; i < levelDat[Board.level][4]; i++) {
                cutSceneText[Board.level][i].paint(g);
            }
        }
    }
}
