package com.api.example.apiexample.controller.response;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author wbbaijq
 */
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 6555943285979897107L;
    private Boolean success = false;
    private String message;
    private T data;

    public ResultModel() {
    }

    public ResultModel(String message, T data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    public ResultModel(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("resultModel {");
        stringBuilder.append("\"success\"").append(":").append(this.success).append(", ");
        stringBuilder.append("\"message\"").append(":").append("\"").append(this.message).append("\"");
        if (this.data != null) {
            stringBuilder.append(", ").append("\"data\"").append(":").append("\"").append(this.data).append("\"");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}
