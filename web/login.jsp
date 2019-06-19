<%@ page pageEncoding="utf-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>

    <style>
        #verifyCodeImage{
            margin-left: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-group">
            <label for="verifyCodeImage">验证码：</label>
            <div>
                <input type="text" name="verifyCode" class="form-control" id="verifyCode" placeholder="请输入验证码" style="width: 190px; float: left"/>
                <a href="javascript:refreshCode()">
                    <img src="${pageContext.request.contextPath}/verifyCodeServlet" title="看不清点击刷新" id="verifyCodeImage"/>
                </a>
            </div>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录" style="width: 370px;">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <strong>${login_error_msg}</strong>
    </div>
</div>

<script>
    function refreshCode() {
        var verifyCodeImage = document.getElementById("verifyCodeImage");
        verifyCodeImage.src =
            "${pageContext.request.contextPath}/verifyCodeServlet?time=" + new Date().getTime();
    }
</script>
</body>
</html>
