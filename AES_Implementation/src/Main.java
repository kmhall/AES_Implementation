public class Main {

    public static void main(String args[]){

        //Apendix C.1
        /****************/
        String apendixCplainText = "00112233445566778899aabbccddeeff";
        String apendixCcipherKey = "000102030405060708090a0b0c0d0e0f";

        //String apendixBplainText = "32 43 f6 a8 88 5a 30 8d 31 31 98 a2 e0 37 07 34";
        //String apendixBcipherKey = "2b 7e 15 16 28 ae d2 a6 ab f7 15 88 09 cf 4f 3c";
        System.out.println("PLAINTEXT:          " + apendixCplainText);
        System.out.println("KEY:                " + apendixCcipherKey);
        System.out.println("CIPHER (ENCRYPT): \n");

        AESEncrypt a = new AESEncrypt(apendixCplainText,apendixCcipherKey);
        a.encrypt();
        /****************/
        System.out.println();

        // Deliverable two
        String key = "000102030405060708090a0b0c0d0e0f";
        String cipherText = "69c4e0d86a7b0430d8cdb78070b4c55a";

        //String key = "2b 7e 15 16 28 ae d2 a6 ab f7 15 88 09 cf 4f 3c";
        //String cipherText = "3925841d02dc09fbdc118597196a0b32";

        AESDecrypt b = new AESDecrypt(key, cipherText);
        b.decrypt();
    }

}

