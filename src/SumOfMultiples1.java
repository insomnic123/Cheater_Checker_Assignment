import java.util.Scanner;

// Testing Comment
public class SumOfMultiples1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        
        int sum = 0; // Does a thing
        
        for (int i = 1; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        } // Does another thing
        
        System.out.println(sum);
    }
}
