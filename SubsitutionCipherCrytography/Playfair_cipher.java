//Author Nilay Gupta
//Sample I/O:
/*
Enter Keyword: 
MONARCHY
Enter word to encyrpt: 
BALLOON         //Enter only one word. Sentences not allowed.
Cipher is: IBSUPMNA
Plain Text is: BALLOON */
package GITHUB.CryptographyEssentials.SubsitutionCipherCrytography;
import java.security.Key;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
class Playfair_Encyrption {
    int pt_size;
    String key;
    String Plain_Text;
    String diag;
    String alpha = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // excluding J
    int[] arr_ele1 = new int[2];
    int[] arr_ele2 = new int[2];

    Playfair_Encyrption(String Plain_Text, String key) {
        this.Plain_Text = Plain_Text.toUpperCase();
        this.key = key.toUpperCase();
        this.pt_size = Plain_Text.length();
    }
    ArrayList<String> diagram;
    String[][] matrix = new String[5][5];

    ArrayList<String> Diagram() {   //Step 1: Creating Diagrams
        int d_space = pt_size % 2;  //Step 2: Modifying the diagrams
        int count = 0;
        if (d_space == 0) {
            diagram = new ArrayList<>(pt_size / 2);
            for (int i = 0; i <= pt_size - 2; i = i + 2) {
                diagram.add(Plain_Text.substring(i, i + 2));
                count++;
            }
            int interate = pt_size / 2;
            for (int i = 0; i < interate; i++) {
                if (Objects.equals(diagram.get(i).substring(0, 1), diagram.get(i).substring(1, 2))) {
                    interate = diagram.size();
                    String store1 = diagram.get(i).substring(1, 2);
                    diagram.set(i, (diagram.get(i).substring(0, 1)) + "X");
                    String store2 = "";
                    for (int j = i + 1; j <= interate; j++) {
                        if (j == interate) {
                            diagram.add(store2 + "X");
                            break;
                        }
                        store2 = diagram.get(j).substring(1, 2);
                        diagram.set(j, store1 + (diagram.get(j).substring(0, 1)));
                        store1 = store2;
                    }
                }

            }
        } else {
            diagram = new ArrayList<>((pt_size / 2) + 1);
            for (int i = 0; i <= pt_size - 3; i = i + 2) {
                diagram.add(Plain_Text.substring(i, i + 2));
                count++;
            }
            diagram.add(Plain_Text.substring(pt_size - 1) + "X");
            int interate = (pt_size / 2) + 1;
            for (int i = 0; i < pt_size / 2; i++) {
                if (Objects.equals(diagram.get(i).substring(0, 1), diagram.get(i).substring(1, 2))) {
                    interate = diagram.size();
                    String store1 = diagram.get(i).substring(1, 2);
                    diagram.set(i, (diagram.get(i).substring(0, 1)) + "X");
                    String store2;
                    for (int j = i + 1; j <= interate; j++) {
                        if (j == interate) {
                            break;
                        }
                        store2 = diagram.get(j).substring(1, 2);
                        diagram.set(j, store1 + (diagram.get(j).substring(0, 1)));
                        store1 = store2;
                    }
                }

            }
        }
        if(Objects.equals(diagram.get(diagram.size()-1),"XX")){
            diagram.remove(diagram.size()-1);
        }
        return diagram;

    }

    String[][] Matrix() { //Step 3: Arranging the given key in the 5*5 matrix (excluding J)
        int count = 0;
        int row = 0;
        int col = 0;
        int count_alpha = 0;
        for (row = 0; row < 5; row++) {
            for (col = 0; col < 5; col++) {
                if (count == key.length()) {
                    if (count_alpha == alpha.length()) {
                        break;
                    }
                    if (isPresent(count_alpha)) {
                        count_alpha++;
                        col--;
                        continue;
                    }
                    matrix[row][col] = alpha.substring(count_alpha, count_alpha + 1);
                    count_alpha++;
                    continue;

                }
                matrix[row][col] = key.substring(count, count + 1);
                count++;
            }
        }
        return matrix;
    }

    boolean isPresent(int index) {
        for (int i = 0; i < key.length(); i++) {
            if (Objects.equals(key.substring(i, i + 1), alpha.substring(index, index + 1))) {
                return true;
            }
        }
        return false;
    }
    String Cipher() { 
        this.Diagram();
        this.Matrix();
        for (int i = 0; i < diagram.size(); i++) {
            String ele1 = diagram.get(i).substring(0, 1);
            String ele2 = diagram.get(i).substring(1, 2);
             if (checkSame(ele1, ele2) == 0) {
                diagram.set(i, matrix[arr_ele1[0]][(arr_ele1[1] + 1) % 5] + matrix[arr_ele2[0]][(arr_ele2[1] + 1) % 5]);
                continue;
            }
            if (checkSame(ele1, ele2) == 1) {
                diagram.set(i, matrix[(arr_ele1[0] + 1) % 5][arr_ele1[1]] + matrix[(arr_ele2[0] + 1) % 5][arr_ele2[1]]);
                continue;
            }
            //Step 6: forming a rectangle to interchange elements and returning the cipher.
            diagram.set(i, matrix[arr_ele1[0]][arr_ele2[1]] + matrix[arr_ele2[0]][arr_ele1[1]]);

        }
        String cipher = "";
        for (int i = 0; i < diagram.size(); i++) {
            cipher = cipher + diagram.get(i);
        }
        return cipher;
    }

