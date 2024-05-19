//Author Nilay Gupta
/* Sample I/O:
Enter key
HI
Enter Plain Text
BALLOON

Cipher is: IISTVWU
Plain Text is: BALLOON
*/
package GITHUB.CryptographyEssentials.SubsitutionCipherCrytography; //remove this line before running the code
import java.util.Objects;
import java.util.Scanner;
class Vigenere_Encyrption{
    private String key;
    private String Plain_Text;
    private int pt_size;
    private int key_size;
    private String alpha;
    private String [][] matrix;
    Vigenere_Encyrption(String key, String Plain_Text){
        this.key=key.toUpperCase();
        this.Plain_Text=Plain_Text.toUpperCase();
        this.pt_size= Plain_Text.length();
        this.key_size=key.length();
        alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        matrix= new String[26][26];
    }
    private String key_modify(){ //if len of PT is not equal to key, modify
        int count=0;     //the key to match its size.
        if(pt_size>key_size){
            while(true){
                key=key+key.substring(count,count+1);
                count=(count+1)%key_size;
                if(key.length()==Plain_Text.length()){
                    key_size=key.length();
                    break;
                }
            }
        } 
        else if (key_size>pt_size) {
            System.out.println("Error: key size more than plain text size");
            key="";            
        }
        return key;       
    }
    String [][] Matrix(){ //Creating a matrix of 26*26
        int index=0;
        String [] arr_alpha= alpha.split("");
        for(int i=0;i<26;i++){
            for(int j=0; j<26;j++){
                matrix[i][j]=arr_alpha[index];
                index=(index+1)%26;
            }
            index++;
        }
        return matrix;
    }
    String Cipher(){// Forming the cipher and returning it.
        this.key_modify(); //PT form Row, Key from Col of the matrix
        this.Matrix();
        String cipher="";
        String l_plaintext="";
        String l_key="";
        for(int i=0; i<Plain_Text.length();i++){
            l_plaintext=Plain_Text.substring(i, i+1);
            l_key=key.substring(i,i+1);
            cipher= cipher+ matrix[indexOf(l_plaintext)][indexOf(l_key)];
        }
        return cipher;
    }
    private int indexOf(String letter){
        for(int i=0;i<alpha.length();i++){
            if(Objects.equals(alpha.substring(i, i+1),letter)){
                return i;
            }
        }
        return -1;
    }

}
class Vigenere_Decyrption{
    private String key;
    private String Cipher;
    private int cp_size;
    private int key_size;
    private String alpha;
    private String [][] matrix;
    Vigenere_Decyrption(String key, String Cipher){
        this.key=key.toUpperCase();
        this.Cipher=Cipher;
        this.cp_size= Cipher.length();
        this.key_size=key.length();
        alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        matrix= new String[26][26];
    }
    String key_modify(){ //if len of PT is not equal to key, modify
        int count=0;     //the key to match its size.
        if(cp_size>key_size){
            while(true){
                key=key+key.substring(count,count+1);
                count=(count+1)%key_size;
                if(key.length()==Cipher.length()){
                    key_size=key.length();
                    break;
                }
            }
        } 
        else if (key_size>cp_size) {
            System.out.println("Error: key size more than plain text size");
            key="";            
        }
        return key;       
    }
    String [][] Matrix(){ //Creating a matrix of 26*26
        int index=0;
        String [] arr_alpha= alpha.split("");
        for(int i=0;i<26;i++){
            for(int j=0; j<26;j++){
                matrix[i][j]=arr_alpha[index];
                index=(index+1)%26;
            }
            index++;
        }
        return matrix;
    }
    String Plain_Text(){//Forming the cipher and returning it.
        this.key_modify(); //PT form Row, Key from Col of the matrix
        this.Matrix();
        String Plain_Text="";
        String l_cipher="";
        String l_key="";
        int col=0;
        for(int i=0; i<Cipher.length();i++){
            l_cipher=Cipher.substring(i, i+1);
            l_key=key.substring(i,i+1);
            col=indexOf(l_key);
            Plain_Text=Plain_Text+find(col,l_cipher);
        }
        return Plain_Text;
    }
    private int indexOf(String letter){
        for(int i=0;i<alpha.length();i++){
            if(Objects.equals(alpha.substring(i, i+1),letter)){
                return i;
            }
        }
        return -1;
    }
    private String find(int col,String l_cipher){
        for(int i=0;i<25;i++){
            if(Objects.equals(matrix[i][col],l_cipher)){
                return alpha.substring(i,i+1);
            }
        }
        return "";
    }

} 
public class VigenereCipher{
    public static void main(String [] args){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter key");
        String key= sc.nextLine();
        key=key.toUpperCase();
        System.out.println("Enter Plain Text");
        String Plain_Text=sc.nextLine();
        Plain_Text=Plain_Text.toUpperCase();
        Vigenere_Encyrption obj1= new Vigenere_Encyrption(key,Plain_Text);
        System.out.println("Cipher is: "+obj1.Cipher());
        Vigenere_Decyrption dec1= new Vigenere_Decyrption(key, obj1.Cipher());
        System.out.println("Plain Text is: "+dec1.Plain_Text());
    }
    
}