<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的商城 | 用户管理</title>
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
                用户列表
                <small>用户管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-user"></i> 首页</a></li>
                <li class="active">用户管理</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户信息</h3>
                            <div class="row" style="padding-left: 12px;padding-top: 10px">
                                <a href="/user/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i>添加</a>&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i>删除</a>&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i>导入</a>&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i>导出</a>
                            </div>

                            <div class="box-tools">
                                <div class="input-group input-group-sm hidden-xs" style="width: 150px;">
                                    <input type="text" name="table_search" class="form-control pull-right" placeholder="搜索">
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>用户名</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="#{tbUsers}" var="tbUser">
                                    <tr>
                                        <td>${tbUser.id}</td>
                                        <td><span class="label label-success">${tbUser.username}</span></td>
                                        <td>${tbUser.phone}</td>
                                        <td>${tbUser.email}</td>
                                        <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd"/> </td>
                                        <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default"><i class="fa fa-edit"></i></button>
                                                <button type="button" class="btn btn-default"><i class="fa fa-fw fa-trash"></i></button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

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
</body>
</html>