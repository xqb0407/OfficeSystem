<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>ok-admin v2.0 | 很赞的后台模版</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="keywords" content="ok-admin v2.0,ok-admin网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
	<meta name="description" content="ok-admin v2.0，顾名思义，很赞的后台模版，它是一款基于Layui框架的轻量级扁平化且完全免费开源的网站后台管理系统模板，适合中小型CMS后台系统。">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="/resource/css/oksub.css" media="all"/>
<!--	<script type="text/javascript" src="../lib/loading/okLoading.js"></script>-->
<!--	<script type="text/javascript" src="../lib/echarts/echarts.min.js"></script>-->
<!--	<script type="text/javascript" src="../lib/echarts/echarts.themez.js"></script>-->
</head>
<body class="console console1 ok-body-scroll">
<div class="ok-body home">
	<div class="layui-row layui-col-space15 ">
		<div class="layui-col-xs6 layui-col-md3">
			<div class="layui-card">
				<div class="ok-card-body">
					<div class="img-box" ok-pc-in-show>
						<img src="/resource/images/home-01.png" alt="none"/>
					</div>
					<div class="cart-r">
						<div class="stat-text incomes-num">0</div>
						<div class="stat-heading">收入</div>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-col-xs6 layui-col-md3">
			<div class="layui-card ">
				<div class="ok-card-body">
					<div class="img-box" ok-pc-in-show>
						<img src="/resource/images/home-02.png" alt="none"/>
					</div>
					<div class="cart-r">
						<div class="stat-text goods-num">0</div>
						<div class="stat-heading">商品</div>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-col-xs6 layui-col-md3">
			<div class="layui-card">
				<div class="ok-card-body">
					<div class="img-box" ok-pc-in-show>
						<img src="/resource/images/home-03.png" alt="none"/>
					</div>
					<div class="cart-r">
						<div class="stat-text blogs-num">0</div>
						<div class="stat-heading">博客</div>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-col-xs6 layui-col-md3">
			<div class="layui-card">
				<div class="ok-card-body">
					<div class="img-box" ok-pc-in-show>
						<img src="/resource/images/home-04.png" alt="none"/>
					</div>
					<div class="cart-r">
						<div class="stat-text fans-num">${userNum}</div>
						<div class="stat-heading">用户</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="layui-row layui-col-space15">
		<div class="layui-col-md8">
			<div class="layui-card">
				<div class="layui-card-header">
					<div class="ok-card-title">用户排行榜</div>
				</div>
				<div class="ok-card-body map-body">
					<div style="height: 100%;" id="userSourceMap"></div>
				</div>
			</div>
		</div>

		<div class="layui-col-md4">
			<div class="layui-card">
				<div class="layui-card-header">
					<div class="ok-card-title">今日用户访问来源</div>
				</div>
				<div class="ok-card-body map-body">
					<div style="height: 100%;" id="userSourceTodayChart"></div>
				</div>
			</div>
		</div>
	</div>

	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					<div class="ok-card-title">本周用户访问来源</div>
				</div>
				<div class="ok-card-body clearfix">
					<div class="map-china" id="userSourceWeekChart"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="/resource/lib/layui/layui.js"></script>
<script type="text/javascript" src="/resource/js/console.js"></script>



















