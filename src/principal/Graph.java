package principal;

import java.util.Hashtable;
import java.util.Vector;

public abstract class Graph {


    protected Numerotation numerotation;

    public Graph(Numerotation num) {
        this.numerotation = num;

    }


    public abstract Graph copyGraph();

    public abstract boolean addVertex(Vertex v);

    public abstract  boolean deleteVertex(Vertex v);

    public abstract boolean addEgde(Edge e);

    public abstract boolean deleteEdge(Vertex a,Vertex b);

    public abstract void readFromKeyBoard();

    public abstract boolean readFromFile(String fileName);

    public abstract void readFromGUI();

    public abstract void displayOnConsole();

    public abstract boolean writeToFile(String fileName);

    public abstract void displayOnGUI();

    public abstract int[] getFS();

    public abstract int[] getAPS();

    public abstract Edge[][] getAdjMat();

    public abstract int[] getDDI();

    public abstract boolean existEdge(Vertex a, Vertex b);

    public abstract int[] getAPP();

    public abstract int[] getFP();


    public abstract boolean existVertex(Vertex a);

    public abstract double valueEdge(Vertex a, Vertex b);

    public abstract double valueEdge(int a, int b);

    public abstract void setValueEdge(int i, int j,int n);


    public abstract int vertexNumber();

    public abstract int edgeNumber();

    public abstract void setVertexNumber(int n);

    public abstract void setEdgeNumber(int n);

    public Numerotation getNumerotation(){return  numerotation;}

}
