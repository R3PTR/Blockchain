package me.R3PTR.Blockchain;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {

    private ArrayList<String> inputs;
    private ArrayList<String> outputs;

    public Transaction(ArrayList<String> inputs, ArrayList<String> outputs) {
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
