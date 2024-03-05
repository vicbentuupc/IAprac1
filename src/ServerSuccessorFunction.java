import aima.search.framework.SuccessorFunction;
import java.util.List;
import java.util.ArrayList;

public class ServerSuccessorFunction implements SuccessorFunction {

    public List<ServerBoard> getSuccessors(Object state) {
        ServerBoard board = (ServerBoard) state;
        List<ServerBoard> successors = new ArrayList<ServerBoard>();
        int[] boardArray = board.getBoard();
        for (int i = 0; i < boardArray.length; i++) {
            int[] newBoardArray = boardArray.clone();
            newBoardArray[i] = (newBoardArray[i] + 1) % 2;
            ServerBoard newBoard = new ServerBoard();
            successors.add(newBoard);
        }
        return successors;
    }

}
