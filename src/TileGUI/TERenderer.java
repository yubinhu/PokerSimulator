package TileGUI;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Utility class for rendering tiles. You do not need to modify this file. You're welcome
 * to, but be careful. We strongly recommend getting everything else working before
 * messing with this renderer, unless you're trying to do something fancy like
 * allowing scrolling of the screen or tracking the avatar or something similar.
 */
public class TERenderer {
    private int tileSize = 16;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    /**
     * Same functionality as the other initialization method. The only difference is that the xOff
     * and yOff parameters will change where the renderFrame method starts drawing. For example,
     * if you select w = 60, h = 30, xOff = 3, yOff = 4 and then call renderFrame with a
     * TETile[50][25] array, the renderer will leave 3 tiles blank on the left, 7 tiles blank
     * on the right, 4 tiles blank on the bottom, and 1 tile blank on the top.
     *
     * @param w width of the window in tiles
     * @param h height of the window in tiles.
     */
    public void initialize(int w, int h, int xOff, int yOff, int tS) {
        this.width = w;
        this.height = h;
        this.xOffset = xOff;
        this.yOffset = yOff;
        this.tileSize = tS;
        StdDraw.setCanvasSize(width * tileSize, height * tileSize);
        Font font = new Font("Monaco", Font.BOLD, tileSize - 2);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    /**
     * Initializes StdDraw parameters and launches the StdDraw window. w and h are the
     * width and height of the world in number of tiles. If the TETile[][] array that you
     * pass to renderFrame is smaller than this, then extra blank space will be left
     * on the right and top edges of the frame. For example, if you select w = 60 and
     * h = 30, this method will create a 60 tile wide by 30 tile tall window. If
     * you then subsequently call renderFrame with a TETile[50][25] array, it will
     * leave 10 tiles blank on the right side and 5 tiles blank on the top side. If
     * you want to leave extra space on the left or bottom instead, use the other
     * initializatiom method.
     *
     * @param w width of the window in tiles
     * @param h height of the window in tiles.
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0, 16);
    }

    public void initialize(int w, int h, int ts) {
        initialize(w, h, 0, 0, ts);
    }

    /**
     * Takes in a 2d array of TETile objects and renders the 2d array to the screen, starting from
     * xOffset and yOffset.
     * <p>
     * If the array is an NxM array, then the element displayed at positions would be as follows,
     * given in units of tiles.
     * <p>
     * positions   xOffset |xOffset+1|xOffset+2| .... |xOffset+world.length
     * <p>
     * startY+world[0].length   [0][M-1] | [1][M-1] | [2][M-1] | .... | [N-1][M-1]
     * ...    ......  |  ......  |  ......  | .... | ......
     * startY+2    [0][2]  |  [1][2]  |  [2][2]  | .... | [N-1][2]
     * startY+1    [0][1]  |  [1][1]  |  [2][1]  | .... | [N-1][1]
     * startY    [0][0]  |  [1][0]  |  [2][0]  | .... | [N-1][0]
     * <p>
     * By varying xOffset, yOffset, and the size of the screen when initialized, you can leave
     * empty space in different places to leave room for other information, such as a GUI.
     * This method assumes that the xScale and yScale have been set such that the max x
     * value is the width of the screen in tiles, and the max y value is the height of
     * the screen in tiles.
     *
     * @param world the 2D TETile[][] array to render
     */
    public void renderFrame(TETile[][] world) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        StdDraw.clear(new Color(0, 0, 0));
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
        StdDraw.show();
    }


    // a world with provided hints
    public void drawWorldWithPrompt(TETile[][] world, String prompt1, String prompt2,
                                    String prompt3, int w, int h, int level, String name) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        // @source: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String datetime = dtf.format(now);
        StdDraw.clear(new Color(0, 0, 0));
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Arial", Font.PLAIN, (int) ((double) numXTiles / 2)));
        int i = (int) StdDraw.mouseX();
        int j = (int) StdDraw.mouseY();
        if (i < world.length && j < world[0].length) {
            StdDraw.text(w * 0.15, h * 1.05,
                    "Tile: " + world[i][j].description());
        }
        StdDraw.text(w * 0.15, h * 1.15, datetime.substring(0, 8));
        StdDraw.text(w * 0.15, h * 1.1, datetime.substring(9));
        StdDraw.text(w * 0.525, 1.15 * h, prompt1);
        StdDraw.text(w * 0.525, 1.1 * h, prompt2);
        StdDraw.text(w * 0.525, 1.05 * h, prompt3);
        StdDraw.text(w * 0.9, 1.15 * h, "Level: " + level);
        StdDraw.text(w * 0.9, 1.05 * h, name);
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
        StdDraw.show();
    }
}
