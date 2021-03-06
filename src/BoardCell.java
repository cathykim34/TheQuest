import java.util.Scanner;

public class BoardCell {
    protected String type;
    protected int capacity; // How many players can be in a cell at a given time
    protected Characters[] contents; // Characters in the cell
    private int row;
    private int col;

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }



    public boolean getIsHeroNexus() {
        return isHeroNexus;
    }

    public void setHeroNexus(boolean heroNexus) {
        isHeroNexus = heroNexus;
    }

    private boolean isHeroNexus;

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    private int laneNumber; // Cell is type "I" if lane number is -1

    public BoardCell(int row, int col){
        this.row = row;
        this.col = col;
        this.type = "";
        this.capacity = 2;
        this.contents = new Characters[this.capacity];
        this.isHeroNexus = false;
    }
    public BoardCell(int row, int col, String s){
        this.row = row;
        this.col = col;
        this.type = s;
        this.capacity = 2;
        this.contents = new Characters[this.capacity];
        this.isHeroNexus = false;
    }
    public void setType(String s){
        this.type = s;
    }
    public String getType(){
        return this.type;
    }
    public String getCellBorder(){
        return " " + this.type + " - " + this.type + " - " + this.type + " ";
    }

    public Characters[] getContents() {
        return this.contents;
    }

    public int getCapacity() {
        return capacity;
    }

    //take out a character from cell
    public void removeCharacter(Characters character) {
        for (int i = 0; i < this.contents.length; i++) {
            if (this.contents[i] != null) {
                if (this.contents[i].getName().equals(character.getName())) {
                    this.contents[i] = null;
                    return;
                }
            }
        }
    }


    //move a character into this cell
    public void addCharacter(Characters character) {
        for (int i = 0; i < this.contents.length; i++) {
            if (this.contents[i] == null) {
                this.contents[i] = character;
                return;
            }
        }
    }

    //checks if cell is full
    public boolean isFull() {
        for (int i = 0; i < this.contents.length; i++) {
            if (this.contents[i] == null) {
                return false;
            }
        }
        return true;

    }

    //checks if cell is empty
    public boolean isEmpty(){
        for(int i = 0; i < this.capacity; i++){
            if(this.contents[i] != null){
                return false;
            }
        }
        return true;
    }

    //return hero
    public Characters getHero(){
        for(int i = 0; i<this.contents.length; i++){
            if(this.contents[i] != null){
                if(this.contents[i] instanceof Hero){
                    return this.contents[i];
                }
            }
        }
        return null;
    }

    //return hero
    public Characters getMonster(){
        for(int i = 0; i<this.contents.length; i++){
            if(this.contents[i] != null){
                if(this.contents[i].getType().equals("Monster")){
                    return this.contents[i];
                }
            }
        }
        return null;
    }

    //checks if there is a monster in the cell
    public boolean monsterExists(){
        boolean doesExist = false;
        if(isEmpty()){
            return doesExist;
        }
        for(int i = 0; i < this.contents.length; i++){
            if(this.contents[i] != null){
                if(this.contents[i] instanceof Monster){
                    return true;
                }
            }
        }
        return doesExist;
    }

    //checks if there is hero nearby
    public boolean heroExists(){
        if(isEmpty()){
            return false;
        }
        for (int i = 0; i < this.contents.length; i++) {
            if (this.contents[i] instanceof Hero) {
                return true;
            }
        }
        return false;
    }

    public void cellAction(Hero hero) {
        if (this.type.equals("B")) {
            // Bush cells
            int dexterity = hero.getDexterity();
            dexterity = (int) Math.round(dexterity + (dexterity*0.1));
            hero.setDexterity(dexterity);
        } else if (this.type.equals("K")) {
            // Koulou cells
            int strength = hero.getStrength();
            strength = (int) Math.round(strength + (strength*0.1));
            hero.setStrength(strength);
        } else if (this.type.equals("C")) {
            // Cave cells
            int agility = hero.getAgility();
            agility = (int) Math.round(agility + (agility*0.1));
            hero.setAgility(agility);
        } else if (this.type.equals("N")) {
            // Nexus cells
            if (!isHeroNexus) {
                System.out.println(hero.getName() + " has entered the enemy nexus!");
                System.out.println("Heroes win!");
                System.out.println("Thank you for playing!");
                System.exit(0);
            } else {
                nexusCellAction(hero, false);
            }
        }
    }

    public boolean reachedHeroNexus(){
        return this.isHeroNexus;
    }

    public void nexusCellAction(Hero hero, boolean needsRevive) {
        if (needsRevive) {
            hero.revive();
            System.out.println(hero.getName() + "'s health is restored!");
            return;
        }

        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        do {
            System.out.println("Would you like to visit the market? [y/n]: ");
            String choice = input.next();
            choice = choice.toUpperCase();
            if (choice.equals("Y")) {
                Weaponry.existingTypes();
                Armory.existingTypes();
                Market market = new Market();
                MarketDriver driver = new MarketDriver();
                driver.play(market, hero);
                invalid = false;
            } else if (choice.equals("N")){
                invalid = false;
            }
        }while(invalid);
    }

}
