import java.util.*;

public class Market{
    protected ArrayList<Potion> potions;
    protected ArrayList<Spell> spells;
    protected ArrayList<Armory> armories;
    protected ArrayList<Weaponry> weapons;

    public Market(){
        this.potions = Potion.getAll();
        this.spells = (Spell.getAll());
        this.weapons = Weaponry.getAll();
        this.armories = Armory.getAll();
    }
    public ArrayList<Potion> getPotion(){
        return this.potions;
    }

    public ArrayList<Spell> getSpells(){
        return this.spells;
    }

    public ArrayList<Armory> getArmories(){
        return this.armories;
    }

    public ArrayList<Weaponry> getWeapons(){
        return this.weapons;
    }


    //prints all potions available
    public void allPotions(){
        for (Potion p: this.potions){
            System.out.println(p);
        }
    }

    //prints all weapons available
    public void allWeapons(){
        for (Weaponry w: this.weapons){
            System.out.println(w);
        }
    }
    //prints all armory available
    public void allArmory(){
        for (Armory a: this.armories){
            System.out.println(a);
        }
    }
    //prints all spells available
    public void allSpells(){
        for (Spell s: this.spells){
            System.out.println(s);
        }
    }


}
