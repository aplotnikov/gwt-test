package com.codenvy.client.matcher;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Plotnikov
 */
public class SomeObjectTest {

    private SomeObject object;

    @Before
    public void setUp() throws Exception {
        object = new SomeObject();
    }

    @Test
    public void integerValueShouldBeValidated() throws Exception {
        object.setIntegerValue(1);

        assertThat(object.getIntegerValue(), is(1));
        assertThat(object.getIntegerValue(), is(not(2)));
        assertThat(object.getIntegerValue(), both(not(0)).and(not(2)));
        assertThat(object.getIntegerValue(), either(not(0)).or(not(2)));
        assertThat(object.getIntegerValue(), anyOf(is(0), is(1), is(2)));
        assertThat(object.getIntegerValue(), allOf(is(1), not(0), not(2)));
    }

    @Test
    public void stringValueShouldBeValidated() throws Exception {
        String value = "myValue";

        object.setStringValue(value);

        assertThat(object.getStringValue(), equalTo("myValue"));
        assertThat(object.getStringValue(), notNullValue());
        assertThat(object.getStringValue(), sameInstance(value));
        assertThat(object.getStringValue(), theInstance(value));
        assertThat(object.getStringValue(), both(startsWith("my")).and(containsString("V")).and(endsWith("e")));
    }

    @Test
    public void stringListShouldBeValidated() throws Exception {
        object.setStringList(Arrays.asList("i1", "i2", "i3", "iMyValue"));

        assertThat(object.getStringList(), notNullValue());
        assertThat(object.getStringList(), isA(List.class));
        assertThat(object.getStringList(), hasItem("i1"));
        assertThat(object.getStringList(), hasItem(startsWith("iMy")));
        assertThat(object.getStringList(), everyItem(startsWith("i")));
        assertThat(object.getStringList(), hasItems("i1", "i2"));
        //noinspection unchecked
        assertThat(object.getStringList(), hasItems(startsWith("i1"), startsWith("i2")));
    }

}