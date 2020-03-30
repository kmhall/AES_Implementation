public class AESDecrypt {
    State curState;
    CipherKey cipherKey;

    public AESDecrypt(String key, String cipherText){
        curState = new State(cipherText);
        cipherKey = new CipherKey(key);
    }

    void decrypt(){
        String output; State after;

        output = "00112233445566778899aabbccddeeff";
        after = new State(output);

        int[][] roundKey = cipherKey.getSpecificRoundKey(10);
        curState.addRoundKey(roundKey);

        for (int i = 1; i < 10; i++){
            System.out.println("Round: "+ i);

            curState.invSubBytes();
            curState.invShiftRows();
            roundKey = cipherKey.getSpecificRoundKey(10 - i);
            curState.addRoundKey(roundKey);
            curState.invMixCol();
            curState.printAsHex();
        }

        curState.invSubBytes();
        curState.invShiftRows();
        roundKey = cipherKey.getSpecificRoundKey(0);
        curState.addRoundKey(roundKey);

        System.out.println("Decryption: " + curState.equals(after));
        //testDecrypt();
    }

    void testDecrypt(){
        //Tests are NIST Appendix C Round 1 of Decryption
        String input; State before;
        String output; State after;

        //Testing SubBytes
        input = "7ad5fda789ef4e272bca100b3d9ff59f";
        before = new State(input);
        output = "bdb52189f261b63d0b107c9e8b6e776e";
        after = new State(output);

        before.invSubBytes();
        System.out.println("InvSubBytes: " + before.equals(after));

        //Testing invShiftRow
        input = "bdb52189f261b63d0b107c9e8b6e776e";
        before = new State(input);
        output = "bd6e7c3df2b5779e0b61216e8b10b689";
        after = new State(output);

        before.invShiftRows();
        System.out.println("InvShiftRows: " + before.equals(after));

        //Testing invMixCol
        input = "bd6e7c3df2b5779e0b61216e8b10b689";
        before = new State(input);
        output = "4773b91ff72f354361cb018ea1e6cf2c";
        after = new State(output);

        before.invMixCol();
        System.out.println("InvMixCol: " + before.equals(after));
    }
}
