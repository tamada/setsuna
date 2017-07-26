package com.github.setsuna;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.setsuna.RunningTime;
import com.github.setsuna.ThrowableTimer;
import com.github.setsuna.TimeredObject;
import com.github.setsuna.Unit;

public class ThrowableTimerTest{ 
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testRunnable() throws InterruptedException{
        RunningTime time = ThrowableTimer.measure(() -> {
            Thread.sleep(1);
        });

        assertThat(time.convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }

    @Test
    public void testExecutable() throws InterruptedException{
        TimeredObject<String> object = ThrowableTimer.measure(() -> {
            Thread.sleep(1);
            return "hoge";
        });

        assertThat(object.value(), is("hoge"));
        assertThat(object.time().convertTo(Unit.SECONDS), is(lessThan(0.01)));
    }

    @Test
    public void testException(){
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("enable to throw the exception!!");

        ThrowableTimer.measure(() -> {
            throw new RuntimeException("enable to throw the exception!!");
        });
    }

    @Test
    public void testException2(){
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("enable to throw the exception!!");

        @SuppressWarnings("unused")
        TimeredObject<String> object = ThrowableTimer.measure(() -> {
            int x = 10;
            if((x * x) % 2 == 1){
                return "hoge";
            }
            throw new RuntimeException("enable to throw the exception!!");
        });
        
    }
}
