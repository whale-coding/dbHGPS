package com.ahu.domain.dto;

import lombok.Data;

/**
 * @Classname: PageParams
 * @Date: 2024/4/22 14:32
 * @Author: 聂建强
 * @Description: 分页条件参数
 */
@Data
public class PageParams {

    private int pageNum = 1;  // 当前页码
    private int pageSize = 10;  // 每页大小
}
