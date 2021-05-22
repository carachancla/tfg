package PhysicallTopo;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

public class TopoAnalysis {

    public static void main(String Args[]) throws Exception {

        File dir = new File("temp/results/physicalTopo/topologies");
        File[] files = dir.listFiles();

        Vector<Integer> numV = new Vector<>();
        Vector<Integer> degree = new Vector<>();
        Vector<Integer> longCables = new Vector<>();
        Vector<Integer> extraSwitch = new Vector<>();

        for(File file:files){
            System.out.println("analysing file: " + file.getName());

            PhysicalLayout p = new PhysicalLayout();
            p.readGraph(file.getPath());
            p.readTopologyProperties("./example/Layouts/Test.layout");

            numV.add(p.graphDetails.getNumTors());
            degree.add(p.graphDetails.getNumEdges() / numV.lastElement());
            longCables.add(p.lonerCablesThan(100));
            extraSwitch.add(p.additionalSwitchesDueToDistance(100));

        }

        File resultFile = new File("temp/results/physicalTopo/analysisResult");
        resultFile.delete();
        resultFile.createNewFile();
        resultFile.setWritable(true);
        FileWriter fw = new FileWriter(resultFile);
        fw.write(numV.toString().replace("[","").replace("]",""));
        fw.write("\n");
        fw.write(degree.toString().replace("[","").replace("]",""));
        fw.write("\n");
        fw.write(longCables.toString().replace("[","").replace("]",""));
        fw.write("\n");
        fw.write(extraSwitch.toString().replace("[","").replace("]",""));
        fw.close();
        System.out.println("Result writen on file");

    }


}
