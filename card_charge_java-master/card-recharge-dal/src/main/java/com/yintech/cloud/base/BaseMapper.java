package com.yintech.cloud.base;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.RowBoundsMapper;
import tk.mybatis.mapper.common.SqlServerMapper;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

/**
 * @author kai.tang
 * @version 1.0-SNAPSHOT
 * @date 2020/5/15 13:33
 */
@RegisterMapper
public interface BaseMapper<T> extends
        BaseSelectMapper<T>,
        BaseUpdateMapper<T>,
        BaseDeleteMapper<T>,
        SqlServerMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        Marker {
}