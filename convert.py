#!/usr/bin/python
# coding=utf-8

import csv
import pprint

note_quater = 12    # 基準にする音の長さ
tone_fre = []       # 音階と周波数の対応リスト

# 音階を引数に周波数を返す関数
def search_in_frequency(x):
    #print(int(x))
    for row in tone_fre:
        if row[0] == x:
            return [float(row[1]), float(row[2])]
    return -1

with open("data/tone_frequency.csv") as f:
    reader = csv.reader(f)
    for row in reader:
        tone_fre.append(row)


with open('data/data.csv') as f:
    reader = csv.reader(f)
    l = [row for row in reader]



data = []


i=0
for row in l:
    
    index = row[0].find('s.setTone(')
    tone = row[0][index+10:index+12]

    index = row[2].find(');')
    length = int(row[2][index-2:index])

    fre_time = search_in_frequency(tone)


    if length == note_quater:
        data.append(fre_time)
        #data[i].append(tone)
        #data[i].append(note_quater)
        #data[i].append(frequency)
        i = i + 1
    else:
        j = 0
        while j < length / note_quater:
            #data.append(fre_time)
            data.append("CD     " + str(round(fre_time[0] / 2)) + "," + str(round(fre_time[1] * 100 / 4)))
            #data[i].append(tone)
            #data[i].append(note_quater)
            #data[i].append(frequency)
            i = i + 1
            j = j + 1
    



print(data)


