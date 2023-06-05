package com.yintech.cloud.service.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description
 * @Company: yintech
 * @date 2021/6/22 4:48 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DemoDTO {
    @NotNull(message = "id不能为空")
    private Integer id;
    @NotEmpty(message = "请求内容不能为空")
    private String request;
    @Max(value = 3, message = "类型最大为3")
    @Min(value = 1, message = "类型最小为1")
    private Byte type;
    private LocalDate localDate;

    private LocalDateTime localDateTime;

    private Date date;
}
