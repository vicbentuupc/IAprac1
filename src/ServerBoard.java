import IA.DistFS.Requests;
import IA.DistFS.Servers;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class ServerBoard {

    //representation
    public Map<Integer, Integer> user2index; // Usr -> [file, server]
    public Vector<Vector<Integer>> serverBoard;
    public Vector<Vector<Integer>> solution; // Usr -> [file, server] (es un map, para agilizar el changeServ)

    //constructor(initial solution)
    //mode == 1(random), mode == 2(nearest server)
    public ServerBoard(int mode, Requests r, Servers s, int usr_num) { //pasarle el #usr(es un parametro que tu das en el main)
        if(mode == 1){
            int n = r.size();
            serverBoard = new Vector<>(usr_num);
            for (int i = 0; i < usr_num; i++) serverBoard.add(new Vector<Integer>(n));
            for (int i = 0; i < usr_num; i++) {
                int[] aux = r.getRequest(i);
                //serverBoard.get(aux[0]).add(aux[1]);
                System.out.println("User " + i + " -> " + aux[0] + " " + aux[1]);
            }
            //for (int i = 0; i < usr_num; i++) Collection.sort(serverBoard.get(i));


            solution = new Vector<>(usr_num);
            for (int i = 0; i < usr_num; i++) solution.add(new Vector<Integer>());
            for (int i = 0; i < usr_num; i++) {
                for (int j = 0; j < serverBoard.get(i).size(); j++) {
                    int rand_serv = random_server(serverBoard.get(i).get(j), s);
                    solution.get(i).add(rand_serv);
                }
            }
        }
        else if(mode == 2){

        }
    }

    //"operators functions"
    public void change_serv(int usr, int file, int serv, Servers s){
        //solution[usr][file] = serv;
    }

    //"aplicability functions"
    public boolean file_in_serv(int file, int serv, Servers s){
        Set servs = (Set) s.fileLocations(file);
        if(servs.contains(serv))return true;
        else return false;
    }

    //"auxiliar functions"

    //return a random serverID which contains the fileID f
    public int random_server(int f, Servers  s) {
        Set servs = (Set) s.fileLocations(f);
        int size = servs.size();
        // Generate random integers in range 0 to size
        Random rand = new Random();
        int rand_int1 = rand.nextInt(size);
        return (int) servs.toArray(new Integer[size])[rand_int1];
    }

    public int[] getBoard(){ //no funciona
        int[] board = new int[serverBoard.size()];
        for (int i = 0; i < serverBoard.size(); i++) {
            //board[i] = serverBoard.get(i);
        }
        return board;
    }

}
