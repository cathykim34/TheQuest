import java.lang.reflect.Array;
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

    //begins the choosing of term team
    public HeroTeam createHeroTeam(Board board){
        System.out.println("Now lets pick your team of heroes!");
        HeroTeam t = new HeroTeam();
        t.addHeroes(board);
        return t;
    }

    //creates the monster team depending on the heroes
    public MonsterTeam createMonsterTeam(HeroTeam heroes, Board board){
        System.out.println("Meet your opponents!");
        MonsterTeam m = new MonsterTeam();
        m.addMonsters(board, heroes);
        for(Characters monster : m.getTeam()){
            System.out.println(monster);
        }
        return m;
    }

    //probability that battle appears in common tile
    private boolean battleAppeared() {
        double battle_chance = 0.75;
        double ran = Math.random();
        return(ran <= battle_chance);
    }

    //if randomly appears, begin fight sequences
    public void checkForBattle(Board board){
        if(battleAppeared()){
            FightDriver FD = new FightDriver();
            FD.play(board);
        }
    }

    //checks if monsters won
    public boolean monstersWon(MonsterTeam monsters){
        for(Monster m: monsters.getTeam()){
            if(m.isReachedNexus()){
                return true;
            }
        }
        return false;
    }

    //gives user choice to take potion or put on armory/weapon
    public String giveOptions(){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            System.out.println("If you would like to take a potion enter P, if you would like to change armory or weapons enter C, if you would like to cast a spell enter S or if none of these, enter N ");
            try{
                String in = input.next();
                String letter = in.toUpperCase();
                if(letter.equals("P")){
                    return "P";
                }
                else if(letter.equals("C")){
                    return"C";
                }
                else if(letter.equals("S")){
                    return"S";
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
