###
###  Released under the MIT License (MIT) --- see ../LICENSE
###  Copyright (c) 2014 Ankit Singla, Sangeetha Abdu Jyothi, Chi-Yao Hong, Lucian Popa, P. Brighten Godfrey, Alexandra Kolla, Simon Kassing
###

# Note you need gnuplot 4.4 for the pdfcairo terminal.
set terminal pdfcairo font "Helvetica, 18" linewidth 1.5 rounded dashed

# Line style for axes
set style line 80 lt rgb "#808080"

# Line style for grid
set style line 81 lt 0  # dashed
set style line 81 lt rgb "#cccccc"  # grey

set grid back linestyle 81
set border 3 back linestyle 80 # Remove border on top and right.  These
             # borders are useless and make it harder
	                  # to see plotted lines near the border.
			      # Also, put it in grey; no need for so much emphasis on a border.
			      set xtics nomirror
			      set ytics nomirror

#set log x
#set mxtics 10    # Makes logscale look good.

# Line styles: try to pick pleasing colors, rather
# than strictly primary colors or hard-to-see colors
# like gnuplot's default yellow.  Make the lines thick
# so they're easy to see in small plots in papers.
set style line 1 lt rgb "#2177b0" lw 2 pt 6 ps 1.4
set style line 2 lt rgb "#fc7f2b" lw 3 pt 1 ps 1
set style line 3 lt rgb "#2f9e37" lw 2 pt 2 ps 1.4
set style line 4 lt rgb "#d42a2d" lw 2 pt 8 ps 1.3
set style line 5 lt rgb "#9167b8" lw 1.6 pt 3 ps 1
set style line 6 lt rgb "#8a554c" lw 1.6 pt 7
set style line 7 lt rgb "#e079be" lw 1.6 pt 6 ps 1
set style line 8 lt rgb "#7d7d7d" lw 1.6 pt 7 ps 1
set style line 9 lt rgb "#666666" lw 1.2 pt 0

#-----

set output "output_1_failcase_ecmp_mean_channel_use.pdf"
#set xtics 0, 1, 5
set format x "%.0f"
set xlabel "Load (MB/s)"
set ylabel "Percentage channel used (%)"
set logscale x 2
#set xrange [0:2500]
#set yrange [0:1]
set key font ",16"
set key top left Left reverse
#set key below Left reverse
#set key tmargin
set key left top

#flow_size (MB/s)
flow_size=0.1

plot    "data_W_1_trafficLimit_hybrid_mean_channel_use.txt" using ($2*flow_size):(stringcolumn(1) eq "jellyfish_n128_d30_ethernet" ? $3: 1/0) title "no wireless" smooth unique w lp ls 2, \
        "data_W_1_trafficLimit_hybrid_mean_channel_use.txt" using ($2*flow_size):(stringcolumn(1) eq "jellyfish_n128_d30_hybrid_cableLength_7" ? $3: 1/0) title "984 wireless ports" smooth unique w lp ls 3, \
        "data_W_1_trafficLimit_hybrid_mean_channel_use.txt" using ($2*flow_size):(stringcolumn(1) eq "jellyfish_n128_d30_hybrid_cableLength_10" ? $3: 1/0) title "420 wireless ports" smooth unique w lp ls 4, \
        "data_W_1_trafficLimit_hybrid_mean_channel_use.txt" using ($2*flow_size):(stringcolumn(1) eq "jellyfish_n128_d30_hybrid_cableLength_13" ? $3: 1/0) title "103 wireless ports" smooth unique w lp ls 5, \
        "data_W_1_trafficLimit_hybrid_mean_channel_use.txt" using ($2*flow_size):(stringcolumn(1) eq "jellyfish_n128_d30_hybrid_cableLength_15" ? $3: 1/0) title "30 wireless ports" smooth unique w lp ls 6

