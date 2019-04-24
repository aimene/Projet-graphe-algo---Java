package principal;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Vertex {

    private String name;

    private double value ;

    private Point position;


    public Vertex(String name, double value,  Point position) {
        this.name = name;
        this.value = value;
        this.position = position;
        AllVertexes.put(counter,this);
        AllVertexesV.put(this,counter);
        counter++;
    }

    public Vertex() {
        this("", 0.0d,  new Point(0, 0));
    }

    public Vertex(int x, int y) {
        this("s" + AllVertexes.size(),0.0, new Point(x, y));
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


    public void deleteVertex(){
        AllVertexes.remove(this.name,this);
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


    public String toString(){
        return "\"" + getName() + "\" " + getPosition().x + " " + getPosition().y+ " " + getValue();
    }

    //Partie utile pour la classe GraphiqueGraphe

    private static Map<Integer, Vertex> AllVertexes = new HashMap<Integer, Vertex>();
    private static Map<Vertex,Integer > AllVertexesV = new HashMap< Vertex,Integer>();

    private static int counter =0;
    public static int nombreSommets() {
        return AllVertexes.size();
    }

    public static Vertex trouverSommet(int i) {
        return AllVertexes.get(i);
    }

    public static Iterator<Vertex> iterator() {
        return AllVertexes.values().iterator();
    }

    public static Map<Integer, Vertex> getAllVertexes() {
        return AllVertexes;
    }

    public static Map< Vertex,Integer> getAllVertexesV() {
        return AllVertexesV;
    }




}
