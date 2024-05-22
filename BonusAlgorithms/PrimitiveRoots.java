//Author Nilay Gupta
/*Primitive roots are important in various areas of 
mathematics, including cryptography. They are used in certain 
encryption algorithms to create secure communication channels.
For example, 2 is a primitive root of 5 (because 2, 4, 3, and 1 
can all be obtained as powers of 2 modulo 5). However, 12 is not 
a prime number, so it does not necessarily have a primitive root 
(and it doesn't in this case).
 */
package GITHUB.CryptographyEssentials.BonusAlgorithms; //remove this line before running the code
import java.util.Arrays;
import java.util.Scanner;
public class PrimitiveRoots {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number to know its primitive roots");
        int n = sc.nextInt();
        int count = 0;

        for (int i = 1; i < n; i++) {
            int[] all = new int[n - 1];
            for (int j = 0; j < n - 1; j++) {
                all[j] = modPow(i, j + 1, n); 
            }
            if (check(all)) {
                count++;
                System.out.println(i + " is a primitive root of " + n);
            }
        }

        System.out.println("Total primitive roots is: " + count);
    }

    public static boolean check(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int modPow(int base, int exp, int mod) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }
}
