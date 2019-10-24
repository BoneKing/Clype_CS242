package data;

import java.util.Date;
import java.io.Serializable;

/**
 * @author Andy Mahoney
 */
public abstract class ClypeData implements Serializable{

    public final static int allUsers = 0;
    public final static int logOut = 1;
    public final static int sendFile = 2;
    public final static int sendMessage = 3;

    private String userName;
    private int type;
    private Date date = new Date();

    /**
     *
     * @param userName
     * @param type
     */
    public ClypeData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }

    /**
     * @param type
     */
    public ClypeData(int type){
        this("Anon", type);
    }

    /**
     * Default contructor for ClypeData
     */
    public ClypeData(){
        this(0);
    }

    /**
     *
     * @param userName
     * @param message
     * @param key
     * @param type
     * for encrypted ClypeData
     */
    public ClypeData(String userName, String message, String key, int type){
        this(userName, type);
        encrypt(message,key);
        if(type != 2) {
            ClypeData c = new MessageClypeData(userName, message, key, type);
        }
        else{
            ClypeData c = new FileClypeData(userName, message,type);
        }
    }

    /**
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return Data implimented in FileClypeData.java and MessageClypeData.java
     */
    public abstract String getData();
    /**
     *
     * @return Data implimented in FileClypeData.java and MessageClypeData.java
     * This version handles encrypted data
     */
    public abstract String getData(String key);

    /**
     *
     * @param inputStringToEncrypt
     * @param key
     * @return encypted String
     */
    protected String encrypt( String inputStringToEncrypt, String key){
        String encryptedString = "";
        int j =0;
        for(int i=0; i<inputStringToEncrypt.length(); i++){
            if(j >= key.length()) {
                j = 0;
            }
            if(inputStringToEncrypt.charAt(i)>='A' || inputStringToEncrypt.charAt(i)<='Z') {
                encryptedString = encryptedString + (((((inputStringToEncrypt.charAt(i)) + key.charAt(j))-65) % 26)+65);
            }
            else{
                encryptedString = encryptedString + (((((inputStringToEncrypt.charAt(i)) + key.charAt(j))-97) % 26)+97);
            }
        }
        return encryptedString;
    }

    /**
     *
     * @param inputStringToDecrypt
     * @param key
     * @return decrypted string
     */
    protected String decrypt(String inputStringToDecrypt, String key){
        String decryptedString = "";
        int j = 0;
        for(int i=0; i<inputStringToDecrypt.length(); i++){
            if(j >= key.length()) {
                j = 0;
            }
            if(inputStringToDecrypt.charAt(i)>='A' || inputStringToDecrypt.charAt(i)<='Z') {
                decryptedString = decryptedString + ((((inputStringToDecrypt.charAt(i)) - key.charAt(j))-65 % 26)+65);
            }
            else {
                decryptedString = decryptedString + ((((inputStringToDecrypt.charAt(i)) - key.charAt(j))-97 % 26)+97);
            }
        }
        return decryptedString;

    }
}
