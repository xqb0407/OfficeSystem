package xyz.oa.service;

import xyz.oa.entity.Node;

import java.util.List;

public interface RbacService {
    List<Node> SelectNodeByUserId(Long UserId); //权限菜单
}
