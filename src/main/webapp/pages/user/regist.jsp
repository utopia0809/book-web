<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@ include file="/pages/common/head.jsp"%>
<%--    <!--写base标签，永远固定相对路径跳转的结果-->--%>
<%--    <base href="http://localhost:8080/book/">--%>

<%--    <link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
    <script type="text/javascript">
        // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
        // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
        // 验证确认密码：和密码相同
        // 邮箱验证：xxxxx@xxx.com
        // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
        $(function () {
            $("#username").blur(function(){
                var username = this.value;
                $.getJSON("<%=basepath%>User","action=ajaxExistsUsername&username="+username,function(data){
                    if(data.existsUsername){
                        $("span.errorMsg").text("用户名已存在!");
                    }else{
                        $("span.errorMsg").text("用户名可用");
                    };
                });
            });


            $("#code_img").click(function (){
                this.src = "${basepath}kaptcha.jpg?d=" + new Date();
            });


            $("#sub_btn").click(function () {
                //验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                var username = $("#username").val();
                var textUser = /^\w{5,12}$/;

                if (textUser.test(username)) {
                    $("span.errorMsg").text("输入的用户名合法");
                } else {
                    $("span.errorMsg").text("输入的用户名非法");
                    return false;
                }
                ;
                $("span.errorMsg").text("");

                //验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                var password = $("#password").val();
                var testKey = /^\w{5,12}$/;

                if (testKey.test(password)) {
                    $("span.errorMsg").text("输入的密码合法");
                } else {
                    $("span.errorMsg").text("输入的密码非法");
                    return false;
                }
                $("span.errorMsg").text("");

                // 验证确认密码：和密码相同
                var repwd = $("#repwd").val();
                if (repwd != password) {
                    $("span.errorMsg").text("两次输入的密码不一致");
                    return false;
                }

                // //邮箱验证：xxxxx@xxx.com
                var email = $("#email").val();
                var testEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

                if (!testEmail.test(email)) {
                    $("span.errorMsg").text("输入的邮箱非法");
                    return false;
                }

                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codetext = $.trim($("#code").val());
                if (codetext == null || codetext == "") {
                    $("span.errorMsg").text("输入的验证码有误");
                    return false;
                }
            });

        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg" >
                        ${requestScope.errormsg}
                    </span>
                </div>
                <div class="form">
                    <form action="User" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username" value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password" value="qazwsx"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd" value="qazwsx"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email" value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 90px;" id="code" value=""/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height:30px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>