public class Main {



    public static void main(String args[]){

        String input = "32 43 f6 a8 88 5a 30 8d 31 31 98 a2 e0 37 07 34";
        String cipherKey = "2b 7e 15 16 28 ae d2 a6 ab f7 15 88 09 cf 4f 3c";

        AESEncrypt a = new AESEncrypt(input,cipherKey);
        a.encrypt();
    }

}

