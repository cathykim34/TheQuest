public class BoardCell {
    private String type;
    private int capacity; // How many players can be in a cell at a given time
    private Characters[] contents; // Characters in the cell
    private int row;
    private int col;

    public boolean getIsHeroNexus() {
        return isHeroNexus;
    }

    public void setHeroNexus(boolean heroNexus) {
        isHeroNexus = heroNexus;
    }

    private boolean isHeroNexus;

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    private int laneNumber;

    public BoardCell(int row, int col){
        this.row = row;
        this.col = col;
        this.type = "";
        this.capacity = 2;
        this.contents = new Characters[this.capacity];
        this.isHeroNexus = false;
    }
    public BoardCell(int row, int col, String s){
        this.row = row;
        this.col = col;
        this.type = s;
        this.capacity = 2;
        this.contents = new Characters[this.capacity];
        this.isHeroNexus = false;
    }
    public void setType(String s){
        this.type = s;
    }
    public String getType(){
        return this.type;
    }
    public String getCellBorder(){
        return " " + this.type + " - " + this.type + " - " + this.type + " ";
    }

    public Characters[] getContents() {
        return contents;
    }

    public void setContents(Characters[] contents) {
        this.contents = contents;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void removeCharacter(Characters character) {
        for (int i = 0; i < contents.length; i++) {
            if (contents[i].name.equals(character.name)) {
                contents[i] = null;
            }
        }
    }

    public void addCharacter(Characters character) {
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] == null) {
                contents[i] = character;
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] == null) {
                return false;
            }
        }
        return true;

    }

    public void cellAction(Hero hero) {
        if (this.type.equals("B")) {
            // Bush cells
            int dexterity = hero.getDexterity();
            dexterity = (int) Math.round(dexterity + (dexterity*0.1));
            hero.setDexterity(dexterity);
        } else if (this.type.equals("K")) {
            // Koulou cells
            int strength = hero.getStrength();
            strength = (int) Math.round(strength + (strength*0.1));
            hero.setStrength(strength);
        } else if (this.type.equals("C")) {
            // Cave cells
            int agility = hero.getAgility();
            agility = (int) Math.round(agility + (agility*0.1));
            hero.setAgility(agility);
        } else if (this.type.equals("N")) {
            // Nexus cells
            if (!isHeroNexus) {
                // TODO: game won action
            } else {
                nexusCellAction(hero);
            }
        }
    }

    public void nexusCellAction(Hero hero) {
        hero.revive();

        Market market = new Market();
        MarketDriver driver = new MarketDriver();
        driver.play(market);
    }

}
