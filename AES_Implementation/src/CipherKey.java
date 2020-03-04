public class CipherKey {

    int[] cipherKeyArr;

    public CipherKey(String cipherKey) {
        cipherKeyArr = hexStringToIntArray(cipherKey.replaceAll("\\s+",""));
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
        System.out.print("[");
        for(int i=0; i< cipherKeyArr.length; i++){
            System.out.print(Integer.toHexString(cipherKeyArr[i]));
            if(i != cipherKeyArr.length -1){
                System.out.print(",");
            }
        }
        System.out.print("]\n");
    }

    public void printAsInt(){
        System.out.print("[");
        for(int i=0; i< cipherKeyArr.length; i++){
            System.out.print(cipherKeyArr[i]);
            if(i != cipherKeyArr.length -1){
                System.out.print(",");
            }
        }
        System.out.print("]\n");
    }


}
