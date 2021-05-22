#!/usr/bin/env bash

#!/usr/bin/env bash

calc() { awk "BEGIN{print $*}"; }

################################################################
### GENERAL SETTINGS


flows_per_s=100
runtime=`calc ${num_flows} / ${flows_per_s}`;
ssh user@machine.com "cd /path/to/folder/netbench; screen -d -m ../java/jre1.8.0_131/bin/java -ea -jar NetBench.jar private/runs/1_failcases/full_fat_tree_k16.properties run_folder_name=1_failcases/tiny_frac/full_fat_tree_k16_actservers_${actservers}_flows_${flows_per_s}_runtime_${runtime}s_ecn_threshold_${ecn_threshold}_intermediary_${intermediary}_flowlet_gap_${FLOWLET_GAP_NS} output_port_ecn_threshold_k_bytes=${ecn_threshold} traffic_lambda_flow_starts_per_s=${flows_per_s} run_time_s=${runtime} network_device_intermediary=${intermediary} FLOWLET_GAP_NS=${FLOWLET_GAP_NS} traffic_flow_size_dist=${traffic_flow_size_dist} traffic_probabilities_file=private/data/pair_distributions/s320_two_tors_probabilities.txt"
