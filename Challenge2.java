import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Challenge2 {
    public static void main(String[] args) {
        try {
            File file = new File("challenge_2_and_3.txt");
            Scanner sc = new Scanner(file);
            Challenge2 challenge2 = new Challenge2();

            for(int i = 0; i <=4; i++){
                System.out.println(challenge2.getBotName(sc));
                System.out.println(challenge2.getInitSecurities(sc));
                challenge2.getDivisible(sc);
                challenge2.getBotOne(sc);
                challenge2.getNotBotTwo(sc);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        
    }

    //
    public String getBotName(Scanner sc){
       String line = sc.nextLine();
       String name = line.substring(4);
       
       return name;
    }

    public ArrayList<Integer> getInitSecurities(Scanner sc){
        String line = sc.nextLine();
        String securitiesString = line.substring(19);
        String[] arr = securitiesString.split(",");
        ArrayList<Integer> securities = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
            securities.add(Integer.valueOf(arr[i]));
        }

        return securities;
    }

    public void getDivisible(Scanner sc){
        String divisible = sc.nextLine();
        

    }

    public void getBotOne(Scanner sc){
        String tradeTo = sc.nextLine();

    }

    public void getNotBotTwo(Scanner sc){
        String notTradeto = sc.nextLine();

    }
}
