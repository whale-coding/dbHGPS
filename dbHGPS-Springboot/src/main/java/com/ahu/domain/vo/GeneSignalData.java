package com.ahu.domain.vo;

import lombok.Data;

/**
 * @Classname: GeneSignalData
 * @Date: 2024/4/25 15:09
 * @Author: 聂建强
 * @Description: 返回给前端的数据模型
 */
@Data
public class GeneSignalData {
    private String id;
    private String geneId;
    private String geneName;
    private Integer siteCount;
    private Integer pasCount;
}
