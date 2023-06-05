package com.yintech.commons.response;

import com.yintech.commons.constant.ResultCodeConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kai.tang
 * @version 1.0-SNAPSHOT
 * @date 2020/5/14 9:16
 */
@Data
public class PageBaseResponse<T> implements Serializable {

    private int code;

    private String msg;

    private List<T> data = new ArrayList<>();

    private int count;


    public static <T> PageBaseResponse<T> success() {
        PageBaseResponse<T> baseResponse = new PageBaseResponse<T>();
        baseResponse.setCode(ResultCodeConstant.SUCCESS.getCode());
        baseResponse.setMsg(ResultCodeConstant.SUCCESS.getMsg());
        return baseResponse;
    }

    public static <T> PageBaseResponse<T> success(List<T> data, int count) {
        PageBaseResponse<T> pageBaseResponse = new PageBaseResponse<T>();
        pageBaseResponse.setCode(ResultCodeConstant.SUCCESS.getCode());
        pageBaseResponse.setMsg(ResultCodeConstant.SUCCESS.getMsg());
        pageBaseResponse.setData(data);
        pageBaseResponse.setCount(count);
        return pageBaseResponse;
    }


    public static <T> PageBaseResponse<T> error(String msg) {
        PageBaseResponse<T> pageBaseResponse = new PageBaseResponse<T>();
        pageBaseResponse.setCode(ResultCodeConstant.ERROR.getCode());
        pageBaseResponse.setMsg(msg);
        return pageBaseResponse;
    }

    public static <T> PageBaseResponse<T> error(int code, String msg) {
        PageBaseResponse<T> pageBaseResponse = new PageBaseResponse<T>();
        pageBaseResponse.setCode(code);
        pageBaseResponse.setMsg(msg);
        return pageBaseResponse;
    }


}
