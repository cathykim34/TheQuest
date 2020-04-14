import java.util.*;

public abstract class Hero extends Characters{

    protected String type = "Hero";
    protected String name;
    protected String nickname;
    protected int level;
    protected double HP;
    protected Wallet wallet;
    protected double mana;
    protected int strength;
    protected int agility;
    protected int dexterity;
    protected Weaponry weapon;
    protected Armory armor;
    protected Backpack backpack;
    protected String favoredSkills;
    protected int row;
    protected int column;
    protected Lane lane;
    protected Lane nexus;
    protected double probDodge;

    public Lane getNexus() {
        return nexus;
    }
    public void setNexus(Lane nexus) {
        this.nexus = nexus;
    }
    //just to use as menu lists
    protected static ArrayList<String> allHeroes = new ArrayList<>();
    public static void allHeroTypes(){
        Hero.allHeroes.add("Paladin");
        Hero.allHeroes.add("Warrior");
        Hero.allHeroes.add("Sorcerer");
    }

    public abstract int walletAmt();
    public abstract void decreaseWalletAmt(int i);
    public abstract void increaseWalletAmt(int i);
    public abstract Backpack getBP();
    public abstract boolean changeNotPossible();
    public abstract void skillSet();
    public abstract void currentStats();
    public abstract void finishRound();
    public abstract void setHP(double i);
    public abstract double getHP();
    public int getStrength() {
        return strength;
    }
    public abstract void setStrength(int i);
    public abstract void setMana(double i);
    public abstract void useMana(double i);
    public int getDexterity() {
        return dexterity;
    }
    public abstract void setDexterity(int i);
    public int getAgility() {
        return agility;
    }
    public abstract void setAgility(int i);
    public abstract void levelUp();
    public abstract String getName();
    public abstract void revive();
    public abstract double getMana();
    public abstract int getLevel();
    public abstract boolean armorOn();
    public abstract void equip(Armory a);
    public abstract void unequip(Armory a);
    public abstract void equip(Weaponry a);
    public abstract void unequip(Weaponry a);
    public abstract boolean checkFainted();
    public abstract String getBackpack();
    public abstract void addXP(int i);

    @Override
    //checks if there is moving forward is possible (no monster in front)
    public boolean enemyNear(Board board) {
        boolean monsterNearby = false;
        //if monster is directly in front
        if(board.boardArray[this.row-1][this.column].monsterExists()){
            monsterNearby = true;
        }
        //if monster is in your cell
        else if(board.boardArray[this.row][this.column].monsterExists()){
            monsterNearby = true;
        }
        //if monster is diagonally in front
        else if(board.boardArray[this.row-1][this.column+1].monsterExists()){
            monsterNearby = true;
        }
        else if(board.boardArray[this.row-1][this.column-1].monsterExists()){
            monsterNearby = true;
        }
        //if monster is next to you
        else if(board.boardArray[this.row][this.column+1].monsterExists()){
            monsterNearby = true;
        }
        //if monster is next to you
        else if(board.boardArray[this.row][this.column-1].monsterExists()){
            monsterNearby = true;
        }
        return monsterNearby;
    }

    //returns whether that move is possible or not
    public boolean makeMove(Board board, String move) {
        boolean spotOpen = false;
        int tempCol = this.getColumn();
        int tempRow = this.getRow();
        if(move.equals("W")){
            tempRow--;
            if(!board.wallExists(tempCol, tempRow) && !board.boardEdge(tempCol, tempRow)){
                BoardCell newCell = board.boardArray[tempRow][tempCol];
                if (!newCell.isFull()) {
                    BoardCell oldCell = board.boardArray[tempRow++][tempCol];
                    oldCell.removeCharacter(this);
                    newCell.addCharacter(this);
                    this.setRow(tempRow);
                    newCell.cellAction(this);
                    spotOpen = true;
                } else {
                    System.out.println("Oops! This cell is at max capacity.");
                }
            }
            else{
                System.out.println("Oof hit a wall!");
            }
        }
        else if(move.equals("A")){
            //check for monsters nearby
            if(!enemyNear(board)) {
                tempCol--;
                if (!board.wallExists(tempCol, tempRow) && !board.boardEdge(tempCol, tempRow)) {
                    BoardCell newCell = board.boardArray[tempRow][tempCol];
                    if (!newCell.isFull()) {
                        BoardCell oldCell = board.boardArray[tempRow++][tempCol];
                        oldCell.removeCharacter(this);
                        newCell.addCharacter(this);
                        this.setColumn(tempCol);
                        newCell.cellAction(this);
                        spotOpen = true;
                    } else {
                        System.out.println("Oops! This cell is at max capacity.");
                    }
                } else {
                    System.out.println("Oof hit a wall!");
                }
            }
        }
        else if(move.equals("S")){
            tempRow++;
            if(!board.wallExists(tempCol, tempRow) && !board.boardEdge(tempCol, tempRow)){
                BoardCell newCell = board.boardArray[tempRow][tempCol];
                if (!newCell.isFull()) {
                    BoardCell oldCell = board.boardArray[tempRow++][tempCol];
                    oldCell.removeCharacter(this);
                    newCell.addCharacter(this);
                    this.setRow(tempRow);
                    newCell.cellAction(this);
                    spotOpen = true;
                } else {
                    System.out.println("Oops! This cell is at max capacity.");
                }
            }
            else{
                System.out.println("Oof hit a wall!");
            }
        }
        else if (move.equals("D")){
            tempCol++;
            if(!board.wallExists(tempCol, tempRow) && !board.boardEdge(tempCol, tempRow)){
                BoardCell newCell = board.boardArray[tempRow][tempCol];
                if (!newCell.isFull()) {
                    BoardCell oldCell = board.boardArray[tempRow++][tempCol];
                    oldCell.removeCharacter(this);
                    newCell.addCharacter(this);
                    this.setColumn(tempCol);
                    newCell.cellAction(this);
                    spotOpen = true;
                } else {
                    System.out.println("Oops! This cell is at max capacity.");
                }

            }else{
                System.out.println("Oof hit a wall!");
            }
        }
        return spotOpen;
    }

