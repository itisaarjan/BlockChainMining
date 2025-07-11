package Transaction;

import org.block.StringUtil;

import java.security.PublicKey;

public class TransactionOutput {
    public String Id;
    public PublicKey recipient;
    public float amount;
    public String parentTransactionId;

    public TransactionOutput(PublicKey recipient, float amount, String parentTransactionId){
        this.recipient = recipient;
        this.amount = amount;
        this.parentTransactionId = parentTransactionId;
        this.Id = StringUtil.applySha256(StringUtil.getStringFromKey(recipient)+Float.toString(amount)+parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey) {
        return (publicKey == recipient);
    }

}
