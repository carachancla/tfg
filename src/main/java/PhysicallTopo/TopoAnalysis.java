package PhysicallTopo;

import edu.asu.emit.algorithm.graph.Graph;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

public class TopoAnalysis {

    private static void do_Stuf(Vector<Vector<Integer>> result, PhysicalLayout p, int l)throws Exception{
        Vector<Integer> temp = new Vector<>();
        temp.add(p.graphDetails.getNumTors());
        temp.add(p.graphDetails.getNumEdges() / p.graphDetails.getNumTors());
        temp.add(p.lonerCablesThan(l));
        temp.add(p.additionalSwitchesDueToDistance(l));
        result.add(temp);
    }
    private static void write_Stuf( Vector<Vector<Integer>> result, File resultFile) throws Exception{
        resultFile.createNewFile();
        FileWriter fw = new FileWriter(resultFile,false);
        fw.write(result.toString().replace("[","").replace("], ","\n").replace("]]",""));
        fw.flush();
        fw.close();
    }

    public static void main(String Args[]) throws Exception {

        File dir = new File("temp/results/physicalTopo/topologies");
        File[] files = dir.listFiles();

        Vector<Vector<Integer>> result = new Vector<>();
        Vector<Vector<Integer>> result_50 = new Vector<>();
        Vector<Vector<Integer>> result_20 = new Vector<>();


        for(File file:files){
            System.out.println("analysing file: " + file.getName());

            PhysicalLayout p = new PhysicalLayout();
            p.readGraph(file.getPath());
            p.readTopologyProperties("./example/Layouts/Test.layout");

            do_Stuf(result,p, 100);
            do_Stuf(result_50,p, 50);
            do_Stuf(result_20,p, 20);

        }
        //System.out.println(result.toString().replace("[","").replace("], ","\n").replace("]]",""));

        File resultFile = new File("temp/results/physicalTopo/analysisResult");
        write_Stuf(result, resultFile);
        resultFile = new File("temp/results/physicalTopo/analysisResult_50");
        write_Stuf(result_50, resultFile);
        resultFile = new File("temp/results/physicalTopo/analysisResult_20");
        write_Stuf(result_20, resultFile);
        System.out.println("Result writen on files");

    }


}
