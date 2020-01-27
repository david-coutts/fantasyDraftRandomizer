package fantasyDraftRandomizer;

import java.text.DecimalFormat;
import java.io.*;
import java.util.*;

public class fantasyDraftRandomizer {

	public static void main(String[] args) {
		
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of players:");
        int numPlayers = input.nextInt();
        
        //Probs we may use = {0.30, 0.25, 0.19, 0.12, 0.04, 0.02, 0.02, 0.02, 0.01, 0.01, 0.01, 0.01};
        
        // Ask for the probability that each player has of getting the first pick. Enter into array.
        double[] probs = new double[numPlayers];
        for (int player=0;player<numPlayers;player++) {
            System.out.println("Enter player "+(player+1)+"'s probability of getting the first pick in decimal:");
            probs[player] = input.nextDouble();
        }
        
        // Check to make sure all probabilities add up to 1
        double totalProb=0.0;
        for (int player=0;player<numPlayers;player++) totalProb+=probs[player];
        if ((totalProb < 0.999) || (totalProb > 1.001)) {
            System.out.println("Total probabilities must equal 1.0; not "+totalProb+"!");
            return;
        }
        
        // Create and initial an array of the drafting places
        int[] order = new int[numPlayers];
        for (int player=0;player<numPlayers;player++) order[player] = 0;
        
        for (int i=0; i<numPlayers; i++) { //times we've done this
            
            // Print probabilities that each ending position will draft next
            DecimalFormat number = new DecimalFormat("#.00");
            System.out.print("Probabilities: ");
            for (int x=0;x<numPlayers;x++) System.out.print((x+1)+":"+number.format(probs[x]) + " ");
            System.out.println();
            
            double total = 0.0;
            int j=0;
            boolean found = false;
            
            // See if the current spot needs to be filled by someone dropping 2 spots
            if (i >= 2) {
                if (probs[i-2] > 0.0) {
                    order[i] = i-1;
                    j=i-2;
                    found = true;
                }
            }
            if (found == false) { // We didn't need to fill the spot with someone that dropped by 2
                double rand = Math.random();
                System.out.println("Random number: "+rand);
                // Keep adding up the probabilities until the toal is > the random number
                for (; j<numPlayers; j++) {                
                    total+=probs[j];              
                    if (rand <= total) {
                        order[i] = j+1;
                        break;
                    }
                }
            }
            
            // Refactor all the probabilities by taking out whoever just got the previous spot
            double factor = 1.0 + (probs[j]/(1-probs[j]));
            probs[j] = 0.0;
            for (int k=0; k<numPlayers; k++) {
                if ((k != j) && (probs[k] > 0.0)) probs[k]*=factor;
            }   
            
            // Print the new draft order
            for (int x=0;x<numPlayers;x++) System.out.print(order[x] + " ");
            System.out.println();
            System.out.println();
        }
    }     
}
