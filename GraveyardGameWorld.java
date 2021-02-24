import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Write a description of class GraveyardGameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GraveyardGameWorld extends World
{

    /**
     * Constructor for objects of class GraveyardGameWorld.
     * 
     */
    public Map<Integer,Tile> tiles = new HashMap<Integer, Tile>();
    static int x,y;
    public Menu menu;
    public Boolean isWorldRendered;

    public GraveyardGameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 800, 1);
        this.isWorldRendered = false;
        registerTiles();
        prepare();
        addEnemies();
        addPlayer();
    }

    public void registerTiles()
    {   
        menu = new Menu();

        tiles.put(-1, null);
        tiles.put(62, new Grass());

        //Grass Forest Path 1
        tiles.put(58, new PathTopLeft());
        tiles.put(59, new PathTopRight());
        tiles.put(85, new PathMiddleLeft());
        tiles.put(86, new PathMiddleRight());
        tiles.put(112, new PathBottomLeft());
        tiles.put(113, new PathBottomMiddle());
        tiles.put(114, new PathBottomRight());

        //Decor
        tiles.put(170, new Rock());
        tiles.put(116, new BushSmall());
        tiles.put(224, new BushLarge());

        tiles.put(122, new BigRockTopLeft());
        tiles.put(123, new BigRockTopRight());
        tiles.put(149, new BigRockBottomLeft());
        tiles.put(150, new BigRockBottomRight());

        tiles.put(91, new RoundTreeTopLeft());
        tiles.put(92, new RoundTreeTopMiddle());
        tiles.put(93, new RoundTreeTopRight());
        tiles.put(118, new RoundTreeMiddleLeft());
        tiles.put(119, new RoundTreeMiddleMiddle());
        tiles.put(120, new RoundTreeMiddleRight());
        tiles.put(145, new RoundTreeBottomLeft());
        tiles.put(146, new RoundTreeBottomMiddle());
        tiles.put(147, new RoundTreeBottomRight());

        tiles.put(199, new SpikedTreeTopLeft());
        tiles.put(200, new SpikedTreeTopMiddle());
        tiles.put(201, new SpikedTreeTopRight());
        tiles.put(226, new SpikedTreeMiddleLeft());
        tiles.put(227, new SpikedTreeMiddleMiddle());
        tiles.put(228, new SpikedTreeMiddleRight());
        tiles.put(253, new SpikedTreeBottomLeft());
        tiles.put(254, new SpikedTreeBottomMiddle());
        tiles.put(255, new SpikedTreeBottomRight());

        tiles.put(126, new LeftBigTreeTop());
        tiles.put(152, new LeftBigTreeMiddleLeft());
        tiles.put(153, new LeftBigTreeMiddleMiddle());
        tiles.put(179, new LeftBigTreeBottomLeft());
        tiles.put(180, new LeftBigTreeBottomMiddle());

        tiles.put(166, new BottomPathTopLeft());
        tiles.put(167, new BottomPathTopRight());
        tiles.put(193, new BottomPathSecondLeft());
        tiles.put(194, new BottomPathSecondMiddle());
        tiles.put(195, new BottomPathSecondMiddleRight());
        tiles.put(220, new BottomPathMiddleLeft());
        tiles.put(221, new BottomPathMiddleMiddle());
        tiles.put(222, new BottomPathMiddleRight());
        tiles.put(247, new BottomPathBottomLeft());
        tiles.put(248, new BottomPathBottomMiddle());
        tiles.put(249, new BottomPathBottomRight());

        tiles.put(236, new BottomBigTreeTopLeft());
        tiles.put(237, new BottomBigTreeTopMiddle());
        tiles.put(238, new BottomBigTreeTopRight());
        tiles.put(263, new BottomBigTreeBottomLeft());
        tiles.put(264, new BottomBigTreeBottomMiddle());
        tiles.put(265, new BottomBigTreeBottomRight());

        tiles.put(128 , new MiddleTreeBunchTopLeft());
        tiles.put(129 , new MiddleTreeBunchTopMiddle());
        tiles.put(130 , new MiddleTreeBunchTopRight());
        tiles.put(155 , new MiddleTreeBunchMiddleLeft());
        tiles.put(156 , new MiddleTreeBunchMiddleMiddle());
        tiles.put(157 , new MiddleTreeBunchMiddleRight());
        tiles.put(182 , new MiddleTreeBunchBottomLeft());
        tiles.put(183 , new MiddleTreeBunchBottomMiddle());
        tiles.put(184 , new MiddleTreeBunchBottomRight());

        tiles.put(132, new RightBigSpikedTreeTopLeft());
        tiles.put(133, new RightBigSpikedTreeTopRight());
        tiles.put(159, new RightBigSpikedTreeMiddleLeft());
        tiles.put(160, new RightBigSpikedTreeMiddleRight());
        tiles.put(186, new RightBigSpikedTreeBottomLeft());
        tiles.put(187, new RightBigSpikedTreeBottomRight());

        tiles.put(74, new TopTreeTopLeft());
        tiles.put(75, new TopTreeTopMiddle());
        tiles.put(76, new TopTreeTopRight());
    }

    /**
     * Internal method that creates and adds a new actor of the specified class into the world.
     *
     * @param cls the class from which the new actor is created 
     * @param xLoc the x-coordinate at which to place the object into the world
     * @param yLoc the y-coordinate at which to place the object into the world 
     */
    private Actor addActor(Class cls, int xLoc, int yLoc)
    {
        if (cls == null) return null;
        Actor actor = null;
        try { 
            actor = (Actor)cls.newInstance();
            if(cls == Grass.class || cls == BushLarge.class || cls == BushSmall.class || cls == Rock.class){
                if(Greenfoot.getRandomNumber(2) == 1) {
                    actor.getImage().mirrorHorizontally();
                    addObject(actor, xLoc, yLoc);
                }
            }
        } catch (Exception e) {}
        if (actor != null) addObject(actor, xLoc, yLoc);
        return actor;
    }

    /**
     * getMap  Reads the mapName given, searches for it's folder in 'maps' 
     * 
     * @param mapName the name of the CSV level files
     * @return mapLayers an Array of layers read via multiple CSV files
     */
    public List<List<List<String>>> getMap(String mapName)
    {
        Tilemap forest = new Tilemap("Forest", 30, 10);

        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\maps\\" + mapName);
            if (Files.exists(path)) {
                File directory= new File(path.toString());
                int fileCount=directory.list().length;
                ArrayList<String> layers = new ArrayList<String>();

                for(int i=1;i<fileCount + 1;i++)
                {
                    String foundLayer = System.getProperty("user.dir") + "\\maps\\"  + mapName + "\\" + mapName + "_Tile Layer " + i + ".csv";
                    layers.add(foundLayer);
                    System.out.println("Adding:" + foundLayer);   

                }
                System.out.println("File Count:" + fileCount);   
                List<List<List<String>>> mapLayers = new ArrayList<>(); 

                for(String i : layers) {
                    System.out.println(i);
                    List<List<String>> map = new ArrayList<>(); 

                    try (BufferedReader br = new BufferedReader(new FileReader(i))) {
                        System.out.println("Locating: " + i);
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split(",");
                            System.out.println("Layer Size: "  + Arrays.asList(values).size());
                            map.add(Arrays.asList(values));
                        }
                    }
                    System.out.println("Value: " + map.getClass().getName());
                    mapLayers.add(map);
                }
                return mapLayers;
            }

        } catch(IOException e) { 
            e.printStackTrace();
        }           
        return null;
    }

    public void prepare()
    {
        if (!isWorldRendered) {
        List<List<List<String>>> map = getMap("Forest");
        System.out.println("Map:" + map.size());

        int x = 0;
        int y = 0;

        try{
            for (List<List<String>> layer : map) {
                y=0;
                for (List<String> row: layer) {
                    x=0; // check x
                    for(String tile: row) {
                        if(Integer.parseInt(tile) > 0 && tiles.containsKey(Integer.parseInt(tile))) { 
                            Class tileToPlace = tiles.get(Integer.parseInt(tile)).getClass();
                            addActor(tileToPlace,x+8,y+8);
                            System.out.println("Placing Tile: " + Integer.parseInt(tile) + "-" + tileToPlace + " - At: " + x + " " + y);   
                        }
                        x+=16;
                    } 
                    y+=16;

                }

            }
            
            this.isWorldRendered = true;
        } catch(NullPointerException e){
            System.out.println("Error: " + e);
            e.printStackTrace();
        }  
    }
    }

    public void addEnemies()
    {
        for(int i =0; i < Greenfoot.getRandomNumber(10) + 5; i++){
            int randomX = Greenfoot.getRandomNumber(getWidth());
            int randomY = Greenfoot.getRandomNumber(getHeight());
            addObject(new Goblin(), randomX, randomY);
            addObject(new ItemTest("potion", 16, 16), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
    }

    public void addPlayer()
    {
        //Player player = new Player(System.getProperty("user.dir") + "/images/Players/female_1/", "spritestrip", ".png", "--", 0, 6, 4, 40, 40);
        PlayerTest player = new PlayerTest();
        addObject(player, 300, 200);
        //player.prepare();

    }
}
