import java.util.*;
public class QuestDriver{

    public QuestDriver(){}

    public void createHeroes(){
        Paladin.existingTypes();
        Warrior.existingTypes();
        Sorcerer.existingTypes();
    }

    //create instance of all items necessary for market
    //these items only need one instance
    public void setUpMarket(){
        IceSpell.existingTypes();
        FireSpell.existingTypes();
        LightningSpell.existingTypes();
        Spell.existingTypes();
        Potion.existingTypes();
        Weaponry.existingTypes();
        Armory.existingTypes();
        Buyable.addAllTypes();
    }

    //creates all necessary elements for the market
    public void market(){
        Weaponry.existingTypes();
        Armory.existingTypes();
        Market m = new Market();
        MarketDriver md = new MarketDriver();
        md.play(m);
    }

    public Team createTeam(){
        System.out.println("Now lets pick your team of heroes!");
        Team t = new Team();
        t.addHeroes();
        return t;
    }

    //probability that battle appears in common tile
    private boolean battleAppeared() {
        double battle_chance = 0.75;
        double ran = Math.random();
        return(ran <= battle_chance);
    }

    //if randomly appears, begin fight sequence
    public void checkForBattle(){
        if(battleAppeared()){
            FightDriver FD = new FightDriver();
            FD.play();
        }
    }

    //gives user choice to take potion or put on armory/weapon
    public String giveOptions(){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            System.out.println("If you would like to take a potion enter P, if you would like to change armory or weapons enter C, or if none of these, enter N ");
            try{
                String in = input.next();
                String letter = in.toUpperCase();
                if(letter.equals("P")){
                    return "P";
                }
                else if(letter.equals("C")){
                    return"C";
                }
                else if(letter.equals("N")){
                    return"N";
                }else{
                    System.out.println("Invalid input, please try again.");
                }

            }catch(Exception e){
                System.out.println("Invalid input, please try again.");
            }

        }while(invalidInput);
        return "";
    }



}
