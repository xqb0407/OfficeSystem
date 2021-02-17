<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加文章</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/resource/css/oksub.css">
    <script type="text/javascript" src="/resource/lib/loading/okLoading.js"></script>
</head>
<body>
<div class="layui-container">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header layui-bg-blue" style="font-size: 15px;">请假申请</div>
            <div class="layui-card-body">
                <div class="ok-body">
                    <!--form表单-->
                    <form class="layui-form layui-form-pane ok-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label">部门</label>
                            <div class="layui-input-block">
                                <input type="text" value="${department.departmentName}" disabled autocomplete="off" class="layui-input"
                                       lay-verify="required">
                                <input type="text" value="${Login_user.employeeId}" name="employeeId" hidden>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">申请人</label>
                            <div class="layui-input-block">
                                <input type="text" value="${current_User.name} - ${current_User.title}" disabled autocomplete="off" class="layui-input"
                                       lay-verify="required">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">请假类别</label>
                            <div class="layui-input-block">
                                <!-- 1-事假、2-病假、3、工伤假、4-婚假、5-产假、6-丧偶', -->
                                <select name="formType" lay-verify="required">
                                    <option value="" selected>请选择</option>
                                    <option value="1">事假</option>
                                    <option value="2">病假</option>
                                    <option value="3">工伤假</option>
                                    <option value="4">婚假</option>
                                    <option value="5">产假</option>
                                    <option value="6">丧假</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">开始时长</label>
                            <div class="layui-input-block">
                                <input type="text" name="startTime" lay-verify="required"  class="layui-input" id="time" placeholder=" - " autocomplete="off">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">结束时长</label>
                            <div class="layui-input-block">
                                <input type="text" name="endTime" lay-verify="required" lay-verify="required" class="layui-input" id="time1" placeholder=" - " autocomplete="off">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">请假事由</label>
                            <div class="layui-input-block">
                                <input type="text" name="reason" required  lay-verify="required" placeholder="请输入事由" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                            <div class="layui-form-item">
                            <div style="">
                                <button class="layui-btn" lay-submit lay-filter="addArticle">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--js逻辑-->
<script src="/resource/lib/layui/layui.js"></script>
<script>
    layui.use(["form", "okUtils", "okLayer","laydate"], function () {
        var laydate = layui.laydate;
        let form = layui.form;
        let okUtils = layui.okUtils;
        let okLayer = layui.okLayer;
        okLoading.close();
        laydate.render({
             elem: '#time' //指定元素
            ,type: 'datetime'
            ,theme: 'molv'
            ,trigger: 'click' //采用click弹出
        });
        laydate.render({
             elem: '#time1' //指定元素
            ,type: 'datetime'
            ,theme: '#393D49'
            ,trigger: 'click' //采用click弹出

        });
            var isCommitted = true;
            form.on("submit(addArticle)", function (dataForm) {
                //表单是否已经提交标识，默认为false
                if (isCommitted){
                    layui.$.ajax({
                        url:"/UserServlet?method=Leave",
                        dataType:"json",
                        data:dataForm.field,
                        type:"POST",
                        success:function (res) {
                            if (res.code==200){
                                layui.layer.msg("申请成功", {icon: 1});
                                isCommitted=false;
                                setTimeout(function () {
                                    location.reload();
                                    // approveTable.html
                                },3000)
                            }else{
                                layui.layer.msg("申请失败", {icon: 5});
                            }
                        }
                    });
                }
                return false;
            });
    });
</script>
</body>
</html>
