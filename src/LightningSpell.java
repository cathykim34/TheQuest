import java.util.ArrayList;

public class LightningSpell extends Spell implements Buyable{
    protected String type = "LightningSpell";
    protected String name;
    protected int cost;
    protected int required_level;
    protected double damage;
    protected double mana_cost;
    protected static ArrayList<Spell> all = new ArrayList<>();


    public LightningSpell(String name, int cost, int required_level, int damage, int mana_cost){
        this.name = name;
        this.cost = cost;
        this.required_level = required_level;
        this.damage = damage;
        this.mana_cost = mana_cost;
    }

    //all existing lightning spells
    public static void existingTypes() {
        LightningSpell.all.add(new LightningDagger());
        LightningSpell.all.add(new Thunder_Blast());
        LightningSpell.all.add(new Electric_Arrows());
        LightningSpell.all.add(new Spark_Needles());
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

    public String getName(){
        return this.name;
    }

    //returns all spells
    public static ArrayList<Spell> getAll(){
        return LightningSpell.all;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }
    @Override
    public String getType() {
        return this.type;
    }
    //prints description of spell
    public String toString(){
        String ret = "";
        ret += (this.type + ": " + this.name + "\n");
        ret += ("Price: " + this.cost + " gold pieces") + "\n";
        ret += ("Minimum level to obtain: " + this.required_level) + "\n";
        ret += ("Damage dealt: " + this.damage) + "\n";
        ret += ("Mana usage: " + this.mana_cost) + "\n";
        return ret;
    }

    public double getMana_cost(){
        return this.mana_cost;
    }


}
