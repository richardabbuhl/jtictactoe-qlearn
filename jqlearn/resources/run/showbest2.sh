#!/bin/bash
grep "^2\." results.txt | grep Random | awk '{print $11,$2,$3}' | sort | tail
