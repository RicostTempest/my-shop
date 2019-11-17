<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp"/>

    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容
                <small>内容管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-user"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容预览</h3>

                            <div class="box-tools">
                                <div class="input-group input-group-sm hidden-xs" style="width: 150px;">
                                    <input type="text" id="keyword" class="form-control pull-right" placeholder="搜索">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" onclick="search()"><i class="fa fa-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="box-body">
                            <a href="/content/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i>添加</a>&nbsp;
                            <a type="button" class="btn btn-default btn-sm" onclick="App.deleteMulti('/content/delete')"><i class="fa fa-trash-o"></i>删除</a>&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i>导入</a>&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i>导出</a>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master" /></th>
                                    <th>ID</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片一</th>
                                    <th>图片二</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>

                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>

<%--在footer下方使用否则无法使用相关的JS导入工具--%>
<tags:modal/>

<script>
    var file_path = "/static/upload/";
    var _dataTable;
    $(function () {
        var _columns = [
            {"data":function (row,type,val,meta) {
                    return '<input type="checkbox" id=" ' + row.id +' " class="minimal"/>';
                }},
            {"data":"id"},
            {"data":"title"},
            {"data":"subTitle"},
            {"data":"titleDesc"},
            {"data":function (row,type,val,meta) {
                    if(row.url == null)
                        return '';
                    //url
                    return '<a href="' + row.url + '" target="_blank">查看</a>';
                }},
            {"data":function (row,type,val,meta) {
                    if(row.pic == null)
                        return '';
                    //pic
                    return '<a href="' +file_path + row.pic + '" target="_blank">图片</a>';
                }},
            {"data":function (row,type,val,meta) {
                    if(row.pic2 == null)
                        return '';
                    //pic2
                    return '<a href="' + row.pic2 + '" target="_blank">图片2</a>';
                }},
            //使用DateTime工具类进行时间数据的格式化
            {"data":function (row,type,val,meta) {
                    return DateTime.format(row.updated, 'yyyy-MM-dd HH:mm');
                }},
            {"data":function (row,type,val,meta) {
                    var detailUrl = "/content/detail?id=" + row.id;
                    return  '<div class="btn-group">'+
                        '<button type="button" class="btn btn-default" onclick="App.showDetail(\'' + detailUrl + '\')"><i class="fa fa-fw fa-tags"></i></button>'+
                        '<a href="/content/form?id='+ row.id +'" type="button" class="btn btn-default"><i class="fa fa-edit"></i></a>'+
                        '<button type="button" class="btn btn-default"><i class="fa fa-fw fa-trash"></i></button>'+
                        '</div>'
                }}
        ];
        _dataTable = App.initDataTables("/content/page", _columns);
    });

    function search() {
        var title = $("#keyword").val();
        var param = {
            "title":title
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }

</script>
</body>
</html>