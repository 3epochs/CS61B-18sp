package byog.Core;
import java.io.Serializable;

public class Position implements Serializable {
    public int x;
    public int y;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object p) {
        if (this == p) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (this.getClass() != p.getClass()) {
            return false;
        }
        Position tmp = (Position) p;
        return (this.x == tmp.x) && (this.y == tmp.y);
    }
}
