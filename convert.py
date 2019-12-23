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

# 音階と周波数/周期の対応表を読み込み
with open("data/tone_frequency.csv") as f:
    reader = csv.reader(f)
    for row in reader:
        tone_fre.append(row)

# 元データを読み込み
with open('data/data.csv') as f:
    reader = csv.reader(f)
    l = [row for row in reader]


data = []       # 書き込み用data配列

i=0
for row in l:

    # 空行ならスキップ
    if row == []:       
        continue
    
    # .setToneの書式でなければスキップ
    index = row[0].find('.setTone')
    if index == -1:
        continue
    
    # 数値のみ取り出す
    tone = re.sub("\\D", "", row[0])
    length = int(re.sub("\\D", "", row[2]))

    # 周波数と周期を取得し、TeC用の書式に変換
    fre_time = search_in_frequency(tone)
    frequency_TeC = round(fre_time[0] / 2)
    time_TeC = round(fre_time[1] * 100 / rate)

    # 書き込み用msg
    msg = "\tDC\t" + str(frequency_TeC) + "," + str(time_TeC)

    # 基準となる長さと同じであれば、処理は一回
    if length == note_length:
        data.append(msg)
        i = i + 1
    # そうでなければ、長さ / 基準だけ繰り返す
    else:
        j = 0
        while j < length / note_length:
            data.append(msg)
            i = i + 1
            j = j + 1


# 書き込み
with open('data/TeC_formatted.csv', 'w') as f:
    writer = csv.writer(f, delimiter='\n')
    writer.writerow(data)

        
    