    int checkSame(String ele1, String ele2) {     //Step 4 & 5:Checking if diagrams are in the 
        for (int i1 = 0; i1 < 5; i1++)/* row */ { //same row or col.
            for (int j1 = 0; j1 < 5; j1++)/* col */ {
                if (Objects.equals(ele1, matrix[i1][j1])) {
                    arr_ele1[0] = i1;
                    arr_ele1[1] = j1;
                }
                if (Objects.equals(ele2, matrix[i1][j1])) {
                    arr_ele2[0] = i1;
                    arr_ele2[1] = j1;
                }
            }
        }
        if (arr_ele1[0] == arr_ele2[0]) {// same row
            return 0;
        }
        if (arr_ele1[1] == arr_ele2[1]) { // same col
            return 1;
        }
        return -1;
    }
}
class Playfair_Decyrption {
    int cp_size;
    String key;
    String Cipher;
    String diag;
    String alpha = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // excluding J
    int[] arr_ele1 = new int[2];
    int[] arr_ele2 = new int[2];
    ArrayList<String> diagram;
    Playfair_Decyrption(String Cipher, String key) {
        this.Cipher = Cipher;
        this.key = key.toUpperCase();
        this.cp_size = Cipher.length();
    }
    String[][] matrix = new String[5][5];
    String[][] Matrix() { // excluding J
        int count = 0;
        int row = 0;
        int col = 0;
        int count_alpha = 0;
        for (row = 0; row < 5; row++) {
            for (col = 0; col < 5; col++) {
                if (count == key.length()) {
                    if (count_alpha == alpha.length()) {
                        break;
                    }
                    if (isPresent(count_alpha)) {
                        count_alpha++;
                        col--;
                        continue;
                    }
                    matrix[row][col] = alpha.substring(count_alpha, count_alpha + 1);
                    count_alpha++;
                    continue;

                }
                matrix[row][col] = key.substring(count, count + 1);
                count++;
            }
        }
        return matrix;
    }

    boolean isPresent(int index) {
        for (int i = 0; i < key.length(); i++) {
            if (Objects.equals(key.substring(i, i + 1), alpha.substring(index, index + 1))) {
                return true;
            }
        }
        return false;
    }
    String Plain_Text() {
        this.Matrix();
        diagram=new ArrayList<>(Cipher.length());
        int count1=0;
        for(int i=0;i+2<=Cipher.length();i=i+2){
            diagram.add(Cipher.substring(i,i+2));
        }
        for (int i = 0; i < diagram.size(); i++) {
            String ele1 = diagram.get(i).substring(0, 1);
            String ele2 = diagram.get(i).substring(1, 2);
             if (checkSame(ele1, ele2) == 0) {
                diagram.set(i, matrix[arr_ele1[0]][(arr_ele1[1]+4) % 5] + matrix[arr_ele2[0]][(arr_ele2[1] + 4) % 5]);
                continue;
            }
            if (checkSame(ele1, ele2) == 1) {
                diagram.set(i, matrix[(arr_ele1[0]+4) % 5][arr_ele1[1]] + matrix[(arr_ele2[0] +4) % 5][arr_ele2[1]]);
                continue;
            }
            diagram.set(i, matrix[arr_ele1[0]][arr_ele2[1]] + matrix[arr_ele2[0]][arr_ele1[1]]);

        }
        String plain_text = "";
        for (int i = 0; i < diagram.size(); i++) {
            if(Objects.equals(diagram.get(i).substring(0,1),"X")){
                plain_text = plain_text + diagram.get(i).substring(1,2);
                continue;
            }
            if(Objects.equals(diagram.get(i).substring(1,2),"X")){
                plain_text = plain_text + diagram.get(i).substring(0,1);
                continue;
            }
            plain_text = plain_text + diagram.get(i);
        }
        return plain_text;
    }

    int checkSame(String ele1, String ele2) {
        for (int i1 = 0; i1 < 5; i1++) {
            for (int j1 = 0; j1 < 5; j1++) {
                if (Objects.equals(ele1, matrix[i1][j1])) {
                    arr_ele1[0] = i1;
                    arr_ele1[1] = j1;
                }
                if (Objects.equals(ele2, matrix[i1][j1])) {
                    arr_ele2[0] = i1;
                    arr_ele2[1] = j1;
                }
            }
        }
        if (arr_ele1[0] == arr_ele2[0]) {// same row
            return 0;
        }
        if (arr_ele1[1] == arr_ele2[1]) { // same col
            return 1;
        }
        return -1;
    }
} 
public class Playfair_cipher {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Keyword: ");
        String key = sc.nextLine();
        key= key.toUpperCase();
        System.out.println("Enter word to encyrpt: ");
        String Plain_Text = sc.nextLine();
        Playfair_Encyrption peC= new Playfair_Encyrption(Plain_Text,key);
        System.out.println("Cipher is: "+peC.Cipher());
        Playfair_Decyrption decyrpt= new Playfair_Decyrption (peC.Cipher(),key);
        System.out.println("Plain Text is: "+decyrpt.Plain_Text());
    }

}
