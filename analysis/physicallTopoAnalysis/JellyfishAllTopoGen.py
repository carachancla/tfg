#!/usr/bin/env python3.8

import os

# Generate jellyfish topologies


os.chdir("../../xpander_generation/python/")
for numV in range(0, 2**14, 2000): #matalb's size limit
    for degree in range(5, 36, 5):
        if numV <= degree + 1 or (numV * degree) % 2 == 1: continue  # check if degree-regular graph is posible
        os.system("python jellyfishGen.py " + str(numV) + ' ' + str(degree))




