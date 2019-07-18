<%--
  Created by IntelliJ IDEA.
  User: 12427
  Date: 2019/7/17
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>修改用户信息</h3>
    <form action="${pageContext.request.contextPath}/updateStuServlet" method="post">
        <input type="hidden" name="id" value="${student.id}" placeholder="${student.id}">
        <div class="form-group">
            <label>姓名：</label>
            <input type="text" class="form-control" id="name" name="name"
                   value="${student.name}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label>学号：</label>
            <input type="text" class="form-control" id="sno" name="sno"
                    value="${student.sno}"/>
        </div>
        <div class="form-group">
            <label>性别：</label>
            <input type="text" class="form-control" id="sex" name="sex"
                   value="${student.sex}"/>
        </div>
        <div class="form-group">
            <label>院系：</label>
            <input type="text" class="form-control" id="department" name="department"
                   value="${student.department}"/>
        </div>
        <div class="form-group">
            <label>籍贯：</label>
            <input type="text" class="form-control" id="hometown" name="hometown"
                   value="${student.hometown}"/>
        </div>
        <div class="form-group">
            <label>学分：</label>
            <input type="text" class="form-control" id="mark" name="mark"
                   value="${student.mark}"/>
        </div>
        <div class="form-group">
            <label>电子邮件：</label>
            <input type="text" class="form-control" id="email" name="email"
                   value="${student.email}"/>
        </div>
        <div class="form-group">
            <label>联系方式：</label>
            <input type="text" class="form-control" id="tel" name="tel"
                   value="${student.tel}"/>
        </div>
        <div class="form-group">
            <input class="btn btn-primary" type="submit" value="提交">
            <input class="btn btn-primary" type="reset" value="重置">
            <input class="btn btn-primary" type="button" value="返回">

        </div>
    </form>
</div>
</body>
</html>
