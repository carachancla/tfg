#!/usr/bin/env bash

#############################
# TINY ALL-TO-ALL FRACTION

FOLDER_IN_RESULTS=W_1_trafficLimit
LABEL=1_failcase_ecmp

# Combine results
python ../../../analysis/multi_combine.py all_mean_fct_ms flow_completion.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows actservers > data_${LABEL}_mean_fct_ms.txt

python ../../../analysis/multi_combine.py channel_usage channel_use.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows actservers > data_${LABEL}_mean_channel_use.txt

python ../../../analysis/multi_combine.py percentage_collision channel_use.statistics ../../../temp/results/${FOLDER_IN_RESULTS} flows actservers > data_${LABEL}_percentage_collision.txt

# Plot
gnuplot plot_1b_traffic_limit.plt

