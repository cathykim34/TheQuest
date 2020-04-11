public class BoardCell {
    private String type;
    private int capacity; // How many players can be in a cell at a given time
    private Characters[] contents; // Characters in the cell

    public BoardCell(){
        this.type = "";
        this.capacity = 2;
        this.contents = new Characters[2];
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

    public void removeCharacter(Characters characters) {

    }
}
