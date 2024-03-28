import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
/**
 * Runs the main code
 *
 * @author Elliott Bown
 * @version 2 (22nd May 2023)
 */
public class Main {
    private String currentFileName; // holds the name of the current file
    private String currentKeyFile; // holds the current key file
    private Scanner scan; // so we can read from keyboard
    private Maindemo maindemo;
    private Caesar caesar;
    private Keyed keyed;
    private Vigenere vigenere;
    private String plainTextFile; // Stores the name of the plain text file in use
    private String plainText; // Stores the contents of the formatted plain Text
    private String cipherTextFile; //Stores the name of the cipher text file in use
    private String cipherText;

    private Main(){
        maindemo = new Maindemo();
        caesar = new Caesar();
        keyed = new Keyed();
        vigenere = new Vigenere();
    }

    /**
     *  Runs from main and loads files
     */
    private void initialise(){
        currentFileName = "Caesar";
        currentKeyFile = "caesar-key.txt";

        try {
            maindemo.load("caesar-key.txt");
            maindemo.load("keyed-caesar-key.txt");
            maindemo.load("vigenere-key.txt");
        } catch (FileNotFoundException e) {
            System.err.println("The file you tried to load does not exist.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the specified file ");
            System.err.println(e.getMessage());
        }
    }

 /**
  *  Allows the user to input text to get different functions to run
  */

    private void runMenu() {
        String response;
        do{
            printMenu();
            System.out.println("Current cipher: "+currentFileName);
            System.out.println("Choose an option");
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();

            switch(response){
                case "A":
                    chooseCipher();
                    break;
                case "B":
                    keyEdit();
                    break;
                case "C":
                    displayKey();
                    break;
                case "D":
                    inputPlainText();
                    break;
                case "E":
                    inputCipherText();
                    break;
                case "F":
                    encrypt();
                    break;
                case "G":
                    displayCipherText();
                    break;
                case "H":
                    saveCipherPlain("cipher");
                    break;
                case "I":
                    decrypt();
                    break;
                case "J":
                    displayPlainText();
                    break;
                case "K":
                    saveCipherPlain("plain");
                    break;
                case "Q":
                    break;

                default:
                    System.out.println("Wrong input, try again");
            }
        } while (!(response.equals("Q")));
    }

    private void printMenu() {
        System.out.println("A - Choose Cipher");
        System.out.println("B - Edit Key");
        System.out.println("C - Display Key");
        System.out.println("D - Input plain text file");
        System.out.println("E - Input cipher text file");
        System.out.println("F - Encrypt");
        System.out.println("G - Display cipher text");
        System.out.println("H - Save cipher text");
        System.out.println("I - Decrypt");
        System.out.println("J - Display plain text");
        System.out.println("K - Save plain text");
        System.out.println("Q - Quit");
    }

    /**
     * Allows the user to choose between Caesar, Keyed and vigenere cipher to use to encrypt and decrypt mesages
     */
    public void chooseCipher(){
        System.out.println("Current cipher: ");
        System.out.println("Which cipher would you like to use?");
        System.out.println("A - Caesar");
        System.out.println("B - Keyed Caesar");
        System.out.println("C - Viginere");
        System.out.println("Q - quit");
        String input;

        do{
            scan = new Scanner(System.in);
            input = scan.nextLine().toUpperCase();

            switch(input){
                case "A":
                    currentFileName= "Caesar";
                    currentKeyFile = "caesar-key.txt";
                    System.out.println("The current cipher is now: "+currentFileName);
                    System.out.println("Type Q to quit");
                    break;
                case "B":
                    currentFileName= "Keyed Caesar";
                    currentKeyFile = "keyed-caesar-key.txt";
                    System.out.println("The current cipher is now: "+currentFileName);
                    System.out.println("Type Q to quit");
                    break;
                case "C":
                    currentFileName= "Vigenere";
                    currentKeyFile = "vigenere-key.txt";
                    System.out.println("The current cipher is now: "+currentFileName);
                    System.out.println("Type Q to quit");
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Wrong input, try again");
            }
        } while (!(input.equals("Q")));
    }

