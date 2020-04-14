public class Lane {

    private int laneNumber;
    private BoardCell[][] cells;

    public Lane(int laneNumber, BoardCell[][] cells){
        this.laneNumber = laneNumber;
        this.cells = new BoardCell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].setLaneNumber(laneNumber);
                this.cells[i][j] = cells[i][j];
            }
        }
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public BoardCell[][] getCells() {
        return cells;
    }
}
