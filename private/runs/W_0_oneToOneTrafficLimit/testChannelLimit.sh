#!/usr/bin/env bash

#!/usr/bin/env bash

calc() { awk "BEGIN{print $*}"; }

################################################################
### GENERAL SETTINGS
machine=pserra@serra.cba.upc.edu
netbechPath=tfg/

#machine=127.127.127.127
#netbechPath=Desktop/tfg/tfg/
runtime=50

properties_file=private/runs/W_0_oneToOneTrafficLimit/jellyfish_n2_d1_wireless.properties
run_folder=W_0_oneToOneTrafficLimit/jellyfish_n2_d1_wireless_flows_


#runtime=`calc ${num_flows} / ${flows_per_s}`;

flows_per_s=100
cd ../../.. ;java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}
exit
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=200
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=400
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=700
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=2000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=4000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=8000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

flows_per_s=10000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime}"

echo "runs initialized"