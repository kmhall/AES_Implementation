public class Matrix {
    int matrix[][];

    public Matrix(String input) {
        matrix = new int[4][4];

        int[] tmpArr = hexStringToIntArray(input.replaceAll("\\s+",""));
        int count = 0;

        for(int col = 0; col< 4; col++){
            for(int row = 0; row < 4; row ++){
                matrix[row][col] = tmpArr[count];
                count ++;
            }
        }
    }

    public int[] hexStringToIntArray(String s) {
        int len = s.length();
        int[] data = new int[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public void printAsHex(){
        for(int row = 0; row < 4; row++){
            System.out.print("[");
            for(int col = 0;col <4; col++){
                System.out.print(Integer.toHexString(matrix[row][col]));
                if(col != 3){
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
        }
        System.out.print("\n");

    }

    public void printAsInt(){
        for(int row = 0; row < 4; row++){
            System.out.print("[");
            for(int col = 0;col <4; col++){
                System.out.print(matrix[row][col]);
                if(col != 3){
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
        }
        System.out.print("\n");
    }

    public int getElement(int row, int col){
        return matrix[row][col];
    }

    public int[] getColumn(int col){
        int tmp[] = new int[4];
        for(int i=0; i< 4; i++){
            for(int j=0; j<4; j++){
                if(j == col){
                    tmp[i] = matrix[i][j];
                }
            }
        }
        return tmp;
    }

    public void replaceCol(int col, int[] mixColResult) {
        for(int i=0; i< 4; i++){
            for(int j=0; j<4; j++){
                if(j == col){
                    matrix[i][j] = mixColResult[i];
                 }
            }
        }
    }

    @Override
    public boolean equals(Object o){
        Matrix other = (Matrix) o;
        for(int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if(this.getElement(row,col) !=
                        other.getElement(row,col)){
                    return false;
                }
            }
        }
        return true;
    }


}
