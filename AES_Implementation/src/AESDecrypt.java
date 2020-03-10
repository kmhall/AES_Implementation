public class AESDecrypt {
    State curState;
    String text;

    public AESDecrypt(String key, String cipherText){
        curState = new State(cipherText);
        text = cipherText;
    }

    void decrypt(){
        testDecrypt();
    }

    void testDecrypt(){
        //Tests are NIST Appendix B Round 1
        String input; State before;
        String output; State after;

        //Testing invShiftRow
        input = "d4bf5d30e0b452aeb84111f11e2798e5";
        before = new State(input);
        output = "d42711aee0bf98f1b8b45de51e415230";
        after = new State(output);

        before.invShiftRows();
        System.out.println("InvShiftRows: " + before.equals(after));
    }
}
