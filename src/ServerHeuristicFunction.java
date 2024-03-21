import java.util.Random;

public class ServerHeuristicFunction implements aima.search.framework.HeuristicFunction{

    public int HeuristicMode = 1;
    public double getHeuristicValue(Object o) {
        switch (HeuristicMode){
            case 1:
                return Heuristic1(o);
//            case 2:
//                return Heuristic2(o);
            default:
                return 0;
        }
    }

    private double Heuristic1(Object o){ // Min. transmission time of the server that takes more time for his requests.
        //return 0;
        Random rand = new Random();
        int rand_int1 = rand.nextInt(10);
        return rand_int1;
    }
}
