package xyz.oa.dao.impl;

import xyz.oa.dao.RbacDao;
import xyz.oa.entity.Node;
import xyz.oa.utils.MybatisUtils;
import xyz.oa.utils.TreeUtils;

import java.util.List;

public class RbacDaoImpl implements RbacDao {
    @Override
    public List<Node> SelectNodeByUserId(Long userId) {
        //把查询出来的集合放入到生成树的工具类当中
        TreeUtils treeUtils = new TreeUtils((List<Node>) MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.selectList("rbac.SelectNodeByUserId",userId);

        }));
        //返回树的集合
        return treeUtils.builderTree();
    }
}
