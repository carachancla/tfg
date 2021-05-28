#!/usr/bin/env bash

echo "Copying results..."
rsync -ravHe ssh --ignore-existing pserra@serra.cba.upc.edu:tfg/temp/results ../temp
