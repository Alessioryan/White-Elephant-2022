import java.util.*;

public class CalcolaProbabilitita2022 {

    private enum People{
        Bau,
        Silvia,
        Peolo,
        Nonna_Grazia,
        Ivan,
        Franciosca
    }

    private static final int NUM_PEOPLE = 6;
    private static final Set<People> bocchi = new HashSet<>(Arrays.asList(People.Silvia, People.Ivan) );
    private static final Set<People> ostrouche = new HashSet<>(Arrays.asList(People.Franciosca, People.Nonna_Grazia) );

    private static int samePerson;
    private static int sameBocchi;
    private static int sameOstrousche;
    private static int successes;


    public static void main(String[] args){
        // Initialize global variables
        samePerson = 0;
        sameBocchi = 0;
        sameOstrousche = 0;
        successes = 0;

        int trialNum = 10000;

        double randomTrials = runTrialsRandom(trialNum);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Same person happened " + (double) samePerson / trialNum);
        System.out.println("Same Bocchi happened " + (double) sameBocchi / trialNum);
        System.out.println("Same Ostrousche happened " + (double) sameOstrousche / trialNum);
        System.out.println("Success happened " + (double) successes / trialNum);

    }


    private static double runTrialsRandom(int trials){

        for(int trial = 0; trial < trials; trial++){
            // Generate who each person is associated to
            People[] results = generateRandom();
            // Test if it fits the requirements
            if(test(results) ){
                successes++;
            }
        }

        return (double) successes / trials;
    }

    private static People[] generateRandom(){
        // Generate a shuffled list of integers from 0 - 5
        int[] intArray = efficientShuffle(NUM_PEOPLE);

        // Shuffle the list of people
        People[] people = new People[6];
        People[] peopleList = People.values();
        for(int i = 0; i < NUM_PEOPLE; i++){
            people[i] = peopleList[intArray[i] ];
        }

        // Return the list of people in a randomly shuffled order
        return people;
    }

    private static boolean test(People[] results){
        System.out.println();
        System.out.println("Results are " + Arrays.toString(results) );

        People[] receivers = People.values();
        System.out.println("Receivers are " + Arrays.toString(receivers) );

        boolean isSamePerson = false;
        boolean isSameBocchi = false;
        boolean isSameOstrousche = false;

        boolean foundFalse = false;

        for(int index = 0; index < NUM_PEOPLE; index++){
            // Nobody can get themselves
            if(receivers[index].equals(results[index]) ){
                isSamePerson = true;
                System.out.println("Same person with index " + index);
                foundFalse = true;
            }

            // A bocchi can't get a bocchi
            if(bocchi.contains(receivers[index]) && bocchi.contains(results[index]) ){
                isSameBocchi = true;
                System.out.println("Same bocchi");
                foundFalse = true;
            }

            // An ostrouska can't get an ostrouska
            if(ostrouche.contains(receivers[index]) && ostrouche.contains(results[index]) ){
                isSameOstrousche = true;
                System.out.println("Same ostrousche");
                foundFalse = true;
            }

        }

        if(isSamePerson){
            samePerson++;
        }
        if(isSameBocchi) {
            sameBocchi++;
        }
        if(isSameOstrousche){
            sameOstrousche++;
        }

        if(foundFalse){
            return false;
        }

        System.out.println("Success!");
        return true;
    }

    // Generates a shuffled array from 0 to numDigits - 1 in O(n^2) so ig not that efficient but it works for small n
    private static int[] efficientShuffle(int numDigits){
        int[] result = new int[numDigits];

        // Initialize sets
        List<Integer> remainingIndeces = new ArrayList<>();
        for(int i = 0; i < numDigits; i++){
            remainingIndeces.add(i);
        }

        // Add the values randomly to the array
        for(int i = 0; i < numDigits; i++){
            int index = (int) (Math.random() * remainingIndeces.size() );
            result[remainingIndeces.get(index)] = i;
            remainingIndeces.remove(index);
        }

        System.out.println("Result is " + Arrays.toString(result) );
        return result;
    }


}
