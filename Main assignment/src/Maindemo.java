import java.io.*;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Represents the background code
 *
 * @author Elliott Bown
 * @version 2 (22nd May 2023)
 */
public class Maindemo {

    private String name;


    /**
     * Loads information from the file
     *
     * @param infileName The file to read from
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void load(String infileName) throws IOException{
        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            infile.useDelimiter("\r?\n|\r");


            while (infile.hasNext()) {
                Cipher c = null;
                String type = infile.next();
                System.out.println(type);

                if (c != null) {
                    c.load(infile);
                }
            }
        }
    }


    /**
     * reads information from the file and returns it in a string
     * @param infileName
     * @return
     * @throws IOException
     */
    public String read(String infileName) throws IOException{
        StringBuilder contents = new StringBuilder();
        try(FileReader fr = new FileReader(infileName);
            BufferedReader br = new BufferedReader(fr);
            Scanner infile = new Scanner(br)) {

            infile.useDelimiter("\r?\n|\r");

            while(infile.hasNext()){
                String type = infile.next();
                contents.append(type);
            }
        }
        return contents.toString();
    }

    /**
     * Saves the Cipher information
     *
     * @param filename The file to save to
     * @throws IOException If some IO error occurs
     */
    public void save(String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw);) {

            outfile.println(name);

        }
    }

    /**
     * writes to the file
     * @param filename
     * @param message
     */
    public void write(String filename, String message){
        try{
            FileWriter fw = new FileWriter (filename);
            fw.write(message);
            fw.close();
            System.out.println("Saved to file");
        }catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

}
