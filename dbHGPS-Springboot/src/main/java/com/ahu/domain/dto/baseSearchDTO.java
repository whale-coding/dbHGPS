package com.ahu.domain.dto;

import lombok.Data;

/**
 * @Classname: baseSearchDTO
 * @Date: 2024/4/22 14:07
 * @Author: 聂建强
 * @Description:  基础查询，查询条件
 */
@Data
public class baseSearchDTO extends PageParams{

    private String selectedType;

    private String keyword;

}
