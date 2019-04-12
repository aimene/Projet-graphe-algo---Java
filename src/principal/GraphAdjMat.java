package principal;




public class GraphAdjMat extends Graph {

    private Edge[][] adjencyMatrix;

    public GraphAdjMat(Numerotation num) {
        super(num);
    }
    public GraphAdjMat(Numerotation num,Edge[][] adjencyMatrix) {
        super(num);
        this.adjencyMatrix=adjencyMatrix;
    }


    @Override
    public Graph copyGraph() {
        return new GraphAdjMat(numerotation,adjencyMatrix);
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
        if (numerotation.existVertex(v)) {
            for (int i = 0; i <= vertexNumber(); i++) {
                adjencyMatrix[numerotation.indexOf(v)][i] = null;
                adjencyMatrix[i][numerotation.indexOf(v)] = null;
            }
            numerotation.deleteVertex(v);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean addEgde(Edge e) {
        if (numerotation.existVertex(e.getVertexA()) && numerotation.existVertex(e.getVertexB())){
            adjencyMatrix[numerotation.indexOf(e.getVertexA())][numerotation.indexOf(e.getVertexB())]=e ;
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteEdge(Vertex a, Vertex b) {
        if (numerotation.existVertex(a) && numerotation.existVertex(b)){
            adjencyMatrix[numerotation.indexOf(a)][numerotation.indexOf(b)]=null ;
            return true;
        }else
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

        int [] fs = new int [vertexNumber()+edgeNumber()+1];
        fs[0]=vertexNumber()+edgeNumber();
        int k=1;
        for (int i = 1; i <= vertexNumber();i++)
        {
            for (int j=1;j<= vertexNumber();j++){
               if (adjencyMatrix[i][j]!=null){

                   fs[k]=numerotation.indexOf(adjencyMatrix[i][j].getVertexA());
                   k++;
               }
            }
            fs[k]=0;
            k++;
        }

        return fs;

    }

    @Override
    public int[] getAPS() {

        int aps [] = new int [vertexNumber()+1];
        aps[0]=vertexNumber();
        aps[1]=1;
        int j = 2 ;
        int [] fs = getFS();
        for (int i =1; i <vertexNumber();i++){
            if (fs[i]==0){
                aps[j]=i+1;
                j++;
            }
        }
        return aps;
    }

    @Override
    public int[] getDDI() {

        int ddi [] = new int[vertexNumber()+1];
        ddi[0]=vertexNumber();
        int [] aps =getAPS();
        int [] fs = getFS();

        for (int i =1;i<=vertexNumber(); i++){
            ddi[i]=0;
        }
        for (int i =1;i<=vertexNumber()+edgeNumber(); i++){
            ddi[fs[i]]++;
        }
        return ddi;
    }

    @Override
    public Edge[][] getAdjMat() {
        return adjencyMatrix;
    }



    @Override
    public boolean existEdge(Vertex a, Vertex b) {
        if( existVertex(a) && existVertex(b) && adjencyMatrix[numerotation.indexOf(a)][numerotation.indexOf(b)]!=null )
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
        return adjencyMatrix[numerotation.indexOf(a)][numerotation.indexOf(b)].getValue();
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
