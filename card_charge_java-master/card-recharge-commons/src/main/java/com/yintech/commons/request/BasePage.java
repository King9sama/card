package com.yintech.commons.request;

import com.yintech.commons.exception.ParamException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
@SuppressWarnings("unused")
public class BasePage {

    private Integer pageSize;

    private Integer pageNo = 0;

    private String orderColumn;

    private String orderBy;

    private transient String appendInfo;

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 0) {
            return 10;
        }
        if (pageSize > 1000) {
            throw new ParamException("每页数量不能超过1000");
        }
        return pageSize;
    }

    public Integer getPageNo() {
        if (Objects.isNull(pageNo) || pageNo <= 0) {
            return 1;
        }
        return pageNo;
    }

    public String getAppendInfo() {
        StringBuilder builder = new StringBuilder();

        String column = getOrderColumn();
        if (StringUtils.isNotBlank(column)) {
            String order = StringUtils.isBlank(orderBy) ? null : orderBy.trim().toUpperCase();
            if (Objects.equals(order, "DESC")) {
                builder.append(" ORDER BY ").append(column).append(" ").append("DESC").append(" ");
            } else {
                builder.append(" ORDER BY ").append(column).append(" ").append("ASC").append(" ");
            }
        }
        int pageNum = pageNo == null ? 0 : pageNo.intValue();
        if (pageNum > 0) {
            int limit = getPageSize();
            int offsetNum = (pageNum - 1) * limit;
            builder.append(" LIMIT ").append(offsetNum).append(" , ").append(limit).append(" ");
        }
        if (StringUtils.isBlank(builder)) {
            return null;
        }
        return builder.toString();
    }

}
