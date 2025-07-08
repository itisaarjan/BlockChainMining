package org.block;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Main {
    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 6;
    public static void main(String[] args) {
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Starting to mine block 1...");
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Starting to mine block 2");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Starting to mine block 3");
        blockChain.get(2).mineBlock(difficulty);

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        if(IsChainValid()){
            System.out.println("The chain is valid!!!");
            System.out.println(blockchainJson);
        }else{
            System.out.println("The chain is invalid!!!");
        };
    }

    public static boolean IsChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockChain.size();i++){
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.CalculateHash())){
                System.out.println("Current Hashes are not equal");
                return false;
            };

            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Fault in block chaining, previous Hashes not equal");
                return false;
            };

            if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
                System.out.println("This Block has not been mined");
                return false;
            }

        }
        return true;
    }
}
