import IA.DistFS.Requests;
import IA.DistFS.Servers;

import java.util.*;

public class ServerBoard {

    //representation
    static public Map<Integer, Integer> index2user_id; // user_index -> user_id
    static public Vector<Vector<Integer>> user2file; // user_index -> file_id
    public Vector<Vector<Integer>> solution; // user_index -> server_id

    //constructor(initial solution)
    //mode == 1(random), mode == 2(nearest server)
    public ServerBoard(int mode, Requests r, Servers s, int usr_num) { //pasarle el #usr(es un parametro que tu das en el main)
        index2user_id = new HashMap<Integer, Integer>();
        user2file = new Vector<>(usr_num);
        for (int i = 0; i < usr_num; i++) user2file.add(new Vector<Integer>(0));
        solution = new Vector<>(usr_num);
        for (int i = 0; i < usr_num; i++) solution.add(new Vector<Integer>(0));
        Integer indexAux = -1;
        for (int i = 0; i < r.size(); i++) {
            //static
            int aux[] = r.getRequest(i); //devuelve(usr,file), estan en orden de usr
            if (!index2user_id.containsValue(aux[0])) {
                indexAux++;
                index2user_id.put(indexAux, aux[0]);
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
            for (int i = 0; i < user2file.size(); i++) {
                for (int j = 0; j < user2file.get(i).size(); j++) {
                    solution.get(i).add(nearest_server(index2user_id.get(i) ,user2file.get(i).get(j), s));
                }
            }
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

    //return a random serverID which contains the fileID f
    public int nearest_server(int us, int f, Servers  s) {
        Set servs = (Set) s.fileLocations(f);
        int size = servs.size();
        Object[] servs_array = servs.toArray(new Integer[size]);
        //buscar el mas cercano
        int actual_serv = (int) servs_array[0];
        int min_t = Integer.MAX_VALUE;
        for (int i = 0; i < servs_array.length; i++) {
            int time = s.tranmissionTime((Integer) servs_array[i], us);
            if (time < min_t){
                actual_serv = (int) servs_array[i];
                min_t = time;
            }
        }
        return (int) actual_serv;
    }


}
