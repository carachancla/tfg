#!/usr/bin/env python3.8

import os
import matplotlib.pyplot as plt

generate = True
analysis = True

# Generate jellyfish topologies

if generate :
    os.chdir("../../xpander_generation/python/")
    for numV in range(0, 2**14, 2000): #matalb's size limit
        for degree in range(5, 36, 5):
            if numV <= degree + 1 or (numV * degree) % 2 == 1: continue  # check if degree-regular graph is posible
            os.system("python jellyfishGen.py " + str(numV) + ' ' + str(degree))

# Analysis here

if analysis :
    os.chdir("../../")
    os.system("java -cp NetBench.jar PhysicallTopo.TopoAnalysis")
    results = open("temp/results/physicalTopo/analysisResult", "r")
    resultsArray = []
    for line in results:
        inner_list = [int(elt.strip()) for elt in line.split(',')]
        resultsArray.append(inner_list)
    plt.plot(resultsArray[0], resultsArray[2], 'ro')
    plt.show()



