package com.github.setsuna;

@FunctionalInterface
public interface ThrowableRunnable<E extends Throwable> {
    void run() throws E;
}
