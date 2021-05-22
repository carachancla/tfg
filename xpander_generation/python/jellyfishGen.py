import matlab.engine
import os
import sys


if len(sys.argv)!=3:
    print("Usage: jellyFishGen (int)numV (int)degree")
    exit(0)

numV = int(sys.argv[1])
degree = int(sys.argv[2])

filename = "Jellyfish_" + str(numV) + "V_" + str(degree) + "deg.topology"
filePath = "../../temp/results/physicalTopo/topologies/"
topoFile = open(filePath + filename, "w")

# add desciption
topoFile.write("#Test\n\n")
# add Details
topoFile.write("#Details\n")
topoFile.write("|V|=" + str(numV) + '\n')
topoFile.write("|E|=" + str(degree * numV) + '\n')
topoFile.write("ToRs=incl_range(0," + str(numV - 1) + ")\n") #all nodes are ToRs
topoFile.write("Servers=incl_range(0," + str(numV - 1) + ")\n")
topoFile.write("Switches=set()\n\n")

# add ToRs Links

path = os.path.dirname(os.path.abspath(__file__))
eng = matlab.engine.start_matlab()
eng.addpath(path + '/../lib')
eng.addpath(path + '/../expansion_algs')
eng.addpath(path + '/../graph_generation')
tf = eng.jellyfish(numV, degree)

# add servers?

topoFile.write("# Links \n")
for x in range(len(tf)):
    for y in range (len(tf[x])):
        if tf[x][y]:
            topoFile.write(str(x) + " " + str(y) + '\n')
