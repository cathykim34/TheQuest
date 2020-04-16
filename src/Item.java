import java.util.*;

public abstract class Item {
    public abstract int getCost();
    protected String type = "Weaponry";
    protected String name;
    protected int cost;
    protected int required_level;


    public Item(String name, int cost, int required_level){
        this.name = name;
        this.cost = cost;
        this.required_level = required_level;
    }
}
