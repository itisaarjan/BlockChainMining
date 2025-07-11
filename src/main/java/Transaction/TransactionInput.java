package Transaction;

public class TransactionInput {
    public String TransactionOutputId;
    public TransactionOutput UTXO;

    public TransactionInput(String TransactionOutputId){
        this.TransactionOutputId = TransactionOutputId;
    }

}
