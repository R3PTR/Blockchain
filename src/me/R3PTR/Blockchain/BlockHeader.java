package me.R3PTR.Blockchain;

import java.time.LocalDateTime;

public class BlockHeader {
    private LocalDateTime timeStemp;
    private String previousHash;
    private int nonce;
    private String merkelTree;

    public BlockHeader(String previousHash, int nonce, String merkelTree) {
        this.previousHash = previousHash;
        this.nonce = nonce;
        this.merkelTree = merkelTree;
        timeStemp = LocalDateTime.now();
    }

    public String getHash() {
        return Sha256.getHash(timeStemp + previousHash + nonce + merkelTree);
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}
