import java.util.ArrayList;

public class IceSpell extends Spell implements Buyable{
    protected String type = "IceSpell";
    protected static ArrayList<Spell> all = new ArrayList<>();

    public IceSpell(String name, int cost, int required_level, int damage, int mana_cost) {
        super(name, cost, required_level, damage, mana_cost);
    }

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
    //selling price
    @Override
    public int sellPrice() {
        return this.cost/2;
    }

    //all existing types of ice spells
    public static void existingTypes() {
        IceSpell.all.add(new Snow_Canon());
        IceSpell.all.add(new Ice_Blade());
        IceSpell.all.add(new Frost_Blizzard());
        IceSpell.all.add(new Arctic_storm());
    }

    //returns all ice spells
    public static ArrayList<Spell> getAll(){
        return IceSpell.all;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public double getMana_cost(){
        return this.mana_cost;
    }
    //to print description
    public String toString(){
        String ret = "";
        ret += (this.type + ": " + this.name + "\n");
        ret += ("Price: " + this.cost + " gold pieces") + "\n";
        ret += ("Minimum level to obtain: " + this.required_level) + "\n";
        ret += ("Damage dealt: " + this.damage) + "\n";
        ret += ("Mana usage: " + this.mana_cost) + "\n";
        return ret;
    }
}
