package PhysicallTopo;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

public class TopoAnalysis {

    public static void main(String Args[]) throws Exception {

        File dir = new File("temp/results/physicalTopo/topologies");
        File[] files = dir.listFiles();

        Vector<Vector<Integer>> result = new Vector<>();


        for(File file:files){
            System.out.println("analysing file: " + file.getName());

            PhysicalLayout p = new PhysicalLayout();
            p.readGraph(file.getPath());
            p.readTopologyProperties("./example/Layouts/Test.layout");
            Vector<Integer> temp = new Vector<>();

            temp.add(p.graphDetails.getNumTors());
            temp.add(p.graphDetails.getNumEdges() / p.graphDetails.getNumTors());
            temp.add(p.lonerCablesThan(100));
            temp.add(p.additionalSwitchesDueToDistance(100));
            result.add(temp);

        }
        //System.out.println(result.toString().replace("[","").replace("], ","\n").replace("]]",""));

        File resultFile = new File("temp/results/physicalTopo/analysisResult");
        resultFile.createNewFile();
        FileWriter fw = new FileWriter(resultFile,false);
        fw.write(result.toString().replace("[","").replace("], ","\n").replace("]]",""));
        fw.flush();
        fw.close();
        System.out.println("Result writen on file");

    }


}
