package principal;

import java.util.Objects;

public class Edge {
    private Vertex vertexA;
    private Vertex vertexB;
    private double value;


    public Edge(Vertex vertexA, Vertex vertexB, double value) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.value = value;
    }
    public Edge( double value) {
        this.vertexA = null;
        this.vertexB = null;
        this.value = value;
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

    @Override
    public String toString() {
        return "Edge{" +
                "   vertexA = " + vertexA +
                " , vertexB = " + vertexB +
                " , value = " + value +
                '}';
    }

}
