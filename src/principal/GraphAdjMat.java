package principal;

import java.util.Hashtable;
import java.util.Vector;

public class GraphAdjMat {
    private Vector<Vector<Edge>> adjencyMatrix;
    private Vertex vertexA;
    private Vertex vertexB;
    private double value;
    protected Numerotation numerotation;
    
    public boolean addVertex(Vertex v) {
        return true;
    }
    
    public boolean deleteVertex(Vertex v) {
        return false;
    }
    
    public boolean addEgde(Vertex a, Vertex b) {
        return true;
    }
    
    public boolean deleteEdge(Vertex a,Vertex b) {
        return true;
    }
    
    public void readFromKeyBoard() {
          
    }
    
    public boolean readFromFile(String fileName) {
        return true;
    }
    
    public void readFromGUI() {
        
    }
    
    public void displayOnConsole() {
        
    }
    
    public Vector<Integer> getFS() {
        
    }
    
    public Vector<Hashtable<Vertex, Integer>> getAPS() {
        
    }
    
    public Vector<Vector<Edge>> getAdjMat() {
        
    }
    
    public Vector<Integer> getDDI() {
        
    }
    
    public boolean existEdge(Vertex a,Vertex b) {
        
    }
    
    public boolean existVertex(Vertex a,Vertex b) {
        
    }
    
    public double valueEdge(Vertex a, Vertex b) {
        
    }
    
    public double valueEdge(int a,int b) {
        
    }
}
