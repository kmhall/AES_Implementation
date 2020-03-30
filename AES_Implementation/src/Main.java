public class Main {

    public static void main(String args[]){

        //Apendix C.1
        /****************/
        String apendixCplainText = "00112233445566778899aabbccddeeff";
        String apendixCcipherKey = "000102030405060708090a0b0c0d0e0f";

        System.out.println("PLAINTEXT:          " + apendixCplainText);
        System.out.println("KEY:                " + apendixCcipherKey);
        System.out.println("CIPHER (ENCRYPT): \n");

        AESEncrypt a = new AESEncrypt(apendixCplainText,apendixCcipherKey);
        a.encrypt();
        /****************/

        // Deliverable two
        String key = "000102030405060708090a0b0c0d0e0f";
        String cipherText = "69c4e0d86a7b0430d8cdb78070b4c55a";

        AESDecrypt b = new AESDecrypt(key, cipherText);
        b.decrypt();
    }

}

