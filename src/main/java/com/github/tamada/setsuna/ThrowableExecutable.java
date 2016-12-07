package com.github.tamada.setsuna;

@FunctionalInterface
public interface ThrowableExecutable<V, E extends Throwable>{
    V execute() throws E;
}
