package com.github.tamada.setsuna;

public class Timer {
    public RunningTime measure(Runnable runnable){
        long nano = System.nanoTime();
        runnable.run();
        return new RunningTime(System.nanoTime() - nano);
    }

    public <R> TimeredObject<R> measure(Executable<R> executable){
        long nano = System.nanoTime();
        R value = executable.execute();
        return new TimeredObject<R>(value, new RunningTime(System.nanoTime() - nano));
    }
}
