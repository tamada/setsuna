package com.github.setsuna;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.setsuna.RunningTime;
import com.github.setsuna.Timer;
import com.github.setsuna.TimeredObject;
import com.github.setsuna.Unit;

public class TimerTest{ 
    @Test
    public void testRunnable(){
        RunningTime time = Timer.measure(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        });

        assertThat(time.convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }

    @Test
    public void testExecutable(){
        TimeredObject<String> object = Timer.measure(() -> {
            try{
                Thread.sleep(1);
            } catch(InterruptedException e){ }
            return "hoge";
        });

        assertThat(object.value(), is("hoge"));
        assertThat(object.time().convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }
}
