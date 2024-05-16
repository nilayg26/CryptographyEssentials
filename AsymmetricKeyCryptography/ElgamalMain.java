package GITHUB.CryptographyEssentials.AsymmetricKeyCryptography;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
class Elgamal{
    private Random keyGen;
    private int q;
    private int Xa;
    private int Ya;
    private int k;
    private int K;
    private int c1;
    private int c2;
    private int a; 
    private int M;
    Elgamal(int plain_text){
        this.M=plain_text;
        keyGen=new Random();
        while(true){
        q=(M+1)+(Math.abs(keyGen.nextInt())%(300));
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
        Ya= modPow(a, Xa, q);
        k=2+(Math.abs(keyGen.nextInt())%q);
    }
    public void getElgamal(){ //displays information about Elgamal
        System.out.println("Global prime q= "+q+"\nPublic key Ya= "+Ya+"\nPrivate key Xa= "+Xa+"\nRandom Integer K= "+k);
    }
    public int [] getKeyComponents(){
        int arr[]={q,a};
        return arr;
    }
    public int getPrivateKey(){ //return public Key pair {Xa, Ya} //Xa: private key, Ya: public key
        return Xa;
    }
    public int getPublicKey(){
        return Ya;
    }
    public int [] getCipher(){ //returns cipher pair
        K=modPow(Ya, k, q);
        c1=modPow(a, k, q);
        c2=(K*M)%q;
        int [] arr= {c1,c2};
        return arr; 
    }
    public int getPlainText(){ //returns decyrpted Plain Text
        int K1=modPow(c1, Xa, q);
        int K1_inverse= modularInverse(K1, q);
        int M1= (c2*K1_inverse)%q;
        return M1; 
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
            if (!check(all)) {
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
 public class ElgamalMain {
        public static void main(String [] args){
            Scanner sc = new Scanner (System.in);
            System.out.println("Enter Plain Text Integer (0<I<100) to be encyrpted: ");
            int n=sc.nextInt();
            Elgamal e1= new Elgamal(n);
            e1.getElgamal();
            System.out.println("Cipher Text is: "+Arrays.toString(e1.getCipher()));
            System.out.println("Plain Text is: "+e1.getPlainText());
        }
}
