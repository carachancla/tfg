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

#set log xy
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

set output "number_of_cables_longer_than_100m.pdf"
#set xtics 0, 1, 5
set format x "%.0fK"
set format y "%.0f"
set xlabel "Network size"
set ylabel "Cables longer than X %"

#set xrange [0:2500]
#set yrange [0:1]
#set logscale y 2
set key font ",16"
set key bot right Left reverse
#set key below Left reverse
#set key tmargin

plot    "../../../temp/results/physicalTopo/analysisResult" using ($1/1000):($3 * 100/($1 * $2)) title "cables longer than 100m" smooth unique w lp ls 1 , \
        "../../../temp/results/physicalTopo/analysisResult_50" using ($1/1000):($3 * 100/($1 * $2)) title "cables longer than 50m" smooth unique w lp ls 2, \
        "../../../temp/results/physicalTopo/analysisResult_20" using ($1/1000):($3 * 100/($1 * $2)) title "cables longer than 20m" smooth unique w lp ls 3
