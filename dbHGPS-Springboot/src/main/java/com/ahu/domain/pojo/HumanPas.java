package com.ahu.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname: polySite
 * @Date: 2024/4/13 22:02
 * @Author: 聂建强
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("human_pas")
public class HumanPas implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("ensemble_gene_id")
    private String ensembleGeneId;

    @TableField("ncbi_gene_id")
    private String ncbiGeneId;

    @TableField("official_symbol")
    private String officialSymbol;

    @TableField("chromosome")
    private String chromosome;

    @TableField("strand")
    private String strand;

    @TableField("start")
    private String start;

    @TableField("end")
    private String end;

    @TableField("tax_id")
    private String taxId;

    @TableField("synonyms")
    private String synonyms;

    @TableField("map_location")
    private String mapLocation;

    @TableField("official_full_name")
    private String officialFullName;

    @TableField("gene_biotype")
    private String geneBioType;

    @TableField("pas_signal")
    private String pasSignal;

    @TableField("start_pas")
    private Integer startPas;

    @TableField("end_pas")
    private Integer endPas;

    @TableField("source")
    private String source;
}
