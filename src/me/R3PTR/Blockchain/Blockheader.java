package me.R3PTR.Blockchain;

import java.time.LocalDateTime;
import java.util.Date;

public class Blockheader {
    private int versionNumber;
    private LocalDateTime timeStemp;
    private String previousHash;
    private int nonce;
    private String merkelTree;

    public Blockheader(int versionNumber, String previousHash, int nonce, String merkelTree) {
        this.versionNumber = versionNumber;
        this.previousHash = previousHash;
        this.nonce = nonce;
        this.merkelTree = merkelTree;
        timeStemp = LocalDateTime.now();
    }
}
