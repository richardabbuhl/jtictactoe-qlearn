#!/bin/bash
echo "Iterations is " $3
qmain.exe -o $1 -e $2 -d -n $3 ttt.xml

echo -n "1. A="$1, "E="$2, "" >> results.txt
qmain.exe -t -d -n 5000 ttt.xml >> results.txt
echo -n "2. A="$1, "E="$2, "" >> results.txt
qmain.exe -g -t -d -n 5000 ttt.xml >> results.txt

echo -n "1. A="$1, "E="$2, "" >> results.txt
qmain.exe -t -n 10 ttt.xml >> results.txt
echo -n "2. A="$1, "E="$2, "" >> results.txt
qmain.exe -g -t -n 10 ttt.xml >> results.txt

##############################
# qmain.exe -d -n $1 ttt.xml
# qmain.exe -t -d -n 2500 ttt.xml >> results.txt
# qmain.exe -t -n 1000 ttt.xml >> results.txt
