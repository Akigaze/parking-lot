package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.parking.tdd.view.ViewListener;
import org.junit.Test;

public class TransformerTest {
    @Test
    public void should_transform_into_a_car_when_give_a_car_id_String(){
        //give
        ViewListener listener=mock(ViewListener.class);
        Transformer transformer=new Transformer(listener);
        when(listener.recept()).thenReturn("12345");

        //when
        Car car=transformer.convertToCar();
        //then
        assertThat(car.getId(),is("12345"));
    }
}
