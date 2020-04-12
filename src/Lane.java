public class Lane {
    private int laneNumber;
    private BoardCell[][] cells;

    public Lane(int laneNumber, BoardCell[][] cells){
        this.laneNumber = laneNumber;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].setLaneNumber(laneNumber);
                this.cells[i][j] = cells[i][j];
            }
        }
    }
}
