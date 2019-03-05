package byog.Core;
import byog.SaveDemo.World;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.TileEngine.TERenderer;
import java.util.Random;

public class WorldGenerator {

    private static final int minRoomSize = 3;
    private static final int maxRoomSize = 6;
    private static final int MAP_WIDTH = 80;
    private static final int MAP_HEIGHT = 30;
    private static final int maxRooms = 18;
    private static final int minRooms = 14;
    private static int ptr = 0;
    private static long seed;
    private static Random random;
    public Position player1;
    public Position door;

    public WorldGenerator(long seed) {
        this.seed = seed;
        random = new Random(this.seed);
    }

    public void addMaze(TETile[][] world, TETile t) {
        Room[] rooms = new Room[maxRooms];
        int roomNumber = minRooms + (int) random.nextInt(maxRooms - minRooms + 1);
        for (int r = 0; r < roomNumber; r += 1) {
            int width = minRoomSize + (int) random.nextInt(maxRoomSize - minRoomSize + 1);
            int height = minRoomSize + (int) random.nextInt(maxRoomSize - minRoomSize + 1);
            int x = (int) random.nextInt(MAP_WIDTH - width - 2) + 1;
            int y = (int) random.nextInt(MAP_HEIGHT - height - 3) + 1;
            Room newRoom = new Room(x, y, width, height);

            rooms[ptr] = newRoom;
            for (int i= rooms[ptr].x1; i < rooms[ptr].x2; i += 1) {
                for (int j = rooms[ptr].y1; j < rooms[ptr].y2; j += 1) {
                    world[i][j] = t;
                }
            }

            Position newCenter = newRoom.center;
            if(ptr !=  0) {
                Position prevCenter = rooms[ptr - 1].center;
                Hallway hallway = new Hallway();
                if (random.nextInt(2) == 1) {
                    Hallway.hHallway hh = hallway.new hHallway(prevCenter.x, newCenter.x, prevCenter.y);
                    Hallway.vHallway vh = hallway.new vHallway(prevCenter.x, prevCenter.y, newCenter.y);
                } else {
                    Hallway.hHallway hh = hallway.new hHallway(prevCenter.x, newCenter.x, newCenter.y);
                    Hallway.vHallway vh = hallway.new vHallway(newCenter.x, prevCenter.y, newCenter.y);
                }
                for (int p = 0; p < MAP_WIDTH; p += 1) {
                    for (int q =0; q < MAP_HEIGHT; q += 1) {
                        if (hallway.arr[p][q] == 1) {
                            world[p][q] = t;
                        }
                    }
                }
            }
            ptr += 1;
        }

        // confused
        world[rooms[5].center.x][rooms[5].center.y] = Tileset.PLAYER;
        world[rooms[7].center.x][rooms[7].center.y] = Tileset.LOCKED_DOOR;
        player1 = new Position(rooms[5].center.x, rooms[5].center.y);
        door = new Position(rooms[7].center.x, rooms[7].center.y);

    }

    public void addWall(TETile[][] world, TETile t) {
        int count = 0;
        for (int i =0; i < MAP_WIDTH; i += 1) {
            for (int j = 0; j < MAP_HEIGHT; j += 1) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count += 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = t;
                }
            }
        }

        for (int j =0; j < MAP_HEIGHT; j += 1) {
            for (int i = 0; i < MAP_WIDTH; i += 1) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count += 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = t;
                }
            }
        }

        for (int i = MAP_WIDTH - 1; i >= 0; i -= 1) {
            for (int j = MAP_HEIGHT -1; j >= 0; j -= 1) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count = 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = t;
                }
            }
        }

        for (int j = MAP_HEIGHT - 1; j >= 0; j -= 1) {
            for (int i = MAP_WIDTH -1; i >= 0; i -= 1) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count = 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = t;
                }
            }
        }
    }
}
