import java.math.BigInteger;
import java.util.Arrays;

public class State extends Matrix{

    //State values are converted from hex to int.
    int[][] sBox;

    //Inverse sBox;
    int[][] invSBox;

    public State(String input) {
        super(input);

        //Initialize sBox
        sBox = new int[16][16];
        String sBoxStr = "63cab7040953d051cd60e0e7ba70e18c 7c82fdc783d1efa30c8132c8783ef8a1 77c993232c00aa40134f3a3725b59889 7b7d26c31aedfb8fecdc0a6d2e66110d f2fa36181b2043925f22498d1c4869bf 6b593f966efc4d9d972a06d5a603d9e6 6f47f7055ab133384490244eb4f68e42 c5f0cc9aa05b85f517885ca9c60e9468 30ad3407526a45bcc446c26ce8619b41 01d4a5123bcbf9b6a7eed356dd351e99 67a2e580d6be02da7eb8acf47457872d 2baff1e2b3397f213d1462ea1fb9e90f fe9c71eb294a501064de91654b86ceb0 d7a4d827e34c3cff5d5e957abdc15554 ab7231b22f589ff3190be4ae8b1d28bb 76c0157584cfa8d273db79088a9edf16";

        int[] tmpArr = hexStringToIntArray(sBoxStr.replaceAll("\\s+",""));
        int count = 0;
        for(int col = 0; col< 16; col++){
            for(int row = 0; row < 16; row ++){
                sBox[row][col] = tmpArr[count];
                count ++;
            }
        }

        //Initialize invSBox
        invSBox = new int[16][16];
        count = 0;
        String hex;
        int newRow;
        int newCol;
        for(int col = 0; col< 16; col++){
            for(int row = 0; row < 16; row ++){
                hex = Integer.toHexString(tmpArr[count]);
                if(hex.length() == 1){
                    hex = "0"+ hex;
                }

                newRow = Integer.parseInt(String.valueOf(hex.charAt(0)),16);
                newCol = Integer.parseInt(String.valueOf(hex.charAt(1)),16);

                hex = Integer.toHexString(row) + Integer.toHexString(col);

                invSBox[newRow][newCol] = Integer.parseInt(hex, 16);
                count ++;
            }
        }
    }

    public void shiftRows(){

        for(int row = 1; row < matrix.length; row++){
            int[] tmp = Arrays.copyOf(matrix[row],4);

            for(int col = 0; col < matrix[0].length; col++){
                matrix[row][(col+(matrix[0].length - row)) % matrix.length ] = tmp[col];
            }
        }
    }

    public void subBytes(){

        int sBoxRow; int sBoxCol;
        for(int col = 0; col< 4; col++){
            for(int row = 0; row < 4; row ++){

                String hexVal = Integer.toHexString(matrix[row][col]);
                if(hexVal.length() == 1){
                    hexVal = "0"+ hexVal;
                }

                sBoxRow = subBytesHelper(Character.toString(hexVal.charAt(0)));
                sBoxCol = subBytesHelper(Character.toString(hexVal.charAt(1)));
                matrix[row][col] = sBox[sBoxRow][sBoxCol];
            }
        }
    }

    public void mixColumns(){
        //Matrix multiplication
        for(int i = 0; i<4;i++){
            int col[] = super.getColumn(i);


            //Mix col res doesn't have correct val
            int mixColResult[] = mixColumnsHelper(col);

            super.replaceCol(i, mixColResult);
            //super.printAsHex();
        }


    }

    public int[] mixColumnsHelper(int[] col){
        int[] resultingCol = new int[4];

        
        resultingCol[0] = GFMult(2,col[0]) ^ GFMult(3,col[1]) ^ col[2] ^ col[3];
        resultingCol[1] = col[0] ^ GFMult(2,col[1]) ^ GFMult(3,col[2]) ^ col[3];
        resultingCol[2] = col[0] ^ col[1] ^ GFMult(2,col[2]) ^ GFMult(3,col[3]);
        resultingCol[3] = GFMult(3,col[0]) ^ col[1] ^ col[2] ^ GFMult(2,col[3]);
        return resultingCol;
    }

