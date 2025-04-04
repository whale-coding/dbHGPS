package com.ahu.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname: TreeNode
 * @Date: 2024/4/26 18:29
 * @Author: 聂建强
 * @Description:
 */
public class TreeNode {
    private String name;
    private List<TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}

