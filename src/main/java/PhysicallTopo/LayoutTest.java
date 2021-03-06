package PhysicallTopo;

public class LayoutTest {

    /////////test///////
    public static void main(String Args[]) throws Exception {
        PhysicalLayout p = new PhysicalLayout();
        //p.readGraph("./example/topologies/xpander/xpander_n100_d10.topology");
        p.readGraph("./temp/results/physicalTopo/topologies/Jellyfish_128V_5deg.topology");
        p.readTopologyProperties("./example/Layouts/Test.layout");
        /*System.out.println("Vertex List:" + p.graph.getVertexList());
        System.out.println("Num tors: " + p.graphDetails.getNumTors());
        System.out.println("Max X: " + p.maxNodeX());
        System.out.println("Pos vertex0: " + p.vertexPosFromId(0));
        System.out.println("Pos vertex1: " + p.vertexPosFromId(24));
        System.out.println("Pos vertex2: " + p.vertexPosFromId(57));
        System.out.println("Cable length vertex1 v2: " + p.calculateCableLenght(24,p.graph.getAdjacentVertices(p.graph.getVertex(24)).get(0).getId()));
        System.out.println(p.calculateDistanceBetweenPorts(24,p.graph.getAdjacentVertices(p.graph.getVertex(24)).get(0).getId()));
        System.out.println(p.calculateDistanceBetweenPorts(0,99));
        System.out.println(p.calculateCableLenght(0,99));
        */
        System.out.println("Total cable: "+ p.getTotalCabling());
        System.out.println("Add switch: " + p.lonerCablesThan(5));
        System.out.println("Add switch: " + p.lonerCablesThan(7));
        System.out.println("Add switch: " + p.lonerCablesThan(10));
        System.out.println("Add switch: " + p.lonerCablesThan(13));
        System.out.println("Add switch: " + p.lonerCablesThan(15));
        System.out.println(p.getTotalCabling());
    }
}
