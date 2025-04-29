package com.ahu.controller;

import com.ahu.domain.dto.advancedSearchDTO;
import com.ahu.domain.dto.baseSearchDTO;
import com.ahu.domain.pojo.HumanPas;
import com.ahu.mapper.HumanPasMapper;
import com.ahu.utils.AjaxResult;
import com.ahu.utils.TreeNode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname: HumanPasController
 * @Date: 2024/12/13 19:34
 * @Author: 聂建强
 * @Description:
 */
@RestController
public class HumanPasController {

    @Resource
    private HumanPasMapper humanPasMapper;

    @GetMapping("/test2")
    public HumanPas index(){
        // siteMapper siteMapper.selectById(2)
        HumanPas pas = humanPasMapper.selectById(2);

        return pas;
    }

    /**
     * 基础查询功能
     * @param params 查询条件： ensemble_gene_id、ncbi_gene_id、official_symbol、chromosome、pas_signal
     * @return 查询结果(分页)
     */
    @RequestMapping("/baseSearch")
    public AjaxResult baseSearch(@RequestBody baseSearchDTO params){
        LambdaQueryWrapper<HumanPas> lambdaQuery = new LambdaQueryWrapper<>();
        String type = params.getSelectedType();
        String keyword = params.getKeyword();
        switch (type) {
            case "ensemble_gene_id":
                lambdaQuery.eq(HumanPas::getEnsembleGeneId, keyword);
                break;
            case "ncbi_gene_id":
                lambdaQuery.eq(HumanPas::getNcbiGeneId, keyword);
                break;
            case "official_symbol":
                lambdaQuery.eq(HumanPas::getOfficialSymbol, keyword);
                break;
            case "chromosome":
                lambdaQuery.eq(HumanPas::getChromosome, keyword);
                break;
            case "pas_signal":
                // 使用 like 条件匹配包含指定信号的记录
                lambdaQuery.like(HumanPas::getPasSignal, "%" + keyword + "%");
                // lambdaQuery.eq(site::getGeneName, keyword);
                break;
            default:
                // 处理无效的搜索类型
                break;
        }

        // 分页
        long currentPage = params.getPageNum();
        long pageSize = params.getPageSize();
        Page<HumanPas> page = new Page<>(currentPage, pageSize);

        // 查询
        Page<HumanPas> results = humanPasMapper.selectPage(page, lambdaQuery);

        // 查询结果返回
        return AjaxResult.succ(results);
    }

    /***
     * 高级查询功能
     * @param params 查询条件
     * @return 查询结果(分页)
     */
    @RequestMapping("/advancedSearch")
    public AjaxResult advancedSearch(@RequestBody advancedSearchDTO params){
        LambdaQueryWrapper<HumanPas> lambdaQuery = new LambdaQueryWrapper<>();
        String chromosome = params.getChromosome();
        String start = params.getStart_pas();
        String end = params.getEnd_pas();
        // 查询条件
        if (chromosome != null && !chromosome.isEmpty()) {
            lambdaQuery.eq(HumanPas::getChromosome, chromosome);
        }
        // // 范围查询条件
        // if (start != null && !start.isEmpty()) {
        //     lambdaQuery.ge(HumanPas::getStartPas, Integer.parseInt(start));
        // }
        //
        // if (end != null && !end.isEmpty()) {
        //     lambdaQuery.le(HumanPas::getEndPas, Integer.parseInt(end));
        // }

        // 判断并添加 startPas 查询条件
        if (start != null && !start.isEmpty()) {
            try {
                lambdaQuery.ge(HumanPas::getStartPas, Integer.parseInt(start));
            } catch (NumberFormatException e) {
                return AjaxResult.fail ("Start position must be a valid integer.");
            }
        }

        // 判断并添加 endPas 查询条件（仅当 end 被提供时）
        if (end != null && !end.isEmpty()) {
            try {
                lambdaQuery.le(HumanPas::getEndPas, Integer.parseInt(end));
            } catch (NumberFormatException e) {
                return AjaxResult.fail ("End position must be a valid integer.");
            }
        }

        // 分页
        long currentPage = params.getPageNum();
        long pageSize = params.getPageSize();
        Page<HumanPas> page = new Page<>(currentPage, pageSize);

        Page<HumanPas> results = humanPasMapper.selectPage(page, lambdaQuery);

        // 结果返回
        return AjaxResult.succ(results);
    }
    // if (geneName != null && !geneName.isEmpty()) {
    //     lambdaQuery.like(HumanPas::getOfficialSymbol, geneName);
    // }
    // if (startPas != null || !endPas.isEmpty()) {
    //     // lambdaQuery.eq(site::getPosition, position);
    //     lambdaQuery.between(HumanPas::, startPas, endPas);
    //     // 判断是否为范围
    //     // if (position.contains("-")) {
    //     //     String[] range = position.split("-");
    //     //     Integer start = Integer.parseInt(range[0]);
    //     //     Integer end = Integer.parseInt(range[1]);
    //     //     lambdaQuery.between(HumanPas::getStart, start, end);
    //     // } else {
    //     //     Integer pos = Integer.parseInt(position);
    //     //     lambdaQuery.eq(HumanPas::getEndPas, pos);
    //     // }
    // }