    public boolean attackDodged() {
        double ran = Math.random();
        return(ran <= this.probDodge);
    }

    public void getAttacked(Monster m) {
        if(attackDodged()){
            System.out.println("Attack Dodged!");
        }
        else{
            if(armorOn()){
                if(this.armor.getDamage_reduction() >= m.getDamage()){
                    System.out.println("No damage thanks to armor!");
                }
                else{
                    this.HP -= (m.getDamage()-this.armor.getDamage_reduction());
                    System.out.println(this.name + "received " + (m.getDamage()-this.armor.getDamage_reduction()) + "damage from "+ m.getName());
                }
            }else {
                this.HP -= m.getDamage();
                System.out.println(this.name + " received " + m.getDamage() + " damage from " + m.getName());
            }
        }
    }

    //carries out potions
    public void usePotion(){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            int i = 0;
            ArrayList<Potion> potions = this.getBP().getPotions();
            for(Potion s: potions){
                System.out.println(i + ": " + s);
            }
            System.out.println("Enter the corresponding number to the Potion:");
            try{
                String in = input.next();
                int chosen = Integer.parseInt(in);
                if(chosen >= 0 && chosen <potions.size()){
                    potions.get(i).potionEffects(this);
                    potions.remove(i);
                    invalidInput = false;
                }
                else{
                    System.out.println("Invalid input. Please try again.");
                }

            }catch(Exception e){
                System.out.println("Incorrect input. Please try again.");
            }

        }while(invalidInput);
    }

    //checking if armor is on
    public boolean handsUsed(){
        return !(this.weapon == null);
    }

    //changing either armory or weapon
    public void changeWorA(){
        Scanner input = new Scanner(System.in);
        String chosenChange = chooseWeaponOrArmory();
        boolean invalidInput = true;
        int index = 0;
        if (chosenChange.equals("Armor")) {
            for(Armory a: this.getBP().getArmory()){
                System.out.println(index + ": "+ a);
                index++;
            }
            do{
                System.out.println("Choose the corresponding number to the armory you would like to equip");
                try{
                    String in = input.next();
                    int chosen = Integer.parseInt(in);
                    if(chosen >= 0 && chosen < this.getBP().getArmory().size()){
                        if(this.getBP().getArmory().get(chosen).isCurrently_using()){
                            System.out.println("Whoops! Already in use, pick another");
                        }
                        else{
                            this.equip(this.getBP().getArmory().get(chosen));
                            invalidInput = false;
                        }
                    }
                    else{
                        System.out.println("Invalid input.");
                    }

                }catch (Exception e){
                    System.out.println("Invalid input.");
                }
            }while(invalidInput);
        } else {
            for(Weaponry w: this.getBP().getWeapons()){
                System.out.println(index+ ": "+ w);
                index++;
            }
            do{
                System.out.println("Choose the corresponding number to the weapon you would like to equip");
                try{
                    String in = input.next();
                    int chosen = Integer.parseInt(in);
                    if(chosen >= 0 && chosen < this.getBP().getWeapons().size()){
                        if(this.getBP().getWeapons().get(chosen).isCurrently_using()){
                            System.out.println("Whoops! Already in use, pick another");
                        }
                        else{
                            this.equip(this.getBP().getWeapons().get(chosen));
                            invalidInput = false;
                        }
                    }
                    else{
                        System.out.println("Invalid input.");
                    }

                }catch (Exception e){
                    System.out.println("Invalid input.");
                }
            }while(invalidInput);
        }
    }

    //allow user to choose weapon or armory to change
    private String chooseWeaponOrArmory(){
        if(this.backpack.noArmor() && !this.backpack.noWeapons()){
            System.out.println("No armory to choose from!");
            return "Weapon";
        }
        else if(this.backpack.noWeapons() && this.backpack.noArmor()){
            System.out.println("No weapons to choose from!");
            return "Armor";
        }
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            System.out.println("Enter A for changing armory or W for weaponry");
            String in = input.next();
            try{
                if(in.toUpperCase().equals("A")){
                    return "Armory";
                }
                else if (in.toUpperCase().equals("W")){
                    return "Weapon";
                }
                else{
                    System.out.println("Invalid Input.");
                }

            }catch(Exception e){
                System.out.println("Invalid input.");
            }
        }while(invalidInput);
        return "";
    }
    //check if there are potions to take
    public boolean potionsExist(){
        return this.backpack.getPotions().isEmpty();
    }

    //take potion
    public void takePotion(){
        if(potionsExist()){
            System.out.println("Currently do not own any potions!");
        }
        else {
            this.usePotion();
        }
    }

    //carries out spell castings
    public boolean useSpell(Monster m){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            int i = 0;
            ArrayList<Spell> spells = this.backpack.getSpells();
            for(Spell s: spells){
                System.out.println(i + ": " + s);
            }

            System.out.println("Enter the corresponding number to the spell:");
            try{
                String in = input.next();
                int chosen = Integer.parseInt(in);
                if(chosen >= 0 && chosen <spells.size()){
                    Spell sp = spells.get(chosen);
                    if(canUseSpell(sp)){
                        spellCasting(sp, m);
                        spells.remove(chosen);
                        invalidInput = false;
                        return true;
                    }
                    else{
                        System.out.println("Not enough mana to use spell!");
                        return false;
                    }
                }
                else{
                    System.out.println("Invalid. Please try again");
                }
            }catch (Exception e){
                System.out.println("Invalid. Please try again.");
            }

        }while(invalidInput);
        return false;
    }
    //attack with spell
    public void spellCasting(Spell s, Monster m){
        this.useMana(s.getMana_cost());
        if(!attackDodged(m)) {
            if (s.getType().equals("IceSpell")) {
                m.setDamage(0.01);
                m.getSpellCasted(s.getDamage());
                System.out.println(this.name + "dealt " + s.getDamage() + "to " + m.getName() + " and lowered damage by 10%");
            } else if (m.getType().equals("FireSpell")) {
                m.setDefense(0.01);
                m.getSpellCasted(s.getDamage());
                System.out.println(this.name + "dealt " + s.getDamage() + "to " + m.getName() + " and lowered defense by 10%");
            } else {
                m.setDodge_chance(0.01);
                m.getSpellCasted(s.getDamage());
                System.out.println(this.name + "dealt " + s.getDamage() + "to " + m.getName() + " and lowered dodge_chance by 10%");
            }
        }
        else{
            System.out.println("Spell casting dodged- no damage!");
        }

    }


    //helper function to getAttacked for dodge probability
    protected boolean attackDodged(Monster m){
        double ran = Math.random();
        return(ran <= m.getDodge_chance());
    }


    //function to see if spell usage is possible
    public boolean canUseSpell(Spell s){
        if (this.getMana() < s.getMana_cost()){
            return false;
        }
        return true;
    }
    public double getHeroDamage(){
        if(handsUsed()){
            return this.strength+this.weapon.getDamage()*0.05;
        }
        return this.strength*0.05;
    }

    public void attack(Monster monster, Board board){
        monster.getAttackedByHero(this, board);
    }

    public void getAttackedByHero(Hero h, Board board){}

    //how much damage to get from an attack
    public void getAttackedByMonster(Monster m, Board board) {
        if(attackDodged()){
            System.out.println("Attack Dodged!");
        }
        else{
            if(armorOn()){
                if(this.armor.getDamage_reduction() >= m.getDamage()){
                    System.out.println("No damage thanks to armor!");
                }
                else{
                    this.HP -= (m.getDamage()-this.armor.getDamage_reduction());
                    System.out.println(this.name + "received " + (m.getDamage()-this.armor.getDamage_reduction()) + "damage from "+ m.getName());
                }
            }else {
                this.HP -= m.getDamage();
                System.out.println(this.name + " received " + m.getDamage() + " damage from " + m.getName());
            }
        }
    }



    //getter and setter methods

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public static ArrayList<String> listGetter(){
        return Hero.allHeroes;
    }




}
