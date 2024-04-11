package blockchain;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Implements main method to run BlockChain.
 *
 * @author Arsal Shaikh
 * @author Vincent Yao
 */

public class BlockChainDriver {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String commandList = """
            Valid commands:
            mine: discovers the nonce for a given transaction
            append: appends a new block onto the end of the chain
            remove: removes the last block from the end of the chain
            check: checks that the block chain is valid
            report: reports the balances of Alexis and Blake
            help: prints this list of commands
            quit: quits the program
                """;
    
        // Validate command line arguments
        if (args.length != 1) {
            System.err.println("Usage: java BlockChainDriver [initalAmount]");
            System.exit(0);
        }
        int initalAmount;
        try {
            initalAmount = Integer.valueOf(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Amount entered must be an Integer.");
            System.exit(1);
        }
        
        // Initialise BlockChain
        initalAmount = Integer.valueOf(100);
        BlockChain blockChain = new BlockChain(initalAmount);

        PrintWriter pen = new PrintWriter(System.out, true);
        Scanner eyes = new Scanner(System.in); 
        
        
        while (true) {
            pen.println(blockChain);
            pen.printf("Command? ");
            pen.flush();
            String input = eyes.nextLine().trim();


            if(input.equalsIgnoreCase("mine")){
                int amount;
                pen.printf("Amount transferred? ");
                amount = Integer.valueOf(eyes.nextLine().trim()) ;
                Block minedBlock = blockChain.mine(amount);
                pen.printf("amount = %s, nonce = %d\n", amount, minedBlock.getNonce());
            } // if input is 'mine'
            

            if(input.equalsIgnoreCase("append")){
                int amount;
                long nonce;

                // Input amount
                pen.printf("Amount transferred? ");
                pen.flush();
                amount = Integer.valueOf(eyes.nextLine().trim());
                // Input nonce
                pen.printf("Nonce? ");
                pen.flush();
                nonce = Long.valueOf(eyes.nextLine().trim());

                int newNum = blockChain.last.block.getNum() + 1;
                Hash prevHash = blockChain.last.block.getHash();
                Block toAppend = new Block(newNum, amount, prevHash, nonce);

                blockChain.append(toAppend);
            } // if input is 'append'


            if (input.equalsIgnoreCase("check")) {
                if (blockChain.isValidBlockChain()) {
                    pen.println("Chain is valid!\n");
                } else {
                    pen.println("Chain invalid\n");
                }
            } // if input is 'check'


            if (input.equalsIgnoreCase("report")) {
                blockChain.printBalances(pen);
            } // if input is 'report'


            if (input.equalsIgnoreCase("remove")) {
                blockChain.removeLast();
            } // if input is 'remove'


            if (input.equalsIgnoreCase("help")) {
                pen.println(commandList);
            } // if input is 'help'


            // Exit condition
            if (input.equalsIgnoreCase("quit")) {
                pen.close();
                eyes.close();
                return;
            } // if input is 'quit'
            } // while (true)

    } // main(String[])

} // class BlockChainDrivers
