//Author Nilay Gupta
//This code calculates Modular Exponentiation with efficient methods implemented by recursion.
/*
 Code Uses three efficient methods to calculate Modular Exponentiation
 1) Memory Efficient method
 2) Successive Squaring method
 3) Fast Modular Exponentiation method
 */
package GITHUB.CryptographyEssentials.BonusAlgorithms; //remove this line before running the code
import java.util.Scanner;
public class ModularExponentiation {
    static int x;
    static int y;
    static int n;
public static void main(String [] args){
    Scanner sc= new Scanner(System.in);
    System.out.println("");
    System.out.println("(x^y)mod(n)");
    System.out.println("Enter x: ");
    x=sc.nextInt();
    System.out.println("Enter y: ");
    y=sc.nextInt();
    System.out.println("Enter n: "); 
    n=sc.nextInt();
    char [] by= Integer.toBinaryString(y).toCharArray();
    int result = fastModularExponentiation(by,by.length-1,1);
    System.out.println(x+"^"+y+" mod "+n+" = "+result);
}
public static int  memoryEfficientMethod(int i,int val){
   if(i==y+1){
    return val;
   }
   val= (val*x)%n;
   return memoryEfficientMethod(i+1, val);
}
public static int  memoryEfficientMethod(int y1,int i,int val){
    if(i==y1+1){
     return val;
    }
    val= (val*x)%n;
    return memoryEfficientMethod(y1,i+1,val);
 }
public static int fastModularExponentiation(char [] by,int i,int pr){
    if(i<0){
        return pr%n;
    }
    int exp=pow(2,by.length-1-i,1); 
    if(by[i]=='1'){
        if(exp%2==0){
            pr=(pr*successiveSquaring(x, exp, 1))%n;
        }
        else{
            pr=(pr*memoryEfficientMethod(exp,1,1))%n;
        }
    }
    return(fastModularExponentiation(by, i-1, pr));
}
public static int successiveSquaring(int x1, int y1,int i){
    if(i>=y1){
        return x1%n;
    }
    x1=(x1*x1)%n;
    i=i*2;
    return successiveSquaring(x1,y1,i);
}
private static int pow(int b,int e,int i){
    if(e==0){
        return i;
    }
    return pow(b,e-1,b*i);
}
}
