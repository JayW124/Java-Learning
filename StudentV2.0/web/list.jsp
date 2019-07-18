<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>

    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">

            <tr class="success">
                <th><input type="checkbox" id="checkbox"></th>
                <th>id</th>
                <th>姓名</th>
                <th>学号</th>
                <th>性别</th>
                <th>院系</th>
                <th>籍贯</th>
                <th>学分</th>
                <th>电子邮件</th>
                <th>联系方式</th>
                <th>操作</th>
            </tr>

            <%--        <c:forEach items="${students}" var="student" varStatus="s">--%>
            <c:forEach items="${pb.list}" var="student" varStatus="s">

                <tr>
                    <td><input type="checkbox" name="id" value="${student.id}"></td>
                    <td>${s.count}</td>
                    <td>${student.name}</td>
                    <td>${student.sno}</td>
                    <td>${student.department}</td>
                    <td>${student.hometown}</td>
                    <td>${student.mark}</td>
                    <td>${student.email}</td>
                    <td>${student.tel}</td>
                    <td>${student.sex}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findStuServlet?id=${student.id}">修改</a>
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${student.id});">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <div align="center">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <c:if test="${pb.curPage == 1}">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.curPage != 1}">
                <li>
                    </c:if>
                    <a class="page-link" href="javascript:prePage(${pb.curPage});" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">


                    <c:if test="${pb.curPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findStuByPageServlet?curPage=${i}&rows=5">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.curPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findStuByPageServlet?curPage=${i}&rows=5">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${pb.curPage == pb.totalPage}">
                    <li class="disabled">
                </c:if>

                <c:if test="${pb.curPage != pb.totalPage}">
                    <li>
                </c:if>
                    <a class="page-link" href="javascript:nextPage(${pb.curPage})" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
                <span style="font-size: 25px; margin-left: 5px;">
                共${pb.totalCount}条记录，共${pb.totalPage}页
            </span>
            </ul>
        </nav>
    </div>

</div>

<script>
    window.onload = function(){
        //给删除选中按钮添加单击事件
        document.getElementById("delSelected").onclick = function(){
            if(confirm("您确定要删除选中条目吗？")){

                var flag = false;
                //判断是否有选中条目
                var cbs = document.getElementsByName("id");
                console.log(cbs);
                for (var i = 0; i < cbs.length; i++) {
                    if(cbs[i].checked){
                        //有一个条目选中了
                        flag = true;
                        break;
                    }
                }

                if(flag){//有条目被选中
                    //表单提交
                    document.getElementById("form").submit();
                }

            }

        };
        //1.获取第一个cb
        document.getElementById("checkbox").onclick = function(){
            //2.获取下边列表中所有的cb
            var cbs = document.getElementsByName("id");
            //3.遍历
            for (var i = 0; i < cbs.length; i++) {
                //4.设置这些cbs[i]的checked状态 = firstCb.checked
                cbs[i].checked = this.checked;
            }
        }
    };

    function deleteUser(id) {
        if (confirm("确定删除该用户？")) {
            location.href = "${pageContext.request.contextPath}/delStuServlet?id=" + id;
        }
    }

    function prePage(curPage) {
        var pageNum = (curPage - 1) <= 0 ? curPage : curPage - 1;
        location.href = "${pageContext.request.contextPath}/findStuByPageServlet?curPage=" + pageNum + "&pageNum=5";
    }

    function nextPage(curPage) {
        var pageNum = (curPage + 1) > ${pb.totalPage} ? curPage : curPage + 1;
        location.href = "${pageContext.request.contextPath}/findStuByPageServlet?curPage=" + pageNum + "&pageNum=5";
    }


</script>
</body>
</html>
