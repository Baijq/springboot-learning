package com.api.example.apiexample.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * TODO
 *
 * @author wbbaijq
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResultModel<S, T> extends ResultModel<T> {

    private static final long serialVersionUID = 1777994529493739156L;
    private S code;

    public ApiResultModel() {
    }

    public ApiResultModel(S code, String message) {
        super(message);
        this.code = code;
    }

    public ApiResultModel(S code, String message, T data) {
        super(message, data);
        this.code = code;
    }

    public S getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ApiResultModel {");
        if (this.code != null) {
            stringBuilder.append("\"code\"").append(":").append(this.code).append(", ");
        }

        stringBuilder.append("\"success\"").append(":").append(super.getSuccess()).append(", ");
        stringBuilder.append("\"message\"").append(":").append("\"").append(super.getSuccess()).append("\"");
        if (super.getData() != null) {
            stringBuilder.append(", ").append("\"data\"").append(":").append("\"").append(super.getData()).append("\"");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
