import java.util.Scanner;


// Test
public class SumOfMultiples2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        
        int total = 0; // Thing
        
        for (int i = 1; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                total += i;
            }
        } // Other thing
        
        System.out.println(total);
    }
}
