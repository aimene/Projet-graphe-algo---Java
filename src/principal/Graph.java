package principal;

import java.util.Hashtable;
import java.util.Vector;

public abstract class Graph {
    protected int numberOfVertex,numberOfEdge;
    protected Numerotation numerotation;
    public abstract Graph copyGraph();
    public abstract boolean addVertex(Vertex v);
    public abstract  boolean deleteVertex(Vertex v);
    public abstract boolean addEgde(Vertex a, Vertex b);
    public abstract boolean deleteEdge(Vertex a,Vertex b);
    public abstract void readFromKeyBoard();
    public abstract boolean readFromFile(String fileName);
    public abstract void readFromGUI();
    public abstract void displayOnConsole();
    public abstract boolean writeToFile(String fileName);
    public abstract void displayOnGUI();
    public abstract Vector<Vertex> getFS();
    public abstract Vector<Hashtable<Vertex, Integer>> getAPS();
    public abstract Vector<Vector<Edge>> getAdjMat();
    public abstract Vector<Integer> getDDI();
    public abstract boolean existEdge(Vertex a,Vertex b);
    public abstract boolean existVertex(Vertex a);
    public abstract double valueEdge(Vertex a, Vertex b);
    public abstract double valueEdge(int a,int b);
}
