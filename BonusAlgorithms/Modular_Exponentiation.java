//Author Nilay Gupta
package GITHUB.CryptographyEssentials.BonusAlgorithms; //remove this line before running the code
public class Modular_Exponentiation {
    static int n=35;
    static int x=6;
    static int y=13;
    static int pr=0;
public static void main(String [] args){
    //to find x^y(mod(n)) 
    //to find (23^20) mod 29
    //change value of static variables(x,y,n) according to your wish.
    System.out.println(memory_efficient_method(2, 1));
}
public static int  memory_efficient_method(int exp,int pr){
    if(exp==2){
    pr= ((int)Math.pow(x, exp-1)%n)*x;
    return memory_efficient_method(exp+1, pr);
    }
    pr=(pr*x)%n;
    if(exp==y){
        return pr;
    }
   return memory_efficient_method(exp+1, pr);
}

}
