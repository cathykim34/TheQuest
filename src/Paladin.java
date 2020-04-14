import java.util.*;

public class Paladin extends Hero{
    protected static String type = "Paladin";
    private static ArrayList<Hero> all = new ArrayList<>();

//    public Paladin(){}

    public Paladin(String name, int mana, int strength, int agility, int dexterity, int starting_money, int starting_exp){
        super(name, mana, strength, agility, dexterity, starting_money, starting_exp);
    }

    public boolean attackDodged() {
        double ran = Math.random();
        return(ran <= this.probDodge);
    }



    //get experience
    public int getExperience(){
        return this.experience;
    }

    public void revive(){
        this.HP = (this.level*100)/2;
    }
    //add XP
    public void addXP(int i){
        this.experience += i;
        levelUp();
    }



    public double getMana(){
        return this.mana;
    }
    //how much damage to get from an attack

    @Override
    public void finishRound(){
        this.HP += (this.HP*0.05);
        this.mana += (this.mana*0.05);
    }

    @Override
    public void setHP(double i){
        this.HP += i;
    }

    @Override
    public double getHP() {
        return this.HP;
    }

    @Override
    public void setStrength(int i) {
        this.strength += i;
    }

    @Override
    public void setMana(double i) {
        this.mana += i;
    }
    @Override
    public void useMana(double i) {
        this.mana -= i;
    }

    @Override
    public void setDexterity(int i) {
        this.dexterity += i;
    }

    @Override
    public void setAgility(int i) {
        this.agility += i;
    }

    //buying objects
    @Override
    public void decreaseWalletAmt(int d){
        this.wallet.dollars -= d;
    }

    //selling objects
    @Override
    public void increaseWalletAmt(int d){
        this.wallet.dollars += d;
    }

    //return backpack
    public Backpack getBP(){
        return this.backpack;
    }
    //returns hero type
    public String getType(){
        return this.type  + ": favored in strength and dexterity" ;
    }

    //creates all existing types of paladins
    public static void existingTypes(){
        Paladin.all.add(new Solonor_Thelandira());
        Paladin.all.add(new Sehanine_Moonbow());
        Paladin.all.add(new Skoraeus_Stonebones());
        Paladin.all.add(new Garl_Glittergold());
    }

    //returns all existing types
    public static ArrayList<Hero> getAll(){
        return Paladin.all;
    }

    //setter for all existing types
    public static void removeHero(Hero p){
        Paladin.all.remove(p);
    }

    public String getName(){
        return this.name;
    }

    //returns what level
    public int getLevel(){
        return this.level;
    }

    //current inventory of tools
    @Override
    public String getBackpack() {
        return backpack.toString();
    }

    //current amount of money
    @Override
    public int walletAmt() {
        return this.wallet.dollars;
    }
    //character's skill set
    @Override
    public void skillSet() {

    }


    //equip armor
    public void equip(Armory a){
        if(armorOn()){
            unequip(this.armor);
        }
        a.putOn();
        this.armor = a;
    }
    //unequip armor
    public void unequip(Armory a){
        this.armor.takeOff();
        this.armor = null;
    }

    //equip weapon
    public void equip(Weaponry a){
        if(handsUsed()){
            unequip(this.weapon);
        }
        a.putOn();
        this.weapon = a;
    }
    //unequip weapon
    public void unequip(Weaponry a){
        this.weapon.takeOff();
        this.weapon = null;
    }
    //checking if armor is on
    public boolean armorOn(){
        return !(this.armor == null);
    }

    //checking if armor is on
    public boolean handsUsed(){
        return !(this.weapon == null);
    }

    //prints current stats of character
    @Override
    public void currentStats() {
        System.out.println(this.name + " Current Statistics:");
        System.out.println("Current HP " + this.HP);
        System.out.println("Chance of dodging an attack: " + this.probDodge);
        if(!armorOn() && !handsUsed()){
            System.out.println("Currently holding no weapons and wearing no armor");
        }
        else if(armorOn() && !handsUsed()){
            System.out.println("Currently wearing: " + this.armor);
            System.out.println("Currently holding no weapons");
        }
        else if(!armorOn() && handsUsed()){
            System.out.println("Currently holding: " + this.weapon);
            System.out.println("Currently wearing no armor");
        }
        else{
            System.out.println("Currently wearing: " + this.armor);
            System.out.println("Currently holding: " + this.weapon);

        }
        System.out.println("Current mana level: " + this.mana);
        System.out.println("Strength: " + this.strength);
        System.out.println("Dexterity: " + this.dexterity);
        System.out.println("Agility: " + this.agility);
    }

    //helper function to determine if character has leveled up
    protected boolean enoughExpToLevelUp(){
        return(this.experience > (this.level*10) || this.experience == (this.level*10));
    }

    //checks whether action of changing armor/weapon is possible
    @Override
    public boolean changeNotPossible() {
        return(!armorOn() && !handsUsed() && this.backpack.noArmor() && this.backpack.noWeapons());
    }

    @Override
    public void levelUp() {
        if(enoughExpToLevelUp()) {
            System.out.println(this.name + "leveled up!");
            this.level++;
            System.out.println("New Level: " + this.level);
            this.dexterity += (0.1 * this.dexterity);
            this.strength += (0.1 * this.strength);
            this.agility += (0.05 * this.agility);
            this.HP = 100 * this.level;
        }
    }
    //prints description of item
    public String toString(){
        String ret = "";
        ret += (this.type + ": favored in strength and dexterity" + "\n");
        ret+= ("Character's name: " + this.name + "\n");
        ret += ("Current HP: " + this.HP) + "\n";
        ret += ("Current Level: " + this.level) + "\n";
        ret += ("Total Gold Pieces in Wallet: " + this.wallet.dollars + "\n");
        ret += ("Current mana level: " + this.mana) + "\n";
        ret += ("Strength: " + this.strength) + "\n";
        ret += ("Dexterity: " + this.dexterity) + "\n";
        ret += ("Agility: " + this.agility) + "\n";
        return ret;
    }
}
