public class Luck_Elixir extends Potion {
    public Luck_Elixir(){
        super("Luck_Elixir", 500, 4, 65);
    }

    @Override
    public void potionEffects(Hero h) {
        h.setDexterity(this.attribute_increase);
    }
}
