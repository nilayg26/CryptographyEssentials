////Author Nilay Gupta
/*A simple transpose symmetric encyrption process for medium sized text. 
//Since it is a transpose encyrption process it shall be used for words/sentences over 10 characters.
//the code automatically generates a key that follows following rules:
//  1)length of key should be with in 9 digits or at max 9 digits.
//  2)if the key is of n digits, then all the 1 to n
//       digits should be present in any order. eg(n=3) then key 
//       should be permuation of(1,2,3) only.
//  3)'0' not allowed.
//  4)repetition of digits not .
//Sample: 
//Enter sentence: 
//gaurish is a boy            
//Sender's Message: gaurish is a boy
//Cipher message: usr aiyg oias hb
//Message recieved by reciever: gaurish is a boy*/
//CODE:
package GITHUB.CryptographyEssentials.BonusAlgorithms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;
public class TranspositionCipher {
    public static void main(String[] args) {
        Random key_gen= new Random();
        int key_int=0;
        while(true){
            key_int= key_gen.nextInt(100,999999999);
            if(check(key_int)){
                break;
            }
        }
        Scanner sc = new Scanner(System.in);
        String key= String.valueOf(key_int);
        System.out.println("Key is: "+key);
        System.out.println("Enter sentence: ");
        String Plain_Text = sc.nextLine();
        String cipher = encyrpt(key, Plain_Text);
        System.out.println();
        System.out.println("Cipher is: "+cipher);
        System.out.println();
        String text= decyrpt(key, cipher);
        System.out.println();
        System.out.println("D(Cipher) is: "+text);
    }
    public static boolean check(int n){
        int a=n;
        int digit=0;
        while(a!=0){
            digit++;
            a=a/10;
        }
        a=n;
        int arr[]=new int[digit];
        for(int i=0;i<digit;i++){
            arr[i]= a%10;
            if(arr[i]==0){
                return false;
            }
            a=a/10;
        }
        int [] check_arr= new int [digit];
        for(int i=0;i<digit;i++){
            check_arr[i]=i+1;
        }
        int count=0;
        for(int i=0;i<digit;i++){
            if(arr[digit-1-i]==check_arr[i]){
                count++;
            }
        }
        if(count>(digit/4)){
            return false;
        }
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=check_arr[i]){
                return false;
            }
        }
        return true;
    }
    public static String encyrpt(String key, String Plain_Text) {
        ArrayList<ArrayList<String>> map = new ArrayList<>();
        ArrayList<String> row = new ArrayList<>();
        int l1 = key.length();
        int l2 = Plain_Text.length();
        int i = 0;
        String num;
        for (i = 0; i < l1; i++) {
            num = key.substring(i, i + 1);
            row.add(num);
        }
        map.add(row);
        row = new ArrayList<>();
        int j = 0;
        double div = (double) l2 / l1;
        double count = 0.0;
        for (j = 0; j < l2; j++) {
            if (j != 0 && j % l1 == 0) {
                map.add(row);
                row = new ArrayList<>();
                row.add(Plain_Text.substring(j, j + 1));
                count++;
                continue;
            }
            row.add(Plain_Text.substring(j, j + 1));
        }
        if (count < div) {
            map.add(row);
        }
        int num2 = 0;
        int l3 = map.size() - 1;
        String cipher = "";
        for (num2 = 0; num2 < l1; num2++) {
            int index = map.get(0).indexOf(String.valueOf(num2 + 1));
            for (int num3 = 1; num3 <= l3; num3++) {
                if (index <= (map.get(num3).size()) - 1) {
                    cipher = cipher + map.get(num3).get(index);
                    continue;
                }
                break;
            }
        }
        return cipher;
    }

    public static String decyrpt(String key, String cipher) {
        int l1 = key.length();
        int l2 = cipher.length();
        int count = l2 / l1;
        double extra_col = l2-(count*l1);
        String [][] map = new String[count+2][l1];
        if(extra_col==0.0){
            map = new String[count+1][l1];
        }
        String [] row1st= new String [l1];
        int i=0;
        for(i=0;i<l1;i++){
            row1st[i]=key.substring(i,i+1);            
        }
        map[0]=row1st;
        int col=0;
        int j=0;
        int ch=0;
        for(i=1;i<=l1; i++){
            col=indexof(row1st,String.valueOf(i));
            for(j=1;j<=count;j++){
                map[j][col]= cipher.substring(ch,ch+1);
                ch++;
            }
            if(extra_col>col){
                 map[j][col]= cipher.substring(ch,ch+1);
                 ch++;
            }
        }
        String Plain_Text="";
        for(i=1;i<map.length;i++){
            for(j=0;j<l1;j++){
                if(map[i][j]==null){
                    break;
                }
                Plain_Text=Plain_Text+map[i][j];
            }
        }
        return Plain_Text;
    }
    public static Integer indexof(String [] row1st, String val){
        int index=0;
        for(int i=0; i<row1st.length;i++){
            if(Objects.equals((row1st[i]), val)){
                index=i;
                break;
            }
        }
        return index;
    }
}
        