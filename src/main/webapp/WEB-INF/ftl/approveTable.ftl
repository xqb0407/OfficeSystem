<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>请假申请</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/resource/css/oksub.css">
    <script type="text/javascript" src="/resource/lib/loading/okLoading.js"></script>
</head>

<body>
<div class="layui-container">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header layui-bg-blue " style="font-size: 15px;">
                请假审批
            </div>
            <div class="layui-card-body">
                <div class="ok-body">
                    <table id="demo"  lay-filter="ApproveTable"></table>
                </div>
            </div>
            <div class="ok-body" id="auditoContent" style="display: none">
                <form class="layui-form layui-form-pane ok-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">部门</label>
                        <div class="layui-input-block">
                            <input type="text"  id="departmentName" disabled="" autocomplete="off" class="layui-input" lay-verify="required">
                            <input type="text"  id="formId" name="formId" hidden="">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-block">
                            <input type="text"  id="name" disabled="" autocomplete="off" class="layui-input" lay-verify="required">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">请假事由</label>
                        <div class="layui-input-block">
                            <input type="text" id="LearReason" disabled=""  lay-verify="required"  autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">请假类别</label>
                        <div class="layui-input-block">
                            <!-- 1-事假、2-病假、3、工伤假、4-婚假、5-产假、6-丧偶', -->
                            <select name="result" lay-verify="required">
                                <option value="" selected="">请选择</option>
                                <option value="approved">同意</option>
                                <option value="refused">驳回</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">起始时间</label>
                        <div class="layui-input-block">
                            <input type="text" id="startTime" disabled=""  lay-verify="required"  autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">结束时间</label>
                        <div class="layui-input-block">
                            <input type="text" id="endTime" disabled=""  lay-verify="required"  autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">审批意见</label>
                        <div class="layui-input-block">
                            <input type="text" id="reason" name="reason"  lay-verify="required"  autocomplete="off" class="layui-input">
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <div style="">
                            <button class="layui-btn  layui-btn-fluid"  lay-submit lay-filter="adito">立即提交</button>
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
<script type="text/html" id="baraudito">
    <a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="audit">审批</a>
</script>
<script>
    layui.use(["form", "okUtils", "okLayer", "laydate", "table", "element",'util',"jquery"], function () {
        // var laydate = layui.laydate;
        var table = layui.table;
        var $ =layui.$;
        // var util = layui.util;
        let form = layui.form;
        // var element = layui.element;
        // let okUtils = layui.okUtils;
        // let okLayer = layui.okLayer;
        okLoading.close();
        layui.laytpl.fn = function (value)
        {
            //json日期格式转换为正常格式
            var date = new Date(parseInt(value.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            return date.getFullYear() + "-" + month + "-" + day;
        }
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 375
            // ,width:800
            ,url: '/UserServlet?method=ApproveTable' //数据接口
            ,page: true //开启分页
            ,limit:3
            ,limits:[5,10,15,20]
            ,cols :[[ //列描述
                {type:'checkbox' ,fixed:'left' }
               ,{title : "序号" ,align:'center', width:40 , type:"numbers"} // numbers代表序号列
               ,{field : "create_time" ,align:'center' , title : "申请时间" , width : 150,templet:"<div>{{layui.util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm')}}</div>"}
               ,{field : "formType" ,align:'center', title : "类型" , width : 100 , templet: function(d){
                        switch (d.form_type) {
                            case 1:
                                return "事假";
                            case 2:
                                return "病假";
                            case 3:
                                return "工伤假";
                            case 4:
                                return "婚假";
                            case 5:
                                return "产假";
                            case 6:
                                return "丧假";
                        }
                }}
               ,{field : "department_name" ,align:'center', title : "部门" , width : 100}
               ,{field : "name" , title : "员工" ,align:'center', width : 100}
               ,{field : "start_time" , title : "起始时间",align:'center' , width : 150, templet: "<div>{{layui.util.toDateString(d.start_time, 'yyyy-MM-dd HH:mm')}}</div>"}
               ,{field : "end_time" , title : "结束时间" ,align:'center', width : 150 , templet: "<div>{{layui.util.toDateString(d.end_time, 'yyyy-MM-dd HH:mm')}}</div>"}
               ,{field : "reason" , title : "请假原因" , width : 240 ,align:'center' }
               ,{title : "操作" ,type:'space',align:'center' ,width:100 , fixed:'right',toolbar:'#baraudito'}
            ]]
        });

        table.on('tool(ApproveTable)', function(obj){
            var data = obj.data;
            // console.log(data);
            if(obj.event === 'audit'){
                $('#departmentName').val(data.department_name);
                $('#formId').val(data.form_id);
                $('#name').val(data.name);
                $('#LearReason').val(data.reason);
                $('#startTime').val(layui.util.toDateString(data.start_ime, 'yyyy-MM-dd HH:mm'));
                $('#endTime').val(layui.util.toDateString(data.end_time, 'yyyy-MM-dd HH:mm'));
                layer.open({
                    title: '审批'
                    ,type: 1
                    ,maxmin: true
                    ,anim: 4
                    ,content: $('#auditoContent')
                });

                form.on("submit(adito)", function (fromdata) {
                    layui.$.ajax({
                        url:'/UserServlet?method=audit',
                        type:"POST",
                        dataType:'json',
                        data:fromdata.field,
                        success:function (res) {
                            if(res.code==200){
                                layui.layer.msg(res.message, {icon: 1});
                                setTimeout(function () {
                                    location.reload();
                                },1300)
                            }else{
                                layui.layer.msg(res.message, {icon: 5});
                            }
                        }
                    });
                    return false;
                });

            }
        });


    });
</script>
</body>

</html>