package com.bluedragon.arth.z_common.dto.response;

public record PageDataResponse <T> (
        T data) {
    public static <T> PageDataResponse <T> create(final T data) {
        return new PageDataResponse<>(data);
    }
}