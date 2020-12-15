package TileGUI;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles
 * in different parts of
 * the code.
 * <p>
 * You are free to (and encouraged to) create and add your own tiles to this
 * file. This file will
 * be turned in with the rest of your code.
 * <p>
 * Ex:
 * world[x][y] = Tileset.FLOOR;
 * <p>
 * The style checker may crash when you try to style check this file due to
 * use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile AVATAR = new TETile('@',
            Color.white, Color.black, "you");
    public static final TETile WALL = new TETile('#',
            new Color(216, 128, 128), Color.darkGray,
            "wall");
    public static final TETile FLOOR = new TETile('·',
            new Color(128, 192, 128), Color.black,
            "floor");
    public static final TETile NOTHING = new TETile(' ',
            Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"',
            Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈',
            Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀',
            Color.magenta, Color.pink, "flower", "byow/image/3Red.png");
    public static final TETile LOCKED_DOOR = new TETile('▩',
            Color.yellow, DesignerColors.PYRAMID_WALL,
            "locked door", "byow/image/locked_door.png");
    public static final TETile UNLOCKED_DOOR = new TETile('□',
            Color.yellow, DesignerColors.PYRAMID_WALL,
            "unlocked door", "byow/image/unlocked_door.png");
    public static final TETile SAND = new TETile('▒',
            Color.yellow, DesignerColors.PYRAMID_FLOOR, "sand",
            "byow/image/sand.png");
    public static final TETile MOUNTAIN = new TETile('▲',
            Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠',
            Color.green, Color.black, "tree");
    public static final TETile PYRAMID_TILE = new TETile(' ',
            Color.white, DesignerColors.PYRAMID_FLOOR, "pyramid floor");
    public static final TETile PYRAMID_WALL = new TETile(' ',
            Color.white, DesignerColors.PYRAMID_WALL, "pyramid wall",
            "byow/image/wall.png");
    public static final TETile SKY = new TETile('☁',
            Color.white, DesignerColors.SKYBLUE, "sky");
    public static final TETile MUMMY = new TETile('@',
            Color.white, DesignerColors.PYRAMID_FLOOR, "you",
            "byow/image/mummy.png");
    public static final TETile EXPLORER = new TETile('ꆜ',
            DesignerColors.NAVY_BLUE, DesignerColors.PYRAMID_FLOOR,
            "enemy", "byow/image/explorer.png");
}


