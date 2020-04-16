import java.util.ArrayList;

public class FireSpell extends Spell implements Buyable{
    protected String type = "FireSpell";
    protected static ArrayList<Spell> all = new ArrayList<>();

    public FireSpell(String name, int cost, int required_level, int damage, int mana_cost) {
        super(name, cost, required_level, damage, mana_cost);
    }


    //list of all required spells
    public static void existingTypes() {
        FireSpell.all.add(new Flame_Tornado());
        FireSpell.all.add(new Breath_of_Fire());
        FireSpell.all.add(new Heat_Wave());
        FireSpell.all.add(new Lava_Commet());
    }

    //checks whether sufficient funds and level
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

    public double getMana_cost(){
        return this.mana_cost;
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
    public static ArrayList<Spell> getAll(){
        return FireSpell.all;
    }

    //Display all info of spells
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
