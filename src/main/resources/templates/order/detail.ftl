<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <table class="table table-hover table-bordered .col-md-4">
                    <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="span12">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
            <div class="span12">
                <#if orderDTO.getOrderStatusEnum().message=="新下单">
                    <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" class="btn btn-primary" type="button">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" class="btn btn-danger" type="button">取消订单</a>
                </#if>
            </div>
        </div>
    </div>
</body>
</html>