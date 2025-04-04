package com.ahu.utils;

import java.util.List;
import java.util.Map;

/**
 * @Classname: TreeBuilder
 * @Date: 2024/4/26 18:34
 * @Author: 聂建强
 * @Description:
 */
public class TreeBuilder {
    public static TreeNode buildTree(String geneName, List<Map<String, Object>> siteData) {
        TreeNode root = new TreeNode(geneName);

        for (Map<String, Object> site : siteData) {
            String siteId = (String) site.get("id");
            String pasSignal = (String) site.get("pasSignal");

            TreeNode siteNode = new TreeNode(siteId);
            root.addChild(siteNode);

            // 拆分 pasSignal 字符串
            String[] pasSignals = pasSignal.split("; ");
            for (String signal : pasSignals) {
                // signal = signal.trim().replace("'", ""); // 去除单引号
                TreeNode pasNode = new TreeNode(signal);
                siteNode.addChild(pasNode);
            }
        }

        return root;
    }
}



//
// public class TreeBuilder {
//     public static TreeNode buildTree(String geneName, List<Map<String, Object>> siteData) {
//         TreeNode root = new TreeNode(geneName);
//
//         for (Map<String, Object> site : siteData) {
//             String siteId = (String) site.get("id");
//             String pasSignal = (String) site.get("pasSignal");
//
//             TreeNode siteNode = new TreeNode(siteId);
//             root.addChild(siteNode);
//
//             // 拆分 pasSignal 字符串
//             String[] pasSignals = pasSignal.split("; ");
//             for (String signal : pasSignals) {
//                 // signal = signal.trim().replace("'", ""); // 去除单引号
//                 TreeNode pasNode = new TreeNode(signal);
//                 siteNode.addChild(pasNode);
//             }
//         }
//
//         return root;
//     }
// }
