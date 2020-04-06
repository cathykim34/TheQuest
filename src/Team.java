import java.util.*;

public class Team {
    protected static ArrayList<Hero> heroes = new ArrayList<>();;
    protected static int numHeroes;
    protected static int curRow;
    protected static int curCol;

    public Team(){
        numHeroes = 0;
        curRow = 0;
        curCol = 0;
    }

    public static ArrayList<Hero> getHeroes(){
        return Team.heroes;
    }

    public static int getCurRow(){
        return Team.curRow;
    }

    public static int getCurCol(){
        return Team.curCol;
    }

    public static void setCurRow(int n){
        Team.curRow = n;
    }

    public static void setCurCol(int n){
        Team.curCol = n;
    }

    public static int getNumHeroes(){
        return Team.numHeroes;
    }

    public static void teamInfo(){
        for(Hero h: heroes){
            System.out.println(h);
        }
    }

    public static int maxLevel(){
        int max = heroes.get(0).getLevel();
        for (Hero h: heroes){
            if(h.getLevel() > max){
                max = h.getLevel();
            }
        }
        return max;
    }

    //Listing possible characters to choose from
    public static Hero listOfCharacters(ArrayList<Hero> list){
        Scanner input = new Scanner(System.in);
        boolean validInput = true;

        //user chooses an input from chosen hero list
        while(validInput){
            System.out.println("Please choose a number corresponding to the character you want");
            int counter = 0;
            for(Hero obj: list){
                System.out.println(counter + ": " + obj);
                counter++;
            }
            String in = input.next();
            try{
                int inputted = Integer.parseInt(in);
                if(inputted < list.size() && inputted >= 0){
                    Hero h = (list.get(inputted));
                    return h;
                }
                else{
                    System.out.println("Invalid input. Please enter a number listed.");
                }
            }catch(Exception e){
                System.out.println("Invalid input. Please enter a number listed.");
            }
        }while(validInput);
        return(list.get(0));
    }
    public void addHeroes() {
        Hero.allHeroTypes();
        Scanner input = new Scanner(System.in);
        boolean notMaxNumHeroes = true;
        System.out.println("You can have between 1-3 heroes on your team chosen from the lists below.");
        while(notMaxNumHeroes){
            //all hero types currently existing
            ArrayList<String> heroTypes = Hero.listGetter();
            System.out.println("Please choose the corresponding number next to the Hero type you would like to choose a character from.");
            for (int i = 0; i < heroTypes.size(); i++) {
                System.out.println(i + ": " + heroTypes.get(i));
            }
            String in = input.next();
            try {
                int heroType = Integer.parseInt(in);
                //checks if possible choice
                if (heroType >= 0 && heroType < heroTypes.size()) {
                    if (heroType == 0) {
                        Hero p = listOfCharacters(Paladin.getAll());
                        Team.heroes.add(p);
                        Paladin.removeHero(p);
                        System.out.println(p.getName() + " added to team!");
                        Team.numHeroes++;
                    } else if (heroType == 1) {
                        Hero p = listOfCharacters(Warrior.getAll());
                        Team.heroes.add(p);
                        Warrior.removeHero(p);
                        System.out.println(p.getName() + " added to team!");
                        Team.numHeroes++;
                    } else {
                        Hero p = listOfCharacters(Sorcerer.getAll());
                        Team.heroes.add(p);
                        Sorcerer.removeHero(p);
                        System.out.println(p.getName() + " added to team!");
                        Team.numHeroes++;
                    }
                } else {
                    System.out.println("Invalid input. Please enter the corresponding number");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter the corresponding number");
            }

            //checks if player wants to continue adding more heroes or not
//            System.out.println(Team.numHeroes);
            boolean rightNum = true;
            if(Team.numHeroes != 3) {
                do {
                    System.out.println("Would you like to add more heroes? Enter Y for yes or N for no.");
                    try {
                        String newPlayer = input.next();
                        if (newPlayer.toUpperCase().equals("N")) {
                            notMaxNumHeroes = false;
                            rightNum = false;
                        } else if (newPlayer.toUpperCase().equals("Y")) {
                            rightNum = false;
                        } else {
                            System.out.println("Invalid input.  Enter Y to add more heroes or N to finish team creation.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Enter Y to add more heroes or N to finish team creation.");
                    }
                } while (rightNum);
            }
            if(Team.numHeroes == 3){
                notMaxNumHeroes = false;
                System.out.println("Congratulations your team has been created!");
                for (Hero h : Team.heroes) {
                    System.out.println(h);
                }
            }
        }

    }

}
