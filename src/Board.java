import java.util.Random;

public class Board {
    public int row;
    public int column;
    public BoardCell[][] boardArray;
    String[][] current;

    public Board(int row, int column){
        this.row = row;
        this.column = column;
        this.boardArray = new BoardCell[row][column];
        this.current = new String[row][column];
        for(int i = 0; i < row; i++){
            if (i == 0 || i == row-1) {
                makeRow(i, true);
            } else {
                makeRow(i, false);
            }
        }
    }
    //check if input is a valid movement
    public boolean validInput(String s){
        return(s.equals("W")||s.equals("D")|| s.equals("A")|| s.equals("S")|| s.equals("I") || s.equals("Q"));
    }

    //check if chosen movement hits a wall
    public boolean wallExists(int col, int row){
        BoardCell b = this.boardArray[row][col];
        return(b.getType().equals("I"));
    }

    // CHeck if there is a fight to take place
    public boolean isFight() {
        return false;
    }


    //check if chosen movement exists on board
    public boolean boardEdge(int col, int row){
        return(col >= this.column && row >= this.row);
    }


    //returns whether that move is possible or not
    public boolean makeMove(Hero hero, String move) {
        boolean spotOpen = false;
        int tempCol = hero.getColumn();
        int tempRow = hero.getRow();
        if(move.equals("W")){
            tempRow--;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                BoardCell newCell = this.boardArray[tempRow][tempCol];
                if (!newCell.isFull()) {
                    BoardCell oldCell = this.boardArray[tempRow++][tempCol];
                    oldCell.removeCharacter(hero);
                    newCell.addCharacter(hero);
                    hero.setRow(tempRow);
                    spotOpen = true;
                } else {
                    System.out.println("Oops! This cell is at max capacity.");
                }
            }
            else{
                System.out.println("Oof hit a wall!");
            }
        }
        else if(move.equals("A")){
            tempCol--;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                BoardCell newCell = this.boardArray[tempRow][tempCol];
                if (!newCell.isFull()) {
                    BoardCell oldCell = this.boardArray[tempRow++][tempCol];
                    oldCell.removeCharacter(hero);
                    newCell.addCharacter(hero);
                    hero.setColumn(tempCol);
                    spotOpen = true;
                } else {
                    System.out.println("Oops! This cell is at max capacity.");
                }
            }
            else{
                System.out.println("Oof hit a wall!");
            }

        }
        else if(move.equals("S")){
            tempRow++;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                BoardCell newCell = this.boardArray[tempRow][tempCol];
                if (!newCell.isFull()) {
                    BoardCell oldCell = this.boardArray[tempRow++][tempCol];
                    oldCell.removeCharacter(hero);
                    newCell.addCharacter(hero);
                    hero.setRow(tempRow);
                    spotOpen = true;
                } else {
                    System.out.println("Oops! This cell is at max capacity.");
                }
            }
            else{
                System.out.println("Oof hit a wall!");
            }
        }
        else if (move.equals("D")){
            tempCol++;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                BoardCell newCell = this.boardArray[tempRow][tempCol];
                if (!newCell.isFull()) {
                    BoardCell oldCell = this.boardArray[tempRow++][tempCol];
                    oldCell.removeCharacter(hero);
                    newCell.addCharacter(hero);
                    hero.setColumn(tempCol);
                    spotOpen = true;
                } else {
                    System.out.println("Oops! This cell is at max capacity.");
                }
            }else{
                System.out.println("Oof hit a wall!");
            }
        }
        return spotOpen;
    }

    public String toString(){
        String result = "";

        for (int i = 0; i < this.row; i++) {
            printRow(i);
        }

        return result;
    }


    private String printRow(int rowIndex) {
        String result = "";

        // For each of the 4 lines to be printed out for each cell, where i is each line
        // 4 is hardcoded here because no matter how big the board, each cell will only
        // ever get printed out with a cell height of 4 lines
        for (int i = 0; i < 4; i++) {
            // For each column, j
            for (int j = 0; j < this.column; j++) {
                BoardCell cell = boardArray[rowIndex][j];
                if (i == 0 || i == 2) {
                    // The lines to be printed that designate the type of cell
                    cell.getType();
                    String cellBorder = cell.getCellBorder();
                    result += cellBorder;
                } else if (i == 1) {
                    String[] cellNicknames = this.cellNicknames(cell);
                    // The side borders of a cell
                    result += " | " + cellNicknames[0] + " " + cellNicknames[1] + " | ";
                } else {
                    // Blank area underneath cells
                    result += "           ";
                }
                result += "\n";
            }
        }

        return result;
    }


    private String[] cellNicknames(BoardCell cell) {
        Characters[] cellContents = cell.getContents();
        String[] result = new String[cellContents.length];
        for (int i = 0; i < cellContents.length; i++) {
            if (cellContents[i] != null) {
                // If there's a character in this cell, we want its nickname to display
                result[i] = cellContents[i].nickname;
            } else {
                // No character means nothing to display
                result[i] = "  ";
            }
        }
        return result;
    }


    private void makeRow(int rowIndex, boolean isNexusRow) {
        String result = "";
        String cellType = "N";

        for (int j = 0; j < this.column; j++) {
            if (!isNexusRow) {
                // If not a nexus row, we can pick a random cell type
                cellType = pickRandomCellType();
            }
            if (j == this.row/3 || j == (2*this.row)/3) {
                // These columns are the 2 barrier walls between the 3 lanes, so they are type "I"
                cellType = "I";
            }
            BoardCell cell = new BoardCell(cellType);
            this.boardArray[rowIndex][j] = cell;
        }
    }

    private static String pickRandomCellType() {
        String[] types = { "p", "p", "p", "p", "p", "p", "p", "k", "c", "b" };
        Random r = new Random();
        int randomIndex = r.nextInt(types.length);
        return types[randomIndex];
    }

}
