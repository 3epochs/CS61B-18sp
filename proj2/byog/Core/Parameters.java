package byog.Core;

import java.io.Serializable;
import java.util.Random;

public class Parameters implements Serializable {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static long seed = 0;
    public static String filePath = "./saveGame.txt";

    public static Random setGameSeed(String input) {
        seed = 0;
        for (int i = 0; i < input.length(); i += 1) {
            if (Character.isDigit(input.charAt(i))) {
                seed = 73 * seed + Long.parseLong("" + input.charAt(i));
            }
        }
        return new Random(seed);
    }
}
