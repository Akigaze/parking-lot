package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.mock;

import com.parking.tdd.core.ParkingCard;
import org.junit.Test;

import java.util.UUID;

public class TransformerTest {
    @Test
    public void should_transform_into_a_car_when_give_a_car_id_String(){
        //give
        //ViewListener listener=mock(ViewListener.class);
        Transformer transformer=new Transformer();
        //when(listener.recept()).thenReturn("12345");

        //when
        Car car=transformer.convertToCar("12345");
        //then
        assertThat(car.getId(),is("12345"));
    }

    @Test
    public void should_transform_into_a_parking_card_when_give_a_card_id_String(){
        //give
        //ViewListener listener=mock(ViewListener.class);
        Transformer transformer=new Transformer();
        String id=UUID.randomUUID().toString();
        //when(listener.recept()).thenReturn(id);

        //when
        ParkingCard card =transformer.convertToParkingCard(id);
        //then
        assertThat(card.getId(),is(id));
    }

    @Test
    public void should_get_the_car_id_when_give_a_car(){
        //give
        Transformer transformer=new Transformer();
        Car car=new Car("1234");

        //when
        String id=transformer.convertToCarId(car);
        //then
        assertThat(id,is("1234"));
    }

    @Test
    public void should_get_the_card_id_when_give_a_card(){
        //give
        Transformer transformer=new Transformer();
        ParkingCard card=new ParkingCard("1234");

        //when
        String id=transformer.convertToParkingCardId(card);
        //then
        assertThat(id,is("1234"));
    }


}
