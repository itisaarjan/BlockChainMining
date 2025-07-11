package org.block;

import java.security.Security;
import java.util.ArrayList;

import Transaction.Transaction;
import com.google.gson.GsonBuilder;
import wallet.Wallet;

public class Main {
    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 6;
    public static Wallet walletA;
    public static Wallet walletB;
    public static void main(String[] args) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();

        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletB.privateKey));

        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
        System.out.println(StringUtil.getStringFromKey(walletB.publicKey));

        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5,null);
        transaction.generateSignature(walletA.privateKey);

        System.out.println("Is signature verified");
        System.out.println(transaction.verifySignature());

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