    /***
     * 根据关键词进行搜索
     * @param keyword 关键词：基因名
     * @return 搜索结果
     */
    @RequestMapping("/searchByKeyword")
    public AjaxResult searchByKeyword(@RequestParam("keyword") String keyword){
        if (keyword ==null || "".equals(keyword)){
            System.out.println("Error");
            return AjaxResult.fail("");
        }
        LambdaQueryWrapper<HumanPas> lambdaQuery = new LambdaQueryWrapper<>();

        // 查询条件
        if (!keyword.isEmpty()) {
            lambdaQuery.eq(HumanPas::getOfficialSymbol, keyword);
        }
        // 查询数据
        List<HumanPas> results = humanPasMapper.selectList(lambdaQuery);

        // 计数器
        // int siteCount = 0;   // 位点数
        int pasCount = 0;   // 信号数
        // 去重后的 PasSignal 列表
        Set<String> uniqueSignals = new HashSet<>();
        // 遍历查询结果，统计基因名对应的记录数和信号数目
        for (HumanPas s : results) {
            if (keyword.equals(s.getOfficialSymbol())) {
                // siteCount++;
                String[] signals = s.getPasSignal().split(";");
                for (String signal : signals) {
                    // 过滤掉@及其后面的内容
                    // String signalOnly = signal.split("@")[0].trim();
                    uniqueSignals.add(signal);
                    pasCount++;
                }
                // pasCount += signals.length;
                // 去除额外的引号和单引号，并添加到 uniqueSignals 集合中
                // for (String signal : signals) {
                //     String cleanSignal = signal.replace("'", "").trim();
                //     uniqueSignals.add(cleanSignal);
                // }
                // uniqueSignals.addAll(Arrays.asList(signals));
            }
        }

        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!results.isEmpty()){
            map.put("ensemble_gene_id", results.get(0).getEnsembleGeneId());
            map.put("ncbi_gene_id", results.get(0).getNcbiGeneId());
            map.put("geneName", results.get(0).getOfficialSymbol());
            // map.put("siteCount", siteCount);
            map.put("pasCount", pasCount);
            map.put("pasList", new ArrayList<>(uniqueSignals));
            data.add(map);
        }

