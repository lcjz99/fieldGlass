<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var jobType=${jobType};
	var logList;
	$(function() {
		logList = $('#logList')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/jobMgr/listLogs?jobType='+jobType,
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
									field : 'sjmIdN',
									title : '编号',
									width : 60 
								},{
									field : 'sjmJobNameV',
									title : '任务名称',
									width : 100 
								},{
									field : 'sjmDateD',
									title : '执行日期',
									width : 100 
								},{
									field : 'sjmStartTimeD',
									title : '执行开始时间',
									width : 100 
								},{
									field : 'sjmEndTimeD',
									title : '执行结束时间',
									width : 100
								},{
									field : 'sjmSuccessN',
									title : '任务状态',
									width : 100,
									formatter : function(value, rec, index) {
										return 1 == value?'成功':'失败';
									}
								}] ],
							onLoadSuccess : function(data) {
								console.log(data);
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
							}
						});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="logList"></table>
	</div>
</div>