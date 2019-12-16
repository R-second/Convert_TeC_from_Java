#!/usr/bin/python
# coding=utf-8

import csv
import pprint

tone_fre = []

def search_in_frequency(x):
    #print(int(x))
    for row in tone_fre:
        if row[0] == x:
            return row[1]
    return -1
        

with open("data/tone_frequency.csv") as f:
    reader = csv.reader(f)

    for row in reader:
        tone_fre.append(row)


with open('data/data.csv') as f:
    reader = csv.reader(f)
    l = [row for row in reader]



data = [[] for row in l ]


i=0
for row in l:

    index = row[0].find('s.setTone(')
    tone = row[0][index+10:index+12]
    data[i].append(tone)

    index = row[2].find(');')
    data[i].append(row[2][index-2:index])

    data[i].append(search_in_frequency(tone))


    i = i + 1

print(data)


