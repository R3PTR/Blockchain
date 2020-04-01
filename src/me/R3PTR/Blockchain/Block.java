package me.R3PTR.Blockchain;

import java.util.List;

public class Block {
    private Blockheader blockheader;
    private String magicNumber;
    private float blockSize;
    private int transactions;
    private List<Transaction> transactionList;

    public Block(String magicNumber) {
        this.magicNumber = magicNumber;
    }
}
