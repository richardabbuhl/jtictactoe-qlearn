#!/bin/bash
echo "Iterations is " $3
jqlearn.exe -o $1 -e $2 -d -n $3

echo -n "1. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -t -d -n 5000 >> results.txt
echo -n "2. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -g -t -d -n 5000 >> results.txt

echo -n "1. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -t -n 10 >> results.txt
echo -n "2. A="$1, "E="$2, "" >> results.txt
jqlearn.exe -g -t -n 10 >> results.txt

##############################
# jqlearn.exe -d -n $1
# jqlearn.exe -t -d -n 2500 >> results.txt
# jqlearn.exe -t -n 1000 >> results.txt
