//Author Nilay Gupta
//GCD using recursion
package GITHUB.CryptographyEssentials.BonusAlgorithms; //remove this line before running of code
import java.util.Scanner;
public class GcdEuclidAlgo {
    static int C=0;
    public static void main(String[] args) {
        System.out.println("Enter integer A: ");
        Scanner sc= new Scanner(System.in);
        int A= sc.nextInt();
        System.out.println("Enter integer B: ");
        int B= sc.nextInt();
        System.out.println("GCD is "+GreatestCommonDivisor(A,B));
    }
    public static int GreatestCommonDivisor(int A, int B){
        if(B==0){
            return A;
        }
        C= A%B;
        A=B;
        B=C;
        return(GreatestCommonDivisor(A,B));
    }
}
