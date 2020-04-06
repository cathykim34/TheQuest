import java.util.*;
public class Dragon extends Monster {
    protected String type = "Dragon";
    protected String name;
    protected int level;
    protected double damage;
    protected double defense;
    protected double dodge_chance;
    protected double HP;
    protected boolean dead;

    public Dragon(String name, int level, double damage, double defense, double dodge_chance){
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodge_chance = dodge_chance/100;
        this.HP = 100*this.level;
        this.dead = false;
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
    //helper function to getAttacked for dodge probability
    private boolean attackDodged(){
        double ran = Math.random();
        return(ran <= this.dodge_chance);
    }

    //check if monster has been defeated
    public boolean checkDeath(){
        if (this.HP <= 0){
            this.dead = true;
            System.out.println("Monster defeated!!");
            return true;
        }
        return false;
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
    public void getAttacked(double d) {
        if(attackDodged()){
            System.out.println("Attack dodged! No damage received.");
        }
        else{
            if(this.defense > d){
                System.out.println("No damage due to " + this.name + "'s defense");
            }
            else{
                this.HP -= (this.defense - d);
                checkDeath();
            }
        }
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
