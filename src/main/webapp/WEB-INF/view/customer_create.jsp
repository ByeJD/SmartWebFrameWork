<%--
  Created by IntelliJ IDEA.
  User: lqq
  Date: 2019/5/19
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理 - 创建客户</title>
</head>
<body>
    <h1>创建客户界面</h1>
    <form id = "customer_form" enctype="multipart/form-data">
        <table>
            <tr>客户名称 : </tr>
            <td>
                <input type="text" name="name" value="${customer.name}">
            </td>
            <tr>联系人 ： </tr>
            <td>
                <input type="text" name="contact" value="${customer.contact}">
            </td>
            <tr>电话号码 ： </tr>
            <td>
                <input type="text" name="telephone" value="${customer.telephone}">
            </td>
            <tr>邮箱地址 ： </tr>
            <td>
                <input type="text" name="email" value="${customer.email}">
            </td>
            <tr>照片 ： </tr>
            <td>
                <input type="type" name="photo" value="${customer.photo}">
            </td>
        </table>
        <button type="submit">保存</button>
    </form>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js"></script>
    <script>
        $(function () {
            $('#customer_form').ajaxForm({
                type:'post',
                url:'${BASE}/customer_create',
                success: function (data) {
                    if (data) {
                        location.href = '${BASE}/customer';
                    }
                }
            });

        })
    </script>
</body>
</html>
