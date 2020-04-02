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

        String key = "000102030405060708090a0b0c0d0e0f";
        String cipherText = "69c4e0d86a7b0430d8cdb78070b4c55a";

        AESDecrypt b = new AESDecrypt(key, cipherText);
        b.decrypt();

        System.out.println();
        // Deliverable two
        String key2 = "30 31 32 33  34 35 36 37  38 39 3A 3B  3C 3D 3E 3F";
        String cipherText2 = "F4 35 15 03  AA 78 1C 52  02 67 D6 90  C4 2D 1F 43";
        AESDecrypt c = new AESDecrypt(key2, cipherText2);
        c.decrypt();
    }

}

