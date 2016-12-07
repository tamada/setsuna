package com.github.tamada.setsuna;

import static com.github.tamada.setsuna.Unit.NANO_SECONDS;

public class RunningTime {
    private long time;

    public RunningTime(long time){
        this.time = time;
    }

    @Override
    public String toString(){
        return format(NANO_SECONDS, "%g");
    }

    public double convertTo(Unit unit){
        return unit.convert(time, NANO_SECONDS);
    }

    public String format(Unit unit){
        return format(unit, "%f");
    }

    public String format(Unit unit, String formatter){
        double newTime = unit.convert(time, NANO_SECONDS);
        return String.format(formatter, newTime);
    }
}
