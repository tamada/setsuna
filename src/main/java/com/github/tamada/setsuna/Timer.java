package com.github.tamada.setsuna;

public class Timer {
    public RunningTime measure(Runnable runnable){
        long nano = System.nanoTime();
        runnable.run();
        return new RunningTime(System.nanoTime() - nano);
    }

    public <V> TimeredObject<V> measure(Executable<V> executable){
        long nano = System.nanoTime();
        V value = executable.execute();
        return new TimeredObject<V>(value, new RunningTime(System.nanoTime() - nano));
    }
}
