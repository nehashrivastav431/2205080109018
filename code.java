import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class code {

    private static final int WINDOW_SIZE = 10;
    private static final BlockingQueue<String> numbersQueue = new LinkedBlockingQueue<>(WINDOW_SIZE);

    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 11, 13, 17, 19, 23, 29);

        
        for (Integer number : numbers) {
            processNumber(number);
        }

        
        System.out.println(getCurrentState());
    }

    
    private static void processNumber(Integer number) {
        try {
            String qualifiedId = number + getQualifiedId(number);

            
            if (numbersQueue.size() >= WINDOW_SIZE) {
                numbersQueue.take(); 
            }
            numbersQueue.put(qualifiedId);

            
            double average = calculateAverage();

            
            System.out.println("Number added: " + qualifiedId);
            System.out.println("Current state: " + getCurrentState());
            System.out.println("Average: " + average);
            System.out.println();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    private static double calculateAverage() {
        double sum = 0;
        for (String qualifiedId : numbersQueue) {
            sum += Integer.parseInt(qualifiedId.replaceAll("[^0-9]", ""));
        }
        return sum / numbersQueue.size();
    }

    
    private static String getCurrentState() {
        return numbersQueue.toString();
    }

    
    private static String getQualifiedId(int number) {
        StringBuilder id = new StringBuilder();

        if (isPrime(number)) {
            id.append("P");
        }
        if (isFibonacci(number)) {
            id.append("F");
        }
        if (number % 2 == 0) {
            id.append("E");
        }
        if (!isPrime(number) && !isFibonacci(number) && number % 2 != 0) {
            id.append("R");
        }

        return id.toString();
    }

    
    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }

    
    private static boolean isFibonacci(int number) {
        int x = 5 * number * number;
        return isPerfectSquare(x + 4) || isPerfectSquare(x - 4);
    }

    private static boolean isPerfectSquare(int x) {
        int s = (int) Math.sqrt(x);
        return s * s == x;
}
}