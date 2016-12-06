package com.github.tamada.setsuna;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

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

        assertThat(Integer.valueOf(time.format(TimeUnit.SECONDS)), is(0));
    }

    @Test
    public void testExecutable(){
        Timer timer = new Timer();
        TimeredObject<String> object = timer.measure(() -> {
            return "hoge";
        });

        assertThat(object.value(), is("hoge"));
    }
}
