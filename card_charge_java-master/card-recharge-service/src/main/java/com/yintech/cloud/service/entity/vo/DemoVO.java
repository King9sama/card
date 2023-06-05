package com.yintech.cloud.service.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class DemoVO {
    private String content;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    private Date date;
}
