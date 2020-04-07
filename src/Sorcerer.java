import java.util.*;

public class Sorcerer extends Hero implements Fight{
    protected String type = "Sorcerer";
    protected String name;
    protected int experience;
    protected int HP;
    protected Wallet wallet;
    protected double mana;
    protected double strength;
    protected double agility;
    protected double dexterity;
    protected int level;
    protected double probDodge;
    protected Weaponry weapon;
    protected Armory armor;
    protected Backpack backpack;
    private static ArrayList<Hero> all = new ArrayList<>();

    public Sorcerer(){}

    public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int starting_money, int starting_exp){
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.wallet = new Wallet(starting_money);
        this.level = 1;
        this.experience = starting_exp;
        this.HP = this.level*100;
        this.probDodge = (0.02*this.agility)/100;
        this.backpack = new Backpack();
    }

    @Override
    public boolean attackDodged() {
        double ran = Math.random();
        return(ran <= this.probDodge);
    }

    //checks whether player fainted or not
    public boolean checkFainted(){
        if(this.HP <= 0){
            return true;
        }
        else{
            return false;
        }
    }
    //add XP
    public void addXP(int i){
        this.experience += i;
        levelUp();
    }
    //get experience
    public int getExperience(){
        return this.experience;
    }
    public void revive(){
        this.HP = (this.level*100)/2;
    }

    public double getMana(){
        return this.mana;
    }
    //how much damage to get from an attack
    @Override
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
    public void finishRound(){
        this.HP += (this.HP*0.05);
        this.mana += (this.mana*0.05);
    }
    @Override
    public void setHP(int i){
        this.HP += i;
    }

    @Override
    public void setStrength(int i) {
        this.strength += i;
    }

    @Override
    public void setMana(int i) {
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

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public void makeMove(Monster m) {
        if(handsUsed()){
            m.getAttacked((this.strength+this.weapon.getDamage())*0.05);
        }
        else{
            m.getAttacked(this.strength*0.05);
        }
    }

    //returns hero type
    public String getType(){
        return this.type + ": favored in agility and dexterity";
    }

    //all existing types of sorcerers
    public static void existingTypes(){
        Sorcerer.all.add(new Carl_Clittercold());
        Sorcerer.all.add(new Rillifane_Rallathil());
        Sorcerer.all.add(new Segojan_Earthcaller());
        Sorcerer.all.add(new Wkoraeus_Wtonebones());
    }

    //returns all existing types
    public static ArrayList<Hero> getAll(){
        return Sorcerer.all;
    }

    //setter for all existing types
    public static void removeHero(Hero p){
        Sorcerer.all.remove(p);
    }

    //current inventory of tools
    @Override
    public String getBackpack(){
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
    private boolean enoughExpToLevelUp(){
        return(this.experience > (this.level*10) || this.experience == (this.level*10));
    }

    @Override
    public void levelUp() {
        if(enoughExpToLevelUp()) {
            System.out.println(this.name + "leveled up!");
            this.level++;
            System.out.println("New Level: " + this.level);
            this.dexterity += (0.1 * this.dexterity);
            this.strength += (0.05 * this.strength);
            this.agility += (0.1 * this.agility);
            this.mana *= 0.1;
            this.HP = 100 * this.level;
        }
    }
    //checks whether action of changing armor/weapon is possible
    @Override
    public boolean changeNotPossible() {
        return(!armorOn() && !handsUsed() && this.backpack.noArmor() && this.backpack.noWeapons());
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void decreaseWalletAmt(int d){
        this.wallet.dollars -= d;
    }

    @Override
    public void increaseWalletAmt(int d){
        this.wallet.dollars += d;
    }

    //return backpack
    public Backpack getBP(){
        return this.backpack;
    }

//prints description of item
    public String toString(){
        String ret = "";
        ret += (this.type + ": favored in agility and dexterity" + "\n");
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