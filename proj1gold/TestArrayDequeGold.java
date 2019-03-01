import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    public static Integer expected;
    public static Integer actual;

    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();

/*        for (int i= 0; i < 10; i +=1 ) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                solution.addFirst(i);
                student.addFirst(i);
            } else {
                solution.addLast(i);
                student.addLast(i);
            }
        }

        for (int i = 0; i < 10; i += 1) {
            if (! solution.isEmpty()) {
                double numberBetweenZeroAndOne = StdRandom.uniform();
                if (numberBetweenZeroAndOne < 0.5) {
                    expected = solution.removeFirst();
                    actual = student.removeFirst();
                } else {
                    expected = solution.removeLast();
                    actual = student.removeLast();
                }
                assertEquals(expected + " not equal to " + actual, expected, actual);
            }
        }*/

        for (int i = 0; i < 10; i += 1) {
            double rand = StdRandom.uniform();
            if(rand < 0.25) {
                solution.addFirst(i);
                student.addFirst(i);
            } else if (0.25 <= rand && rand < 0.5) {
                solution.addLast(i);
                student.addLast(i);
            } else if (0.5 <= rand && rand < 0.75) {
                if (!solution.isEmpty()) {
                    expected = solution.removeFirst();
                    actual = student.removeFirst();
                    assertEquals(expected, actual);
                }
            } else {
                if (!solution.isEmpty()) {
                    expected = solution.removeLast();
                    actual = student.removeLast();
                    assertEquals(expected, actual);
                }
            }
        }
    }
/*        for (int i = 0; i < solution.size(); i += 1) {
            assertEquals(solution.get(i), student.get(i));
        }*/
}


