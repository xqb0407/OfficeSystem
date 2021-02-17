package xyz.oa.Test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import xyz.oa.dao.RbacDao;
import xyz.oa.dao.impl.RbacDaoImpl;
import xyz.oa.entity.Node;
import xyz.oa.utils.MybatisUtils;
import xyz.oa.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

public class TestNode {
    private RbacDao rbacDao = new RbacDaoImpl();

    @Test
    public void compareLong(){
        Long a = 1L;
        Long b= 1L;
         if(a.equals(b)){
            System.out.println("equals比较");
        }
    }


    @Test
    public void TestNodechrien() {
        List<Node> nodeList=  (List<Node>) MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.selectList("rbac.SelectNodeByUserId",1L);

        });
        TreeUtils treeUtils = new TreeUtils(nodeList);
        List<Node> nodeList1 = treeUtils.builderTree();
        System.out.println(nodeList1.size());
//    TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
//        // 自定义属性名 都要默认值的
////        treeNodeConfig.setWeightKey("order");
////        treeNodeConfig.setIdKey("rid");
//        treeNodeConfig.setIdKey("nodeId");
//        treeNodeConfig.setParentIdKey("parentId");
//        treeNodeConfig.setWeightKey("nodeType");
//        treeNodeConfig.setWeightKey("nodeName");
//        treeNodeConfig.setWeightKey("nodeCode");
//        treeNodeConfig.setWeightKey("url");
//        // 最大递归深度
//        treeNodeConfig.setDeep(5);
//
//        //转换器
//        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
//                (treeNode, tree) -> {
//                    tree.setId(String.valueOf(treeNode.getNodeId()));
//                    tree.setParentId(String.valueOf(treeNode.getParentId()));
//                    tree.putExtra("nodeType",treeNode.getNodeType());
//                    tree.putExtra("nodeName",treeNode.getNodeName());
//                    tree.putExtra("nodeCode",treeNode.getNodeCode());
//                    tree.putExtra("url",treeNode.getUrl());
//                });
//        String json = JSON.toJSONString(treeNodes);
//        System.out.println(json);

//        System.out.println(treeNodes.get(0).getChildren());


    }

}
