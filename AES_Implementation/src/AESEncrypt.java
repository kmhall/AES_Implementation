public class AESEncrypt {

    CipherKey cipherKey;
    State curState;

    public AESEncrypt(String inputStr, String cipherKeyStr) {
        curState = new State(inputStr);
        cipherKey = new CipherKey(cipherKeyStr);
    }

    void encrypt(){
        int[][] roundKey = cipherKey.getSpecificRoundKey(0);

        System.out.println("round[ 0].input     " + curState.printAsHexOneLine());
        System.out.println("round[ 0].k_sch     " + cipherKey.printAsHexOneLine(0));
        curState.addRoundKey(roundKey);
        System.out.println("round[ 1].start     " + curState.printAsHexOneLine());

        for (int i = 1; i < 10; i++){
            curState.subBytes();
            System.out.println("round[ "+ i+"].s_box     " + curState.printAsHexOneLine());
            curState.shiftRows();
            System.out.println("round[ "+ i+"].s_row     " + curState.printAsHexOneLine());
            curState.mixColumns();
            System.out.println("round[ "+ i+"].m_col     " + curState.printAsHexOneLine());
            System.out.println("round[ "+ i+"].k_sch     " + cipherKey.printAsHexOneLine(i));
            roundKey = cipherKey.getSpecificRoundKey(i);
            curState.addRoundKey(roundKey);
        }
        System.out.println("round[ "+ 10+"].start    " + curState.printAsHexOneLine());
        curState.subBytes();
        System.out.println("round[ "+ 10+"].s_box    " + curState.printAsHexOneLine());
        curState.shiftRows();
        System.out.println("round[ "+ 10+"].s_row    " + curState.printAsHexOneLine());
        System.out.println("round[ "+10+"].k_sch    " + cipherKey.printAsHexOneLine(10));

        roundKey = cipherKey.getSpecificRoundKey(10);
        curState.addRoundKey(roundKey);
        System.out.println("round[ "+ 10+"].ioutput   " + curState.printAsHexOneLine());
    }

    void testEncrypt(){
        //Tests are NIST Appendix B Round 1
        String input; State before;
        String output; State after;

        //Testing RoundKey
        input = "3243f6a8885a308d313198a2e0370734";
        before = new State(input);
        output = "193de3bea0f4e22b9ac68d2ae9f84808";
        after = new State(output);

        int[][] roundKey = cipherKey.getSpecificRoundKey(0);
        before.addRoundKey(roundKey);
        System.out.println("AddRoundKey: " + before.equals(after));

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

        //Testing MixColumns
        input = "d4bf5d30e0b452aeb84111f11e2798e5";
        before = new State(input);
        output = "046681e5e0cb199a48f8d37a2806264c";
        after = new State(output);

        before.mixColumns();
        System.out.println("MixColumns: " + before.equals(after));

        //Testing RoundKey
        input = "046681e5e0cb199a48f8d37a2806264c";
        before = new State(input);
        output = "a49c7ff2689f352b6b5bea43026a5049";
        after = new State(output);

        roundKey = cipherKey.getSpecificRoundKey(1);
        before.addRoundKey(roundKey);
        System.out.println("AddRoundKey: " + before.equals(after));

    }
}
