package Transaction;
import org.block.StringUtil;

import java.util.ArrayList;
import java.security.*;

public class Transaction {
    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public float amount;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0;

    public Transaction(PublicKey from, PublicKey to, float amount, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.amount = amount;
        this.inputs = inputs;
    }

    private String calculateHash(){
        sequence++;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender)+
                StringUtil.getStringFromKey(recipient)+
                        Float.toString(amount)+
                        sequence
        );
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(amount)	;
        signature = StringUtil.applyECDSASig(privateKey,data);
    }
    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(amount)	;
        return StringUtil.verifyECDSASig(sender, data, signature);
    }
}
