
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.example.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>个人信息</title>

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

<section id="container" >
    <!-- **********************************************************************************************************************************************************
    TOP BAR CONTENT & NOTIFICATIONS
    *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
        <div class="sidebar-toggle-box">
            <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
        </div>
        <!--logo start-->
        <a href="/user_index" class="logo"><b>图书·管理·系统</b></a>
        <!--logo end-->

        <div class="top-menu">
            <ul class="nav pull-right top-menu">
                <li><a class="logout" href="/logout">退出</a></li>
            </ul>
        </div>
    </header>
    <!--header end-->

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
        <div id="sidebar"  class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">

                <p class="centered"><a href="/user_person"><img src="/statics/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
                <h5 class="centered">-${sessionScope.name}-</h5>


                <!--导航栏-->
                <li class="sub-menu">
                    <a href="javascript:;" class="active">
                        <i class="fa fa-tasks"></i>
                        <span>个人·中心</span>
                    </a>
                    <ul class="sub">
                        <li class="active"><a  href="/user_person">个人信息</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a class="active" href="javascript:;" >
                        <i class="fa fa-th"></i>
                        <span>全部文件</span>
                    </a>
                    <ul class="sub">
                        <li class="active"><a  href="/user_query">查看</a></li>
                    </ul>
                </li>

            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i> 个人·信息</h3>



                <div class="row ">
                    <div class="col-lg-12  col-md-12 col-sm-12 mb">
                        <div class="form-panel">
                            <h4 ><i class="fa fa-angle-right"></i> 基本信息 </h4>
                            <form class="form-horizontal style-form" method="post" action="/user_update">

                                   <tr>
                                         <div class="form-group">
                                                 <label class="col-lg-1 col-sm-2 control-labe1">·姓名：</label>
                                                 <input type="text" value="${user.getFullname()}" name="fullname"  class="col-lg-1 col-sm-2">
                                         </div>

                                   </tr>
                                       <div class="form-group">
                                           <label class="col-lg-1 col-sm-2 control-label">·密码：</label>
                                           <input class="col-lg-1 col-sm-2" type="text" value="${user.getPassword()}"  name="password"></td>
                                       </div>

                                   </tr>
                                   <tr>
                                       <div class="form-group">
                                           <label class="col-lg-1 col-sm-2 control-label">·联系方式：</label>
                                           <input class="col-lg-1 col-sm-2" type="text" value="${user.getPhone()}" name="phone"></label>
                                       </div>
                                   </tr>
                                   <td><input type="submit" class="btn btn-theme"  value=" 保 存 "></td>

                            </form>
                        </div>
                    </div><!-- col-lg-12-->
                </div><!-- /row -->



        </section><!--/wrapper -->
    </section><!-- /MAIN CONTENT -->

    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer ">
        <div class="text-center ">
            Copyright - © - 2019 - All Right Reserved - 筋斗云小组

        </div>
    </footer>
    <!--footer end-->
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/statics/js/jquery.js"></script>
<script src="/statics/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="/statics/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="/statics/js/jquery.scrollTo.min.js"></script>
<script src="/statics/js/jquery.nicescroll.js" type="text/javascript"></script>


<!--common script for all pages-->
<script src="/statics/js/common-scripts.js"></script>

<!--script for this page-->

<script>
    //custom select box

    $(function(){
        $('select.styled').customSelect();
    });

</script>

</body>
</html>
