import numpy as np
import csv
import sys
import os


##################################
# Setup
#

print("NetBench python analysis tool v0.01")

# Usage print
def print_usage():
    print("Usage: python analyze.py /path/to/run/folder")

# Check length of arguments
if len(sys.argv) != 2:
    print("Number of arguments must be exactly two: analyze.py and /path/to/run/folder.")
    print_usage()
    exit()

# Check run folder path given as first argument
run_folder_path = sys.argv[1]
if not os.path.isdir(run_folder_path):
    print("The run folder path does not exist: " + run_folder_path)
    print_usage()
    exit()

# Create analysis folder
analysis_folder_path = run_folder_path + '/analysis'
if not os.path.exists(analysis_folder_path):
    os.makedirs(analysis_folder_path)


##################################
# Analyze flow completion
#
def analyze_channel_use():
    with open(run_folder_path + '/channel_usage.cvs.log') as file:
        reader = csv.reader(file)

        # To enable preliminary read to determine size:
        # data = list(reader)
        # row_count = len(data)

        # Column lists
        channel_ids = []
        used_perecntage = []
        number_collisions = []
        number_packets_sent = []
        percentage_collisions = []

        print("Reading in flow completion log file...")

        # Read in column lists
        for row in reader:
            channel_ids.append(float(row[0]))
            used_perecntage.append(float(row[1]))
            number_collisions.append(float(row[2]))
            number_packets_sent.append(float(row[3]))
            percentage_collisions.append(float(row[2])/float(row[3]))
            if len(row) != 4:
                print("Invalid row: ", row)
                exit()

        print("Calculating statistics...")

        statistics = {
            'num_channels': len(channel_ids),
            'channel_usage': np.mean(used_perecntage),
            'collsions': np.mean(number_collisions),
            'packets_sent': np.mean(number_packets_sent),
            'percentage_collision': np.mean(percentage_collisions)
        }

        # Print raw results
        print('Writing to result file flow_completion.statistics...')
        with open(analysis_folder_path + '/channel_use.statistics', 'w+') as outfile:
            for key, value in sorted(statistics.items()):
                outfile.write(str(key) + "=" + str(value) + "\n")


# Call analysis functions
analyze_channel_use()
