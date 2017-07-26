package com.github.setsuna;

public class Timer {
    public static RunningTime measure(Runnable runnable){
        long nano = System.nanoTime();
        runnable.run();
        return new RunningTime(System.nanoTime() - nano);
    }

    public static <R> TimeredObject<R> measure(Executable<R> executable){
        long nano = System.nanoTime();
        R value = executable.execute();
        return new TimeredObject<R>(value, new RunningTime(System.nanoTime() - nano));
    }
}
