import java.util.*;

public class HeroTeam extends Team {
    protected ArrayList<Hero> heroes;
    protected int numberHeroes = 3;

    public HeroTeam() {
        heroes = new ArrayList<>();
    }

    public ArrayList<Hero> getTeam() {
        return this.heroes;
    }

    //Listing possible characters to choose from
    public static Hero listOfCharacters(ArrayList<Hero> list) {
        Scanner input = new Scanner(System.in);
        boolean validInput = true;

        //user chooses an input from chosen hero list
        while (validInput) {
            System.out.println("Please choose a number corresponding to the character you want");
            int counter = 0;
            for (Hero obj : list) {
                System.out.println(counter + ": " + obj);
                counter++;
            }
            String in = input.next();
            try {
                int inputted = Integer.parseInt(in);
                if (inputted < list.size() && inputted >= 0) {
                    Hero h = (list.get(inputted));
                    return h;
                } else {
                    System.out.println("Invalid input. Please enter a number listed.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number listed.");
            }
        }
        while (validInput) ;
        return (list.get(0));
    }

    //update hero health every round
    public void roundHealthIncrease(){
        for(Hero h: this.heroes){
            h.setMana((h.getMana()*0.1));
            h.setHP(h.getHP()*0.1);
        }
    }

    //choose heroes
    public void addHeroes(Board board) {
        Hero.allHeroTypes();
        Scanner input = new Scanner(System.in);
        System.out.println("You can choose three heroes to use for your quest! Each hero will begin in their own lane.");

        //choose the necessary three heroes
        for (int numHeroes = 0; numHeroes < this.numberHeroes; numHeroes++) {
            boolean invalidInput = true;
            while (invalidInput) {
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
                            this.heroes.add(p);
                            Paladin.removeHero(p);
                            System.out.println(p.getName() + " added to team!");
                            invalidInput = false;
                        } else if (heroType == 1) {
                            Hero p = listOfCharacters(Warrior.getAll());
                            this.heroes.add(p);
                            Warrior.removeHero(p);
                            System.out.println(p.getName() + " added to team!");
                            invalidInput = false;
                        } else {
                            Hero p = listOfCharacters(Sorcerer.getAll());
                            this.heroes.add(p);
                            Sorcerer.removeHero(p);
                            System.out.println(p.getName() + " added to team!");
                            invalidInput = false;
                        }
                    } else {
                        System.out.println("Invalid input. Please enter the corresponding number");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter the corresponding number");
                }
            }
        }
        assignPosition(board);
        System.out.println("Congratulations your team has been created!");
        for (Hero h : this.heroes) {
            System.out.println(h);
        }
    }
    //helper function for first assigning positions at beginning of game
    private void assignPosition(Board board){
        int j = -3;
        for(int i = 0; i<this.heroes.size(); i++) {
            j += 3;
            this.heroes.get(i).setLane(board.lanes[i]);
            this.heroes.get(i).setNexus(board.lanes[i]);
            this.heroes.get(i).setRow(board.row-1);
            this.heroes.get(i).setColumn(j);
            this.heroes.get(i).setNickname("h" + (i+1));
            board.boardArray[this.heroes.get(i).getRow()][this.heroes.get(i).getColumn()].addCharacter(this.heroes.get(i));
        }
    }
}
