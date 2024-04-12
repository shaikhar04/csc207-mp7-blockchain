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
            \t mine: discovers the nonce for a given transaction
            \t append: appends a new block onto the end of the chain
            \t remove: removes the last block from the end of the chain
            \t check: checks that the block chain is valid
            \t report: reports the balances of Alexis and Blake
            \t help: prints this list of commands
            \t quit: quits the program
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
        initalAmount = Integer.valueOf(args[0]);
        if (initalAmount < 0) {
            System.err.println("Amount entered must be a positive Integer.");
            System.exit(1);
        }//I am not sure if this is necessary but I added it just in case
        BlockChain blockChain = new BlockChain(initalAmount);

        PrintWriter pen = new PrintWriter(System.out, true);
        Scanner eyes = new Scanner(System.in); 
        
        // Command loop
        boolean executedCommand;
        while (true) {
            executedCommand = false;
            pen.printf("\n" + blockChain + "\n");
            pen.printf("Command? ");
            pen.flush();
            String input = eyes.nextLine().trim();
            if (input.equalsIgnoreCase("mine")){
                executedCommand = true;
                int amount;
                pen.printf("Amount transferred? ");
                amount = Integer.valueOf(eyes.nextLine().trim()) ;
                Block minedBlock = blockChain.mine(amount);
                pen.printf("amount = %s, nonce = %d\n", amount, minedBlock.getNonce());
                pen.println("Hash for new block: " + minedBlock.getHash());
            } // if input is 'mine'
            

            if(input.equalsIgnoreCase("append")){
                executedCommand = true;
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
                executedCommand = true;
                if (blockChain.isValidBlockChain()) {
                    pen.printf("Chain is valid!\n");
                } else {
                    pen.printf("Chain invalid\n");
                }
            } // if input is 'check'


            if (input.equalsIgnoreCase("report")) {
                executedCommand = true;
                blockChain.printBalances(pen);
            } // if input is 'report'


            if (input.equalsIgnoreCase("remove")) {
                executedCommand = true;
                boolean removedSuccessfully = blockChain.removeLast();
                if (!removedSuccessfully) {
                    pen.printf("Cannot remove last block. Only single block remaining\n");
                }
            } // if input is 'remove'


            if (input.equalsIgnoreCase("help")) {
                executedCommand = true;
                pen.println(commandList);
            } // if input is 'help'


            // Exit condition
            if (input.equalsIgnoreCase("quit")) {
                executedCommand = true;
                pen.close();
                eyes.close();
                return;
            } // if input is 'quit'

            if (!executedCommand) {
                pen.printf("'%s' is not a valid command. Type 'help' for a list of commands\n", input);
            } // if command not recognized
            } // while (true)

    } // main(String[])
} // class BlockChainDrivers