    /**
     * Allows the user to edit the key for each cipher
     */
    private void keyEdit(){
        displayKey();

        System.out.println("Would you like to edit the key for this cipher?");
        System.out.println("Y");
        System.out.println("N");
        scan = new Scanner(System.in);
        String input = scan.nextLine().toUpperCase();

        switch(currentFileName) {
            case "Caesar":
                switch (input) {
                    case "Y":
                        System.out.println("Enter a new key: ");
                        scan = new Scanner(System.in);
                        int newKey = Integer.parseInt(scan.nextLine());

                        maindemo.write(currentKeyFile, String.valueOf(newKey));

                        System.out.println("New key for Caesar cipher is: "+newKey);
                        break;
                    case "N":
                        break;
                    default:
                        System.out.println("Wrong input, try again");
                    }
                    break;
            case "Keyed Caesar":
                switch(input){
                    case "Y":
                        System.out.println("Enter a new number shift: ");
                        scan = new Scanner(System.in);
                        int newShiftKey = Integer.parseInt(scan.nextLine());
                        while(newShiftKey > 26){
                            System.out.println("You can't have a shift of over 26, try another shift: ");
                            newShiftKey = Integer.parseInt(scan.nextLine());
                        }
                        System.out.println("Enter a new key word: ");
                        String newWordKey = scan.nextLine();
                        newWordKey = newWordKey.toUpperCase();
                        String betterNewWordKey =  newWordKey.replace(" ", "");
                        String concatNewKey = newShiftKey + betterNewWordKey;

                        maindemo.write(currentKeyFile, concatNewKey);
                        System.out.println("New key for Keyed Caesar cipher is: "+concatNewKey);

                        break;
                    case "N":
                        break;
                }
                break;
            case "Vigenere":
                switch(input){
                    case "Y":
                        System.out.println("Enter a new key: ");
                        scan = new Scanner(System.in);
                        String newKey = scan.nextLine();
                        while(newKey.matches(".*\\d.*")){
                            System.out.println("You can't have any numbers in this key, try again: ");
                            newKey = scan.nextLine();
                        }
                        newKey = newKey.toUpperCase();
                        newKey =  newKey.replace(" ", "");

                        maindemo.write(currentKeyFile, newKey);
                        System.out.println("New key for Keyed Caesar cipher is: "+newKey);
                        break;
                    case "N":
                        break;
                }
                break;
        }
    }

