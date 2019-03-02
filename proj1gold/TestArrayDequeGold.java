import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        String message = "\n";

        for (int i = 0; i < 100; i += 1) {
            double rand = StdRandom.uniform();
            if(rand < 0.25) {
                solution.addFirst(i);
                student.addFirst(i);
                message += "addFirst(" + i + ")\n";
            } else if (0.25 <= rand && rand < 0.5) {
                solution.addLast(i);
                student.addLast(i);
                message += "addLast(" + i + ")\n";
            } else if (0.5 <= rand && rand < 0.75) {
                if (!solution.isEmpty()) {
                    Integer expected = solution.removeFirst();
                    Integer actual = student.removeFirst();
                    message += "removeFirst()\n";
                    assertEquals(message, expected, actual);
                }
            } else {
                if (!solution.isEmpty()) {
                    Integer expected = solution.removeLast();
                    Integer actual = student.removeLast();
                    message += "removeLast()\n";
                    assertEquals(message, expected, actual);
                }
            }
            System.out.println(message);
        }
    }
}


