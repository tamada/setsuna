package com.github.tamada.setsuna;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ThrowableTimerTest{ 
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testRunnable() throws InterruptedException{
        ThrowableTimer timer = new ThrowableTimer();
        RunningTime time = timer.measure(() -> {
            Thread.sleep(1);
        });

        assertThat(time.convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }

    @Test
    public void testExecutable() throws InterruptedException{
        ThrowableTimer timer = new ThrowableTimer();
        TimeredObject<String> object = timer.measure(() -> {
            Thread.sleep(1);
            return "hoge";
        });

        assertThat(object.value(), is("hoge"));
        assertThat(object.time().convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }

    @Test
    public void testException(){
        ThrowableTimer timer = new ThrowableTimer();

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("enable to throw the exception!!");

        timer.measure(() -> {
            throw new RuntimeException("enable to throw the exception!!");
        });
    }

    @Test
    public void testException2(){
        ThrowableTimer timer = new ThrowableTimer();

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("enable to throw the exception!!");

        @SuppressWarnings("unused")
        TimeredObject<String> object = timer.measure(() -> {
            int x = 10;
            if((x * x) % 2 == 1){
                return "hoge";
            }
            throw new RuntimeException("enable to throw the exception!!");
        });
        
    }
}