    /**
     * Displays the current key in a simple way
     */
    private void displayKey() {
        System.out.println("The current key for "+currentFileName+" is: ");
        try{
            maindemo.load(currentKeyFile);
        }catch (FileNotFoundException e) {
            System.err.println("The file: " + currentKeyFile + " does not exist.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + currentKeyFile);
            System.err.println(e.getMessage());
        }
    }

    /**
     * Allows the user to input their own file with some cipher text on
     */
    private void inputCipherText(){
        String contents = "";
        System.out.println("Input the cipher text file name: ");
        scan = new Scanner(System.in);
        File input = new File(scan.nextLine());
        boolean exists = input.exists();

        if(exists == true){
            cipherTextFile = String.valueOf(input);
            System.out.println("Current contents of the cipher text file: ");
            try{
                contents = maindemo.read(cipherTextFile);
            }catch (FileNotFoundException e) {
                System.err.println("The file: " + cipherTextFile + " does not exist.");
            } catch (IOException e) {
                System.err.println("An unexpected error occurred when trying to open the file " + cipherTextFile);
                System.err.println(e.getMessage());
            }
        }else{
            System.out.println("That's not a valid file.");
        }

        System.out.println(contents);
        contents = contents.toUpperCase();
        cipherText =  contents.replace(" ", "");

        System.out.println("The formatted version of the file is: ");
        System.out.println(cipherText);
        maindemo.write("prep.txt", cipherText);
        System.out.println("And is stored in prep.txt");
    }

    /**
     * Allows the user to input their own file with some plain text on
     */
    private void inputPlainText(){
        String contents = "";
        System.out.println("Input the file name: ");
        scan = new Scanner(System.in);
        File input = new File(scan.nextLine());
        boolean exists = input.exists();

        if(exists == true){
            plainTextFile = String.valueOf(input);
            System.out.println("Current contents of file: ");
            try{
                contents = maindemo.read(plainTextFile);
            }catch (FileNotFoundException e) {
                System.err.println("The file: " + plainTextFile + " does not exist.");
            } catch (IOException e) {
                System.err.println("An unexpected error occurred when trying to open the file " + plainTextFile);
                System.err.println(e.getMessage());
            }
        }else{
            System.out.println("That's not a valid file.");
        }

        System.out.println(contents);
        contents = contents.toUpperCase();
        plainText =  contents.replace(" ", "");

        System.out.println("The formatted version of the file is: ");
        System.out.println(plainText);
        maindemo.write("prep.txt", plainText);
        System.out.println("And is stored in prep.txt");
    }

    /**
     * Displays the text in the file
     */
    private void displayCipherText(){
        try{
            cipherText = maindemo.read(String.valueOf("prep.txt"));
        }catch (FileNotFoundException e) {
            System.err.println("The file: " + "prep.txt" + " does not exist.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + "prep.txt");
            System.err.println(e.getMessage());
        }

        System.out.println("The Formatted contents of the file prep.txt is: ");
        System.out.println(cipherText);
    }

    /**
     * Displays the text in the file
     */
    private void displayPlainText(){
        try{
            plainText = maindemo.read(String.valueOf("prep.txt"));
        }catch (FileNotFoundException e) {
            System.err.println("The file: " + "prep.txt" + " does not exist.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + "prep.txt");
            System.err.println(e.getMessage());
        }

        System.out.println("The Formatted contents of the file prep.txt is: ");
        System.out.println(plainText);
    }

    /**
     * encrypts the current text file with either the Caeser, keyed or vigenere cipher dependingon which one is currently selected
     */
    private void encrypt(){
        System.out.println("The Encryption of this message becomes: ");
        switch(currentFileName){
            case "Caesar":
                contentsOfCurrentKey();
                System.out.println(caesar.cipherEncryption(plainText, Integer.parseInt(contentsOfCurrentKey())));
                break;
            case "Keyed Caesar":

                contentsOfCurrentKey();

                String numbers = contentsOfCurrentKey().replaceAll("[^0-9]","");
                String letters = contentsOfCurrentKey().replaceAll("\\d","");

                System.out.println(keyed.cipherEncryption(plainText, letters, Integer.parseInt(numbers)));

                break;
            case "Vigenere":
                contentsOfCurrentKey();
                System.out.println(vigenere.cipherEncryption(plainText, contentsOfCurrentKey()));
                break;
            default:
                System.out.println("There is no Encryption key selected.");
                break;
        }
    }

    /**
     * Decrypts the current text file with either the Caeser, keyed or vigenere cipher dependingon which one is currently selected
     */
    private void decrypt(){
        System.out.println("The Decryption of this message becomes: ");
        switch(currentFileName){
            case "Caesar":
                contentsOfCurrentKey();
                System.out.println(caesar.cipherDecryption(plainText, Integer.parseInt(contentsOfCurrentKey())));
                break;
            case "Keyed Caesar":

                contentsOfCurrentKey();

                String numbers = contentsOfCurrentKey().replaceAll("[^0-9]","");
                String letters = contentsOfCurrentKey().replaceAll("\\d","");

                System.out.println(keyed.cipherDecryption(plainText, letters, Integer.parseInt(numbers)));

                break;
            case "Vigenere":
                contentsOfCurrentKey();
                System.out.println(vigenere.cipherDecryption(plainText, contentsOfCurrentKey()));
                break;
            default:
                System.out.println("There is no decryption key selected.");
                break;
        }
    }

    /**
     * reads the file and returns the contents in a string
     * @return
     */
    private String contentsOfCurrentKey(){
        String contents = "";
        try{
            contents = maindemo.read(String.valueOf(currentKeyFile));
        }catch (FileNotFoundException e) {
            System.err.println("The file: " + currentKeyFile + " does not exist.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + currentKeyFile);
            System.err.println(e.getMessage());
        }
        return contents;
    }

    /**
     * saves either the plain or cipher text
     * @param cipherPlain
     */
    private void saveCipherPlain(String cipherPlain) {
        String text;

        if(cipherPlain == "cipher"){
            text = "cipher";
        } else{
            text = "plain";
        }

        System.out.println("Specify the file you want to save this"+text+ "text to: ");
        scan = new Scanner(System.in);
        String filename = scan.nextLine();

        try{
            File cipherFile = new File(filename);
            if (cipherFile.createNewFile()) {
                System.out.println("File created with the name: " + filename);
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        if(cipherPlain == "cipher"){
            cipherTextFile = filename;
        } else{
            plainTextFile = filename;
        }
    }


    /**
     * Save() method runs from main and writes back to file
     */
    private void save(String filename) {
        try {
            maindemo.save(filename);
        } catch (IOException e) {
            System.err.println("Problem when trying to write to file: " + currentFileName);
        }
    }

    //////////////////////////////////////////////////
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("-----------------------------------Cipher Crypting--------------------------------------");
        System.out.println("Welcome!");
        main.initialise();
        main.runMenu();
        System.out.println("-----------------------------------Goodbye----------------------------------------------");
    }
}