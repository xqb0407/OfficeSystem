package xyz.oa.utils;

import xyz.oa.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {
    private List<Node> nodeList ;

    /**
     * 构造器给nodeList 赋值
     * @param nodeList
     */
    public TreeUtils(List<Node> nodeList){
        this.nodeList = nodeList;
    }
    //获取根节点
    private List<Node> getNodeId(){
        List<Node> getNodeList =new ArrayList<Node>();
        for (Node menuNode: nodeList) {
            if (menuNode.getParentId().longValue()==0){
                getNodeList.add(menuNode);
            }
        }
        return getNodeList;
    }
    //递归建立子树
    private Node builderChiltree(Node node){
        List<Node> childernList = new ArrayList<Node>();
        for (Node childern:nodeList) {
            if (childern.getParentId().longValue()==node.getNodeId().longValue()){
                childernList.add(builderChiltree(childern));
            }
        }
        node.setChildren(childernList);
        return node;
    }
    //建立整棵树
    public List<Node> builderTree(){
        List<Node> nodes = new ArrayList<>();
        for (Node NodeTree:getNodeId()){
            NodeTree = builderChiltree(NodeTree);
            nodes.add(NodeTree);
        }
        return nodes;
    }
}
