package GITHUB.CryptographyEssentials.BonusAlgorithms;
import java.util.Arrays;
import java.util.Scanner;

public class PrimitiveRoots {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;

        for (int i = 1; i < n; i++) {
            int[] all = new int[n - 1];
            boolean isPrimitiveRoot = true;

            for (int j = 0; j < n - 1; j++) {
                all[j] = modPow(i, j + 1, n); 
            }

            if (!check(all)) {
                isPrimitiveRoot = false;
            }

            if (isPrimitiveRoot) {
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
