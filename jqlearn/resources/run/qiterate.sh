#!/bin/bash
echo "Iterations is " $3
jqlearn.exe -o $1 -e $2 -d -n $3 ttt.xml

echo -n "1. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -t -d -n 5000 ttt.xml >> results.txt
echo -n "2. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -g -t -d -n 5000 ttt.xml >> results.txt

echo -n "1. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -t -n 10 ttt.xml >> results.txt
echo -n "2. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -g -t -n 10 ttt.xml >> results.txt

##############################
# jqlearn.exe -d -n $1 ttt.xml
# jqlearn.exe -t -d -n 2500 ttt.xml >> results.txt
# jqlearn.exe -t -n 1000 ttt.xml >> results.txt
