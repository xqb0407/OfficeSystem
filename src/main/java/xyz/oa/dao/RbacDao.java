package xyz.oa.dao;

import xyz.oa.entity.Node;

import java.util.List;

public interface RbacDao {
    List<Node> SelectNodeByUserId(Long userId); //查询功能
}
