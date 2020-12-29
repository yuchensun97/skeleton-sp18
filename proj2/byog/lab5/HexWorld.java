package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 * class
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static void initWorld(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }
    }

    private static TETile randomTile(int num) {
        switch (num) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    private static boolean canInsert(int x, int y) {
        return x < WIDTH && y < HEIGHT;
    }

    private static TETile fillTile(int I, int s, int left, int num, int j) {
        int begin = left + s - 1 - I;
        if (j >= begin && j < begin + s + 2 * I) {
            return randomTile(num);
        }
        return Tileset.NOTHING;
    }

    //DONE: simplify the code
    //DONE: debugging
    public static void addHexagon(int up, int left, int s, TETile[][] world) {
        int width = s * 3 - 2;
        int height = s * 2;
        int right = left + width - 1;
        int bottom = up + height - 1;
        int tileNum = RANDOM.nextInt(3);
        if (!canInsert(right, bottom)) {
            throw new RuntimeException("The Hexagon is out of bound");
        }
        for (int i = up; i <= bottom ; i++) {
            int I = i - up;
                if (I >= s) {
                    I = bottom - i;
                }
            for (int j = left; j <= right; j++) {
                if (world[j][i] == Tileset.NOTHING) {
                    world[j][i] = fillTile(I, s, left, tileNum, j);
                }
            }
        }
    }

    private static int rtVer(int i) {
        if (i < 3) {
            return 0;
         } else if (i >= 3 && i < 7) {
            return 1;
         } else if (i >= 7 && i < 12) {
            return 2;
         } else if (i >= 12 && i < 16) {
            return 3;
         } else {
            return 4;
         }
    }

    //TODO: debug
    private static int[][] posMat(int x, int y, int s) {
        int[][] pos = new int[19][2];
        pos[0][0] = x;
        pos[0][1] = y;
        int ver = 0;
        int width = 2 * s - 1;
        int height = 2 * s;
        int j = 1;
        int newy = y;
        for (int i = 1; i < 19; i++) {
            ver = rtVer(i);
            pos[i][0] = x + ver * width;
            if (pos[i - 1][0] < pos[i][0]) {
                j = 0;
                if (i < 12) {
                    newy = y + ver * s;
                } else {
                    newy = y + (4 - ver) * s;
                }
            }
            pos[i][1] = newy - j * height;
            j += 1;
        }
        return pos;
    }

    public static void Tesselation(int x, int y, int s, TETile[][] world) {
        int[][] pos = posMat(x, y, s);
        for (int i = 0; i < pos.length; i++) {
            int yPos = pos[i][0];
            int xPos = pos[i][1];
            addHexagon(xPos, yPos, s, world);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexTiles = new TETile[WIDTH][HEIGHT];
        initWorld(hexTiles);
        Tesselation(10, 30, 3, hexTiles);

        ter.renderFrame(hexTiles);
    }
}
