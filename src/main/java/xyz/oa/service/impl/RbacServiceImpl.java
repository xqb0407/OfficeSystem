package xyz.oa.service.impl;

import xyz.oa.dao.RbacDao;
import xyz.oa.dao.impl.RbacDaoImpl;
import xyz.oa.entity.Node;
import xyz.oa.service.RbacService;

import java.util.List;

public class RbacServiceImpl implements RbacService {

    //注入业务层
    private RbacDao rbacDao =new RbacDaoImpl();


    @Override
    public List<Node> SelectNodeByUserId(Long UserId) {
        List<Node> nodeList = rbacDao.SelectNodeByUserId(UserId);

        return nodeList;
    }
}
