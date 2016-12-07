# Setsuna

Setsuna is a tool for measuring the running time of a certain process for the Java programs.

## Problem of Previous Timer

Consider the measuring process in the conventional method.
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
A program has a much measuring process, the essential process will be concealed,
then causes the confusion.

## Our Solution

### Basically

Therefore, Setsuna introduces lambda expressions, and functional interfaces in Java 8 platform,
and measure the running time, like below.

```java
Timer timer = new Timer();
RunningTime time = timer.measure(() -> {
    // essential process for measuring time.
    ....
});
```

From the above program, the essential process will be in a block, 
we can eliminate ```start``` and ```stop``` method calls for measuring.
The measured time is returned by an object of ```RunningTime```.

### Value returned process

Of course, Setsuna accepts the value returned process.

```java
Timer timer = new Timer();
TimeredObject<?> object = timer.measure(() -> {
    // essential process for measuring time.
    ....
    return hoge;
});
```

From the above program, when some value is returned in the block, ```measure``` method
returns an object of ```TimeredObject```.  ```TimeredObject``` is just wrapper
of ```RunningTime``` and returned value.

### Exceptions

If the essential process will throw some ```Exception```,
we can use ```ThrowableTimer``` instead of ```Timer```.

```java
ThrowableTimer timer = new ThrowableTimer();
RunningTime time = timer.measure(() -> {
    // essential process for measuring time.
    ....
});
TimeredObject<?> object = timer.measure(() -> {
    // essential process for measuring time.
    ....
    return hoge;
});
```

# Setsuna

Setsuna はメソッドやJavaプログラムの実行時間を計測するツールです．

## Problem of Previous Timer

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

### Basically

そこで，本プロジェクトでは，ラムダ式と関数型インターフェースを利用して，次のように処理時間を計測します．

```java
Timer timer = new Timer();
RunningTime time = timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
});
```

このように行うことで，本質的な処理をブロックに置き，計測のための```start```，```stop```
メソッドの呼び出しを不要にしています．
ラムダ式の代わりに，適切なインターフェースを実装して，本質的な処理を別のクラスに抽出できます．

### Value returned process

もちろん，返り値のあるプログラムであっても問題ありません．

```java
Timer timer = new Timer();
TimeredObject<?> object = timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
    return hoge;
});
```

上記のようにブロックの中で値を返すと```timer.measure```は ```TimeredObject```
を返します．```TimeredObject```は単なる計測時間と返り値のラッパです．

### Exceptions

ある処理が例外を投げる場合も```Timer```の代わりに```ThrowableTimer```を利用することで計測可能です．

```java
ThrowableTimer timer = new ThrowableTimer();
RunningTime time = timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
});
TimeredObject<?> object = timer.measure(() -> {
    // ここで何らかの本質的な処理
    ....
    return hoge;
});
```

