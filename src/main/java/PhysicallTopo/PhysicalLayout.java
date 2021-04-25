package PhysicallTopo;

import ch.ethz.systems.netbench.core.config.GraphDetails;
import ch.ethz.systems.netbench.core.config.GraphReader;
import edu.asu.emit.algorithm.graph.Graph;
import edu.asu.emit.algorithm.graph.Vertex;
import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;

/*
*  layout matrix fill following aspect ratio
*
*   x--->     y
*   xxxxxxxxx |
*   xxxxxxxxx |
*   xxxxx---- v
*
*   Z:=HIGH
*
*
* */


public class PhysicalLayout {
    // Topology design
    Graph graph;
    GraphDetails graphDetails;

    //Physical layout conditions
    TopoDetails topoDetails = new TopoDetails();

    public void readGraph(String path) {
        Pair<Graph, GraphDetails> result = GraphReader.read(path);
        graph = result.getLeft();
        graphDetails = result.getRight();
    }
    public void readTopologyProperties(String fileName) throws Exception{
        try {

            // Open file stream
            FileReader input = new FileReader(fileName);
            BufferedReader br = new BufferedReader(input);

            // Go over parameter lines one-by-one, stop when encountering non-parameter lines
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Skip empty or commented lines
                if (line.length() == 0 || line.startsWith("#")) {
                    continue;
                }

                // First check for parameters
                int index = line.indexOf("=");
                if (index != -1) {

                    String key = line.substring(0, index);
                    String val = line.substring(index + 1);
                    switch (key) {

                        // |X|=DISTANCE X
                        case "|X|":
                            topoDetails.setDistanceX(Double.valueOf(val));
                            break;

                        // |Y|=DISTANCE Y
                        case "|Y|":
                            topoDetails.setDistanceY(Double.valueOf(val));
                            break;

                        // |Z|=DISTANCE Z
                        case "|Z|":
                            topoDetails.setDistanceZ(Double.valueOf(val));
                            break;

                        // |R|= ASPECT RATIO
                        case "|R|":

                            Fraction ar = Fraction.getFraction(val);
                            topoDetails.setFraction(ar);
                            break;

                        default:
                            throw new IllegalArgumentException("Unauthorized key in file: " + key);
                    }

                } else {
                    break;
                }

            }

            br.close();

            // Check all necessary arguments
            if (topoDetails.getFraction().equals(Fraction.getFraction(-1)) || topoDetails.getDistanceX() == -1 || topoDetails.getDistanceY() == -1 || topoDetails.getDistanceZ() == -1) {
                throw new IllegalArgumentException("One of the mandatory keys is missing");
            }


        } catch (Exception e){
            throw e;
        }
    }
    public int maxNodeX(){
        //squared proportion
        double tmp = Math.sqrt(graphDetails.getNumTors());
        //apply aspect ratio
        tmp = tmp * topoDetails.getFraction().doubleValue();
        return (int) (Math.ceil(tmp));

    }
    public Point2D vertexPosFromId(int id){
        int x,y;
        graph.getVertex(id);
        x = id%maxNodeX();
        y = id/maxNodeX();
        return new Point2D.Double(x* topoDetails.getDistanceX(),y * topoDetails.getDistanceY());
    }

    public double calculateDistanceBetweenPorts(int id1, int id2) throws Exception{
        Vertex port1 = graph.getVertex(id1);
        Vertex port2 = graph.getVertex(id2);

        //Check ports are connected
        if(!graph.getAdjacentVertices(port1).contains(port2)) {
            throw new Exception("nodes are not directly connected");
        }

        return vertexPosFromId(id1).distance(vertexPosFromId(id2));

    }

    public double calculateCableLenght(int id1, int id2) throws Exception{

        Vertex port1 = graph.getVertex(id1);
        Vertex port2 = graph.getVertex(id2);

        //Check ports are connected
        if(!graph.getAdjacentVertices(port1).contains(port2)) {
            throw new Exception("nodes are not directly connected");
        }

        Point2D p1, p2;
        p1 = vertexPosFromId(id1);
        p2 = vertexPosFromId(id2);

        return Math.abs(p1.getX()-p2.getX()) + Math.abs(p1.getY()-p2.getY())+
                2*topoDetails.getDistanceZ();
    }

    public ArrayList<Double> getNodeCabling(int id)throws Exception{
        ArrayList<Double> r = new ArrayList<Double>();
        for(Vertex t:graph.getAdjacentVertices(graph.getVertex(id))){
            int id2 = t.getId();
            r.add(this.calculateCableLenght(id,id2));
        }
        return r;
    }

    public double getTotalCabling() throws Exception {
        double r = 0;
        for (Vertex v:graph.getVertexList()){
            ArrayList<Double> t = this.getNodeCabling(v.getId());
            for (double cable:t) r+=cable;
        }
        //every cable gets added twice
        return r/2;

    }

}
