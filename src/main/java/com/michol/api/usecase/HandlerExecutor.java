package com.michol.api.usecase;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class HandlerExecutor {

    private ApplicationContext applicationContext;

    private static Map<Class<? extends RequestHandler>, RequestHandler> requestHandlers = new HashMap();

    public HandlerExecutor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <RequestModel, ResponseModel> ResponseModel execute(Class<? extends RequestHandler> type, RequestModel requestModel) {
        if (!requestHandlers.containsKey(type)) {
            requestHandlers.put(type, applicationContext.getBean(type));
        }
        RequestHandler<RequestModel, ResponseModel> requestHandler = requestHandlers.get(type);
        return requestHandler.handle(requestModel);
    }

}
