import java.util.*;
public class Dragon extends Monster{
    protected String type = "Dragon";

    public Dragon(String name, int level, double damage, double defense, double dodge_chance){
        super(name, level, damage, defense, dodge_chance);
    }
    public static ArrayList<? extends Monster> existingTypes(){
        ArrayList<Dragon> all = new ArrayList<>();
        all.add(new Desghidorrah());
        all.add(new Chrysophylax());
        all.add(new BunsenBurner());
        all.add(new Natsunomeryu());
        all.add(new TheScaleless());
        all.add(new Kas_Ethelinh());
        all.add(new Alexstraszan());
        all.add(new Phaarthurnax());
        all.add(new D_Maleficent());
        all.add(new TheWeatherbe());
        return all;
    }

    //display current stats
    @Override
    public void currentStats() {
        System.out.println(this.name + "'s Current Health:");
        System.out.println("HP: " + this.HP + "\n");
    }



    @Override
    public String getType() {
        return this.type;
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


    @Override
    public void getSpellCasted(double d){
        this.damage -= d;
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
