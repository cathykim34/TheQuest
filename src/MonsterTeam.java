import java.util.*;
public class MonsterTeam extends Team {
    protected ArrayList<Monster> monsters;

    public MonsterTeam(){
        monsters = new ArrayList<>();
    }

    @Override
    public ArrayList<? extends Characters> getTeam() {
        return this.monsters;
    }

    public void addMonsters(HeroTeam heroes) {

    }


}
