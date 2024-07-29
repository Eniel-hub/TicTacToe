public class Table {
    String[][] table = new String[3][3];

    public void newTable(){
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[0].length; j++){
                table[i][j] = " ";
            }
        }
    }

    public void printTable(){
        int rowNbr = 1;
        System.out.println();
        System.out.println("   1  2  3 ");
        System.out.print("  ---------");
        for(String[] col : table){
            System.out.println();
            System.out.print(rowNbr++ +" ");
            for(String row : col){
                System.out.print("|");
                System.out.print(row);
                System.out.print("|");
            }
        }
        System.out.println();
        System.out.println("  ---------");
        System.out.println();
    }

    public void insertToken (int row, int col, String token){
        table[row][col] = token;
    }

    public boolean isValid(int row, int col){
        try {
            return table[row][col].compareTo(" ") == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFull() {
        for (String[] cols : table) {
            for (String cells : cols) {
                if (cells.compareTo(" ") == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean isWon(){
        //check horizontally
        for (String[] row : table) {
            if (row[0].compareTo(" ") == 0)
                continue;
            if (row[0].compareTo(row[1]) == 0 && row[1].compareTo(row[2]) == 0)
                return true;
        }
        //check vertically
        for(int j = 0; j < table[0].length; j++){
            if(table[0][j].compareTo(" ") == 0)
                continue;
            if(table[0][j].compareTo(table[1][j]) == 0
                    && table[1][j].compareTo(table[2][j]) == 0)
                return true;
        }
        //check diagonals
        if(table[0][0].compareTo(" ") != 0){
            if(table[0][0].compareTo(table[1][1]) == 0
                    && table[1][1].compareTo(table[2][2]) == 0)
                return true;
        }
        if(table[2][0].compareTo(" ") != 0){
            return table[0][2].compareTo(table[1][1]) == 0
                    && table[1][1].compareTo(table[2][0]) == 0;
        }

        return false;
    }
}
