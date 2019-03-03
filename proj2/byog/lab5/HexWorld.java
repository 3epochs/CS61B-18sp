package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private int width;
    private int height;
    private TETile[][] tiles;
    private TERenderer ter;

    public HexWorld(int width, int height) {
        this.width = width;
        this.height = height;
        ter = new TERenderer();
        ter.initialize(width, height);
        tiles = new TETile[width][height];
        for (int i = 0; i < tiles[0].length; i += 1) {
            for (int j = 0; j < tiles.length; j += 1) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }
    }

    private static class Position {
        private int xPos;
        private int yPos;
        public Position(int x, int y) {
            xPos = x;
            yPos = y;
        }
    }

    public static Random  RANDOM = new Random();

    /** helper function that calculate the rowWidth. */
    private static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }


    /** helper function that calculate the offset. */
    private static int hexRowOffset (int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - effectiveI - 1;
        }
        return -effectiveI;
    }

    /** helper function to draw a row to tile. */
    private static void addRow(TETile[][] world,Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi +=1) {
            int xCoord = p.xPos + xi;
            int yCoord = p.yPos;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    public  static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.yPos + yi;
            int xRowStart = p.xPos + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
    }



    /** helper function that generates a rectangle that match the side height. */
    private TETile[][] generateRectangle(int sideHeight) {
        // width = sideHeight + (sideHeight -1) * 2
        int width = sideHeight * 3 - 2;
        int height = sideHeight * 2;
        TETile[][] rectangle = new TETile[width][height];
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                rectangle[i][j] = Tileset.NOTHING;
            }
        }
        return rectangle;
    }


    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(7,hexRowWidth(3, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(-3, hexRowOffset(4, 3));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(0, hexRowOffset(3, 0));
    }



    /** test our addHexagon methods. */
    public static void main(String[] args) {
        HexWorld world = new HexWorld(50, 50);
        TETile[][] tmpTile = world.tiles;
        Position p = new Position(10, 10);
        addHexagon(tmpTile, p, 3,Tileset.FLOWER);
        //world.addHexagon(Tileset.GRASS, 5, 25, 25);
        world.ter.renderFrame(world.tiles);
    }
}
