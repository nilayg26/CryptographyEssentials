package CRYPTOJAVA;
import java.util.Random;
public class DSS {
    public static void main(String [] args){
        System.out.println("**Global Key Component Generation**");
        generateGlobalKeyComponents();
    }
    public static void generateGlobalKeyComponents(){
            Random keyGen= new Random();
            int check=0;
            while(true){
                check =13+(Math.abs(keyGen.nextInt())%112);
                if(checkPrime(check)){
                    break;
                }
            }
            int p=check;
            System.out.println("Global Prime p is: "+p);
            while(true){
                check=2+(Math.abs(keyGen.nextInt())%p);
                if(checkPrime(check) &&(p-1)%check==0){
                    break;
                }
            }
            int q=check;
            System.out.println("Global Prime q is:"+q);
            int g=0;
            while(true){
                check=3+(Math.abs(keyGen.nextInt())%p);
                g=modPow(check,((p-1)/q) ,p );
                if(g>1){
                    break;
                }
            }
            System.out.println("Global g is:"+g);
            keyPairGeneration(p,q,g);
        }
    public static void keyPairGeneration(int p,int q,int g){
        System.out.println();
        System.out.println("**Key Pair Generation process**");
        Random keyGen= new Random();
        int check=0;
        int x=1+(Math.abs(keyGen.nextInt())%q);
        System.out.println("Private Key x is: "+x);
        int y= modPow(g, x, p);
        System.out.println("Public key y is: "+y);
        while (true) {
            check=1+(Math.abs(keyGen.nextInt())%q);
            if(checkCoprime(check,q)){
                break;
            }
        }
        int k=check;
        System.out.println("Pseudo Random Integer k is: "+k);
        int r= modPow(g, k, p)%q;
        int k_inverse;
        while(true){
         check=11+(Math.abs(keyGen.nextInt())%112);
         k_inverse=modularInverse(k, q);
          if(checkPrime(check)&&(k_inverse*(check+(x*r)))%q!=0) {
             break;
          }
        }
        System.out.println("Modulo Inverse of k is: "+k_inverse);
        int hash_code=check;
        System.out.println("Hash Value H(M) is:"+hash_code);
        int s=(k_inverse*(hash_code+(x*r)))%q;
        System.out.println("Signature Pair {r,s} is: {"+r+","+s+"}");
        verifyingSignature(y,hash_code, q, p,r, s,g);
    }
public static void verifyingSignature(int y,int hash_code,int q,int p,int r, int s,int g){
    if(s==0){
        System.out.println("s cannot be zero, restart the programme");
        return ;
    }
    System.out.println();
    System.out.println("**Verification of the Signature**");
    int w= modularInverse(s, q);
    System.out.println("Variable w is: "+w);
    int u1= (hash_code*w)%q;
    int u2= (r*w)%q;
    System.out.println("Variable u1 is: "+u1);
    System.out.println("Variable u2 is: "+u2);
    int v= (modPow(g, u1, p)*modPow(y, u2, p))%p%q;
    System.out.println("Variable v is: "+v);
    if(v==r){
        System.out.println("Since v = r");
        System.out.println("Hence Verified");
    }
    else{
        System.out.println("Code Issue Found: Not Verified");
    }
}
//Helper Functions:
    public static boolean checkPrime(int check){
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
    public static int modularInverse(int n,int q){
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
    public static boolean checkCoprime(int n1, int n2){
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
    public static int modPow(int base, int exp, int mod) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }
    
}

