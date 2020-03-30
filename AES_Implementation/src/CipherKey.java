public class CipherKey{
    int[][] sBox;

    int[][] roundKeys;

    int[][] rcon;

    public CipherKey(String cipherKey) {
        roundKeys = new int[4][44];

        int[] tmpArr = Matrix.hexStringToIntArray(cipherKey.replaceAll("\\s+",""));
        int count = 0;

        for(int col = 0; col< 4; col++){
            for(int row = 0; row < 4; row ++){
                roundKeys[row][col] = tmpArr[count];
                count ++;
            }
        }

        //Initialize sBox
        sBox = new int[16][16];
        String sBoxStr = "63cab7040953d051cd60e0e7ba70e18c 7c82fdc783d1efa30c8132c8783ef8a1 77c993232c00aa40134f3a3725b59889 7b7d26c31aedfb8fecdc0a6d2e66110d f2fa36181b2043925f22498d1c4869bf 6b593f966efc4d9d972a06d5a603d9e6 6f47f7055ab133384490244eb4f68e42 c5f0cc9aa05b85f517885ca9c60e9468 30ad3407526a45bcc446c26ce8619b41 01d4a5123bcbf9b6a7eed356dd351e99 67a2e580d6be02da7eb8acf47457872d 2baff1e2b3397f213d1462ea1fb9e90f fe9c71eb294a501064de91654b86ceb0 d7a4d827e34c3cff5d5e957abdc15554 ab7231b22f589ff3190be4ae8b1d28bb 76c0157584cfa8d273db79088a9edf16";

        tmpArr = Matrix.hexStringToIntArray(sBoxStr.replaceAll("\\s+",""));
        count = 0;
        for(int col = 0; col< 16; col++){
            for(int row = 0; row < 16; row ++){
                sBox[row][col] = tmpArr[count];
                count ++;
            }
        }

        //Initialize Rcon
        rcon = new int[4][10];
        String rConStr = "01000000 02000000 04000000 08000000 10000000 20000000 40000000 80000000 1b000000 36000000";

        tmpArr = Matrix.hexStringToIntArray(rConStr.replaceAll("\\s+",""));
        count = 0;
        for(int col = 0; col< 10; col++){
            for(int row = 0; row < 4; row ++){
                rcon[row][col] = tmpArr[count];
                count ++;
            }
        }

        createRoundKeys();
    }

    public void createRoundKeys(){
        int[] temp = new int[4];
        int[] prevWord = new int[4];
        int[] xOr = new int[4];

        int count = 4; // start at 4th word
        while (count < 44){
            for(int row = 0; row < 4; row ++){
                temp[row] = roundKeys[row][count - 1];
            }

            if (count % 4 == 0){
                for(int row = 0; row < 4; row ++){
                    xOr[row] = rcon[row][count/4 - 1];
                }
                temp = xOrFunc(subWord(rotWord(temp)), xOr);
            }
            for(int row = 0; row < 4; row ++){
                prevWord[row] = roundKeys[row][count - 4];
            }
            temp = xOrFunc(prevWord, temp);

            for(int row = 0; row < 4; row ++){
                roundKeys[row][count] = temp[row];
            }
            count++;
        }
    }

    public int[] rotWord(int[] word){
        int[] temp = new int[4];
        temp[0] = word[1];
        temp[1] = word[2];
        temp[2] = word[3];
        temp[3] = word[0];
        return temp;
    }

    public int[] subWord(int[] word){
        int[] temp = new int[4];

        int sBoxRow; int sBoxCol;
        for(int i = 0; i < 4; i ++){

            String hexVal = Integer.toHexString(word[i]);
            if(hexVal.length() == 1){
                hexVal = "0"+ hexVal;
            }

            sBoxRow = State.subBytesHelper(Character.toString(hexVal.charAt(0)));
            sBoxCol = State.subBytesHelper(Character.toString(hexVal.charAt(1)));
            temp[i] = sBox[sBoxRow][sBoxCol];
        }
        return temp;
    }

    public int[] xOrFunc(int[] word, int[] xOrWord){
        int[] temp = new int[4];
        temp[0] = word[0] ^ xOrWord[0];
        temp[1] = word[1] ^ xOrWord[1];
        temp[2] = word[2] ^ xOrWord[2];
        temp[3] = word[3] ^ xOrWord[3];
        return temp;
    }

    public int[][] getSpecificRoundKey(int round){
        int[][] roundKey = new int[4][4];

        int index = (4*(round));
        int count = 0;
        for (int col = index; col < index + 4; col++){
            for (int row = 0; row < 4; row ++){
                roundKey[row][count] = roundKeys[row][col];
            }
            count++;
        }
        return roundKey;
    }

    public String printAsHexOneLine(int round){
        int[][]roundKey = new int[4][4];
        roundKey = getSpecificRoundKey(round);

        String str = "";
        for(int i = 0; i < 4; i++){
            int[] col = getColumn(i, roundKey);
            for(int j=0;j<col.length;j++){
                String tmp = Integer.toHexString(col[j]);
                if(tmp.length() == 1){
                    tmp = "0" + tmp;
                }
                str += tmp;
            }
        }
        return str;
    }

    public int[] getColumn(int col, int[][] roundKey){
        int tmp[] = new int[4];
        for(int i=0; i< 4; i++){
            for(int j=0; j<4; j++){
                if(j == col){
                    tmp[i] = roundKey[i][j];
                }
            }
        }
        return tmp;
    }
}
