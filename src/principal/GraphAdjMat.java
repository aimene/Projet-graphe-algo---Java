package principal;




public class GraphAdjMat extends Graph{
    private Edge [][] adjencyMatrix;


    @Override
    public Graph copyGraph() {
        return null;
    }

    @Override
    public boolean addVertex(Vertex v) {
        if (!numerotation.existVertex(v)) {
            Edge[][] adjencyMatrixTemp = new Edge[vertexNumber() + 1][vertexNumber() + 1];
            for (int i = 1; i <= vertexNumber();i++)
            {
                for (int j=1;j<= vertexNumber();j++){
                    adjencyMatrixTemp[i][j]=adjencyMatrix[i][j];
                }
            }
            adjencyMatrix=adjencyMatrixTemp;
            numerotation.addVertex(v);
                return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteVertex(Vertex v) {
        for(){

        }
        return false;
    }

    @Override
    public boolean addEgde(Edge e) {
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
    public int[] getFS() {
        int [] fs = new int [vertexNumber()+1]
        return new Vertex[0];
    }

    @Override
    public int[] getAPS() {
        return new int[0];
    }

    @Override
    public Edge[][] getAdjMat() {
        return adjencyMatrix;
    }

    @Override
    public int[] getDDI() {
        return new int[0];
    }

    @Override
    public boolean existEdge(Vertex a, Vertex b) {
        if( existVertex(a) && existVertex(b) && adjencyMatrix[a.getIndex()][b.getIndex()]!=null )
            return true;
        else
            return false;
    }

    @Override
    public boolean existVertex(Vertex a) {
        if (numerotation.indexOf(a)==-1)
            return false;
        else
            return true;
    }

    @Override
    public double valueEdge(Vertex a, Vertex b) {
        return adjencyMatrix[a.getIndex()][b.getIndex()].getValue();
    }

    @Override
    public double valueEdge(int a, int b) {
        return adjencyMatrix[a][b].getValue();
    }

    @Override
    public int vertexNumber() {
        return (int)adjencyMatrix[0][0].getValue();
    }

    @Override
    public int edgeNumber() {
        return (int)adjencyMatrix[0][1].getValue();
    }
}
