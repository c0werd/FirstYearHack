
// sussy baka
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;

public class Challenge1 {
    private File file;
    private Scanner scanner;

    

    public Challenge1() {
        
    }

    public void setFile(String filename) {
        //takes the filename in and creates a file
        this.file = new File(filename);
        //runs the top five bots check
        topFive();
    }

    // Returns the top five bots with the highest CPU usage
    private void topFive() {
        int runningTotal = 0;
        ArrayList<Integer> numbers = new ArrayList<>();

        try {
            //initialises new scanner
            scanner = new Scanner(file);
            //iterates through file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                //if the next line is not empty, sum the running total
                if (!line.isEmpty()) {
                    runningTotal += Integer.valueOf(line);
                } 
                //
                else {
                    numbers.add(runningTotal);
                    runningTotal = 0;
                }
            }
            scanner.close();
            // adds the runningtime of the last bot once the scanner finishes
            numbers.add(runningTotal);
            runningTotal = 0;

        } 
        //exception for no file found
        catch (FileNotFoundException e) {
            System.out.print("invalid file");
        }

        //sorts the list of sums in descending order
        Collections.sort(numbers, Collections.reverseOrder());

        //creates a comma separated list of the top 5 bots
        String outputString = "";
        for (int i = 0; i <= 4; i++) {
            if (i == 4) {
                outputString += numbers.get(i);
            } else {
                outputString += numbers.get(i) + ", ";
            }
        }

        //prints the top 5 list
        System.out.println(outputString);

    }
}
