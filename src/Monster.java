import java.util.*;
public abstract class Monster extends Characters {
    protected String name;
    protected int level;
    protected double damage;
    protected double defense;
    protected double dodge_chance;
    protected double HP;
    protected double dead;
    public abstract void currentStats();
    public static ArrayList<Monster> all;
    public abstract boolean checkDeath();
    public abstract String getType();
    public abstract int getLevel();
    public abstract double getDamage();
    public abstract double getDefense();
    public abstract double getDodge_chance();
    public abstract String getName();
    public abstract void getAttacked(double d);
    public abstract void setDamage(double d);
    public abstract void setDodge_chance(double d);
    public abstract void setDefense(double d);
    public abstract void getSpellCasted(double d);
    protected Lane nexus;

    public Lane getNexus() {
        return nexus;
    }
    public void setNexus(Lane nexus) {
        this.nexus = nexus;
    }
    protected static int maxLevel = 10;

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    protected Lane lane;

    public static int getMaxLevel(){
        return Monster.maxLevel;
    }

    //new instances of monsters
    public static ArrayList<Monster> allPossible(){
        ArrayList<Monster> huge = new ArrayList<>();
        huge.addAll(Exoskeleton.existingTypes());
        huge.addAll(Dragon.existingTypes());
        huge.addAll(Spirit.existingTypes());
        return huge;
    }

    //check if there are heroes in range to fight
    public boolean needToAttack(Board board){
        if()
    }

    //either move forward or fight
    public void makeMove(Board board){

    }


    public Monster(){}

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
