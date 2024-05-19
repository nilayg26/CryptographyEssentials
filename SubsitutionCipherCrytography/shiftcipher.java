//Author Nilay Gupta
//Sample I/O:
/*
Enter word to encyrpt: 
HELLO
Enter Key: 
10
Cipher: ROVVY
Decyrption: HELLO */

package GITHUB.CryptographyEssentials.SubsitutionCipherCrytography; //remove this line before running the code
import java.util.Objects;
import java.util.Scanner;

public class shiftCipher {
    static String alpha = "abcdefghijklmnopqrstuvwxyz";
    static String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String[] arr_alpha = alpha.split("");
    static String[] arr_ALPHA = ALPHA.split("");
    static int count = 0;

    public static void main(String[] args) {
        System.out.println("Enter word to encyrpt: ");
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        System.out.println("Enter Key: ");
        int Key = sc.nextInt();
        System.out.println("Cipher: "+encyrpt(Key, sentence));
        String cipher=encyrpt(Key, sentence);
        System.out.println("Decyrption: "+decyrpt(Key, cipher));

    }

    public static String encyrpt(int Key, String sentence) {
        String cipher = "";
        for (int index = 0; index < sentence.length(); index++) {
            String curr_letter = sentence.substring(index, index + 1);
            if (indexOf(curr_letter) == -1) {
                return "alphabet not found";
            }
                if (count == 0) {
                    cipher = cipher + arr_alpha[(indexOf(curr_letter) + Key) % 26];
                } else if (count == 1) {
                    cipher = cipher + arr_ALPHA[(indexOf(curr_letter) + Key) % 26];
                }  
        }
        return cipher;
    }
    public static String decyrpt(int Key, String cipher) {
        String Plain_text = "";
        for (int index = 0; index < cipher.length(); index++) {
            String curr_letter = cipher.substring(index, index + 1);
            if (indexOf(curr_letter) == -1) {
                return "alphabet not found";
            }
            int index_cipher=indexOf(curr_letter)-Key;
             while(true){
                        if(index_cipher>=0){
                            break;
                        }
                        index_cipher=index_cipher+26;
                    }
                if (count == 0) {
                    Plain_text = Plain_text + arr_alpha[index_cipher % 26];
                } else if (count == 1) {
                    Plain_text = Plain_text + arr_ALPHA[index_cipher % 26];
                }  
        }
        return Plain_text;
    }

    public static int indexOf(String curr_letter) {
        int index = 0;
        count = 0;
        for (String str : arr_alpha) {
            if (Objects.equals(str, curr_letter)) {
                return index;
            }
            index++;
        }
        count++;
        index = 0;
        for (String str : arr_ALPHA) {
            if (Objects.equals(str, curr_letter)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
