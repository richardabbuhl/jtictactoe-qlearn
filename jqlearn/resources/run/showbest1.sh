#!/bin/bash
grep "^1\." results.txt | grep Random | awk '{print $9,$2,$3}' | sort | tail