    //x^4 +1 in binary is 17
    public int GFMult(int x, int y){

        //X is shorter
        String xStr = Integer.toBinaryString(x);
        String yStr = Integer.toBinaryString(y);

        //System.out.print("0x"+Integer.toHexString(x) + " 0x" + Integer.toHexString(y) + "\n");
        //System.out.print(xStr + " " + yStr + "\n");

        int posVal = -1;
        int count = 0;
        int numBeforeMod = -1;
        for(int i = yStr.length()-1; i>= 0; i--){
            if(yStr.charAt(i) == '1'){
                if(posVal == -1){
                    posVal = (int)Math.pow(2,count) * x;
                    numBeforeMod = posVal;
                }else{
                    posVal = (int)Math.pow(2,count) * x;

                    numBeforeMod = numBeforeMod ^ posVal;
                }
                //System.out.println(posVal);
            }
            count++;
        }

        //System.out.println(" " + Integer.toBinaryString(numBeforeMod));

        int dividend = numBeforeMod;
        int divisor = 283;

        //System.out.println("^" + Integer.toBinaryString(divisor));

        while(dividend > divisor){
            String curDiv = Integer.toBinaryString(dividend);
            String firstPart = curDiv.substring(0,9);
            String secondPart = curDiv.substring(9);

            //System.out.println("First part " +firstPart);
            //System.out.println("Second Part " +secondPart);

            int f = Integer.parseInt(firstPart, 2);

            int xor = f ^ divisor;
            //System.out.println("XOR result " + Integer.toBinaryString(xor));

            String xorPart = Integer.toBinaryString(xor);
            String combined = xorPart + secondPart;

            //System.out.println("XOR Combined with previous last " + combined);

            dividend = Integer.parseInt(combined, 2);
            //System.out.println(" " + Integer.toBinaryString(dividend));
            //System.out.println("^" + Integer.toBinaryString(divisor));

        }

        return dividend ^ divisor;
    }



    public int subBytesHelper(String s){
        switch (s){
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "a":
                return 10;
            case "b":
                return 11;
            case "c":
                return 12;
            case "d":
                return 13;
            case "e":
                return 14;
            case "f":
                return 15;
        }
        return -1;
    }

    public void invSubBytes(){
        int invSBoxRow; int invSBoxCol;
        for(int col = 0; col< 4; col++){
            for(int row = 0; row < 4; row ++){

                String hexVal = Integer.toHexString(matrix[row][col]);
                if(hexVal.length() == 1){
                    hexVal = "0"+ hexVal;
                }

                invSBoxRow = subBytesHelper(Character.toString(hexVal.charAt(0)));
                invSBoxCol = subBytesHelper(Character.toString(hexVal.charAt(1)));

                matrix[row][col] = invSBox[invSBoxRow][invSBoxCol];
            }
        }
    }

    public void invShiftRows(){

        for(int row = 1; row < matrix.length; row++){
            int[] tmp = Arrays.copyOf(matrix[row],4);

            for(int col = 0; col < matrix[0].length; col++){
                matrix[row][(col+(matrix[0].length + row)) % matrix.length ] = tmp[col];
            }
        }
    }

    public void invMixCol(){
        Matrix invPolys = new Matrix("0e090d0b0b0e090d0d0b0e09090d0b0e");
        int s0col;
        int s1col;
        int s2col;
        int s3col;

        for(int col = 0; col< 4; col++){
            s0col = matrix[0][col];
            s1col = matrix[1][col];
            s2col = matrix[2][col];
            s3col = matrix[3][col];
            for(int mixRow = 0; mixRow< 4; mixRow++){
                matrix[mixRow][col] = invMixColHelper(invPolys.matrix[mixRow][0],invPolys.matrix[mixRow][1],invPolys.matrix[mixRow][2],invPolys.matrix[mixRow][3],
                        s0col,s1col,s2col,s3col);
            }
        }
    }

    public int invMixColHelper(int hex1, int hex2, int hex3, int hex4, int s0col, int s1col, int s2col, int s3col){
        // hex1 * s1col and so on

        int result = 0;
        return result;
    }
}
