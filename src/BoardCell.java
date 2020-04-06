public class BoardCell {
    private String type;
    public BoardCell(){
        this.type = "";
    }
    public BoardCell(String s){
        this.type = s;
    }
    public void setType(String s){
        this.type = s;
    }
    public String getType(){
        return this.type;
    }
}
