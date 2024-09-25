package itstep.learning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int digit : digits) executorService.submit(() -> addDigit(digit));

        executorService.shutdown();
    }
}