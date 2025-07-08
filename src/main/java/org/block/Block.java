package org.block;

import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private transient String data;
    private transient long timeStamp;   //as number of milliseconds from 1/1/1970
    private transient int nonce;

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = CalculateHash();
    }

    public String CalculateHash(){
        String calculatedHash = StringUtil.applySha256(data+previousHash+Long.toString(timeStamp)+Integer.toString(nonce));
        return calculatedHash;
    }

    public void mineBlock(int diffficulty){
        String target = new String(new char[diffficulty]).replace('\0', '0');
        while(!hash.substring(0,diffficulty).equals(target)){
            nonce++;
            hash = CalculateHash();
        };

        System.out.println("Block mined !!!: " + hash);

    }
}
