package com.github.tamada.setsuna;

import static com.github.tamada.setsuna.Unit.MICRO_SECONDS;
import static com.github.tamada.setsuna.Unit.MILLI_SECONDS;
import static com.github.tamada.setsuna.Unit.NANO_SECONDS;
import static com.github.tamada.setsuna.Unit.SECONDS;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RunningTimeTest {
    private RunningTime time = new RunningTime(1000);

    @Test
    public void testFormat() {
        assertThat(time.format(NANO_SECONDS),  is("1000.000000"));
        assertThat(time.format(MICRO_SECONDS), is("1.000000"));
        assertThat(time.format(MILLI_SECONDS), is("0.001000"));
        assertThat(time.format(SECONDS),       is("0.000001"));

        assertThat(time.toString(), is("1000.00"));
    }

    @Test
    public void testConvert() {
        assertThat(time.convertTo(NANO_SECONDS),  is(closeTo(1000.0,   0.1)));
        assertThat(time.convertTo(MICRO_SECONDS), is(closeTo(1.000,    0.0001)));
        assertThat(time.convertTo(MILLI_SECONDS), is(closeTo(0.001,    0.0000001)));
        assertThat(time.convertTo(SECONDS),       is(closeTo(0.000001, 0.0000000001)));
    }
}
