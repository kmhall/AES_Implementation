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
        /****************/
        String key = "30 31 32 33 34 35 36 37 38 39 3A 3B 3C 3D 3E 3F";
        String cipherText = "F4 35 15 03 AA 78 1C 52 02 67 D6 90 C4 2D 1F 43";

        //AESDecrypt b = new AESDecrypt(key, cipherText);
        //b.decrypt();
    }

}

