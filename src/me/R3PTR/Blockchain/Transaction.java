package me.R3PTR.Blockchain;

import java.util.List;

public class Transaction {

    private List<String> inputs;
    private List<String> outputs;

    public Transaction(List<String> inputs, List<String> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public String getHash() {
        StringBuilder sb = new StringBuilder();
        for (String in : inputs) {
            sb.append(in);
        }
        for (String out : outputs) {
            sb.append(out);
        }
        return Sha256.getHash(sb.toString());
    }
}
