package principal;

import java.awt.*;
import java.util.Objects;

public class Vertex {

    private String name;
    private double value;
    private int index;
    private Point position;


    public Vertex(String name, double value, int index, Point position) {
        this.name = name;
        this.value = value;
        this.index = index;
        this.position = position;
    }

    public Vertex() {
        this("", 0.0d, 0, new Point(0, 0));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Double.compare(vertex.value, value) == 0 &&
                index == vertex.index &&
                Objects.equals(name, vertex.name) &&
                Objects.equals(position, vertex.position);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value, index, position);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", index=" + index +
                ", position=" + position +
                '}';
    }
}
