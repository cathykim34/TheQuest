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
            for(int j = 0; j < column; j++){
                this.boardArray[i][j] = createBoardCell();
                if(this.boardArray[i][j].getType().equals("C")){
                    this.current[i][j] = " ";
                }
                else{
                    this.current[i][j] = this.boardArray[i][j].getType();
                }
            }
        }
        this.current[Team.curRow][Team.curCol] = "X";
    }
    //check if input is a valid movement
    public boolean validInput(String s){
        return(s.equals("W")||s.equals("D")|| s.equals("A")|| s.equals("S")|| s.equals("I") || s.equals("Q"));
    }

    //check if chosen movement hits a wall
    public boolean wallExists(int col, int row){
        BoardCell b = this.boardArray[row][col];
        return(b.getType().equals("---"));
    }

    //check if chosen movement exists on board
    public boolean boardEdge(int col, int row){
        return(col >= this.column && row >= this.row);
    }

    //randomly assign each boardCell
    public BoardCell createBoardCell(){
        double num = Math.random();
        if(num <.3){
            return new BoardCell("M");
        }
        else if(num < .5){
            return new BoardCell("---");
        }
        else{
            return new BoardCell("C");
        }
    }

    //make move
    public void updateBoard(int col, int row){
        //so to show blank on map instead of C
        String currType = this.boardArray[Team.getCurRow()][Team.getCurCol()].getType();
        if(currType.equals("C")) {
            this.current[Team.getCurRow()][Team.getCurCol()] = "";
        }
        else{
            this.current[Team.getCurRow()][Team.getCurCol()] = currType;
        }
        this.current[row][col] = "X";
    }



    //returns whether that move is possible or not
    public boolean makeMove(Team t, String move) {
        boolean spotOpen = false;
        int tempCol = Team.getCurCol();
        int tempRow = Team.getCurRow();
        if(move.equals("W")){
            tempRow--;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                updateBoard(tempCol, tempRow);
                Team.setCurRow(tempRow);
                spotOpen = true;
            }
            else{
                System.out.println("Oof hit a wall!");
            }
        }
        else if(move.equals("A")){
            tempCol--;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                updateBoard(tempCol, tempRow);
                Team.setCurCol(tempCol);
                spotOpen = true;
            }
            else{
                System.out.println("Oof hit a wall!");
            }

        }
        else if(move.equals("S")){
            tempRow++;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                updateBoard(tempCol, tempRow);
                Team.setCurRow(tempRow);
                spotOpen = true;
            }
            else{
                System.out.println("Oof hit a wall!");
            }
        }
        else if (move.equals("D")){
            tempCol++;
            if(!wallExists(tempCol, tempRow) && !boardEdge(tempCol, tempRow)){
                updateBoard(tempCol, tempRow);
                Team.setCurCol(tempCol);
                spotOpen = true;
            }else{
                System.out.println("Oof hit a wall!");
            }
        }
        return spotOpen;
    }

    public String toString(){
        String boardPrint = "+";
        for(int col = 0; col < this.column; col++){
            boardPrint += "----+";
        }
        boardPrint += "\n";
        for(int r = 0; r<this.row; r++){
            for(int c = 0; c < this.column; c++){
                if(c == 0){
                    boardPrint += "| ";
                }
                boardPrint += String.format("%3s", this.current[r][c]) + "| ";

                if(c == this.column-1){
                    boardPrint += "\n";
                }
            }
        }
        boardPrint += ("+");
        for(int col = 0; col < this.column; col++){
            boardPrint += "----+";
        }
        boardPrint += "\n";
        return boardPrint;
    }

}
