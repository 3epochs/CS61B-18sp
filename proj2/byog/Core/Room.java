package byog.Core;

public class Room {
    public int x1;
    public int x2;
    public int y1;
    public int y2;

    public int width;
    public int height;

    public Position center;

    public Room(int x, int y, int width, int height) {
        this.x1 = x;
        this.x2 = x + width;
        this.y1 = y;
        this.y2 = y + height;
        this.center = new Position((int) Math.floor((x1 + x2) / 2),
                (int) Math.floor((y1 + y2) / 2));
    }
}
