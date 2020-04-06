public class Strength_Potion extends Potion {
    public Strength_Potion(){
        super("Strength_Potion", 200, 1, 75);
    }

    @Override
    public void potionEffects(Hero h) {
        h.setStrength(this.attribute_increase);

    }
}
