package com.parking.tdd.ctrl;


import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;
import org.junit.Test;
import sun.applet.Main;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class RouterTest {
    @Test
    public void should_call_process_of_RootController_when_call_initPage(){
        //give
        Map<String,BasicController> controllerMap=new HashMap<>();
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        RootController rootController=mock(RootController.class);
        controllerMap.put("root",rootController);
        Router router=new Router(controllerMap,request);

        //when
        router.initPage();

        //then
        verify(rootController).process();
    }

    @Test
    public void should_call1_process_of_MainController_when_call_root_1_or_root_2(){
        //give
        Map<String,BasicController> controllerMap=new HashMap<>();
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        BasicController controller=mock(MainController.class);
        controllerMap.put("root/1",controller);
        controllerMap.put("root/2",controller);
        Router router=new Router(controllerMap,request);

        //when
        when(request.getCommand()).thenReturn("1","2");
        router.setCurrentPath("root");
        router.processRequest();

        //when
        router.setCurrentPath("root");
        router.processRequest();

        //then
        verify(controller,times(2)).process();
    }
}
