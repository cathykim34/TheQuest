public class Healing_Potion extends Potion {
    public Healing_Potion(){
        super("Healing_Potion", 250, 1, 100);
    }

    @Override
    public void potionEffects(Hero hero) {
        hero.setHP(this.attribute_increase);
    }
}
