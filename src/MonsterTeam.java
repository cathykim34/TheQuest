import java.util.*;

public class MonsterTeam extends Team {
    protected ArrayList<Monster> monsters;

    public MonsterTeam(){
        monsters = new ArrayList<>();
    }

    public ArrayList<Monster> getTeam() {
        return this.monsters;
    }

    //randomly create group of monsters of appropriate level
    public void addMonsters(HeroTeam heroTeam) {
        ArrayList<Monster> all = Monster.allPossible();
        ArrayList<Monster> monsterPossibilities = new ArrayList<>();
        for(int i = 0; i < heroTeam.numberHeroes; i++){
            int level = heroTeam.heroes.get(i).getLevel();
            if(monsterLevelExists(level)) {
                monsterPossibilities = potentialMonsters(level, all);
            }
            else{
                monsterPossibilities = potentialMonsters(Monster.getMaxLevel(), all);
            }
            this.monsters.add(chooseMonsters(monsterPossibilities, heroTeam.heroes.get(i).getLane()));
        }
    }

    //checks whether hero level is larger than max monster level
    protected boolean monsterLevelExists(int i){
        return (i<= Monster.getMaxLevel());
    }

    //return list of potential monsters
    protected ArrayList<Monster> potentialMonsters(int level, ArrayList<Monster> list){
        ArrayList<Monster> monsters = new ArrayList<>();
        for(Monster m: list){
            if(m.getLevel() == level){
                monsters.add(m);
            }
        }
        return monsters;
    }

    //choose monster randomly
    protected Monster chooseMonsters(ArrayList<Monster> m, Lane lane){
        Collections.shuffle(m);
        return newCopy(m.get(0), lane);
    }

    //returns the new copy of chosen monster
    protected <T extends Monster>Monster newCopy(Monster m, Lane lane){
        String type = m.getType();
        if(type.equals("Exoskeleton")){
            return newInstanceExoskeleton(m, lane);
        }
        else if(type.equals("Spirit")){
            return newInstanceSpirit(m, lane);
        }
        else{
            return newInstanceDragon(m, lane);
        }
    }

    //create new instance of Dragon
    private Dragon newInstanceDragon(Monster m, Lane lane){
        return new Dragon(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
    }

    //create new instance of Exoskeletons
    private Exoskeleton newInstanceExoskeleton(Monster m, Lane lane){
        return new Exoskeleton(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
    }

    //create new instance of spirit
    private Spirit newInstanceSpirit(Monster m, Lane lane){
        return new Spirit(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
    }

}
