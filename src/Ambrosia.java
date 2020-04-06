public class Ambrosia extends Potion {
    public Ambrosia(){
        super("Ambrosia", 1000, 8, 150);
    }

    @Override
    public void potionEffects(Hero h) {
        h.setAgility(this.attribute_increase);
    }
}
