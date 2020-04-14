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

    //returns the nearest monster to hero to attack
    public Monster nearestMonster(Lane lane){
        ArrayList<Monster> appropriateMonsters = rightLane(lane);
        return closestMonster(appropriateMonsters);
    }

    //finds monster that is closest to hero's nexus
    public Monster closestMonster(ArrayList<Monster> monsters){
        Monster nearest = monsters.get(0);
        for(int i = 0; i < monsters.size(); i++){
            if(monsters.get(i).getRow() > nearest.getRow()){
                nearest = monsters.get(i);
            }
        }
        return nearest;
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
    public void addMonsters(Board board, HeroTeam heroTeam) {
        ArrayList<Monster> all = Monster.allPossible();
        ArrayList<Monster> monsterPossibilities = new ArrayList<>();
        for(int i = 0; i < heroTeam.numberHeroes; i++){
            Lane nexus = heroTeam.heroes.get(i).getNexus();
            int level = heroTeam.heroes.get(i).getLevel();
            int column = heroTeam.heroes.get(i).getColumn();
            if(monsterLevelExists(level)) {
                monsterPossibilities = potentialMonsters(level, all);
            }
            else{
                monsterPossibilities = potentialMonsters(Monster.getMaxLevel(), all);
            }
            this.monsters.add(chooseMonsters(board, monsterPossibilities, nexus, column));
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
    protected Monster chooseMonsters(Board board, ArrayList<Monster> m, Lane nexus, int column){
        Collections.shuffle(m);
        Monster mon = newCopy(m.get(0), nexus, column);
        board.boardArray[mon.getRow()][mon.getColumn()].addCharacter(mon);
        return mon;
    }

    //returns the new copy of chosen monster
    protected <T extends Monster>Monster newCopy(Monster m, Lane nexus, int column){
        String type = m.getType();
        if(type.equals("Exoskeleton")){
            return newInstanceExoskeleton(m, nexus, column);
        }
        else if(type.equals("Spirit")){
            return newInstanceSpirit(m, nexus,column);
        }
        else{
            return newInstanceDragon(m, nexus,column);
        }
    }

    //create new instance of Dragon
    private Dragon newInstanceDragon(Monster m, Lane nexus, int column){
        Dragon d = new Dragon(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
        d.setNexus(nexus);
        d.setLane(nexus);
        d.setRow(0);
        d.setColumn(column);
        d.setNickname("M!");
        return d;
    }

    //create new instance of Exoskeletons
    private Exoskeleton newInstanceExoskeleton(Monster m, Lane nexus, int column){
        Exoskeleton e = new Exoskeleton(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
        e.setNexus(nexus);
        e.setLane(nexus);
        e.setRow(0);
        e.setColumn(column);
        e.setNickname("M!");
        return e;
    }

    //create new instance of spirit
    private Spirit newInstanceSpirit(Monster m, Lane nexus, int column){
        Spirit s = new Spirit(m.getName(), m.getLevel(), m.getDamage(), m.getDefense(), m.getDodge_chance());
        s.setNexus(nexus);
        s.setLane(nexus);
        s.setRow(0);
        s.setColumn(column);
        s.setNickname("M!");
        return s;
    }

}
