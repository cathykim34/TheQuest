public class Mermaid_Tears extends Potion {
    public Mermaid_Tears(){
        super("Mermaid_Tears", 850, 5, 100);
    }

    @Override
    public void potionEffects(Hero h) {
        h.setAgility( this.attribute_increase);
    }
}
