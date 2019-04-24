package principal;

import java.util.*;

public class Edge {
    private Vertex vertexA;
    private Vertex vertexB;
    private double value;


    public Edge(Vertex vertexA, Vertex vertexB, double value) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.value = value;
        AllEdges.add(this);
    }
    public Edge( double value) {
        this(null,null,value);
    }

    public Edge(Vertex vertexA, Vertex vertexB){
        this(vertexA,vertexB, 0.0);
    }

    public Edge(){
        this(null,null,0);
    }

    public Vertex getVertexA() {
        return vertexA;
    }

    public void setVertexA(Vertex vertexA) {
        this.vertexA = vertexA;
    }

    public Vertex getVertexB() {
        return vertexB;
    }

    public void setVertexB(Vertex vertexB) {
        this.vertexB = vertexB;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void lessValue(){ this.value -= 1; }

    public void moreValue(){ this.value += 1; }

    public void deleteEdge(){
        AllEdges.remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.value, value) == 0 &&
                Objects.equals(vertexA, edge.vertexA) &&
                Objects.equals(vertexB, edge.vertexB);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vertexA, vertexB, value);
    }

    public String toString(){
        return "\"" + vertexA.getName() + "\" \"" + vertexB.getName()
                + "\"" + " " +  getValue() ;
    }

    /*
     * Structure utile pour la partie graphique
     */
    private static Collection<Edge> AllEdges = new Vector<Edge>();

    public static int nombreAretes() {
        return AllEdges.size();
    }

    public static Iterator<Edge> iterator() {
        return AllEdges.iterator();
    }

    public static Collection<Edge> getEdge(){
        return AllEdges;
    }



}
