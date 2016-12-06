# Setsuna

Setsuna はメソッドやJavaプログラムの実行時間を計測するツールです．

## Problem of Previous Timer

実行時間計測を行うクラスを作成してみました．
従来の実行時間計測は，プログラムを汚すものが多いと感じていました．
例えば，次のように．

```java
timer.start();
// ここで何らかの本質的な処理
....
timer.stop();
long time = timer.getTime();
```

上記のプログラムだと，時間計測のための```start```，```stop```は本質的な処理ではありません．
にもかかわらず，本質的な処理に紛れてしまっています．
時間計測処理が多くなればなるほど，本質的な処理と計測のための処理が混ざり，混乱の元になります．

## Our Solution

そこで，本プロジェクトでは，次のように処理時間を計測します．

```java
RunningTime time = timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
});
```

このように行うことで，本質的な処理をブロックに置き，計測のための```start```，
 ```stop```呼び出しを不要にしています．
関数インタフェースを利用しない場合は，本質的な処理を別のクラスに抽出できます．

もちろん，返り値のあるプログラムであっても問題ありません．

```java
TimeredObject<?> object = timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
    return hoge;
});
```

上記のようにブロックの中で値を返すと```timer.measure```は ```TimeredObject```を返します．
 ```TimeredObject```は単なる計測時間と返り値のラッパです．

