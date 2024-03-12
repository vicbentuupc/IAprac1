import IA.DistFS.Requests;
import IA.DistFS.Servers;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception{

        int users = 10;
        int requests = 3;
        int servers = 10;
        int nrep = 3;
        int seed = new Date().hashCode();
        Requests r = new Requests(users, requests, seed);
        Servers s = new Servers(servers, nrep, seed);

        ServerBoard board = new ServerBoard(1, r, s, users);

        Problem p = new  Problem(board,
                new ServerSuccessorFunction(),
                new ServerGoalTest(),
                new ServerHeuristicFunction());

        // Search alg = new SimulatedAnnealingSearch(200000, 10, 5, 0.001);
        Search alg = new HillClimbingSearch();
        SearchAgent agent = new SearchAgent(p, alg);

        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());


        for (int i = 0; i < board.solution.size(); i++) System.out.println("User " + i + " -> " + board.solution.get(i));
        for (int i = 0; i < r.size(); ++i) System.out.println("File " + r.getRequest(i)[1] + ": " + s.fileLocations(r.getRequest(i)[1]));
        for (int i = 0; i < ServerBoard.user2file.size(); ++i) System.out.println("User " + i + " wants files: " + ServerBoard.user2file.get(i));


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