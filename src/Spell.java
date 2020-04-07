import java.util.*;

public abstract class Spell extends Item implements Buyable {
    protected String type = "Spell";
    protected String name;
    protected int cost;
    protected int required_level;
    protected double damage;
    protected double mana_cost;
    protected static ArrayList<Spell> all = new ArrayList<>();

    //adds all existing spells to array
    public static void existingTypes(){
        all.addAll(IceSpell.getAll());
        all.addAll(FireSpell.getAll());
        all.addAll(LightningSpell.getAll());
    }

    public static ArrayList<Spell> getAll(){
        return Spell.all;
    }


    public String toString(){
        String ret = "";
        ret += (this.type + ": " + this.name + "\n");
        ret += ("Price: " + this.cost + " gold pieces") + "\n";
        ret += ("Minimum level to obtain: " + this.required_level) + "\n";
        ret += ("Damage dealt: " + this.damage) + "\n";
        ret += ("Mana usage: " + this.mana_cost) + "\n";
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

    public abstract double getMana_cost();
    public abstract String getName();
    public abstract double getDamage();
    public abstract String getType();

    //check whether high enough level + enough money
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
