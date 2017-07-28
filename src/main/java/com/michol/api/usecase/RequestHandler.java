package com.michol.api.usecase;

public interface RequestHandler<RequestModel, ResponseModel> {

    ResponseModel handle(RequestModel requestModel);

}
