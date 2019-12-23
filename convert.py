#!/usr/bin/python
# coding=utf-8

import csv
import pprint
import re

###  note_lengthにもっとも短い音の長さを登録する。
note_length = 6    # 基準にする音の長さ


rate = 48 / note_length
tone_fre = []       # 音階と周波数の対応リスト

# 音階を引数に周波数を返す関数
def search_in_frequency(x):
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


print(l)

data = []


i=0
for row in l:
    if row == []:
        continue
    
    index = row[0].find('s.setTone(')

    if index == -1:
        continue
    
    tone = re.sub("\\D", "", row[0])

    length = int(re.sub("\\D", "", row[2]))

    fre_time = search_in_frequency(tone)
    frequency_TeC = round(fre_time[0] / 2)
    time_TeC = round(fre_time[1] * 100 / rate)
    msg = "\tDC\t" + str(frequency_TeC) + "," + str(time_TeC)


    if length == note_length:
        data.append(msg)
        i = i + 1
    else:
        j = 0
        while j < length / note_length:
            data.append(msg)
            i = i + 1
            j = j + 1



with open('data/TeC_formatted.csv', 'w') as f:
    writer = csv.writer(f, delimiter='\n')
    writer.writerow(data)

        
    


