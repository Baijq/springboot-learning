package com.api.example.apiexample.controller.response;

/**
 * 带分页的返回
 *
 * @author wbbaijq
 */
public class PageResultModel<T> extends ResultModel<T> {

    private static final long serialVersionUID = 5001896963554548570L;
    private Long total;
    private Integer size;
    private Long pages;
    private Long current;

    public PageResultModel() {
    }

    public PageResultModel(String message, T data, long total, int size, long pages, long current) {
        super(message, data);
        this.total = total;
        this.size = size;
        this.pages = pages;
        this.current = current;
    }

    public PageResultModel(String message, T data) {
        super(message, data);
    }

    public PageResultModel(String message) {
        super(message);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PageResultModel {");
        stringBuilder.append("\"success\"").append(":").append(super.getSuccess()).append(", ");
        stringBuilder.append("\"message\"").append(":").append("\"").append(super.getSuccess()).append("\"");
        if (super.getData() != null) {
            stringBuilder.append(", ").append("\"data\"").append(":").append("\"").append(super.getData()).append("\"");
        }

        if (this.total != null) {
            stringBuilder.append(", ").append("\"total\"").append(":").append(this.total);
        }

        if (this.size != null) {
            stringBuilder.append(", ").append("\"size\"").append(":").append(this.size);
        }

        if (this.pages != null) {
            stringBuilder.append(", ").append("\"pages\"").append(":").append(this.pages);
        }

        if (this.current != null) {
            stringBuilder.append(", ").append("\"current\"").append(":").append(this.current);
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public Long getTotal() {
        return this.total;
    }

    public Integer getSize() {
        return this.size;
    }

    public Long getPages() {
        return this.pages;
    }

    public Long getCurrent() {
        return this.current;
    }

}
