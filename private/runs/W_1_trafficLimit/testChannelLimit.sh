#!/usr/bin/env bash

#!/usr/bin/env bash

calc() { awk "BEGIN{print $*}"; }

################################################################
### GENERAL SETTINGS
machine=pserra@serra.cba.upc.edu
netbechPath=tfg/

#machine=127.127.127.127
#netbechPath=Desktop/tfg/tfg/
num_flows=5000
runtime=50


#runtime=`calc ${num_flows} / ${flows_per_s}`;
#cd ../..
#java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}
flows_per_s=100
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=200
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=400
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=700
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=1500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=2000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=2500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=5000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=7500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"


flows_per_s=75000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar private/runs/W_1_trafficLimit/jellyfish_n128_d30_hybrid.properties run_folder_name=W_1_trafficLimit/jellyfish_n128_d30_hybrid_${actservers}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

echo "runs initialized"