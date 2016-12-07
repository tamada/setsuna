package com.github.tamada.setsuna;

public class ThrowableTimer {
    public <R, E extends Throwable> TimeredObject<R> measure(ThrowableExecutable<R, E> executable) throws E{
        long nano = System.nanoTime();
        return buildObject(nano, executable);
    }

    public <E extends Throwable> RunningTime measure(ThrowableRunnable<E> executable) throws E{
        long nano = System.nanoTime();
        executable.run();
        return new RunningTime(System.nanoTime() - nano);
    }

    private <R, E extends Throwable> TimeredObject<R> buildObject(long nano, ThrowableExecutable<R, E> executable) throws E{
        R value = executable.execute();
        RunningTime time = new RunningTime(System.nanoTime() - nano);
        return new TimeredObject<R>(value, time);
    }
}
