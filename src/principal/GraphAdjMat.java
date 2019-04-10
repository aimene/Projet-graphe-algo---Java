package principal;

import java.util.ArrayList;
import java.util.Hashtable;


public class GraphAdjMat extends Graph{
    private Edge [][] adjencyMatrix;


    @Override
    public Graph copyGraph() {
        return null;
    }

    @Override
    public boolean addVertex(Vertex v) {
        return false;
    }

    @Override
    public boolean deleteVertex(Vertex v) {
        return false;
    }

    @Override
    public boolean addEgde(Vertex a, Vertex b) {
        return false;
    }

    @Override
    public boolean deleteEdge(Vertex a, Vertex b) {
        return false;
    }

    @Override
    public void readFromKeyBoard() {

    }

    @Override
    public boolean readFromFile(String fileName) {
        return false;
    }

    @Override
    public void readFromGUI() {

    }

    @Override
    public void displayOnConsole() {

    }

    @Override
    public boolean writeToFile(String fileName) {
        return false;
    }

    @Override
    public void displayOnGUI() {

    }

    @Override
    public Vertex[] getFS() {
        return new Vertex[0];
    }

    @Override
    public int[] getAPS() {
        return new int[0];
    }

    @Override
    public Edge[][] getAdjMat() {
        return new Edge[0][];
    }

    @Override
    public int[] getDDI() {
        return new int[0];
    }

    @Override
    public boolean existEdge(Vertex a, Vertex b) {
        return false;
    }

    @Override
    public boolean existVertex(Vertex a) {
        return false;
    }

    @Override
    public double valueEdge(Vertex a, Vertex b) {
        return 0;
    }

    @Override
    public double valueEdge(int a, int b) {
        return 0;
    }

    @Override
    public int vertexNumber() {
        return 0;
    }

    @Override
    public int edgeNumber() {
        return 0;
    }
}
