<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href='<c:url value="/static/bootstrap/css/bootstrap.min.css"/>'/>
  <!-- Font Awesome -->
  <link rel="stylesheet" href='<c:url value="/static/adminlte/css/font-awesome.min.css"/>'/>
  <!-- Ionicons -->
  <link rel="stylesheet" href='<c:url value="/static/adminlte/css/ionicons.min.css"/>'>
    <!-- DataTables -->
  <link rel="stylesheet" href='<c:url value="/datatables.net-bs/css/dataTables.bootstrap.min.css"/>'>

 
  <!-- Theme style -->
  <link rel="stylesheet" href='<c:url value="/static/adminlte/css/AdminLTE.min.css"/>'>
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href='<c:url value="/static/adminlte/css/skins/_all-skins.min.css" />' />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<title>商品查询</title>
</head>
<body>
<%--  上面是一个表单，用于 goods查询参数 --%>

 <!-- Horizontal Form -->
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">查询条件</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                
                  <label for="id" class="col-sm-1 control-label">商品id</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="id" >
                  </div>
                  
                  <label for="goodsName" class="col-sm-1 control-label">商品名称</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="goodsName" >
                  </div>
                  
                  <label for="goodsPriceBegin" class="col-sm-1 control-label">价格(min)</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="goodsPriceBegin" >
                  </div>
                  
                  <label for="goodsPriceEnd" class="col-sm-1 control-label">价格(max)</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="goodsPriceEnd" >
                  </div>
                  
                  
                </div>
                <div class="form-group">
                  
                  
                  			  <label for="spike" class="col-sm-1 control-label">是否秒杀</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="spike" >
                  </div>
                  
                  <label for="state" class="col-sm-1 control-label">状态</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="state" >
                  </div>
                  
                  <label for="minGoodsStockNo" class="col-sm-1 control-label">库存量（min)</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="minGoodsStockNo" >
                  </div>
                  
                  <label for="maxGoodsStockNo" class="col-sm-1 control-label">库存量(max)</label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="maxGoodsStockNo" >
                  </div>
                  
                </div>
               
               
                <div class="form-group">
                  <label for="onShelfTimeBegin" class="col-sm-1 control-label">上架时间（min)</label>
                  <div class="col-sm-2">
                    <input type="date" class="form-control" id="onShelfTimeBegin" >
                  </div>
                  
                  <label for="onShelfTimeEnd" class="col-sm-1 control-label">上架时间(max)</label>
                  <div class="col-sm-2">
                    <input type="date" class="form-control" id="onShelfTimeEnd" >
                  </div>
                  
                   <label for="offShelfTimeBegin" class="col-sm-1 control-label">下架时间（min)</label>
                  <div class="col-sm-2">
                    <input type="date" class="form-control" id="offShelfTimeBegin" >
                  </div>
                  
                  <label for="offShelfTimeEnd" class="col-sm-1 control-label">下架时间(max)</label>
                  <div class="col-sm-2">
                    <input type="date" class="form-control" id="offShelfTimeEnd" >
                  </div>
                </div>                               
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="button" class="btn btn-info pull-right">查询</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>



<%-- 中间是一个表格用于展示查询出来的内容，并且是分面的 --%>
<!-- 
			<div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
	                <tr>
	                  <th>Rendering engine</th>
	                  <th>Browser</th>
	                </tr>
                </thead>
          		<tr>
                  <td>Other browsers</td>
                  <td>All others</td>
                </tr>
              </table>
            </div>   -->
            <!-- /.box-body -->






<!-- jQuery 3 -->
<script src='<c:url value="/static/jquery/js/jquery.min.js" />'></script>
<!-- Bootstrap 3.3.7 -->
<script src='<c:url value="/static/bootstrap/js/bootstrap.min.js" />' ></script>
<!-- DataTables -->
<script src='<c:url value="/static/datatables.net/js/jquery.dataTables.min.js" />' ></script>

<script src='<c:url value="/static/datatables.net-bs/js/dataTables.bootstrap.min.js" />' ></script>
<!-- SlimScroll -->
<script src='<c:url value="/static/jquery-slimscroll/jquery.slimscroll.min.js" />' ></script>
<!-- FastClick -->
<script src='<c:url value="/static/fastclick/lib/fastclick.js" />' ></script>
<!-- AdminLTE App -->
<script src='<c:url value="/static/adminlte/js/adminlte.min.js" />' ></script>
<!-- AdminLTE for demo purposes -->
<script src='<c:url value="/static/adminlte/js/demo.js" />' ></script>
<!-- page script -->
<!-- 
<script type="text/javascript">
  $(function () {
   
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false,
      "serverSide": false//打开后台分页  
    })
  })
</script> -->

<script type="text/javascript">  
	
</script>  
    
</body>
</html>