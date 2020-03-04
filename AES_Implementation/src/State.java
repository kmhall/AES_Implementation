import java.util.Arrays;

public class State {

    //State values are converted from hex to int.
    int[][] state;
    int[][] sBox;

    public State(String input) {
        state = new int[4][4];

        int[] tmpArr = hexStringToIntArray(input.replaceAll("\\s+",""));
        int count = 0;

        //Init State
        for(int col = 0; col< 4; col++){
            for(int row = 0; row < 4; row ++){
                state[row][col] = tmpArr[count];
                count ++;
            }
        }
    }

    public void shiftRows(){

        for(int row = 1; row < state.length; row++){
            int[] tmp = Arrays.copyOf(state[row],4);

            for(int col = 0; col < state[0].length; col++){
                state[row][(col+(state[0].length - row)) % state.length ] = tmp[col];
            }
        }
    }

    public void subBytes(){
        String sBoxStr = "63cab7040953d051cd60e0e7ba70e18c 7c82fdc783d1efa30c8132c8783ef8a1 77c993232c00aa40134f3a3725b59889 7b7d26c31aedfb8fecdc0a6d2e66110d f2fa36181b2043925f22498d1c4869bf 6b593f966efc4d9d972a06d5a603d9e6 6f47f7055ab133384490244eb4f68e42 c5f0cc9aa05b85f517885ca9c60e9468 30ad3407526a45bcc446c26ce8619b41 01d4a5123bcbf9b6a7eed356dd351e99 67a2e580d6be02da7eb8acf47457872d 2baff1e2b3397f213d1462ea1fb9e90f fe9c71eb294a501064de91654b86ceb0 d7a4d827e34c3cff5d5e957abdc15554 ab7231b22f589ff3190be4ae8b1d28bb 76c0157584cfa8d273db79088a9edf16";

        int[][] sBox = new int[16][16];
        int[] tmpArr = hexStringToIntArray(sBoxStr.replaceAll("\\s+",""));
        int count = 0;
        for(int col = 0; col< 16; col++){
            for(int row = 0; row < 16; row ++){
                sBox[row][col] = tmpArr[count];
                count ++;
            }
        }

        int sBoxRow; int sBoxCol;
        for(int col = 0; col< 4; col++){
            for(int row = 0; row < 4; row ++){

                String hexVal = Integer.toHexString(state[row][col]);
                if(hexVal.length() == 1){
                    hexVal = "0"+ hexVal;
                }

                sBoxRow = subBytesHelper(Character.toString(hexVal.charAt(0)));
                sBoxCol = subBytesHelper(Character.toString(hexVal.charAt(1)));
                state[row][col] = sBox[sBoxRow][sBoxCol];
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
                System.out.print(Integer.toHexString(state[row][col]));
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
                System.out.print(state[row][col]);
                if(col != 3){
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
        }
        System.out.print("\n");
    }

    public int getElementInState(int row, int col){
        return state[row][col];
    }

    @Override
    public boolean equals(Object o){
        State other = (State) o;
        for(int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if(this.getElementInState(row,col) !=
                        other.getElementInState(row,col)){
                    return false;
                }
            }
        }
        return true;
    }
}
