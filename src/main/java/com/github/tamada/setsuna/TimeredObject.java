package com.github.tamada.setsuna;

public class TimeredObject<S> {
    private S value;
    private RunningTime time;

    public TimeredObject(S value, RunningTime time){
        this.value = value;
        this.time = time;
    }

    public RunningTime time(){
        return time;
    }

    public S value(){
        return value;
    }
}
