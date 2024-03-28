/**
 * Represents the code for the keyed caesar cipher
 *
 * @author Elliott Bown
 * @version 2 (22nd May 2023)
 */
public class Keyed extends Cipher{


    public Keyed() {
    }

    // TEST - initially didn't remove duplicates from the keyword - cause i didn't put the variables in right with the for loops
    // then didn't remove those letters form the alphabet - because I forgot to call teh same variable alphabetForKeying to update it rather than overwrite it each time
    /**
     *Encrypts a file with the keyed caesar encryption
     */
    public String cipherEncryption(String input, String key, int numKey){
        StringBuilder key1 = new StringBuilder(key);

        // removes duplicates from letter key
        for(int b =0; b<key1.length();b++){
            for(int c=b+1; c<key1.length();c++){
                if(key1.charAt(b) == key1.charAt(c)){
                    key1.deleteCharAt(c);
                }
            }
        }

        // Removes the letters of the key from the rest of the alphabet
        for(int a = 0; a<key1.length(); a++){
            // Only removes letters if the alphabet contains them, acts as a check for non letters
            if(alphabet.contains(String.valueOf(key1.charAt(a)).toUpperCase())) {
                alphabetForKeying = alphabetForKeying.replace(String.valueOf(key1.charAt(a)).toUpperCase(), "");
            } else{
                System.out.println("Error, key contains incorrect input type.");
                break;
            }
        }

        // Just the encrypted alphabet without shift
        String alphabetKeyed = key1 + alphabetForKeying;
        // Encrypted alphabet with shift
        String alphabetKeyedNShifted = alphabetKeyed.substring(numKey) + alphabetKeyed.substring(0,numKey);

        StringBuilder inputEncrypt = new StringBuilder(input);

        // goes through the length of the input, compares the place of each letter in the normal alphabet with the keyed alphabet and changes the letter accordingly
        for(int x=0; x<inputEncrypt.length(); x++ ){
            char thisChar = inputEncrypt.charAt(x);
            int index = alphabet.indexOf(String.valueOf(thisChar).toUpperCase());
            if (index != -1){
                char newChar = alphabetKeyedNShifted.charAt(index);
                inputEncrypt.setCharAt(x, newChar);
            }
        }
        return inputEncrypt.toString();
    }

    /**
     * Decrypts a file with the keyed caesar cipher
     */
    public String cipherDecryption(String input, String key, int numKey){
        StringBuilder key1 = new StringBuilder(key);

        // removes duplicates from letter key
        for(int b =0; b<key1.length();b++){
            for(int c=b+1; c<key1.length();c++){
                if(key1.charAt(b) == key1.charAt(c)){
                    key1.deleteCharAt(c);
                }
            }
        }

        // Removes the letters of the key from the rest of the alphabet
        for(int a = 0; a<key1.length(); a++){
            // Only removes letters if the alphabet contains them, acts as a check for non letters
            if(alphabetForKeying.contains(String.valueOf(key1.charAt(a)).toUpperCase())) {
                alphabetForKeying = alphabetForKeying.replace(String.valueOf(key1.charAt(a)).toUpperCase(), "");
            } else{
                System.out.println("Error, key contains incorrect input type.");
                break;
            }
        }

        // Just the encrypted alphabet without shift
        String alphabetKeyed = key1 + alphabetForKeying;
        // Encrypted alphabet with shift
        String alphabetKeyedNShifted = alphabetKeyed.substring(numKey) + alphabetKeyed.substring(0,numKey);

        StringBuilder inputDecrypt = new StringBuilder(input);

        // goes through the length of the input, compares the place of each letter in the normal alphabet with the keyed alphabet and changes the letter accordingly
        for(int x=0; x<inputDecrypt.length(); x++ ){
            char thisChar = inputDecrypt.charAt(x);
            int index = alphabetKeyedNShifted.indexOf(String.valueOf(thisChar).toUpperCase());
            if (index != -1){
                char newChar = alphabet.charAt(index);
                inputDecrypt.setCharAt(x, newChar);
            }
        }
        return inputDecrypt.toString();
    }


}
