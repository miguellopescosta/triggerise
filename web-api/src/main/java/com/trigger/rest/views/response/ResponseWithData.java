package com.trigger.rest.views.response;

import com.trigger.rest.views.errors.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseWithData<D> implements Serializable {

    private D data;
    private Status status;

}
