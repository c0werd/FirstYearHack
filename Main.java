import java.util.Scanner;
import java.util.HashMap;

public class Main {
    Challenge1 challenge1;
    Challenge2 challenge2;
    Scanner scanner;
    String file1;
    String file2;
    String file3;
    HashMap<String, Challenge2Bot> bots;


    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        //gets user input for filenames
        scanner = new Scanner(System.in);
        challenge1 = new Challenge1();
        challenge2 = new Challenge2();
        System.out.println("Enter the filename for the .txt file to be used for Challenge 1:");
        file1 = scanner.nextLine();
        System.out.println("Enter the filename for the .txt file to be used for Challenge 2 and 3:");
        file2 = scanner.nextLine();

        
        challenge1.setFile(file1);
    }

    
}
