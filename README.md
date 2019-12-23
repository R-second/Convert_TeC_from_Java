# Convert_TeC_from_Java
- Javaのプログラムで音楽を作成する際に、次のようなメソッドを想定します。
`name.setTone(62,127,24);`
このとき、62は音階を、(127は強さを)、18は長さを表しています。

- この音階と長さの情報を元に次のような、周波数/2 と 長さの関係に直すプログラムです。ここで、長さはもっとも短いものを基準とし、例えば、12が基準なら36は12の長さの３行分として、表します。
`   DC  周波数/2,長さ`


## 使い方
1. `data/data.csv`ファイルに変換したい元のコードを書き込みます。
(すでに例として上がっています。)
2. `convert.py`の以下のような変数宣言に、変換したい元のコードの中で最も短い音の長さを設定します。(例だと6)
3. `convert.py`を実行すると、変換されたコードが`TeC_formatted.csv`に書き込まれます。

- 備考1：tone_frequencyには56から71までの音階しか載せていません。それ以外の音を設定したい場合は、(音階, 周波数, 周期)の順に追記してください。
- 備考2：`name.setTone(62,127,24)`以外の書き方には対応していません。要望があれば、作ります。
