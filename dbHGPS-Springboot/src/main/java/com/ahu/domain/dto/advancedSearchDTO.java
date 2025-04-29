package com.ahu.domain.dto;

import lombok.Data;

/**
 * @Classname: advancedSearchDTO
 * @Date: 2024/4/22 14:26
 * @Author: 聂建强
 * @Description: 高级查询，查询条件
 */
@Data
public class advancedSearchDTO extends PageParams{
    private String chromosome;
    private String start_pas;
    private String end_pas;
}
