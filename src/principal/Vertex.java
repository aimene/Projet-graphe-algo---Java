package principal;

import java.awt.Point;
import java.util.Objects;

public class Vertex {

    private String name;

    private double value ;

    private Point position;


    public Vertex(String name, double value,  Point position) {
        this.name = name;
        this.value = value;

        this.position = position;
    }

    public Vertex() {
        this("", 0.0d,  new Point(0, 0));
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
                Objects.equals(name, vertex.name) &&
                Objects.equals(position, vertex.position);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value,  position);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", position=" + position +
                '}';
    }
}
