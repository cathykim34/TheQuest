import java.util.*;
public class Backpack {
    protected ArrayList<Potion> potions;
    protected ArrayList<Weaponry> weapons;
    protected ArrayList<Armory> armor;
    protected ArrayList<Spell> spells;
    protected static ArrayList<String> all = new ArrayList<>();

    public Backpack(){
        this.potions = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.armor = new ArrayList<>();
        this.spells = new ArrayList<>();
    }

    //checks if entire backpack is empty
    public boolean isEmpty(){
        return(this.potions.isEmpty() && this.weapons.isEmpty() && this.armor.isEmpty() && this.spells.isEmpty());
    }

    //checks whether there is any armor
    public boolean noArmor(){
        return(this.armor.size() == 0);
    }

    //checks whether there is any weapons
    public boolean noWeapons(){
        return(this.weapons.size() == 0);
    }

    //add spell to backpack
    public void addSpell(Spell s){
        this.spells.add(s);
    }
    //delete spell once used or sold
    public void deleteSpell(Spell s){
        for(int i = 0; i < this.spells.size(); i++){
            if(this.spells.get(i) == s){
                this.spells.remove(i);
                break;
            }
        }
    }
    //get all potions
    public ArrayList<Potion> getPotions(){
        return this.potions;
    }

    //get all spells
    public ArrayList<Spell> getSpells(){
        return this.spells;
    }

    //get all Armory
    public ArrayList<Armory> getArmory(){
        return this.armor;
    }

    //get all weapons
    public ArrayList<Weaponry> getWeapons(){
        return this.weapons;
    }

    //add potion to backpack
    public void addPotion(Potion p){
        this.potions.add(p);
    }

    //delete potion once used or sold
    public void deletePotion(Potion p){
        for(int i = 0; i < this.potions.size(); i++){
            if(this.potions.get(i) == p){
                this.potions.remove(i);
                break;
            }
        }
    }

    //add armor to backpack
    public void addArmor(Armory a){
        this.armor.add(a);
    }

    //delete armor once sold
    public void deleteArmor(Armory a){
        for(int i = 0; i < this.armor.size(); i++){
            if(this.armor.get(i) == a){
                this.armor.remove(i);
                break;
            }
        }
    }

    //add weapon to backpack
    public void addWeapon(Weaponry w){
        this.weapons.add(w);
    }

    //delete weapon once used or sold
    public void deleteWeapon(Weaponry w){
        for(int i = 0; i < this.weapons.size(); i++){
            if(this.weapons.get(i) == w){
                this.weapons.remove(i);
                break;
            }
        }
    }

    public String toString(){
        String ret = "";
        ret += ("Items in backpack:" + "\n");
        ret += ("Weapons:" + "\n");
        if(this.weapons.size() == 0){
            ret += ("None"+ "\n");
        }
        else{
            for(Weaponry w: this.weapons){
                ret += w+ "\n";
            }
        }
        ret += ("Armory:" + "\n");
        if(this.armor.size() == 0){
            ret += ("None"+ "\n");
        }
        else{
            for(Armory w: this.armor){
                ret += w + "\n";
            }
        }
        ret += ("Spells:" + "\n");
        if(this.spells.size() == 0){
            ret += ("None" + "\n");
        }
        else{
            for(Spell w: this.spells){
                ret += w+ "\n";
            }
        }

        ret += ("Potions:" + "\n");
        if(this.potions.size() == 0){
            ret += "None" + "\n";
        }
        else{
            for(Potion w: this.potions){
                ret += w+ "\n";
            }
        }
        return ret;
    }
}
