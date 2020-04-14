import java.util.*;
public class Warrior extends Hero{
    protected String type = "Warrior";
    protected String name;
    protected int experience;
    protected double HP;
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
    protected int row;
    protected int column;
    protected int lane;
    protected static ArrayList<Hero> all = new ArrayList<>();

    public Warrior(String name, int mana, int strength, int agility, int dexterity, int starting_money, int starting_exp){
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

    //returns hero type
    public String getType(){
        return this.type + ": favored in strength and agility";
    }

    //returns list of all existing warriors
    public static void existingTypes(){
        Warrior.all.add(new Gaerdal_Ironhand());
        Warrior.all.add(new Mehanine_Sonnbow());
        Warrior.all.add(new Muamman_Duathall());
        Warrior.all.add(new Flandal_Steelskin());
    }

    //return all warrior types
    public static ArrayList<Hero> getAll(){
        return Warrior.all;
    }

    //setter for all existing types
    public static void removeHero(Hero p){
        Warrior.all.remove(p);
    }

    //current inventory of tools
    @Override
    public String getBackpack() {
        return backpack.toString();
    }

    //return backpack
    public Backpack getBP(){
        return this.backpack;
    }

    //checks whether action of changing armor/weapon is possible
    @Override
    public boolean changeNotPossible() {
        return(!armorOn() && !handsUsed() && this.backpack.noArmor() && this.backpack.noWeapons());
    }

    //current amount of money
    @Override
    public int walletAmt() {
        return this.wallet.dollars;
    }
    @Override
    public void decreaseWalletAmt(int d){
        this.wallet.dollars -= d;
    }

    @Override
    public void increaseWalletAmt(int d){
        this.wallet.dollars += d;
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

    public void revive(){
        this.HP = (this.level*100)/2;
    }

    public double getMana(){
        return this.mana;
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

    //
    @Override
    public void levelUp() {
        if(enoughExpToLevelUp()) {
            System.out.println(this.name + "leveled up!");
            this.level++;
            System.out.println("New Level: " + this.level);
            this.dexterity += (0.05 * this.dexterity);
            this.strength += (0.1 * this.strength);
            this.agility += (0.1 * this.agility);
            this.HP = this.level * 100;
            this.mana *= 0.1;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    public boolean attackDodged() {
        double ran = Math.random();
        return(ran <= this.probDodge);
    }

    public boolean checkFainted(){
        if(this.HP <= 0){
            return true;
        }
        else{
            return false;
        }
    }


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

    //get experience
    public int getExperience(){
        return this.experience;
    }
    //add XP
    public void addXP(int i){
        this.experience += i;
        levelUp();
    }

    //prints description of item
    public String toString(){
        String ret = "";
        ret += (this.type + ": favored in strength and agility" + "\n");
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
