
import java.util.ArrayList;



public class ServerHeuristicFunction implements aima.search.framework.HeuristicFunction{
    static ArrayList<Double> serv;

    static int nums;

    public int HeuristicMode = 1;
    public double getHeuristicValue(Object o) {
        ServerBoard board = (ServerBoard) o;

        serv = new ArrayList<>(nums);
        for(int i = 0; i < nums; i++) {
            serv.add((double)0);
        }

        for(int i= 0; i < board.solution.size();++i){
            for (int j = 0; j < board.solution.get(i).size(); ++j) {
                int ser = board.solution.get(i).get(j);
                int tt = board.servers.tranmissionTime(ser, i);
                serv.set(j,serv.get(j) + (double)tt);
            }
        }

        switch (HeuristicMode){
            case 1:
                return Heuristic1(o);
//
            case 2:
                return Heuristic2(o);
            default:
                return 0;
        }
    }

    public static void numserv(int nserv){
        nums = nserv;
    }

    /*public static void Vectorinicial(Object o){
        ServerBoard board = (ServerBoard) o;
        int v = 0;
        v = board.servers.size();
        serv = new ArrayList<>(v);
        for(int i= 0; i < board.solution.size();++i){
            for (int j = 0; j < board.solution.get(i).size(); ++j) {
                int ser = board.solution.get(i).get(j);
                int tt = board.servers.tranmissionTime(ser, i);
                serv.set(j,(double)tt);
            }
        }

    }*/
    private double Heuristic1(Object o){ // Min. transmission time of the server that takes more time for his requests.
        double res = Double.NEGATIVE_INFINITY;
        for(int i= 0; i < serv.size();++i){
            if(serv.get(i)>res) res = serv.get(i);
        }
        System.out.println(res);
        return res;
    }

    private double Heuristic2(Object o){ // Min. transmission time of the server that takes more time for his requests.
        double min = Double.POSITIVE_INFINITY;
        double max =  Double.NEGATIVE_INFINITY;
        double total = 0;
        for(int i= 0; i < serv.size();++i){
            total += serv.get(i);
            if(serv.get(i)>max) max = serv.get(i);
            if(serv.get(i)<min) min = serv.get(i);
        }
        System.out.println(total + (max-min)*(max-min));
        return total + (max-min)* (max-min);
    }

}
