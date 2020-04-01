package me.R3PTR.Blockchain;

public class Input {
    private String previousHash;
    private String index;
    private Signature signature;

    public Input(String previousHash, String index, Signature signature) {
        this.previousHash = previousHash;
        this.index = index;
        this.signature = signature;
    }
}
