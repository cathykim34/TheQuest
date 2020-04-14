import java.util.*;
public abstract class Monster extends Characters{
    protected String Type = "Monster";
    protected String name;
    protected int level;
    protected double damage;
    protected double defense;
    protected double dodge_chance;
    protected double HP;
    protected boolean dead;
    protected boolean reachedNexus = false;
    public abstract void currentStats();
    public static ArrayList<Monster> all;
    public abstract String getType();
    public abstract int getLevel();
    public abstract double getDamage();
    public abstract double getDefense();
    public abstract double getDodge_chance();
    public abstract String getName();
    public abstract void setDamage(double d);
    public abstract void setDodge_chance(double d);
    public abstract void setDefense(double d);
    public abstract void getSpellCasted(double d);
    protected static int maxLevel = 10;
    protected Lane lane;
    protected Lane nexus;
    protected String nickname = "M";

    //new instances of monsters
    public static ArrayList<Monster> allPossible(){
        ArrayList<Monster> huge = new ArrayList<>();
        huge.addAll(Exoskeleton.existingTypes());
        huge.addAll(Dragon.existingTypes());
        huge.addAll(Spirit.existingTypes());
        return huge;
    }
    //check if monster has been defeated
    public boolean checkFainted(){
        if (this.HP <= 0){
            this.dead = true;
            System.out.println("Monster defeated!!");
            return true;
        }
        return false;
    }


    //either move forward or fight
    public void makeMove(Board board){
        if(!enemyNear(board)){
            //only move forward is cell isn't full
            if(!board.boardArray[this.row+1][this.column].isFull()){
                //leave current cell
                board.boardArray[this.row][this.column].removeCharacter(this);
                //move to cell in front
                this.row++;
                //enter new cell
                board.boardArray[this.row][this.column].addCharacter(this);
                //check if monsters arrived at heroNexus
                if(board.boardArray[this.row][this.column].getIsHeroNexus()){
                    this.reachedNexus = true;
                }
            }
        }
    }

    @Override
    //check if there is a hero nearby
    public boolean enemyNear(Board board){
        if(board.boardArray[this.row+1][this.column].heroExists()){
            //get the hero and attack them
            board.boardArray[this.row+1][this.column].getHero().getAttackedByMonster(this, board);
            return true;
        }
        //if monster is in your cell
        else if(board.boardArray[this.row][this.column].heroExists()){
            //get the hero and attack them
            board.boardArray[this.row][this.column].getHero().getAttackedByMonster(this, board);
            return true;
        }
        else if(this.column != board.column-1) {
            //if monster is diagonally in front
            if (this.row != 0 && board.boardArray[this.row + 1][this.column + 1].heroExists()) {
                //get the hero and attack them
                board.boardArray[this.row + 1][this.column+1].getHero().getAttackedByMonster(this, board);
                return true;
            }

            //if monster is next to you
            else if (board.boardArray[this.row][this.column + 1].heroExists()) {
                //get the hero and attack them
                board.boardArray[this.row][this.column+1].getHero().getAttackedByMonster(this, board);
                return true;
            }
        }else if (this.column != 0) {
            if (board.boardArray[this.row + 1][this.column - 1].heroExists()) {
                //get the hero and attack them
                board.boardArray[this.row + 1][this.column-1].getHero().getAttackedByMonster(this, board);
                return true;
            }
            //if monster is next to you
            else if (board.boardArray[this.row][this.column - 1].heroExists()) {
                //get the hero and attack them
                board.boardArray[this.row][this.column -1].getHero().getAttackedByMonster(this, board);
                return true;
            }
        }
        return false;
    }
    //getters and setters for attributes
    public Lane getNexus() {
        return nexus;
    }
    public void setNexus(Lane nexus) {
        this.nexus = nexus;
    }
    public Lane getLane() {
        return lane;
    }
    public void setLane(Lane lane) {
        this.lane = lane;
    }
    public static int getMaxLevel(){
        return Monster.maxLevel;
    }
    public boolean isReachedNexus() {
        return reachedNexus;
    }

    //checks chance of dodging attacks
    public boolean attackDodged(){
        double ran = Math.random();
        return(ran <= this.dodge_chance);
    }

    //receives damage from hero
    public void getAttackedByHero(Hero h, Board board) {
        if(attackDodged()){
            System.out.println("Attack dodged! No damage received.");
        }

        else{
            double damage = h.getHeroDamage();
            if(this.defense > damage){
                System.out.println("No damage due to " + this.name + "'s defense");
            }
            else{
                this.HP -= (this.defense - damage);
                updateIsDead(board);
            }
        }
    }

    public void getAttackedByMonster(Monster m, Board board){}

    //checks if monster is dead and appropriately cleans up
    public void updateIsDead(Board board) {
        if(this.HP <= 0){
            board.boardArray[this.row][this.column].removeCharacter(this);
        }
    }

    public Monster(String name, int level, double damage, double defense, double dodge_chance){
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodge_chance = dodge_chance/100;
        this.HP = 100*this.level;
        this.dead = false;
    }

    public String toString(){
        String ret = "";
        ret += (this.name + " Info:" + "\n");
        ret += ("Level " + this.level) + "\n";
        ret += ("Damage "+ this.name + " deals: " + this.damage) + "\n";
        ret += ("Defense :" + this.defense) + "\n";
        ret += ("Chance of dodging attack out of 100: " + this.dodge_chance);
        ret += ("Starting HP: " + this.HP);
        return ret;
    }
}
