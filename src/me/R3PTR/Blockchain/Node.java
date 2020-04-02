package me.R3PTR.Blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {

    private String nodeName;
    private List<String> nodes = new ArrayList<>();
    private List<Block> blockchain;
    private List<Transaction> openTransactions = new ArrayList<>();

    public Node(String firstNode, String nodeName) {
        nodes.add(firstNode);
        blockchain = Nodes.getNode(nodes.get(0)).getBlockchain();
        this.nodeName = nodeName;
    }

    public Node(String nodeName, List<Block> blockchain) {
        this.nodeName = nodeName;
        this.blockchain = blockchain;
    }

    public void addTransaction(Transaction transaction) {
        openTransactions.add(transaction);
    }

    public void printBlockchain() {
        for (Block block : blockchain) {
            System.out.println(block.toString());
        }
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public void sendBlock(Block block) {
        for (String node : nodes) {
            Nodes.getNode(node).newBlock(block);
        }
    }

    public void newBlock(Block block) {
        if (block.getHash().charAt(0) == '0' && !blockchain.contains(block)) {
            blockchain.add(block);
            for (Transaction transaction : block.getTransactions()) {
                openTransactions.remove(transaction);
            }
            sendBlock(block);
        }
    }

    public void sendTransaction(Transaction transaction) {
        for (String node : nodes) {
            Nodes.getNode(node).newTransaction(transaction);
        }
    }

    public void newTransaction(Transaction transaction) {
        if (!openTransactions.contains(transaction)) {
            openTransactions.add(transaction);
        }
    }

    public void mine() {
        Random random = new Random();
        Block block = initBlock();
        while (!block.getHash().startsWith("00000")) {
            block.setNonce(random.nextInt());
        }
        blockchain.add(block);
        sendBlock(block);
    }

    public Block initBlock() {
        Random random = new Random();
        String previousHash = blockchain.get(blockchain.size() - 1).getHash();
        List<Transaction> blockTransactions = new ArrayList<>();
        for (int i = 0; i < 10 && i < openTransactions.size(); i++) {
            blockTransactions.add(openTransactions.get(i));
        }
        String merkelTree = getMerkelTree(blockTransactions);
        int nonce = random.nextInt();
        BlockHeader header = new BlockHeader(previousHash, nonce, merkelTree);
        Block block = new Block(header, blockTransactions);
        return block;
    }

    public String getMerkelTree(List<Transaction> blockTransactions) {
        List<String> hashedTransactions = new ArrayList<>();
        for (Transaction transaction : blockTransactions) {
            hashedTransactions.add(transaction.getHash());
        }
        while (hashedTransactions.size() != 1) {
            List<String> newHashes = new ArrayList<>();
            if (hashedTransactions.size() % 2 != 0) {
                hashedTransactions.add(hashedTransactions.get(hashedTransactions.size() - 1));
            }
            int i1 = 0;
            int i2 = 1;
            while (i2 < hashedTransactions.size()) {
                newHashes.add(Sha256.getHash(hashedTransactions.get(i1) + hashedTransactions.get(i2)));
                i1 += 2;
                i2 += 2;
            }

        }
        return hashedTransactions.get(0);
    }

    public String getName() {
        return nodeName;
    }
}
