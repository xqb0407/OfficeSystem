# OA办公系统

## 需求分析

- 6级以下员工为业务岗，对应人员执行公司业务事宜
- 7-8级为管理岗 其中7级为部门经理，8级为总经理
- 业务岗与管理岗员工系统功能不痛痛，要求允许灵活配置

### 请求流程

- 公司所有员工都可以使用“请求申请” 功能申请休假
- 请假时间少于72小时，部门经理审批后直接通过
- 请假时间大于72小时，部门经理审批后还需要总经理进行审批
- 部门经理只允许批准本部门员工申请
- 部门经理请假需要直接有总经理审批
- 总经理提起请假申请，系统自动批准通过

<img src="https://cdn.jsdelivr.net/gh/xqb0407/imgurl/img/qinjia.png" alt="image-20210203112352399" style="zoom:80%;" />

## 环境搭建

### 技术栈：

> - Mysql5.7 
> - Mybatis 3.5
> - Alibaba Druid
> - Servlet3.1
> - Freemarker2.3
> - Layui2.5

## 完成功能

**登录、退出、验证码、树形菜单、请假功能、审批流程、部门经理审批，总经理审批**

## 主要工具

**过滤器**

```java
/**
 * 全局过滤器
 */
public class FilterUtils implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request =(HttpServletRequest) req;
        HttpServletResponse response =(HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        User login_user = (User)request.getSession().getAttribute("Login_user");
        String spath = request.getServletPath();
        String[] urls = {"/login.html","/json",".woff",".ttf",".eot",".woff2",".js",".css",".ico",".jpg",".png","/verifyServlet","/UserServlet"};
        boolean flag = true;
        for (String str : urls) {
            if (spath.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }
        if (flag) {
            if (login_user == null) {
                System.out.println(login_user);
                response.sendRedirect("/login.html");
            }else {
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
```

**Mybatis工具类**

```java
/**
 * Mybatis工具类，创建全局唯一的SqlSessionFactory工厂对象
 *
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory =null;
    static{
        Reader reader =null;
        try {
                reader = Resources.getResourceAsReader("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (IOException e){
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 执行SELECT查询sql
     * @param func 要执行查询语句的代码块
     * @return 查询结果
     */
    public static Object executeQuery(Function<SqlSession,Object> func){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Object obj = func.apply(sqlSession);
            return obj;
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 执行写操作，删除更新插入操作SQL
     * @param func 要执行的代码块
     * @return 写操作执行后的返回结果
     */
    public static Object executeUpdate(Function<SqlSession,Object> func){
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            Object obj = func.apply(sqlSession);
            sqlSession.commit();
            return obj;
        }catch (RuntimeException e){
            sqlSession.rollback();
            throw e;
        }finally {
            sqlSession.close();
        }
    }
}
```

**树形菜单工具类**

```java
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
```

## 效果图

<img src="https://cdn.jsdelivr.net/gh/xqb0407/imgurl/img/QQ截图20210217202910.png">

<img src="https://cdn.jsdelivr.net/gh/xqb0407/imgurl/img/QQ截图20210217202949.png">

<img src="https://cdn.jsdelivr.net/gh/xqb0407/imgurl/img/QQ截图20210217203112.png">

<img src="https://cdn.jsdelivr.net/gh/xqb0407/imgurl/img/QQ截图20210217203016.png">