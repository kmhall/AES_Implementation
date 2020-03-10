public class AESEncrypt {

    CipherKey cipherKey;
    State curState;

    public AESEncrypt(String inputStr, String cipherKeyStr) {
        curState = new State(inputStr);
        cipherKey = new CipherKey(cipherKeyStr);
    }

    void encrypt(){
        testEncrypt();
    }

    void testEncrypt(){
        //Tests are NIST Appendix B Round 1
        String input; State before;
        String output; State after;

        //Testing SubBytes
        input = "193de3bea0f4e22b9ac68d2ae9f84808";
        before = new State(input);
        output = "d42711aee0bf98f1b8b45de51e415230";
        after = new State(output);

        before.subBytes();
        System.out.println("SubBytes: " + before.equals(after));


        //Testing ShiftRow
        input = "d42711aee0bf98f1b8b45de51e415230";
        before = new State(input);
        output = "d4bf5d30e0b452aeb84111f11e2798e5";
        after = new State(output);

        before.shiftRows();
        System.out.println("ShiftRows: " + before.equals(after));

        /*Testing MixColumns
        input = "d4bf5d30e0b452aeb84111f11e2798e5";
        before = new State(input);
        output = "046681e5e0cb199a48f8d37a2806264c";
        after = new State(output);


        before.shiftRows();
        System.out.println("MixColumns: " + before.equals(after));
         */
    }
}
