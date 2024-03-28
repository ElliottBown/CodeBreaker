import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;  // Import the File class

/**
 * Represents teh parent class for the ciphers
 *
 * @author Elliott Bown
 * @version 2 (22nd May 2023)
 */
public class Cipher {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetForKeying = alphabet;

    private String Name;

    public Cipher(){}


    /**
     * Reads in information about the cipher from the file
     */
    public void load(Scanner infile){

        Name = infile.next();
        int numOwners = infile.nextInt();
        for (int oCount = 0; oCount < numOwners; oCount++) {
            String name = infile.next();
            String phone = infile.next();
        }
    }
}




