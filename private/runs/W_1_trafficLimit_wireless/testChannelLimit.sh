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

properties_file=private/runs/W_1_trafficLimit_wireless/jellyfish_n128_d30_wireless.properties
run_folder=W_1_trafficLimit_wireless/jellyfish_n128_d30_wireless


#runtime=`calc ${num_flows} / ${flows_per_s}`;
wireless_port_access_mode=non-persistent
flows_per_s=100
#cd ../../.. ;java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}
#exit
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"


flows_per_s=400
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"


flows_per_s=700
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"


flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=1500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=2000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=3000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"


################################################################
################## test 1-persisten acces mode #################
################################################################

flows_per_s=100
wireless_port_access_mode=1-persistent
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=400
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine}  "cd ${netbechPath}; screen -L -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"


flows_per_s=700
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=1500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=2000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

flows_per_s=3000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_mediumAccesMode_${wireless_port_access_mode}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode}"

echo "runs initialized"