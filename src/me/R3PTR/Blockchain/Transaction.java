package me.R3PTR.Blockchain;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    private int version;
    private Date lock_time;
    private ArrayList<Input> tx_in;
    private ArrayList<Output> tx_out;
    private int tx_in_count;
    private int tx_out_count;

    public Transaction(int version, Date lock_time, ArrayList<Input> tx_in, ArrayList<Output> tx_out) {
        this.version = version;
        this.lock_time = lock_time;
        this.tx_in = tx_in;
        this.tx_out = tx_out;
        tx_in_count = tx_in.size();
        tx_out_count = tx_out.size();
    }
}
