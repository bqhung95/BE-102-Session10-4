package com.example.listdoctor;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String status;      // "success" | "error"
    private int code;           // HTTP status code
    private String message;     // Mô tả ngắn
    private T data;             // Dữ liệu trả về
    private Meta meta;          // Thông tin phân trang (nếu có)
    private LocalDateTime timestamp;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(String status, int code, String message, T data, Meta meta) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
        this.meta = meta;
        this.timestamp = LocalDateTime.now();
    }

    // ===== Static factory methods =====

    /** Thành công có data, không phân trang */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("success", 200, message, data, null);
    }

    /** Thành công có data + phân trang */
    public static <T> ApiResponse<T> success(T data, String message, Meta meta) {
        return new ApiResponse<>("success", 200, message, data, meta);
    }

    /** Tạo mới thành công (201) */
    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>("success", 201, message, data, null);
    }

    /** Lỗi */
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>("error", code, message, null, null);
    }

    // ===== Getters & Setters =====
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }


    // ===== Inner class Meta =====
    public static class Meta {
        private long totalRecords;
        private int currentPage;
        private int pageSize;
        private int totalPages;

        public Meta() {}

        public Meta(long totalRecords, int currentPage, int pageSize) {
            this.totalRecords = totalRecords;
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        }

        public long getTotalRecords() { return totalRecords; }
        public void setTotalRecords(long totalRecords) { this.totalRecords = totalRecords; }

        public int getCurrentPage() { return currentPage; }
        public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

        public int getPageSize() { return pageSize; }
        public void setPageSize(int pageSize) { this.pageSize = pageSize; }

        public int getTotalPages() { return totalPages; }
        public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    }

}
