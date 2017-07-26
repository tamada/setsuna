package com.github.setsuna;

import static com.github.setsuna.Unit.MICRO_SECONDS;
import static com.github.setsuna.Unit.MILLI_SECONDS;
import static com.github.setsuna.Unit.NANO_SECONDS;
import static com.github.setsuna.Unit.SECONDS;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.setsuna.Unit;

public class UnitTest {
    @Test
    public void testBasic(){
        assertThat(NANO_SECONDS.convert(1, NANO_SECONDS),  is(closeTo(1, 0.1)));
        assertThat(MICRO_SECONDS.convert(1, NANO_SECONDS), is(closeTo(0.001, 0.0001)));
        assertThat(MILLI_SECONDS.convert(1, NANO_SECONDS), is(closeTo(0.000001, 0.0000001)));
        assertThat(SECONDS.convert(1, NANO_SECONDS),       is(closeTo(0.000000001, 0.0000000001)));

        assertThat(NANO_SECONDS.convert(1, MICRO_SECONDS),  is(closeTo(1000, 0.1)));
        assertThat(MICRO_SECONDS.convert(1, MICRO_SECONDS), is(closeTo(1, 0.1)));
        assertThat(MILLI_SECONDS.convert(1, MICRO_SECONDS), is(closeTo(0.001, 0.0001)));
        assertThat(SECONDS.convert(1, MICRO_SECONDS),       is(closeTo(0.000001, 0.0000001)));

        assertThat(NANO_SECONDS.convert(1, MILLI_SECONDS),  is(closeTo(1000_000, 0.1)));
        assertThat(MICRO_SECONDS.convert(1, MILLI_SECONDS), is(closeTo(1000, 0.1)));
        assertThat(MILLI_SECONDS.convert(1, MILLI_SECONDS), is(closeTo(1, 0.1)));
        assertThat(SECONDS.convert(1, MILLI_SECONDS),       is(closeTo(0.001, 0.0001)));

        assertThat(NANO_SECONDS.convert(1, SECONDS),  is(closeTo(1000_000_000, 0.1)));
        assertThat(MICRO_SECONDS.convert(1, SECONDS), is(closeTo(1000_000, 0.1)));
        assertThat(MILLI_SECONDS.convert(1, SECONDS), is(closeTo(1000, 0.1)));
        assertThat(SECONDS.convert(1, SECONDS),       is(closeTo(1, 0.1)));
    }

    @Test
    public void testValues(){
        Unit[] units = Unit.values();

        assertThat(units.length, is(4));
        assertThat(units, is(arrayContaining(NANO_SECONDS, MICRO_SECONDS, MILLI_SECONDS, SECONDS)));
    }

    @Test
    public void testValueOf(){
        assertThat(Unit.valueOf("NANO_SECONDS"),  is(NANO_SECONDS));
        assertThat(Unit.valueOf("MICRO_SECONDS"), is(MICRO_SECONDS));
        assertThat(Unit.valueOf("MILLI_SECONDS"), is(MILLI_SECONDS));
        assertThat(Unit.valueOf("SECONDS"),       is(SECONDS));
    }
}
