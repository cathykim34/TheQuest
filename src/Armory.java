import java.util.*;
public class Armory extends Item implements Buyable{
    protected String name;
    protected int cost;
    protected int required_level;
    protected int damage_reduction;
    protected boolean currently_using;
    protected static ArrayList<Armory> all = new ArrayList<>();
    public Armory(String name, int cost, int required_level, int damage_reduction){
        this.name = name;
        this.cost = cost;
        this.required_level = required_level;
        this.damage_reduction = damage_reduction;
        this.currently_using = false;
    }

    //list of existing armory items
    public static void existingTypes() {
        Armory.all.add(new Platinum_Shield());
        Armory.all.add(new Breastplate());
        Armory.all.add(new Full_Body_Armor());
        Armory.all.add(new Wizard_Shield());
        Armory.all.add(new Speed_Boots());
    }
    //take off armor
    public void takeOff(){
        this.currently_using = false;
    }

    //put on armor
    public void putOn(){
        this.currently_using = true;
    }

    //get damage reduced
    public int getDamage_reduction(){
        return this.damage_reduction;
    }

    //tells whether armor is already in use
    public boolean isCurrently_using(){
        return this.currently_using;
    }

    //returns list of existing armory
    public static ArrayList<Armory> getAll(){
        return Armory.all;
    }
    //prints description of item
    public String toString(){
        String ret = "";
        ret += (this.name + "\n");
        ret += ("Price: " + this.cost + " gold pieces") + "\n";
        ret += ("Minimum level to obtain: " + this.required_level) + "\n";
        ret += ("Damage reduction: " + this.damage_reduction) + "\n";
        return ret;
    }
    //selling price
    @Override
    public int sellPrice() {
        return this.cost/2;
    }

    //returns cost
    public int getCost(){
        return this.cost;
    }

    //checks whether hero has sufficient funds/level
    @Override
    public <T extends Hero> boolean isPossibleToBuy(T h) {
        if(h.getLevel() < this.required_level){
            return false;
        }
        if(h.walletAmt() < this.cost){
            return false;
        }
        return true;
    }
} 
