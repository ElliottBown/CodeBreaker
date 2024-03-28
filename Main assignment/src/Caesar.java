/**
 * Represents the code for the Caesar cipher
 *
 * @author Elliott Bown
 * @version 2 (22nd May 2023)
 */
public class Caesar extends Cipher {
    public Caesar() {
    }

    // for testing, the cipher did not work first time, went through and put print statements at every line to see what was wrong, turns out it was the "thisChar"
    // that wasn't capital so it was always printing -1 for the index as the alphabet was in upper case

    /**
     * Encrypts a given input with a given key
     * @param input
     * @param key
     * @return the encrypted message
     */
    public String cipherEncryption(String input, int key){

        String alphabetKeyed = alphabet.substring(key) + alphabet.substring(0,key);

        StringBuilder inputEncrypt = new StringBuilder(input);

        for(int x=0; x<inputEncrypt.length(); x++){
            char thisChar = inputEncrypt.charAt(x);

            // Finds the fist instance of this character in the alphabet and gives the number of it
            int index = alphabet.indexOf(String.valueOf(thisChar).toUpperCase());
            if (index != -1){
                char newChar = alphabetKeyed.charAt(index);
                inputEncrypt.setCharAt(x, newChar);
            }
        }
        return inputEncrypt.toString();
    }

    /**
     * decrypts a file
     * @param input
     * @param key
     * @return
     */
    public String cipherDecryption(String input, int key){
        String alphabetKeyed = alphabet.substring(key) + alphabet.substring(0,key);

        StringBuilder inputDecrypt = new StringBuilder(input);

        for(int x=0; x<inputDecrypt.length(); x++){
            char thisChar = inputDecrypt.charAt(x);
            int index = alphabetKeyed.indexOf(String.valueOf(thisChar).toUpperCase());
            if(index !=-1){
                char newChar = alphabet.charAt(index);
                inputDecrypt.setCharAt(x, newChar);
            }
        }
        return inputDecrypt.toString();
    }

}
