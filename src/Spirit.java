import java.util.*;

public class Spirit extends Monster {
    protected static String type = "Exoskeleton";
    protected String name;
    protected int level;
    protected double damage;
    protected double defense;
    protected double dodge_chance;
    protected double HP;
    protected boolean dead;


    public Spirit(String name, int level, double damage, double defense, double dodge_chance){
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodge_chance = dodge_chance/100;
        this.HP = 100*this.level;
        this.dead = false;
    }


    //create all existing types of Monsters
    public static ArrayList<? extends Monster> existingTypes(){
        ArrayList<Monster> all = new ArrayList<>();
        all.add(new Andrealphus());
        all.add(new Aim_Haborym());
        all.add(new Andromalius());
        all.add(new Chiang_shih());
        all.add(new FallenAngel());
        all.add(new Ereshkigall());
        all.add(new Melchiresas());
        all.add(new Jormunngand());
        all.add(new Rakkshasass());
        all.add(new Taltecuhtli());
        return all;
    }

    public String getType(){
        return Spirit.type;
    }

    //display current stats
    @Override
    public void currentStats() {
        System.out.println(this.name + "'s Current Health:");
        System.out.println("HP: " + this.HP + "\n");
        System.out.println("Damage "+ this.name + " deals: " + this.damage + "\n");
        System.out.println("Defense: " + this.defense + "\n");
        System.out.println("Chance of dodging attack out of 100: " + this.dodge_chance + "\n");
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public double getDefense() {
        return this.defense;
    }

    @Override
    public double getDodge_chance() {
        return this.dodge_chance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //spells used on monsters
    @Override
    public void setDamage(double d){
        this.damage -= (this.damage*d);
    }
    //spells used on monsters
    @Override
    public void setDefense(double d){
        this.defense -= (this.defense*d);
    }
    //spells used on monsters
    @Override
    public void setDodge_chance(double d){
        this.dodge_chance -= (this.dodge_chance*d);
    }

    @Override
    public void getSpellCasted(double d){
        this.damage -= d;
    }


    public String toString(){
        String ret = "";
        ret += (this.name + " Info:" + "\n");
        ret += ("Level " + this.level) + "\n";
        ret += ("Damage "+ this.name + " deals: " + this.damage) + "\n";
        ret += ("Defense: " + this.defense) + "\n";
        ret += ("Chance of dodging attack out of 100: " + this.dodge_chance) + "\n";
        ret += ("Starting HP: " + this.HP);
        return ret;
    }
}
