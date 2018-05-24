<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Job定时任务管理</title>
<c:if test="${fn:contains(sessionInfo.resourceList, '/jobMgr/listLogs')}">
	<script type="text/javascript">
		$.canLook = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/jobMgr/exec')}">
	<script type="text/javascript">
		$.canExec = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/jobMgr/edit')}">
	<script type="text/javascript">
		$.canUpdate = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/jobMgr/change')}">
	<script type="text/javascript">
		$.canChange = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/jobMgr/list',
							fit : true,
							fitColumns : true,
							border : false,
							pagination : true,
							//idField : 'umIdN',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							//sortName : 'name',
							//sortOrder : 'asc',
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							frozenColumns : [ [ {} ] ],
							nowrap : false,
							columns : [ [ {
									field : 'sjiIdN',
									title : '编号',
									width : 40 
								},{
									field : 'sjiNameV',
									title : '任务名称',
									width : 100 
								},{
									field : 'sjiSpringIdV',
									title : 'Spring ID',
									width : 100 
								},{
									field : 'sjiMethodNameV',
									title : '方法名',
									width : 100 
								},{
									field : 'sjiCronV',
									title : 'cron 表达式',
									width : 60
								},{
									field : 'sjiStatusN',
									title : '任务状态',
									width : 60,
									formatter : function(value, rec, index) {
										return 1 == value?'启动中':'已停止';
									}
								},{
									field : 'action',
									title : '操作',
									width : 150 ,
									formatter : function(value, rec, index) {
										var tmpStr = rec.sjiStatusN == 0?'启动':'停止';
										var cmd = rec.sjiStatusN == 0?1:0;
										
										var str = '';
										if ($.canChange) {
											str += $.formatString('<img onclick="change(\'{0}\',\'{1}\',\'{2}\',\'{3}\');" src="{4}" title="启停"/>', rec.sjiIdN, cmd, tmpStr, rec.sjiNameV, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											str += tmpStr;
										}
										if ($.canUpdate) {
											str += $.formatString('<img onclick="update(\'{0}\',\'{1}\');" src="{2}" title="修改cron"/>', rec.sjiIdN, rec.sjiNameV, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											str += '修改cron';
										}
										if ($.canExec) {
											str += $.formatString('<img onclick="exec(\'{0}\',\'{1}\');" src="{2}" title="立即执行"/>', rec.sjiIdN, rec.sjiNameV, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											str += '立即执行';
										}
										if ($.canLook) {
											str += $.formatString('<img onclick="look(\'{0}\');" src="{1}" title="查看日志"/>', rec.sjiCodeV, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											str += '查看日志';
										}
										return str;
									} 
								}] ],
							toolbar : '#toolbar',
							onLoadSuccess : function(data) {
								console.log(data);
								
								$('#jobManageForm table').show();
								parent.$.messager.progress('close');
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
							}
						});
	});

	function searchJobInfo() {
		dataGrid.datagrid('load', $.serializeObject($('#jobManageForm')));
	}
	function cleanFun() {
		$('#jobManageForm input').val('');
		$('#jobManageForm select').val('');
		dataGrid.datagrid('load', {});
	}
	function change(jobId, status, tmpStr, jobName) {
		parent.$.messager.confirm('询问', '您是否要'+tmpStr+jobName+'Job？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${ctx}/jobMgr/change', {
					'jobId':jobId,
					'status':status
				}, function(result) {
					console.log(result);
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
		
	}
	function exec(jobId, jobName) {
		parent.$.messager.confirm('询问', '您是否要执行'+jobName+'Job？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${ctx}/jobMgr/exec', {
					'jobId':jobId
				}, function(result) {
					console.log(result);
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}
	
	function update(jobId) {
		parent.$.modalDialog({
			title : '编辑Cron',
			width : 500,
			height : 300,
			href : '${ctx}/jobMgr/editPage?jobId=' + jobId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function look(jobType){
		parent.$.modalDialog({
			title : '查看详情',
			width : 900,
			height : 600,
			href : '${ctx}/jobMgr/logsPage?jobType=' + jobType,
			buttons : [ {
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			} ]
		});
		parent.$.messager.progress('close');
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false"
			style="height:80px; overflow: hidden;">
			<form id="jobManageForm">
				<table style="width: 600px;" cellpadding="1" cellspacing="1" class="formtable">
				<tr>
					<td align="left" width="15%" nowrap style="font-size: 15px;">任务名称：
						<select name="bidStatus" style="width: 100px;">
						    <option value="" selected="selected">全部</option>
							<option value="1001" >发布日收盘价</option>
							<option value="1002" >预测开奖</option>
							<option value="1003" >金币奖励上限</option>
						</select>
					</td>
				</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a href="javascript:void(0);" class="easyui-linkbutton"data-options="iconCls:'brick_add',plain:true"onclick="searchJobInfo();">查询</a> 
		<a href="javascript:void(0);"class="easyui-linkbutton"data-options="iconCls:'brick_delete',plain:true"onclick="cleanFun();">清空条件</a>
	</div>
	
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
	<div onclick="change();" data-options="iconCls:'pencil'">启停</div>
	<div onclick="update();" data-options="iconCls:'pencil'">修改cron</div>
	<div onclick="exec();" data-options="iconCls:'pencil'">立即执行</div>
	<div onclick="look();" data-options="iconCls:'pencil'">查看日志</div>
	</div>
</body>
</html>
