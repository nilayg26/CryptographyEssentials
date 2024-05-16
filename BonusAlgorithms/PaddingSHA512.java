//Author Nilay Gupta
/*Sample Test Case at the end of code*/
package GITHUB.CryptographyEssentials.BonusAlgorithms; //remove this line before running the code
import java.util.Scanner;
public class PaddingSHA512 {
    public static void main(String [] args){
        System.out.println("Enter message: ");
        Scanner sc=new Scanner (System.in);      
        String msg= sc.next();
        char []m= msg.toCharArray();
        String[]m_bin= new String[m.length];
        for(int i=0;i<m.length;i++){
            m_bin[i]=addZero(Integer.toBinaryString((int)m[i]),8);
        }
        String s="";
        for(String n:m_bin){
            s=s+n;
        }
        int l= s.length();
        int pb=getPaddingBits(l);
        for(int i=0;i<pb;i++){
            if(i==0){
                s=s+"1";
            }
            else{
                s=s+"0";
            }
        }
        String d= addZero(Integer.toBinaryString(l),128);
        s=s+d;
        System.out.println(s);
        System.out.println(s.length()+" bits");
    }
    public static String addZero(String s,int n){
        int size= s.length();
        if(size<n){
            for(int i=0;i<n-size;i++){
                s="0"+s;
            }
        }
        return s;
    }
    public static int getPaddingBits(int l){
        for(int i=0;i<896;i++){
            if((l+i)%1024==896%1024){
                return i;
            }
        }
        return 0;
    }
}
/*
Enter message: 
Let's Code for fun 

Output:
01001100011001010111010000100111011100111000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000
0000000000000000000000000000000000000000000101000
1024 bits*/
