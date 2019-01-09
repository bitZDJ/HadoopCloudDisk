
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>登陆</title>

    <!-- Bootstrap core CSS -->
    <link href="/statics/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="/statics/font-awesome/css/font-awesome.css" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="/statics/css/style.css" rel="stylesheet">
    <link href="/statics/css/style-responsive.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<!-- **********************************************************************************************************************************************************
MAIN CONTENT
*********************************************************************************************************************************************************** -->

<div id="login-page">
    <div class="container">

        <form class="form-login" action="signin" method="post" id="form">
            <h2 class="form-login-heading">SIGN IN NOW</h2>
            <div class="login-wrap">
                <input type="text" class="form-control" placeholder="登录名" autofocus name="username" id="username">
                <br>
                <input type="password" class="form-control" placeholder="密码" name="password" id="password">
                <br>
                <div class="centered">
                    <button class="btn btn-theme btn-block"  type="submit" id="signin_btn"><i class="fa fa-lock"></i> 登 陆</button>
                </div>
                <div><br>${error}</div>
                <hr>

                <div class="registration">
                    Don't have an account yet?<br/>
                    <a class="" href="signup">
                        创 建 账 户
                    </a>
                </div>

            </div>
        </form>

    </div>
</div>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/statics/js/jquery.js"></script>
<script src="/statics/js/bootstrap.min.js"></script>

<!--BACKSTRETCH-->
<!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
<script type="text/javascript" src="/statics/js/jquery.backstretch.min.js"></script>
<script>
    $.backstretch("/statics/img/login-bg.jpg", {speed: 500});
</script>

</body>
</html>
