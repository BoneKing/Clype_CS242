package data;

import data.ClypeData;

/**
 * MessageClypeData is a subclass of ClypeData
 */
public class MessageClypeData extends ClypeData {
    String message;
    public static final int HASH_MESSAGE_OFFSET = 500;
    public MessageClypeData( String userName, String message, int type){
        super(userName, type);
        this.message=message;
    }

    /**
     * Default constructor of MessageClypeData
     */
    public MessageClypeData(){
            super();
            message="";
    }

    /**
     *  Contructor for an Encrypted Message
     * @param userName
     * @param message
     * @param key
     * @param type
     */
    public MessageClypeData(String userName, String message, String key, int type){
        this.message=encrypt(message,key);
    }

    /**
     *  returns the data for the object
     * @return Clype Data
     */
    public String getData(){return message;}

    /**
     * an override for getData so it can work with encrypted data
     * @param key
     * @return Clype Data
     */
    @Override
    public String getData(String key){
        return decrypt(message,key);
    }

    /**
     * reurns the hashcode for the object
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int offset = super.hashCode() *HASH_MESSAGE_OFFSET;
        return (offset+this.message.hashCode());
    }

    /**
     *  takes two CLypeDatas and sees if they're equal
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this.message.equals(message)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *  returns the message
     * @return message
     */
    @Override
    public String toString() {
        return super.toString() + " " + message+ '\n';
    }
}
