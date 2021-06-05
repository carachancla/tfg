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

properties_file=private/runs/W_1_trafficLimit_hybrid/jellyfish_n128_d30_hybrid.properties
run_folder=W_1_trafficLimit_hybrid/jellyfish_n128_d30_hybrid

#######################
#Default config changes

wireless_port_access_mode=non-persistent
max_cable_length=5
flows_per_s=500
#cd ../../../;java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}
#exit

ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=2500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=4000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=7000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=9000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

####################
#change cable length
####################
max_cable_length=15
flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=2500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=4000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=7000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=9000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

####################
#change cable length
####################
max_cable_length=40
flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=2500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=4000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=7000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=9000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"


####################
#change cable length
####################
max_cable_length=70
flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=2500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=4000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=7000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=9000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

####################
#change cable length
####################
max_cable_length=90
flows_per_s=1000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=2500
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=4000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=7000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

flows_per_s=9000
#runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh ${machine} "cd ${netbechPath}; screen -d -m java -ea -jar NetBench.jar ${properties_file} run_folder_name=${run_folder}_cableLength_${max_cable_length}_flows_${flows_per_s}_runtime_${runtime}s traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} wireless_port_access_mode=${wireless_port_access_mode} max_cable_length=${max_cable_length}"

echo "runs initialized"