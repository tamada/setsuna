[![Build Status](https://travis-ci.org/tamada/setsuna.svg?branch=master)](https://travis-ci.org/tamada/setsuna)
[![Coverage Status](https://coveralls.io/repos/github/tamada/setsuna/badge.svg?branch=master)](https://coveralls.io/github/tamada/setsuna?branch=master)

# Setsuna

Setsuna is a tool for measuring the running time of a certain process for the Java programs.

## Background

### Problem of Previous Timer

Consider a typical measuring process in the conventional method.
The measuring process is generally like below.

```java
timer.start();
// essential process for measuring time.
....
timer.stop();
long time = timer.getTime();
```

The problem of the above program is that: ```start``` and ```stop```
method is not the essential process, however,
the both method calls were beside the essential process.
If a program has a much measuring processes, the essential process will be concealed,
then it will be the cause of the confusion.

### Our Solution

#### Basically

Therefore, Setsuna introduces lambda expressions, and functional interfaces in Java 8 platform,
then measures the running time, like below.

```java
RunningTime time = Timer.measure(() -> {
    // essential process for measuring time.
    ....
});
```

From the above program, the essential process will be in a block, 
we can eliminate ```start``` and ```stop``` method calls for measuring.
The measured time is returned by an object of ```RunningTime```.

#### Value returned process

Of course, Setsuna accepts the value returned process.

```java
TimeredObject<SUITABLE_CLASS> object = Timer.measure(() -> {
    // essential process for measuring time.
    ....
    return hoge;
});
```

From the above program, when some value is returned in the block, ```measure``` method
returns an object of ```TimeredObject```.  ```TimeredObject``` is just wrapper
of ```RunningTime``` and returned value.

#### Exceptions

If the essential process will throw some ```Exception```,
we can use ```ThrowableTimer``` instead of ```Timer```.

```java
RunningTime time = ThrowableTimer.measure(() -> {
    // essential process for measuring time.
    ....
});
TimeredObject<SUITABLE_CLASS> object = ThrowableTimer.measure(() -> {
    // essential process for measuring time.
    ....
    return hoge;
});
```

## Install

### GitHub

* Checkout the project from GitHub,
* Change directory to the project,
* Type ```mvn package```,
    * If ```mvn``` was not found, install [Maven](http://maven.apache.org/).
* ```setsuna-1.0-SNAPSHOT.jar``` will be packaged in ```target``` directory.
* Add the jar file into your project, and measure the running time like above programs.

# Setsuna

Setsuna はメソッドやJavaプログラムの実行時間を計測するツールです．

## Background

### Problem of Previous Timer

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

### Our Solution

#### Basically

そこで，本プロジェクトでは，ラムダ式と関数型インターフェースを利用して，次のように処理時間を計測します．

```java
RunningTime time = Timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
});
```

このように行うことで，本質的な処理をブロックに置き，計測のための```start```，```stop```
メソッドの呼び出しを不要にしています．
ラムダ式の代わりに，適切なインターフェースを実装して，本質的な処理を別のクラスに抽出できます．

#### Value returned process

もちろん，返り値のあるプログラムであっても問題ありません．

```java
TimeredObject<SUITABLE_CLASS> object = Timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
    return hoge;
});
```

上記のようにブロックの中で値を返すと```timer.measure```は ```TimeredObject```
を返します．```TimeredObject```は単なる計測時間と返り値のラッパです．

#### Exceptions

ある処理が例外を投げる場合も```Timer```の代わりに```ThrowableTimer```を利用することで計測可能です．

```java
RunningTime time = ThrowableTimer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
});
TimeredObject<?> object = ThrowableTimer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
    return hoge;
});
```

## Install

### GitHub

* GitHubからプロジェクトをチェックアウトしてください．
* チェックアウトしてできたディレクトリに移動して，```mvn package```を実行してください．
    * ```mvn```が見つからない場合は，[Maven](http://maven.apache.org/) を先にインストールしてください．
* ```mvn package```が実行できれば，```setsuna-1.0-SNAPSHOT.jar```が```target```に作成されています．
* 作成できたjarファイルをご自身のプロジェクトのクラスパスに追加して上記のようなプログラムで時間を計測してください．

