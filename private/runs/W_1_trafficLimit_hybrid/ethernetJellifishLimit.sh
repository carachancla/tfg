#!/usr/bin/env bash

#!/usr/bin/env bash

calc() { awk "BEGIN{print $*}"; }

################################################################
### GENERAL SETTINGS
machine=pserra@serra.cba.upc.edu
netbechPath=tfg/

#machine=127.127.127.127
#netbechPath=Desktop/tfg/tfg/
runtime=30

properties_file=private/runs/W_1_trafficLimit_hybrid/jellyfish_n128_d30_ethernet.properties
run_folder=W_1_trafficLimit_hybrid/jellyfish_n128_d30_ethernet_flows_


#runtime=`calc ${num_flows} / ${flows_per_s}`;

flows_per_s=500
#cd ../../.. ;java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}
#exit

#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=1500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=3000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=5000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=7000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=10000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=50000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"
flows_per_s=80000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

echo "runs initialized"