        // 返回
        return AjaxResult.succ(data);
    }


    /**
     *
     * @param geneName
     * @return   树形图的一个数据
     */
    @RequestMapping("/getSiteByGeneName")
    public AjaxResult getSiteByGeneName(@RequestParam("geneName") String geneName){
        if (geneName ==null || geneName.isEmpty()){
            return AjaxResult.fail("");
        }
        System.out.println("===========");
        System.out.println(geneName);
        System.out.println("===========");
        LambdaQueryWrapper<HumanPas> lambdaQuery = new LambdaQueryWrapper<>();
        // 条件
        lambdaQuery.eq(HumanPas::getOfficialSymbol, geneName);
        // 查询
        List<HumanPas> humanPasList = humanPasMapper.selectList(lambdaQuery);

        // 根据PAS信号分组
        Map<String, List<HumanPas>> signalMap = humanPasList.stream()
                .collect(Collectors.groupingBy(HumanPas::getPasSignal));

        // 打印分组后的信息
        for (Map.Entry<String, List<HumanPas>> entry : signalMap.entrySet()) {
            System.out.println("PAS Signal: " + entry.getKey());
            for (HumanPas pas : entry.getValue()) {
                System.out.println(" - Official Symbol: " + pas.getOfficialSymbol());
                System.out.println(" - Chromosome: " + pas.getChromosome());
                System.out.println(" - Start PAS: " + pas.getStartPas());
                System.out.println(" - End PAS: " + pas.getEndPas());
                System.out.println(" - Strand: " + pas.getStrand());
            }
        }

        // 构建树形结构
        TreeNode root = new TreeNode(geneName);
        for (Map.Entry<String, List<HumanPas>> entry : signalMap.entrySet()) {
            TreeNode signalNode = new TreeNode(entry.getKey());
            root.addChild(signalNode);

            for (HumanPas s : entry.getValue()) {
                String chromosome = s.getChromosome();
                String signal = s.getPasSignal();
                Integer pas_start = s.getStartPas();
                Integer pas_end = s.getEndPas();
                String strand = s.getStrand();

                // 拼接字符串,组成pas_id
                String pas_id = chromosome + "_" + signal + "_" + pas_start + "_" + pas_end + "_" + strand;

                TreeNode infoNode = new TreeNode(pas_id);
                signalNode.addChild(infoNode);
            }
        }

        // 将树形结构转换为所需的格式（例如，JSON）
        // 这里需要一个方法将TreeNode转换为JSON或其他格式
        // String treeData = convertTreeToJson(root);
        //
        // return AjaxResult.success(treeData);

        // List<String> resultList = new ArrayList<>();
        // List<Map<String, Object>> resultList = new ArrayList<>();

        // for (HumanPas s : siteList) {
        //     // Map<String, Object> resultMap = new HashMap<>();
        //     // resultMap.put("id", s.getId());
        //     String chromosome = s.getChromosome();
        //     String signal = s.getPasSignal();
        //     String pas_start = s.getStartPas();
        //     String pas_end = s.getEndPas();
        //     String strand = s.getStrand();
        //
        //     // 拼接字符串,组成pas_id
        //     String pas_id = chromosome + "_" + signal + "_" + pas_start + "_" + pas_end + "_" + strand;
        //
        //     // chr10_AACAAG_101771484_101771489_-
        //
        //     // 过滤掉 pasSignal 中的 @ 及其后面的内容
        //     // String pasSignal = s.getPasSignal();
        //     // String[] signals = pasSignal.split(",");
        //     // List<String> filteredSignals = new ArrayList<>();
        //     // for (String signal : signals) {
        //     //     String signalOnly = signal.split("@")[0].trim();
        //     //     filteredSignals.add(signalOnly);
        //     // }
        //     // resultMap.put("pasSignal", String.join(",", filteredSignals));
        //
        //
        //     // resultMap.put("pasSignal", pas_id);
        //
        //     resultList.add(pas_id);
        // }

        // TreeNode tree = TreeBuilder.buildTree(geneName, resultList);


        // for (HumanPas s : humanPasList) {
        //     Map<String, Object> resultMap = new HashMap<>();
        //
        //     resultMap.put("pas_signal", s.getPasSignal());
        //
        //     resultList.add(resultMap);
        // }

        // TreeNode tree = TreeBuilder.buildTree(geneName, resultList);

        // 返回
        return AjaxResult.succ(root);
    }


}
