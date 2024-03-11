import IA.DistFS.Requests;
import IA.DistFS.Servers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Map;

public class ServerBoard {

    //representation
    public ArrayList<Map<int,int>> solution; // Usr -> [file, server] (es un map, para agilizar el changeServ)

    //constructor(initial solution)
    //mode == 1(random), mode == 2(nearest server)
    public ServerBoard(int mode, Requests r, Servers s, int usr_num) { //pasarle el #usr(es un parametro que tu das en el main)
        if(mode == 1){
            int n = r.size();
            solution.resize(usr_num);
            for(int i = 0; i<n; i++){
                int[] aux = r.getRequest(i);
                int rand_serv = random_server(aux[1], s);
                solution[aux[0]].(new int[]{aux[1], rand_serv});
            }
        }
        else if(mode == 2){

        }
    }

    //"operators functions"
    public void change_serv(int usr, int file, int serv, Server s){
        solution[usr][file] = serv;
    }

    //"aplicability functions"
    public boolean file_in_serv(int file, int serv, Server s){
        Set servs = (Set) s.fileLocations(f);
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
        return servs[rand_int1];
    }

}
