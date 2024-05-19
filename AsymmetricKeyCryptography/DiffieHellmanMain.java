//Author Nilay Gupta
//Diffie Hellman Key Exchange
/*The accuracy and execution time of the code is proven only for Integers 
less than 100. Large Integers will require High Computational 
Cost and time to run*/
/*Sample I/O:
 *  Alice and Bob are communicating
    Global prime q= 11
    Primitive Root a= 8
    Public key for Alice Ya= 6
    Private key for Alice Xa= 3
    Public key for Bob Yb= 7
    Private key for Bob Xb= 9
    Secret Key produced by Alice Ka= 2
    Secret Key produced by Bob Kb= 2
    Ka=Kb Hence secret key K=2
 */
package GITHUB.CryptographyEssentials.AsymmetricKeyCryptography; //Remove this line before running the code
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
class DiffieHellman{
    private Random keyGen;
    private int q;
    private int Xa;
    private int Ya;
    private int Xb;
    private int Yb;
    private int Ka;
    private int Kb;
    private int a; 
    private int K;
    DiffieHellman(){
        keyGen=new Random();
        while(true){
        q=(5)+(Math.abs(keyGen.nextInt())%(300));
        if(checkPrime(q)){
             break;
        }
        }
        while(true){
            a=2+(Math.abs(keyGen.nextInt())%q);
            if(checkPrimitiveRoot(a, q)){
                break;
            }
        }
        Xa= 2+(Math.abs(keyGen.nextInt())%q);
        Xb= 2+(Math.abs(keyGen.nextInt())%q);
        Ya= modPow(a, Xa, q);
        Yb= modPow(a, Xb, q);
        Ka= modPow(Yb, Xa, q);
        Kb= modPow(Ya, Xb, q);
        K=(Ka==Kb)?Ka:-1;
    }
    public void getDiffieHellman(){ //displays information about Diffie Hellman Key Exchange
        System.out.println("Global prime q= "+q+"\nPrimitive Root a= "+a+"\nPublic key for Alice Ya= "+Ya+"\nPrivate key for Alice Xa= "+Xa+"\nPublic key for Bob Yb= "+Yb+"\nPrivate key for Bob Xb= "+Xb+"\nSecret Key produced by Alice Ka= "+Ka+"\nSecret Key produced by Bob Kb= "+Kb);
        System.out.println("Ka=Kb Hence secret key K="+K);
    }
    public int getSecertKey(){
        if(K==-1){
            System.out.println("Code Error");
        }
        return K;
    }
    public int getPrivateKeyA(){
        return Xa;
    }
    public int getPrivateKeyB(){ 
        return Xb;
    }
    public int getPublicKeyA(){
        return Ya;
    }
    public int getPublicKeyB(){
        return Yb;
    }
    //Helper Functions:
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
    private boolean checkPrimitiveRoot(int a,int q){
        for (int i = 1; i < q; i++) {
            int[] all = new int[q - 1]; 
            for (int j = 0; j < q - 1; j++) {
                all[j] = modPow(i, j + 1, q);
            }
            if (check(all)) {
                if(i==a){
                    return true;
                }
            }
        }
        return false;
    }
    private int modPow(int base, int exp, int mod) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }
   private  boolean check(int []arr){
        Arrays.sort(arr);
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]){
                return false;
            }
        }
        return true;
    }
}
 public class DiffieHellmanMain {
        public static void main(String [] args){
            Scanner sc = new Scanner (System.in);
            System.out.println();
            System.out.println("Alice and Bob are communicating");
            DiffieHellman d= new DiffieHellman();
            d.getDiffieHellman();
        }
}

