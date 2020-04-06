import java.util.*;

public interface Buyable{
    //if item can be bought
    <T extends Hero> boolean isPossibleToBuy(T hero);
    //for menus
    ArrayList<String> allGeneralTypes = new ArrayList<>();
    static void addAllTypes(){
        allGeneralTypes.add("Potions");
        allGeneralTypes.add("Spells");
        allGeneralTypes.add("Weapons");
        allGeneralTypes.add("Armory");
    }
    //selling price
    int sellPrice();
}
