#!/usr/bin/python
# coding=utf-8

import csv
import pprint

with open('data/data.csv') as f:
    reader = csv.reader(f)
    l = [row for row in reader]
#print(l)

data = [[] for row in l ]

i=0
for row in l:
    # print(row)
    index = row[0].find('s.setTone(')
    data[i].append(row[0][index+10:index+12])

    index = row[2].find(');')
    data[i].append(row[2][index-2:index-1])
    #print(row[0][index+10:index+12])
    #print(row);
    i = i + 1

print(data)
