import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Collections;

public class Challenge2 {
    String currentLine;
    File file;
    Scanner sc;
    HashMap<String, Challenge2Bot> bots;
    ArrayList<String> botList;

    public static void main(String[] args) {
        
    }

    public Challenge2() {
        bots = new HashMap<>();
        botList = new ArrayList<>();
        
    }

    // Sets the file for the challenge
    public void setFile(String filename) {
        file = new File(filename);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        scan();

        int numberOfTimePeriods = 2; // Set to =2 for challenge 2 and =3 for challenge 3

        for (int i = 0; i < numberOfTimePeriods; i++) {
            trading(0.2);
        }

        sortBotInspections();

    for (String bot : botList) {
        bots.get(bot).reset();
    }

        numberOfTimePeriods = 5000; // Set to =2 for challenge 2 and =3 for challenge 3

        for (int i = 0; i < numberOfTimePeriods; i++) {
            trading(1.0);
        }

        sortBotInspections();
    }

    // Function that parses the input file for the various fields
    public void scan() {
        int lineNumber = 0;
        String botName = "";
        ArrayList<Integer> sec = new ArrayList<>();
        int divisible = 0;
        String firstBot = "";
        String secondBot = "";
        while (sc.hasNextLine()) {
            lineNumber++;
            String currLine = sc.nextLine();
            switch (lineNumber % 5) {
                case 1:
                    botName = getBotName(currLine);

                    break;
                case 2:
                    sec = getInitSecurities(currLine);
                    break;
                case 3:
                    divisible = getDivisible(currLine);
                    firstBot = getBotOne(currLine);
                    break;
                case 4:
                    secondBot = getBotTwo(currLine);
                    break;
                case 0:
                    bots.put(botName, new Challenge2Bot(botName, sec, divisible, firstBot, secondBot));
                    botList.add(botName);
                    break;
            }
        }
        botList.add(botName);
        bots.put(botName, new Challenge2Bot(botName, sec, divisible, firstBot, secondBot));
    }

    // Function that returns the name of a bot from a .txt file
    public String getBotName(String line) {
        return line.substring(4).replace(":", "");
    }

    // Function that returns the initial securities of a bot from a .txt file
    public ArrayList<Integer> getInitSecurities(String line) {
        String securitiesString = line.replace("initial securities: ", "");
        String[] arr = securitiesString.split(",");
        ArrayList<Integer> securities = new ArrayList<>();

        //
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
            securities.add(Integer.valueOf(arr[i]));
        }

        return securities;
    }

    // Function that returns the value a security must be divisible by
    public int getDivisible(String line) {
        line = line.replace("Trading rule: If value of security divisible by ", "");

        int divisible = Character.getNumericValue(line.charAt(0));

        return divisible;
    }

    // Function that returns the name of the bot that is traded to if the security
    // is divisible by a given value
    public String getBotOne(String line) {
        String[] parts = line.split("bot ");
        return parts[1].replace(".", "");
    }

    // Function that returns the name of the bot that is traded to otherwise
    public String getBotTwo(String line) {
        return line.replace("Otherwise trade to bot ", "").replace(".", "").strip();
    }

    // Function that determines the trading bot
    public void trading(double factor) {

        for (String bot : botList) {

            Challenge2Bot currentBot = bots.get(bot);
            currentBot.trade(bots.get(currentBot.getFirstBot()), bots.get(currentBot.getSecondBot()), factor);

        }
    }
    //Function that sorts the bots 
    public void sortBotInspections() {
        HashMap<Integer, Challenge2Bot> botInspection = new HashMap<>();
        ArrayList<Integer> inspectionList = new ArrayList<>();
        ArrayList<Challenge2Bot> namesList = new ArrayList<>();
        
        for(String bot : botList){
             botInspection.put(bots.get(bot).getInspections(), bots.get(bot));
             inspectionList.add(bots.get(bot).getInspections());
        }

        Collections.sort(inspectionList);
        Collections.reverse(inspectionList);

        for (Integer i : inspectionList) {
            namesList.add(botInspection.get(i));
        }
        
        for (int i = 0; i < namesList.size(); i++){
            System.out.println(namesList.get(i) + ": " + inspectionList.get(i));
        }
    }

}

// ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
// ⠄⠄⠄⠄⠄⠄⠄⣀⣀⣐⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
// ⠄⠄⢠⠄⣠⣶⣿⣿⣿⠿⠿⣛⣂⣀⣀⡒⠶⣶⣤⣤⣬⣀⡀⠄⢀⠄⠄⠄⠄⠄⠄⠄
// ⠄⠄⢀⣾⣿⣿⣿⡟⢡⢾⣿⣿⣿⣿⣿⣿⣶⣌⠻⣿⣿⣿⣿⣷⣦⣄⡀⠄⠄⠄⠄⠄
// ⠄⠄⣈⣉⡛⣿⣿⣿⡌⢇⢻⣿⣿⣿⣿⣿⠿⠛⣡⣿⣿⣿⣿⣿⣿⣿⣿⣦⣄⠄⠄⠄
// ⠄⠺⠟⣉⣴⡿⠛⣩⣾⣎⠳⠿⠛⣋⣩⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠄⠄
// ⠄⠄⠄⠘⢋⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠄
// ⠄⠄⢀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠄
// ⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⣀
// ⠄⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠘⠛
// ⠄⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⣀⣀⣠⣤
// ⠄⠄⣀⣀⡙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⢛⣩⠤⠾⠄⠛⠋⠉⢉
// ⠄⠺⠿⠛⠛⠃⠄⠉⠙⠛⠛⠻⠿⠿⠿⠟⠛⠛⠛⠉⠁⠄⠄⣀⣀⣠⣤⣠⣴⣶⣼⣿
// SUS......... AMOGUS.....