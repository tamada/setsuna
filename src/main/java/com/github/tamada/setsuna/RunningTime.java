package com.github.tamada.setsuna;

import java.util.concurrent.TimeUnit;

public class RunningTime {
    private long time;

    public RunningTime(long time){
        this.time = time;
    }

    @Override
    public String toString(){
        return format(TimeUnit.NANOSECONDS);
    }

    public String format(TimeUnit unit){
        return Long.toString(unit.convert(time, TimeUnit.NANOSECONDS));
    }
}
