package me.R3PTR.Blockchain;

import java.util.List;

public class Block {


    private BlockHeader blockheader;
    private List<Transaction> transactionList;

    public Block(BlockHeader blockheader, List<Transaction> transactionList) {
        this.blockheader = blockheader;
        this.transactionList = transactionList;
    }

    public String getPreviousHash() {
        return blockheader.getPreviousHash();
    }

    public String getHash() {
        return blockheader.getHash();
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public int getNonce() {
        return blockheader.getNonce();
    }

    public void setNonce(int nonce) {
        blockheader.setNonce(nonce);
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockheader=" + blockheader.toString() +
                ", transactionList=" + transactionList +
                '}';
    }
}
