package itstep.learning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    private static StringBuilder number = new StringBuilder();
    private static synchronized void addDigit(int digit) {
        number.append(digit);
        System.out.println("added " + digit + ": " + number.toString());
    }
    public static void main(String[] args) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i <= 9; i++) digits.add(i);

        Collections.shuffle(digits);

        for (int digit : digits) {
            Thread thread = new Thread(() -> addDigit(digit));
            thread.start();
        }
    }
}