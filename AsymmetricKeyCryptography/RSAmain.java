//Author Nilay Gupta
/*The accuracy and execution time of the code is proven only for Integers 
less than 100. Large Integers require High Computational 
Cost and time to run*/
/*Sample I/O:
 * Enter Plain Text Integer (0<I<100) to be encyrpted: 
    11
    Global prime 1. p= 89
    Global prime 2. q= 3
    Public key e= 57
    Private key d= 105
    Phi(n)= 176
    Cipher Text is: 146
    Plain Text is: 11
 */
package GITHUB.CryptographyEssentials.AsymmetricKeyCryptography;
import java.util.Scanner;
import java.util.Random;
class RSA{
    private int p;
    private int q;
    private int e;
    private int d;
    private int m;
    private int c;
    private Random keyGen;
    public  RSA(int m){
         this.m=m;
         keyGen= new Random();
         while(true){
             p=3+(Math.abs(keyGen.nextInt()%100));
             q=3+(Math.abs(keyGen.nextInt()%100));
             if (checkPrime(p)&& checkPrime(q)&&p!=q) {
                 break;
             }
         }
         int phiN= (p-1)*(q-1);
         while(true){
             e=2+(Math.abs(keyGen.nextInt()%phiN));;
             if(checkCoprime(phiN, e)){
                 break;
             }
         }
         d=modularInverse(e, phiN);
     }
    public int getPhiN(){ //returns value of function phi(n)
         return(p-1)*(q-1);
    }
    public int getPublicKey(){ //returns public key component
         return e;
     }
    public int getPrivateKey(){ //returns private key component
         return d;
     }
    public void getRSA(){ //displays information about RSA
         System.out.println("Global prime 1. p= "+p+"\nGlobal prime 2. q= "+q+"\nPublic key e= "+e+"\nPrivate key d= "+d+"\nPhi(n)= "+getPhiN());
     }
    public int getCipher(){ //returns cipher text
         c = modPow(m, e, p*q);
         return c; 
     }
    public int getPlainText(){ //returns Plain Text after Decyrption
         int pt= modPow(c, d, p*q);
         return(pt); 
     }
    //Helper Functions:
    private int modPow(int base, int exp, int mod) {
         int result = 1;
         for (int i = 0; i < exp; i++) {
             result = (result * base) % mod;
         }
         return result;
     }
    private boolean checkPrime(int check){
         int count =0;
         for(int i=1;i<check;i++){
             if(check%i==0){
                 count++;
             }
         }
         if(count==1){
             return true;
         }
         return false;
     }
    private boolean checkCoprime(int n1, int n2){
         int count =0;
         for(int i=1;i<=Math.min(n1,n2);i++){
             if(n1%i==0 && n2%i==0){
                 count++;
             }
         }
         if(count==1){
             return true;
         }
       
         return false;
     }
    private int modularInverse(int n,int q){
         int i=0;
         double n1=(double)n;
         double q1=(double)q;
         while(true){
             double f= (double)(1+(i*q1))/(double)n1;
             int f1=(1+(i*q))/n;
             if(f==f1){
                 return f1;
             }
             i++;
         }
     }
}
public class RSAmain{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Plain Text Integer (0<I<100) to be encyrpted: ");
        int n= sc.nextInt();
        RSA r= new RSA(n);
        r.getRSA();
        System.out.println();
        System.out.println("Cipher Text is: "+r.getCipher());
        System.out.println("Plain Text is: "+r.getPlainText());
    }
 }
