package xyz.oa.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String id;
    private String parentId;
    private String name;
    private int weight;
    private List<TreeNode> chrien = new ArrayList<>();

    public TreeNode(String id, String parentId, String name, int weight) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
