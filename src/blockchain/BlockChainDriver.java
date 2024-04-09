package blockchain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

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
        initalAmount = Integer.valueOf(args[0]);
        BlockChain blockChain = new BlockChain(initalAmount);

        PrintWriter pen = new PrintWriter(System.out, true);
        BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in)); 
        String input = eyes.readLine();

        while(input.equals("quit")){

            if(input.equals("mine")){
                int amount;
                pen.print("Amount transferred? ");
                amount = Integer.valueOf(eyes.readLine()) ;
                Block minedBlock = blockChain.mine(amount);
                pen.printf("amount = %s, nonce = %d",amount, minedBlock.getNonce());
            }//mine
            
            if(input.equals("append")){
                int amount;
                long nonce;

                // Input amount
                pen.print("Amount transferred? ");
                pen.flush();
                amount = Integer.valueOf(eyes.readLine());
                // Input nonce
                pen.print("Nonce? ");
                pen.flush();
                nonce = Long.valueOf(eyes.readLine());

                int newNum = blockChain.last.block.getNum() + 1;
                Hash prevHash = blockChain.last.block.getHash();
                Block toAppend = new Block(newNum, amount, prevHash);

                blockChain.append(toAppend);
            } // if

            if (input.equals("check")) {
                if (blockChain.isValidBlockChain()) {
                    pen.println("Chain is valid!\n");
                } else {
                    pen.println("Chain invalid");
                }
            } // if check

            if (input.equals("report")) {
                blockChain.printBalances(pen);
            }

            if (input.equals("remove")) {
                blockChain.removeLast();
            }

            if (input.equals("help")) {
                pen.println(commandList);
            }

            // Prints all blocks
            pen.println(blockChain.toString());
            }// while
    } // main(String[])

}
