import IA.DistFS.Requests;
import IA.DistFS.Servers;

import java.util.*;

public class ServerBoard {

    //representation
    static public Map<Integer, Integer> user2index; // Usr -> [file, server]
    static public Vector<Vector<Integer>> user2file; // which file in [user][position]
    public Vector<Vector<Integer>> solution; // Usr -> [file, server] (es un map, para agilizar el changeServ)

    //constructor(initial solution)
    //mode == 1(random), mode == 2(nearest server)
    public ServerBoard(int mode, Requests r, Servers s, int usr_num) { //pasarle el #usr(es un parametro que tu das en el main)
        user2index = new HashMap<Integer, Integer>();
        user2file = new Vector<>(usr_num);
        for (int i = 0; i < usr_num; i++) user2file.add(new Vector<Integer>(0));
        solution = new Vector<>(usr_num);
        for (int i = 0; i < usr_num; i++) solution.add(new Vector<Integer>(0));
        Integer indexAux = -1;
        for (int i = 0; i < r.size(); i++) {
            //static
            int aux[] = r.getRequest(i);
            if (!user2index.containsKey(aux[0])) {
                indexAux++;
                user2index.put(aux[0], indexAux);
            }
            System.out.println(indexAux);
            System.out.println(aux[1]);

            user2file.get(indexAux).add(aux[1]);
            }
        if(mode == 1) {
            for (int i = 0; i < user2file.size(); i++) {
                for (int j = 0; j < user2file.get(i).size(); j++) {
                    solution.get(i).add(random_server(user2file.get(i).get(j), s));
                }
            }
        }
        else if(mode == 2){

        }
    }

    //"operators functions"
    public void change_serv(int usrind, int fileind, int serv, Servers s){
        solution.get(usrind).set(fileind, serv);
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
        //Generate random integers in range 0 to size
        Random rand = new Random();
        int rand_int1 = rand.nextInt(size);
        return (int) servs.toArray(new Integer[size])[rand_int1];
    }



}
