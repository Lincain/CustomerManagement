<%@ page pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h3 style="text-align: center;margin-bottom: 20px">客户信息列表</h3>
    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/customerListByPageServlet" method="post">
            <div class="form-group" style="margin-right: 5px">
                <%--<label for="exampleInputName2">姓名</label>--%>
                <input type="text" name="name" value="${conditions.name[0]}" class="form-control" id="exampleInputName2" placeholder="请输入姓名">
            </div>
            <div class="form-group" style="margin-right: 5px">
                <%--<label for="exampleInputName3">籍贯</label>--%>
                <input type="text" name="address" value="${conditions.address[0]}" class="form-control" id="exampleInputName3" placeholder="请输入地址">
            </div>

            <div class="form-group">
                <%--<label for="exampleInputEmail2">邮箱</label>--%>
                <input type="text" name="email" value="${conditions.email[0]}" class="form-control" id="exampleInputEmail2" placeholder="请输入邮箱" >
            </div>
            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="条件查询" />
                <input class="btn btn-default" type="button" value="清空条件" id="clear_btn"/>
            </div>
        </form>

    </div>
    <div style="float: right;margin: 5px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/cityListServlet?referer=add&totalCount=${pageBean.totalCount}">添加联系人</a>
        <a class="btn btn-primary" href="javascript:deleteSelected();">删除选中</a>
    </div>

    <form action="${pageContext.request.contextPath}/deleteSelectedServlet" id="checkeForm">
        <table border="1" class="table table-bordered table-hover">

            <tr class="success">
                <th>
                    <input type="checkbox" name="firstBox" id="firstBox">
                </th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pageBean.list}" var="customer" varStatus="i">
                <tr>
                    <td>
                        <input type="checkbox" name="uid" id="uid" value="${customer.id}">
                    </td>
                    <td>${i.count}</td>
                    <td>${customer.name}</td>
                    <td>${customer.gender}</td>
                    <td>${customer.age}</td>
                    <td>${customer.address}</td>
                    <td>${customer.qq}</td>
                    <td>${customer.email}</td>
                    <td>
                        <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/showCustomerServlet?id=${customer.id}&currentPage=${pageBean.currentPage}">修改</a>&nbsp;
                        <a class="btn btn-danger btn-sm" href="javascript:deleteCustomer(${customer.id});">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div style="padding-top: 19.5px;width: 20px;float: left;margin-right: 30px">
        <select id="pageRows" style="height: 34px;padding-left: 5px;border-radius: 5px;border: 1px solid #dddddd">
            <c:forEach begin="5" end="10" var="i">
                <c:if test="${pageBean.rows == i}">
                    <option value="${i}" selected>${i}</option>
                </c:if>
                <c:if test="${pageBean.rows != i}">
                    <option value="${i}">${i}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pageBean.currentPage != 1}">
                    <li>
                </c:if>
                <c:if test="${pageBean.currentPage == 1}">
                    <li class="disabled">
                </c:if>
                    <a href="${pageContext.request.contextPath}/customerListByPageServlet?currentPage=${pageBean.currentPage - 1}&rows=${pageBean.rows}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
                    <c:if test="${pageBean.currentPage == i}">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/customerListByPageServlet?currentPage=${i}&rows=${pageBean.rows}">${i}</a>
                    </li>
                    </c:if>
                    <c:if test="${pageBean.currentPage != i}">
                    <li>
                        <a href="${pageContext.request.contextPath}/customerListByPageServlet?currentPage=${i}&rows=${pageBean.rows}">${i}</a>
                    </li>
                    </c:if>
                </c:forEach>
                <c:if test="${pageBean.currentPage != pageBean.totalPage}">
                    <li>
                </c:if>
                <c:if test="${pageBean.currentPage == pageBean.totalPage}">
                    <li class="disabled">
                </c:if>
                    <a href="${pageContext.request.contextPath}/customerListByPageServlet?currentPage=${pageBean.currentPage + 1}&rows=${pageBean.rows}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">共${pageBean.totalCount}条记录，共${pageBean.totalPage}页</span>
            </ul>
        </nav>
    </div>
</div>

<script>
    var firstBox = document.getElementById("firstBox");
    firstBox.onclick = function () {
        var boxs = document.getElementsByName("uid");
        for(var i = 0; i < boxs.length; i++){
            boxs[i].checked = this.checked;
        }
    }

    function deleteSelected(){
        var flag = false;
        var uids = document.getElementsByName("uid");
        for (var i = 0; i< uids.length; i++){
            if (uids[i].checked){
                flag = true;
                break;
            }
        }

        if (flag){
            if(confirm("确定需要删除吗？")){
                document.getElementById("checkeForm").submit();
            }
        }else {
            alert("请选择删除项");
        }
    }

    function deleteCustomer(id){
        if (confirm("确定需要删除吗？")){
            location.href = "${pageContext.request.contextPath}/deleteCustomerServlet?id=" + id + "&currentPage=${pageBean.currentPage}";
        }
    }

    var pageRows = document.getElementById("pageRows");
    pageRows.onchange = function () {
        var index = pageRows.selectedIndex;
        var value =  pageRows.options[index].value;
        location.href = "${pageContext.request.contextPath}/customerListByPageServlet?currentPage=${pageBean.currentPage}&rows=" + value;
    }

    var clear_btn = document.getElementById("clear_btn");
    clear_btn.onclick = function () {
        var conditionText = document.getElementsByClassName("form-control");
        for (var i = 0; i < conditionText.length; i++){
            conditionText[i].value = "";
        }
    }


</script>
</body>
</html>

