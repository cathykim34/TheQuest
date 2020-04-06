public class Magic_Potion extends Potion {
    public Magic_Potion(){
        super("Magic_Potion", 350, 2, 100);
    }

    @Override
    public void potionEffects(Hero h) {
        h.setMana(this.attribute_increase);
    }
}
