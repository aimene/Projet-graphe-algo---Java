package principal;

import java.util.Objects;

public class DirectedEdge extends Edge {

    private int startingIndexVertex;
    private int arrivalIndexVertex;

    public DirectedEdge(Vertex vertexA, Vertex vertexB, double value, int startingIndexVertex, int arrivalIndexVertex) {
        super(vertexA, vertexB, value);
        this.startingIndexVertex = startingIndexVertex;
        this.arrivalIndexVertex = arrivalIndexVertex;
    }

    public int getStartingIndexVertex() {
        return startingIndexVertex;
    }

    public void setStartingIndexVertex(int startingIndexVertex) {
        this.startingIndexVertex = startingIndexVertex;
    }

    public int getArrivalIndexVertex() {
        return arrivalIndexVertex;
    }

    public void setArrivalIndexVertex(int arrivalIndexVertex) {
        this.arrivalIndexVertex = arrivalIndexVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DirectedEdge that = (DirectedEdge) o;
        return startingIndexVertex == that.startingIndexVertex &&
                arrivalIndexVertex == that.arrivalIndexVertex;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), startingIndexVertex, arrivalIndexVertex);
    }

    @Override
    public String toString() {
        return "DirectedEdge{ vertexA = " + getVertexA() +
                " , vertexB = " + getVertexB() +
                " , value = " + getValue() +
                " , startingIndexVertex = " + startingIndexVertex +
                ", arrivalIndexVertex = " + arrivalIndexVertex +
                '}';
    }
}
