package rgb_alpha;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;



/**
 * This class represents a level, containing data structures for all of the Platforms and entities.
 * There are currently two methods that should be called from outside the class: loadLevel() and paintLevel()
 * 
 * @author Polygon
 */
public class Level
{
    /**
     * The data structure containing information about all of the platforms in the currently loaded level
     */
    private ArrayList<Platform> platforms;
    /**
     * The data structure containing information about all of the entities in the currently loaded level
     */
    private ArrayList<Entity> entities;
    /**
     * The player object
     */
    private Player player;
    /**
     * The initial x coordinate of the player
     */
    private int initialPlayerX;
    /**
     * The initial y coordinate of the player
     */
    private int initialPlayerY;
    /**
     * The amount of offset of the camera on the x axis in relation to the absolute coordinate system of all the platforms, entities, and the player.
     * 
     * When this variable is zero, the leftmost column of pixels is the pixels in the "0" column of the absolute coordinate system.
     * 
     * When it is one, the leftmost column shown is the "1" column of the absolute coordinate system, and so on.
     */
    private double CameraOffsetX;
    /**
     * The amount of offset of the camera on the y axis in relation to the absolute coordinate system of all the platforms, entities, and the player.
     * 
     * When this variable is zero, the topmost row of pixels is the pixels in the "0" row of the absolute coordinate system.
     * 
     * When it is one, the topmost row shown is the "1" row of the absolute coordinate system, and so on.
     */
    private double CameraOffsetY;
    /**
     * The width of the screen in pixels: used to determine if the engine should draw certain objects
     */
    private int screenWidth;
    /**
     * The height of the screen in pixels: used to determine if the engine should draw certain objects
     */
    private int screenHeight;
    /**
     * This variable determines how the player completes the currently selected level
     */
    private int LevelType;
    /**
     * An option for the method of completing the level: the player must survive until a certain objective is reached
     */
    private static final int SURVIVE = 0;
    /**
     * An option for the method of completing the level: the player must collect a number of objects in the level
     */
    private static final int COLLECTION = 1;
    /**
     * An option for the method of completing the level: the player must craft a certain sign or object
     */
    private static final int CRAFT_SIGN = 2;
    /**
     * An option for the method of completing the level: the player must reach a certain point in the level
     */
    private static final int GO_TO = 3;
    /**
     * An option for the method of completing the level: adds a timed factor to any other challenge
     */
    private static boolean IS_TIMED = false;
    
    /**
     *
     * Default constructor for the level class: initializes several variables
     */
    public Level()
    {
        platforms = new ArrayList<Platform>();
        entities = new ArrayList<Entity>();
        player = new Player();
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    
    /**
     * Loads a level from a specified text file into memory
     * @param path The file path of the level to be loaded
     */
    public void loadLevel(String path)
    {
        //load the values in the file specified by path into their proper variables
    }
    
    /**
     * Paints the level
     * @param g the Graphics context that the level is to be painted to
     */
    public void paintLevel(Graphics g)
    {
        //paint all the things
    }
}
