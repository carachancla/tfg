#!/usr/bin/env bash

#############################
# TINY ALL-TO-ALL FRACTION

FOLDER_IN_RESULTS=W_5_trafficLimit_hybrid_multi_channel_50
LABEL=W_1_trafficLimit_hybrid

# Combine results
python ../../../analysis/multi_combine.py all_median_fct_ms flow_completion.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows flows > data_${LABEL}_mean_fct_ms.txt
sort data_${LABEL}_mean_fct_ms.txt -o data_${LABEL}_mean_fct_ms.txt

python ../../../analysis/multi_combine.py channel_usage channel_use.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows flows > data_${LABEL}_mean_channel_use.txt
sort data_${LABEL}_mean_channel_use.txt -o data_${LABEL}_mean_channel_use.txt

python ../../../analysis/multi_combine.py percentage_collision channel_use.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows flows > data_${LABEL}_percentage_collision.txt
sort  data_${LABEL}_percentage_collision.txt -o  data_${LABEL}_percentage_collision.txt

python ../../../analysis/multi_combine.py all_throughput_median_Gbps flow_completion.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows flows > data_${LABEL}_mean_bandwidth.txt
sort data_${LABEL}_mean_bandwidth.txt -o data_${LABEL}_mean_bandwidth.txt

python ../../../analysis/multi_combine.py all_flows_completed_fraction flow_completion.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows flows > data_${LABEL}_unfinished_flows.txt
sort data_${LABEL}_unfinished_flows.txt -o data_${LABEL}_unfinished_flows.txt

# Plot
gnuplot plot_flow_mean_delay_Jellyfish.plt

gnuplot plot_flow_mean_channel_use_Jellyfish.plt

gnuplot plot_flow_mean_percentage_collision_Jellyfish.plt

gnuplot plot_flow_mean_bandwidth_Jellyfish.plt

gnuplot plot_flow_unfinished_flows_Jellyfish.plt

