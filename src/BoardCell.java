public class BoardCell {
    private String type;
    private int capacity; // How many players can be in a cell at a given time
    private Characters[] contents; // Characters in the cell

    public BoardCell(){
        this.type = "";
        this.capacity = 2;
        this.contents = new Characters[this.capacity];
    }
    public BoardCell(String s){
        this.type = s;
        this.capacity = 2;
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
}
