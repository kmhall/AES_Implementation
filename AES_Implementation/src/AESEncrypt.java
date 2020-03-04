public class AESEncrypt {

    CipherKey cipherKey;
    State curState;

    public AESEncrypt(String inputStr, String cipherKeyStr) {

        curState = new State(inputStr);
        cipherKey = new CipherKey(cipherKeyStr);

        //curState.printAsHex();

    }

    void encrypt(){

        curState.printAsInt();

        curState.shiftRows();

        curState.printAsInt();

        //All encrypt steps done here

    }



}
