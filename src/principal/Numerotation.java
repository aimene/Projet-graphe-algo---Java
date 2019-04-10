package principal;

import java.util.Hashtable;
import java.util.Vector;

public class Numerotation {

    private int counter ;
    private Hashtable< Vertex ,Integer> vertexHashTable;
    private Vector<Vertex> vertex;

    public Numerotation(int n){
        counter = -1;
        vertexHashTable = new Hashtable<Vertex,Integer>();
        vertex = new Vector<Vertex>(n);
        vertex.setSize(n);
    }
    public int indexOf(Vertex v){
        return vertexHashTable.get(v);
    }

    public Vertex vertexOf(int i){
        return vertex.get(i) ;
    }

    public Vector<Vertex> getAllVertex(){
        return vertex;
    }

    public boolean addVertex ( Vertex v){
        if(!vertexHashTable.containsKey(v)){
            counter++;
            vertexHashTable.put(v, counter);
            vertex.set(counter, v);
            return true;
        }
        return false;

    }


}
