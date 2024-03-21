import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class ServerSuccessorFunctionSA implements SuccessorFunction {

    public List<Successor> getSuccessors(Object state) {
        List<Successor> list = new ArrayList<Successor>();
        ServerBoard board = (ServerBoard) state;
        for (int i = 0; i < board.solution.size(); ++i) {
            for (int j = 0; j < board.solution.get(i).size(); ++j) {
                Integer[] serversWithFile = ServerBoard.servers.fileLocations(ServerBoard.user2file.get(i).get(j)).toArray(new Integer[0]);
                for (int k = 0; k < serversWithFile.length; ++k) {
                    if (serversWithFile[k] == board.solution.get(i).get(j)) continue;
                    ServerBoard newBoard = new ServerBoard(board);
                    newBoard.change_serv(i, j, serversWithFile[k]);
                    list.add(new Successor("User " + i + " changes file in index " + j + " from server " + board.solution.get(i).get(j) + " to server " + serversWithFile[k], newBoard));
                }
            }
        }

        //list.add(new Successor("Successor", newBoard));
        return list;
    }

}
