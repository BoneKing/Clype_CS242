package data;
import java.io.*;

import data.ClypeData;

public class FileClypeData extends ClypeData {
    String fileName;
    String fileContents;
    public static final int HASH_FILE_OFFSET=1500;

    /**
     * constructor for FileClypeData
     * @param userName
     * @param fileName
     * @param type
     */
    public FileClypeData(String userName, String fileName, int type)
    {
        super(userName, type);
    }

    /**
     * default constructor for FileClypeData
     */
    public FileClypeData(){
        super();
    }

    /**
     * @param fileName
     * sets the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return data
     */
    public String getData(){
        return fileContents;
    }

    /**
     * @throws IOException
     * reads the file, if the file doesn't exist or it is corrupted it'll throw and excetion
     */
    public void readFileContents() throws IOException{
        try {
            FileReader reader = new FileReader( fileName );
            String FileContents ="";
            boolean doneReadingFile = false;
            while (!doneReadingFile) {
                int nextCharacterAsInteger = reader.read();
                doneReadingFile = nextCharacterAsInteger == -1;
                if (!doneReadingFile) {
                    char nextCharacter = (char)nextCharacterAsInteger;
                    System.out.print( nextCharacter );
                    FileContents=FileContents+nextCharacter;
                }
            }
            reader.close();
        } catch ( FileNotFoundException fnfe ) {
            System.err.println( "File is not found, message: " + fnfe.getMessage() );
            fnfe.printStackTrace();
        } catch ( IOException ioe ) {
            System.err.println( "IO exception occurred because file may be corrupt." );
        }
    }

    /**
     * reads the encrypted file, if the file doesn't exist or it is corrupted it'll throw and excetion
     * @param key
     * @throws IOException
     */
    public void readFileContents (String key) throws IOException{
        try {
            FileReader reader = new FileReader( fileName );
            String FileContents ="";
            boolean doneReadingFile = false;
            while (!doneReadingFile) {
                int nextCharacterAsInteger = reader.read();
                doneReadingFile = nextCharacterAsInteger == -1;
                if (!doneReadingFile) {
                    char nextCharacter = (char)nextCharacterAsInteger;
                    System.out.print( nextCharacter );
                    FileContents=FileContents+nextCharacter;
                }
            }
            reader.close();
            encrypt(fileContents, key);
        } catch ( FileNotFoundException fnfe ) {
            System.err.println( "File is not found, message: " + fnfe.getMessage() );
            fnfe.printStackTrace();
        } catch ( IOException ioe ) {
            System.err.println( "IO exception occurred because file may be corrupt." );
        }
    }

    /**
     * writes information to the file <br>
     * Throws exception if an error occurs
     * @throws IOException
     */
    public void writeFileContents() throws IOException {
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(fileContents);
            writer.close();
        }
        catch (IOException ioe){
            System.err.println("IO Exception Occured.");
        }
    }

    /**
     * decrypts information and writes it to the file  <br>
     * Throws exception if an error occurs
     * @param key
     * @throws IOException
     */
    public void writeFileContents(String key) throws IOException {
        try{
            fileContents = decrypt(fileContents, key);
            FileWriter writer = new FileWriter(fileName);
            writer.write(fileContents);
            writer.close();
        }
        catch (IOException ioe){
            System.err.println("IO Exception Occured.");
        }
    }

    /**
     * decrypts data and returns it
     * @param key
     * @return
     */
    @Override
    public String getData(String key){
        return decrypt(fileContents,key);
    }

    /**
     *  generates and returns hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int offset = super.hashCode() * HASH_FILE_OFFSET;
        return (offset+this.fileName.hashCode()) * (offset+this.fileContents.hashCode());
    }

    /**
     * compares two objects and sees if their equal
     * @param o
     * @return bool
     */
    @Override
    public boolean equals(Object o) {
        if(this.fileContents==null) {
            if (this.fileName == fileName) {
                return true;
            }
        }
        else if((this.fileName==fileName) && (this.fileContents==fileContents)){
            return true;
        }
        else{
            return false;
        }
        return false;
    }

    /**
     *
     * @return filename and contents
     */
    @Override
    public String toString() {
        return super.toString() + " " + fileName + ": \n" +fileContents + '\n';
    }
}
