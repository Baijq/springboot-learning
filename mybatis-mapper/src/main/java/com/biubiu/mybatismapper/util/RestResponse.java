package com.biubiu.mybatismapper.util;

import java.util.HashMap;

/**
 * RestResponse
 *
 * @author biubiu
 */
public class RestResponse extends HashMap<String, Object> {

    public static RestResponse success() {
        return success("success");
    }

    public static RestResponse success(String message) {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        response.setMessage(message);
        response.setCode(0);
        return response;
    }

    public static RestResponse failure(String message) {
        RestResponse response = new RestResponse();
        response.setSuccess(false);
        response.setMessage(message);
        response.setCode(-1);
        return response;
    }

    public RestResponse setSuccess(Boolean success) {
        if (success != null) {
            put("success", success);
        }
        return this;
    }

    public RestResponse setMessage(String message) {
        if (message != null) {
            put("message", message);
        }
        return this;
    }

    public RestResponse setData(Object data) {
        if (data != null) {
            put("data", data);
        }
        return this;
    }

    public RestResponse setCode(Integer code) {
        if (code != null) {
            put("code", code);
        }
        return this;
    }

    public RestResponse setPage(Integer page) {
        if (page != null) {
            put("page", page);
        }
        return this;
    }

    public RestResponse setCurrentPage(Integer currentPage) {
        if (currentPage != null) {
            put("page", currentPage);
        }
        return this;
    }

    public RestResponse setLimit(Integer limit) {
        if (limit != null) {
            put("limit", limit);
        }
        return this;
    }

    public RestResponse setTotal(Integer total) {
        if (total != null) {
            put("total", total);
        }
        return this;
    }

    public RestResponse setAny(String key, Object value) {
        if (key != null && value != null) {
            put(key, value);
        }
        return this;
    }
}
