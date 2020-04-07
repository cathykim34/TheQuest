import java.util.*;
public abstract class Potion extends Item implements Buyable{
    protected String type = "Potion";
    protected String name;
    protected int cost;
    protected int required_level;
    protected int attribute_increase;
    protected static ArrayList<Potion> all = new ArrayList<>();

    public Potion(String name, int cost, int required_level, int attribute_increase){
        this.name = name;
        this.cost = cost;
        this.required_level = required_level;
        this.attribute_increase = attribute_increase;
    }

    public abstract void potionEffects(Hero h);

    //list of all types of potions for this assignment
    public static void existingTypes(){
        Potion.all.add(new Healing_Potion());
        Potion.all.add(new Strength_Potion());
        Potion.all.add(new Magic_Potion());
        Potion.all.add(new Luck_Elixir());
        Potion.all.add(new Mermaid_Tears());
        Potion.all.add(new Ambrosia());
    }

    //returns all potions
    public static ArrayList<Potion> getAll(){
        return Potion.all;
    }

    //returns cost
    public int getCost(){
        return this.cost;
    }

    //selling price
    @Override
    public int sellPrice() {
        return this.cost/2;
    }
    public String toString(){
        String ret = "";
        ret += (this.type + ": " + this.name + "\n");
        ret += ("Price: " + this.cost + " gold pieces") + "\n";
        ret += ("Minimum level to obtain: " + this.required_level) + "\n";
        return ret;
    }
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
