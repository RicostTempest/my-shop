<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>我的商城 | 新增内容</title>
    <jsp:include page="../includes/header.jsp" />
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dropzone.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css" />
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
                ${tbContent.id == null ? "新增" : "编辑"}内容
                <small>控制面板</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-user"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-6">
                    <div class="alert alert-${baseResult.status == 200 ? "success":"danger"} alert-dismissible" ${baseResult == null ? "style='display:none'" : ""}>
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${baseResult.message}
                    </div>
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">添加新内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <form:form cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="categoryId" class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                        <form:hidden path="categoryId"/>
                                        <input id="categoryName" class="form-control required " data-toggle="modal" data-target="#modal-default"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">Title</label>

                                    <div class="col-sm-10">
                                        <form:input path="title" cssClass="form-control" placeholder="Title"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">subTitle</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control" placeholder="SubTitle"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">titleDesc</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control" placeholder="TitleDesc"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">Url</label>

                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control" placeholder="Url"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">pic</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control" placeholder="Pic"/>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">pic2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control" placeholder="Pic2"/>
                                        <div id="dropz2" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="content" class="col-sm-2 control-label">content</label>

                                    <div class="col-sm-10">
                                        <!--此处为隐藏域内容-->
                                        <form:hidden path="content"/>

                                        <div id="editor">${tbContent.content}</div>
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button type="submit" id="btnSubmit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
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

<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script> //图片上传
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<%--在footer下方使用否则无法使用相关的JS导入工具--%>
<tags:modal title="请选择" message="<ul id='myTree' class='ztree'></ul>"/>
<script>
    $(function () {
        App.initZTree("/content/category/tree/data",["id"],function (nodes) {
            var node = nodes[0];
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide");

        })
    });

    App.initDropzone({
        id:"#dropz",
        url:"/upload",
        init:function () {
            this.on("success",function (file,data) {
                $("#pic").val(data.fileName);
            });
        }
    });
    App.initDropzone({
        id:"#dropz2",
        url:"/upload",
        init:function () {
            this.on("success",function (file,data) {
                $("#pic2").val(data.fileName);
            });
        }
    });

    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.customConfig.uploadImgServer = "/upload";
    editor.customConfig.uploadFileName = "dropFile"
    editor.create();
    $("#btnSubmit").bind("click", function () {
        var contentHtml = editor.txt.html();
        $("#content").val(contentHtml);
    });
</script>
</body>
</html>