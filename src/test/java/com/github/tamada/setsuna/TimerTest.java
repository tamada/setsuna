package com.github.tamada.setsuna;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimerTest{ 
    @Test
    public void testRunnable(){
        Timer timer = new Timer();
        RunningTime time = timer.measure(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        });

        assertThat(time.convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }

    @Test
    public void testExecutable(){
        Timer timer = new Timer();
        TimeredObject<String> object = timer.measure(() -> {
            try{
                Thread.sleep(1);
            } catch(InterruptedException e){ }
            return "hoge";
        });

        assertThat(object.value(), is("hoge"));
        assertThat(object.time().convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }
}
