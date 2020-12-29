package byog.Core;

import java.util.Random;
import java.util.LinkedList;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class MapGenerator {

    private static class Position {
        private int x; // x postion
        private int y; // y position

        public Position(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    private Position pos; // a class that store the position of each room
    private TETile[][] world;
    private int width; // width of the whole world
    private int height; // height of the whole world
    private LinkedList<Position[]> roomPositions; // the list record the positions of the rooms
    private Random random;

    /**
     * initialize the world
     * @param x width
     * @param y height
     * @param startX the x position of start position
     * @param startY the y position of start position
     */
    public MapGenerator(int x, int y, int startX, int startY) {
        width = x;
        height = y;
        pos = new Position(startX, startY);
        world = new TETile[width][height];
        random = new Random();
    }

    public MapGenerator(int x, int y, int startX, int startY, int seed) {
        width = x;
        height = y;
        pos = new Position(startX, startY);
        world = new TETile[width][height];
        random = new Random(seed);
    }

    /** fill the world with NOTHING */
    private void initWorld() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    /**
     * create a rectangular room surrounded by walls
     * @param leftBottom
     * @param rightUp
     */
    private void createRoom(Position leftBottom, Position rightUp) {
        recordRoom(leftBottom, rightUp);
        int left = leftBottom.x;
        int bottom = leftBottom.y;
        int right = rightUp.x;
        int up = rightUp.y;

        for (int i = left; i <= right; i++) {
            for (int j = bottom; j <= up; j++) {
                if (i == left || i == right || j == bottom || j == up) {
                    world[i][j] = Tileset.WALL;
                } else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    /**
     * record the position of a room
     * @param leftBottom
     * @param rightUp
     */
    private void recordRoom(Position leftBottom, Position rightUp) {
        Position[] roomPos = new Position[]{leftBottom, rightUp};
        roomPositions.addLast(roomPos);
    }

    /**
     * create the lockDoor in the world
     * @param door position of the lock door
     */
    private void createDoor(Position door) {
        world[door.x][door.y] = Tileset.LOCKED_DOOR;
    }

    /**
     * break the wall of a room
     * @param exit position of the broken wall
     */
    private void breakWall(Position exit) {
        world[exit.x][exit.y] = Tileset.FLOOR;
    }

    /**
     * return true if a new room overlap with old rooms, false otherwise.
     * @param leftBottom
     * @param rightUp
     * @return 
     */
    private boolean checkOverlap(Position leftBottom, Position rightUp) {
        int left = leftBottom.x;
        int bottom = leftBottom.y;
        int right = rightUp.x;
        int up = rightUp.y;
        
        for (int i = left; i <= right; i++) {
            for (int j = bottom; j <= up; j++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

}