package byog.Core;

public class Hallway {
    private static final int MAP_WIDTH = 80;
    private static final int MAP_HEIGHT = 30;
    public static int[][] arr = new int[MAP_WIDTH][MAP_HEIGHT];

    public Hallway() {
        for (int i = 0; i < MAP_WIDTH; i += 1) {
            for (int j =0; j < MAP_HEIGHT; j += 1) {
                arr[i][j] = 0;
            }
        }
    }

    /** class for horizontal hallway. */
    public class hHallway {

        public hHallway(int x1, int x2, int y) {
            for (int x = Math.min(x1, x2); x < Math.max(x1, x2); x += 1) {
                arr[x][y]= 1;
            }
        }
    }

    /** class for vertical hallway. */
    public class vHallway {

        public vHallway(int x, int y1, int y2) {
            for (int y = Math.min(y1, y2); y < Math.max(y1, y2); y += 1) {
                arr[x][y] = 1;
            }
        }
    }

}
