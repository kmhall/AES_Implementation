import java.util.Arrays;

public class State {

    int[][] state;



    public State(String input) {
        state = new int[4][4];

        int[] tmpArr = hexStringToIntArray(input.replaceAll("\\s+",""));
        int count = 0;
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
}
