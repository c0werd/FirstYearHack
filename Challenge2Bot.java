import java.util.ArrayList;

public class Challenge2Bot {

    private String name;
    private ArrayList<Integer> securityList;
    private int divisible;
    private String firstBot;
    private String secondBot;
    private int inspections;
    private ArrayList<Integer> original = new ArrayList<>();

    public Challenge2Bot(String name, ArrayList<Integer> securities, int divisible, String firstBot, String secondBot) {
        
        this.name = name;
        this.securityList = securities;
        this.divisible = divisible;
        this.firstBot = firstBot;
        this.secondBot = secondBot;
        inspections = 0;
        for (Integer sec : securityList) {
	        original.add(sec);
        }

    }

    public void trade(Challenge2Bot botOne, Challenge2Bot botTwo, double factor) {
        for (Integer security : securityList) {
            int dummySecurity = 0;
            dummySecurity = (int) Math.ceil(security * security * factor); // Add a *0.2 for challenge 2
            if (dummySecurity % divisible == 0) {
                botOne.addSecurity(security);
            }
            else { 
                botTwo.addSecurity(security);
            }
            inspections++;
            }
            securityList.clear();
    }

    public void addSecurity(int security) {
        securityList.add(security);
    }

    public String getFirstBot() {
        return firstBot;
    }

    public String getSecondBot() {
        return secondBot;
    }

    public String getName() {
        return name;
    }

    public int getInspections() {
        return inspections;
    }

    public String toString() {
        return name;
    }

    public void reset() {
        securityList = original;
        inspections = 0;
    }
    
    
}

