package principal;

import java.util.Hashtable;
import java.util.Vector;

public class Numerotation {


    private int counter ;
    private Hashtable< Vertex ,Integer > vertexHashTable;
    private Vector<Vertex> vertex;

    public int getCounter() {
        return counter;
    }

    public Numerotation(){
        counter = 0;
        vertexHashTable = new Hashtable<Vertex ,Integer>();
        vertex = new Vector<Vertex>();

    }


   public boolean existVertex( Vertex v){
        return vertex.contains(v);
   }

    public boolean existVertex( int i){
        return vertexHashTable.contains(i);
    }

    public Vertex vertexOf(int i){
        if (existVertex(i))
            return vertex.get(i) ;
        else
            return null;
    }

    public int indexOf(Vertex v){
        if (vertexHashTable.containsKey(v))
            return vertexHashTable.get(v);
        else
            return -1;
    }
    public Vector<Vertex> getAllVertex(){
        return vertex;
    }

    public boolean addVertex ( Vertex v){
        if(!existVertex(v)){
            counter++;
            vertexHashTable.put( v, counter);
            vertex.setSize(counter); // ce que j'ai ajoute : oumar
            vertex.add(counter, v); // change : oumar
            return true;
        }
        return false;

    }

    public boolean deleteVertex ( Vertex v){

        if (existVertex(v)){
            vertexHashTable.remove(v);
            vertex.remove(v);
            return true;
        }else{
            return false;
        }

    }


}
