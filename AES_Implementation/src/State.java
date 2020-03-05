import java.util.Arrays;

public class State extends Matrix{

    //State values are converted from hex to int.
    int[][] sBox;

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




}
