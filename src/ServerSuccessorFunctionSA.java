import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerSuccessorFunctionSA implements SuccessorFunction {

    public List<Successor> getSuccessors(Object state) {
        Random rand = new Random();
        List<Successor> list = new ArrayList<Successor>();
        ServerBoard board = (ServerBoard) state;
        int i = rand.nextInt(board.solution.size());
        int j;
        do {
            j = rand.nextInt(board.solution.get(i).size());
        } while (j == i);
        Integer[] serversWithFile = ServerBoard.servers.fileLocations(ServerBoard.user2file.get(i).get(j)).toArray(new Integer[0]);
        int k;
        do {
            k = rand.nextInt(serversWithFile.length);
        } while (k == board.solution.get(i).get(j));
        ServerBoard newBoard = new ServerBoard(board);
        newBoard.change_serv(i, j, serversWithFile[k]);
        list.add(new Successor("User " + i + " changes file in index " + j + " from server " + board.solution.get(i).get(j) + " to server " + serversWithFile[k], newBoard));
        return list;
    }

}
