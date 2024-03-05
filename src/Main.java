import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception{
        int [] prob = new int []{1 ,0, 1, 1, 0};
        int [] sol = new int[]{1, 1, 0, 1, 0};

        ServerBoard board = new ServerBoard();

        // Create the Problem object
        Problem p = new  Problem(board,
                new ServerSuccessorFunction(),
                new ServerGoalTest(),
                new ServerHeuristicFunction());
        

        // Instantiate the search algorithm
        // Hillclimbing o simulated annealing (s'haura de canviar main pro ara per provar)
        // Search alg = new SimulatedAnnealingSearch(200000, 10, 5, 0.001);
        Search alg = new HillClimbingSearch();
        // Instantiate the SearchAgent object
        SearchAgent agent = new SearchAgent(p, alg);

        // We print the results of the search
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());

        // You can access also to the goal state using the
        // method getGoalState of class Search

    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }

}