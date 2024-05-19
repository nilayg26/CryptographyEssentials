// Author: Nilay Gupta
//This code calulates Multiplicative Modulo Inverse 
package GITHUB.CryptographyEssentials.BonusAlgorithms; //remove this line before running the code
import java.util.Scanner;
public class MultiplicativeModuloInverse {
    static int x;
    static int y;
    static int n;
    public static void main(String [] args){
        Scanner sc= new Scanner (System.in);
        System.out.println("(x^(-1*y)) mod n");
        System.out.println("Enter x");
        x=sc.nextInt();
        System.out.println("Enter y");
        y=sc.nextInt();
        System.out.println("Enter n");
        n=sc.nextInt();
        System.out.println("Multiplicative Modulo Inverse of "+x+"^"+y+" mod "+n+" = "+getModuloInverse());
    }
    public static int getModuloInverse(){
        x=memoryEfficientMethod(1, 1);
            int i=0;
            double n1=(double)n;
            double x1=(double)x;
            while(true){
                double f= (double)(1+(i*n1))/(double)x1;
                int f1=(1+(i*n))/x;
                if(f==f1){
                    return f1;
                }
                i++;
            }
        }
    public static int  memoryEfficientMethod(int i,int val){
        if(i==y+1){
         return val%n;
        }
        val= (val*x)%n;
        return memoryEfficientMethod(i+1,val);
     }

}
