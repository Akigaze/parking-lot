package com.parking.tdd.ctrl;


import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.*;

public class RouterTest {
    @Test
    public void should_call_process_of_RootController_when_call_initPage(){
        //give
        HashMap<String,BasicController> controllerMap=mock(HashMap.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        RootController rootController=mock(RootController.class);
        Router router=new Router(controllerMap,request);

        //when
        when(controllerMap.get(router.getInitPath())).thenReturn(rootController);
        router.initPage();

        //then
        verify(rootController).process();
    }
}
