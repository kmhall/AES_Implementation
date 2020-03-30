public class Main {

    public static void main(String args[]){

        //Apendix B
//        String input = "32 43 f6 a8 88 5a 30 8d 31 31 98 a2 e0 37 07 34";
//        String cipherKey = "2b 7e 15 16 28 ae d2 a6 ab f7 15 88 09 cf 4f 3c";
//
//        AESEncrypt a = new AESEncrypt(input,cipherKey);
//        a.encrypt();

        //Apendix C.1

        String input2 = "00112233445566778899aabbccddeeff";
        String cipherKey2 = "000102030405060708090a0b0c0d0e0f";

        AESEncrypt b = new AESEncrypt(input2,cipherKey2);
        //b.encrypt();

        // Deliverable two
        String key = "000102030405060708090a0b0c0d0e0f";
        String cipherText = "69c4e0d86a7b0430d8cdb78070b4c55a";

        AESDecrypt a = new AESDecrypt(key, cipherText);
        a.decrypt();
    }

}

