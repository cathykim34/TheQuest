import java.util.*;

public class FightDriver {
    public FightDriver(){}
    public void play(){
        Scanner input = new Scanner(System.in);
        System.out.println("Surprise Battle!");
        System.out.println("Rules of the Game: ");
        System.out.println("Battle ends when all heroes have fainted (no HP) or all monsters have been defeated");
        System.out.println("Hero team is randomly ordered and play one at a time until the current hero KOs or wins the battle");
        System.out.println("Rounds consist of the current hero making a move(attack or changing armor/weapons) and then the monster's attack");
        System.out.println("Let's introduce the Opponents: ");

        //randomly create group of monsters of appropriate level
        ArrayList<Monster> all = Monster.allPossible();
        ArrayList<Monster> monsters = new ArrayList<>();

        //find correct level
        if(monsterLevelExists(Team.maxLevel())) {
            monsters = potentialMonsters(Team.maxLevel(), all);
        }
        else{
            monsters = potentialMonsters(Monster.getMaxLevel(), all);
        }
        monsters = chooseMonsters(Team.getNumHeroes(), monsters);

        //display monsters
        for(Monster m: monsters){
            System.out.println(m + "\n");
        }
        int index = 0;
        Monster currentMonster = monsters.get(index);

        boolean stillAlive = true;

        //randomize order of heroes
        Collections.shuffle(Team.getHeroes());
        for(Hero h: Team.getHeroes()){
            System.out.println("Welcome to the battle "+ h.getName() + "!!");
            do{
                h.currentStats();
                System.out.println( "\n");
                currentMonster.currentStats();
                boolean invalidMove = true;
                do{
                    //make your move
                    System.out.println("Enter your move: Enter A to attack, S to use Spell, P to use potion, C to change Armor/Weapons or I to see current stats");
                    try{
                        String in = input.next();
                        if(in.toUpperCase().equals("A")){
                            h.makeMove(currentMonster);
                            invalidMove = false;
                        }
                        else if(in.toUpperCase().equals("I")){
                            h.currentStats();
                        }
                        else if (in.toUpperCase().equals("S")){
                            if(h.getBP().getSpells().isEmpty()){
                                System.out.println("Currently do not own any Spells");
                            }
                            else {
                                useSpell(h, currentMonster);
                                invalidMove = false;
                            }
                        }
                        else if(in.toUpperCase().equals("P")){
                            if(h.getBP().getPotions().isEmpty()){
                                System.out.println("Currently do not own any Potions");
                            }
                            else{
                                usePotion(h);
                                System.out.println(h.getName() + "dealt no damage to " + currentMonster.getName());
                                invalidMove = false;
                            }
                        }
                        else if(in.toUpperCase().equals("C")){
                            //allows user to pick another move if changing is not possible
                            if(h.changeNotPossible()){
                                System.out.println("Currently do not own any weapons/armory");
                            }
                            else{
                                changeWorA(h);
                                invalidMove = false;
                            }
                        }
                        else{
                            System.out.println("Invalid input. Please try again.");
                        }
                    }catch (Exception e){
                        System.out.println("Invalid input. Please try again.");
                    }
                }while(invalidMove);

                //monster's turn to attack
                System.out.println("Monster's turn for attacking!");
                h.getAttacked(currentMonster);

                //check if current character fainted
                if(h.checkFainted()){
                    System.out.println("Oh no- looks like " + h.getName()+ " fainted!" + "\n");
                    break;
                }
                //check if monster was defeated
                else if(currentMonster.checkDeath()){
                    if(index == monsters.size()-1){
                        stillAlive = false;
                    }
                    else{
                        index++;
                        currentMonster = monsters.get(index);
                    }
                }
                //otherwise finish current round
                h.finishRound();

            }while(stillAlive);
        }
        checkWinner(Team.getHeroes(), monsters);

    }
    //carries out potions
    public void usePotion(Hero h){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            int i = 0;
            ArrayList<Potion> potions = h.getBP().getPotions();
            for(Potion s: potions){
                System.out.println(i + ": " + s);
            }
            System.out.println("Enter the corresponding number to the Potion:");
            try{
                String in = input.next();
                int chosen = Integer.parseInt(in);
                if(chosen >= 0 && chosen <potions.size()){
                    potions.get(i).potionEffects(h);
                    potions.remove(i);
                    invalidInput = false;
                }
                else{
                    System.out.println("Invalid input. Please try again.");
                }

            }catch(Exception e){
                System.out.println("Incorrect input. Please try again.");
            }

        }while(invalidInput);
    }


    //carries out spell castings
    public void useSpell(Hero h, Monster m){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            int i = 0;
            ArrayList<Spell> spells = h.getBP().getSpells();
            for(Spell s: spells){
                System.out.println(i + ": " + s);
            }

            System.out.println("Enter the corresponding number to the spell:");
            try{
                String in = input.next();
                int chosen = Integer.parseInt(in);
                if(chosen >= 0 && chosen <spells.size()){
                    Spell sp = spells.get(chosen);
                    if(canUseSpell(h, sp)){
                        spellCasting(h, sp, m);
                        spells.remove(chosen);
                        invalidInput = false;
                    }
                    else{
                        System.out.println("Not enough mana to use spell!");
                    }
                }
                else{
                    System.out.println("Invalid. Please try again");
                }
            }catch (Exception e){
                System.out.println("Invalid. Please try again.");
            }

        }while(invalidInput);


    }

    //helper function to getAttacked for dodge probability
    private boolean attackDodged(Monster m){
        double ran = Math.random();
        return(ran <= m.getDodge_chance());
    }

    //attack with spell
    public void spellCasting(Hero h, Spell s, Monster m){
        h.useMana(s.getMana_cost());
        if(!attackDodged(m)) {
            if (s.getType().equals("IceSpell")) {
                m.setDamage(0.01);
                m.getSpellCasted(s.getDamage());
                System.out.println(h.getName() + "dealt " + s.getDamage() + "to " + m.getName() + " and lowered damage by 10%");
            } else if (m.getType().equals("FireSpell")) {
                m.setDefense(0.01);
                m.getSpellCasted(s.getDamage());
                System.out.println(h.getName() + "dealt " + s.getDamage() + "to " + m.getName() + " and lowered defense by 10%");
            } else {
                m.setDodge_chance(0.01);
                m.getSpellCasted(s.getDamage());
                System.out.println(h.getName() + "dealt " + s.getDamage() + "to " + m.getName() + " and lowered dodge_chance by 10%");
            }
        }
        else{
            System.out.println("Spell casting dodged- no damage!");
        }

    }


    //function to see if spell usage is possible
    public boolean canUseSpell(Hero h, Spell s){
        if (h.getMana() < s.getMana_cost()){
            return false;
        }
        return true;
    }

    //check who the winner is and give reward to heroes if they won
    public void checkWinner(ArrayList<Hero> h, ArrayList<Monster> m){
        boolean allFainted = true;
        for(Hero hero: h){
            if(!hero.checkFainted()){
                allFainted = false;
            }
        }
        if(!allFainted){
            int level = 0;
            if(monsterLevelExists(Team.maxLevel())){
                level = Team.maxLevel();
            }
            else{
                level = Monster.getMaxLevel();
            }
            System.out.println("Congratulations the heroes won!");
            for(Hero hero: h){
                if(!hero.checkFainted()){
                    hero.increaseWalletAmt(100*level);
                    hero.addXP(2);
                }
                else{
                    hero.revive();
                }
            }
        }
        else{
            System.out.println("Unfortunately, the monsters won this round!");
            for(Hero hero:h){
                hero.decreaseWalletAmt(hero.walletAmt()/2);
                hero.revive();
            }
        }
    }


    //changing either armory or weapon
    public void changeWorA(Hero h){
        Scanner input = new Scanner(System.in);
        String chosenChange = chooseWeaponOrArmory(h);
        boolean invalidInput = true;
        int index = 0;
        if (chosenChange.equals("Armor")) {
            for(Armory a: h.getBP().getArmory()){
                System.out.println(index + ": "+ a);
                index++;
            }
            do{
                System.out.println("Choose the corresponding number to the armory you would like to equip");
                try{
                    String in = input.next();
                    int chosen = Integer.parseInt(in);
                    if(chosen >= 0 && chosen < h.getBP().getArmory().size()){
                        if(h.getBP().getArmory().get(chosen).isCurrently_using()){
                            System.out.println("Whoops! Already in use, pick another");
                        }
                        else{
                            h.equip(h.getBP().getArmory().get(chosen));
                            invalidInput = false;
                        }
                    }
                    else{
                        System.out.println("Invalid input.");
                    }

                }catch (Exception e){
                    System.out.println("Invalid input.");
                }
            }while(invalidInput);
        } else {
            for(Weaponry w: h.getBP().getWeapons()){
                System.out.println(index+ ": "+ w);
                index++;
            }
            do{
                System.out.println("Choose the corresponding number to the weapon you would like to equip");
                try{
                    String in = input.next();
                    int chosen = Integer.parseInt(in);
                    if(chosen >= 0 && chosen < h.getBP().getWeapons().size()){
                        if(h.getBP().getWeapons().get(chosen).isCurrently_using()){
                            System.out.println("Whoops! Already in use, pick another");
                        }
                        else{
                            h.equip(h.getBP().getWeapons().get(chosen));
                            invalidInput = false;
                        }
                    }
                    else{
                        System.out.println("Invalid input.");
                    }

                }catch (Exception e){
                    System.out.println("Invalid input.");
                }
            }while(invalidInput);
        }
    }

    //allow user to choose weapon or armory to change
    private String chooseWeaponOrArmory(Hero h){
        if(h.getBP().noArmor() && !h.getBP().noWeapons()){
            System.out.println("No armory to choose from!");
            return "Weapon";
        }
        else if(h.getBP().noWeapons() && h.getBP().noArmor()){
            System.out.println("No weapons to choose from!");
            return "Armor";
        }
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        do{
            System.out.println("Enter A for changing armory or W for weaponry");
            String in = input.next();
            try{
                if(in.toUpperCase().equals("A")){
                    return "Armory";
                }
                else if (in.toUpperCase().equals("W")){
                    return "Weapon";
                }
                else{
                    System.out.println("Invalid Input.");
                }

            }catch(Exception e){
                System.out.println("Invalid input.");
            }
        }while(invalidInput);
        return "";
    }

    //checks whether there is another monster to fight
    public boolean nextMonsterExists(int i, ArrayList<Monster> m){
        return((m.get(i+1)==null));
    }

    //gives next monster
    public Monster nextMonster(int i, ArrayList<Monster> m){
        return(m.get(i+1));
    }


    //checks whether max hero level is larger than max monster level
    public boolean monsterLevelExists(int i){
        return (i<= Monster.getMaxLevel());
    }

    //return list of potential monsters
    public ArrayList<Monster> potentialMonsters(int level, ArrayList<Monster> list){
        ArrayList<Monster> monsters = new ArrayList<>();
        for(Monster m: list){
            if(m.getLevel() == level){
                monsters.add(m);
            }
        }
        return monsters;
    }

    //choose randomly appropriate number of monsters
    public ArrayList<Monster> chooseMonsters(int i, ArrayList<Monster> m){
        ArrayList<Monster> ret = new ArrayList<>();
        Collections.shuffle(m);
        if(i > m.size()){
            ret.addAll(m);
            for(int j = 0; j < i-m.size(); j++){
                ret.add(newCopy(m.get(j)));
            }
        }
        else{
            for(int j = 0; j<i; j++){
                ret.add(m.get(j));
            }
        }
        return ret;
    }

    //returns the new copy of chosen monster
    private <T extends Monster>Monster newCopy(Monster m){
        String type = m.getType();
        if(type.equals("Exoskeleton")){
            return newInstanceExoskeleton(m);
        }
        else if(type.equals("Spirit")){
            return newInstanceSpirit(m);
        }
        else{
            return newInstanceDragon(m);
        }
    }

    //create new instance of Dragon
    private Dragon newInstanceDragon(Monster m){
        return new Dragon(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
    }

    //create new instance of Exoskeletons
    private Exoskeleton newInstanceExoskeleton(Monster m){
        return new Exoskeleton(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
    }

    //create new instance of spirit
    private Spirit newInstanceSpirit(Monster m){
        return new Spirit(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
    }

}
