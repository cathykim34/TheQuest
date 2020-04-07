import java.lang.reflect.Array;
import java.util.ArrayList;

public class Weaponry extends Item implements Buyable{
    protected String type = "Weaponry";
    protected String name;
    protected int cost;
    protected int required_level;
    protected int damage;
    protected int requiredHands;
    protected static ArrayList<Weaponry> all = new ArrayList<>();
    protected boolean currently_using;


    public Weaponry(String name, int cost, int level, int damage, int hands){
        this.name = name;
        this.cost = cost;
        this.required_level = level;
        this.damage = damage;
        this.requiredHands = hands;
        this.currently_using = false;
    }

    //checks if weapon is currently being used
    public boolean isCurrently_using(){
        return this.currently_using;
    }

    //put back weapon
    public void takeOff(){
        this.currently_using = false;
    }

    //get the damage
    public int getDamage(){
        return this.damage;
    }

    //hold weapon
    public void putOn(){
        this.currently_using = true;
    }


        //gather all existing types of weaponry
    public static void existingTypes() {
        Weaponry.all.add(new Sword());
        Weaponry.all.add(new Bow());
        Weaponry.all.add(new Scythe());
        Weaponry.all.add(new Axe());
        Weaponry.all.add(new Shield());
        Weaponry.all.add(new TSwords());
        Weaponry.all.add(new Dagger());
    }

    //return all existing types of weaponry
    public static ArrayList<Weaponry> getAll(){
        return Weaponry.all;
    }
    public String toString(){
        String ret = "";
        ret += (this.name + "\n");
        ret += ("Price: " + this.cost + " gold pieces") + "\n";
        ret += ("Minimum level to obtain: " + this.required_level) + "\n";
        ret += ("Damage dealt: " + this.damage) + "\n";
        ret += ("Number of hands required: " + this.requiredHands) + "\n";
        return ret;
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
    //returns cost
    public int getCost(){
        return this.cost;
    }

    @Override
    public int sellPrice() {
        return this.cost/2;
    }

}
