import java.lang.reflect.Array;
import java.util.*;

public class MonsterTeam extends Team {
    protected ArrayList<Monster> monsters;
    protected static int rounds;

    public MonsterTeam(){
        monsters = new ArrayList<>();
        rounds = 1;
    }

    public ArrayList<Monster> getTeam() {
        return this.monsters;
    }

    //return appropriate list of monsters in a lane
    public ArrayList<Monster> rightLane(Lane lane){
        ArrayList<Monster> laneMonsters = new ArrayList<>();
        for(int i = 0; i < this.monsters.size(); i++){
            if(this.monsters.get(i).getLane().getLaneNumber() == lane.getLaneNumber()){
                laneMonsters.add(this.monsters.get(i));
            }
        }
        return laneMonsters;
    }

    //finds monster that is closest to hero's nexus
    public int[] closestMonster(ArrayList<Monster> monsters){
        int[] coordinates = new int[2];
        int maxRow = monsters.get(0).getRow();
        int maxCol = monsters.get(0).getColumn();
        for(int i = 0; i < monsters.size(); i++){
            if(monsters.get(i).getRow() > maxRow){
                maxRow = monsters.get(i).getRow();
                maxCol = monsters.get(i).getColumn();
            }
        }
        coordinates[0] = maxRow;
        coordinates[1] = maxCol;
        return coordinates;
    }

    public static int getRounds() {
        return rounds;
    }

    public static void increaseRound() {
        MonsterTeam.rounds++;
    }
     public static void setRounds(int round){
        MonsterTeam.rounds = round;
     }

    //randomly create group of monsters of appropriate level
    public void addMonsters(HeroTeam heroTeam) {
        ArrayList<Monster> all = Monster.allPossible();
        ArrayList<Monster> monsterPossibilities = new ArrayList<>();
        for(int i = 0; i < heroTeam.numberHeroes; i++){
            Lane nexus = heroTeam.heroes.get(i).getNexus();
            int level = heroTeam.heroes.get(i).getLevel();
            if(monsterLevelExists(level)) {
                monsterPossibilities = potentialMonsters(level, all);
            }
            else{
                monsterPossibilities = potentialMonsters(Monster.getMaxLevel(), all);
            }
            this.monsters.add(chooseMonsters(monsterPossibilities, nexus));
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
    protected Monster chooseMonsters(ArrayList<Monster> m, Lane nexus){
        Collections.shuffle(m);
        return newCopy(m.get(0), nexus);
    }

    //returns the new copy of chosen monster
    protected <T extends Monster>Monster newCopy(Monster m, Lane nexus){
        String type = m.getType();
        if(type.equals("Exoskeleton")){
            return newInstanceExoskeleton(m, nexus);
        }
        else if(type.equals("Spirit")){
            return newInstanceSpirit(m, nexus);
        }
        else{
            return newInstanceDragon(m, nexus);
        }
    }

    //create new instance of Dragon
    private Dragon newInstanceDragon(Monster m, Lane nexus){
        Dragon d = new Dragon(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
        d.setNexus(nexus);
        d.setLane(nexus);
        return d;
    }

    //create new instance of Exoskeletons
    private Exoskeleton newInstanceExoskeleton(Monster m, Lane nexus){
        Exoskeleton e = new Exoskeleton(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
        e.setNexus(nexus);
        e.setLane(nexus);
        return e;
    }

    //create new instance of spirit
    private Spirit newInstanceSpirit(Monster m, Lane nexus){
        Spirit s = new Spirit(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
        s.setNexus(nexus);
        s.setLane(nexus);
        return s;
    }

}
