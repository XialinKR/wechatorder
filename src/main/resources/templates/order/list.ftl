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
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>订单</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTOPage.content as orderDTOPage>
                        <tr>
                            <td>${orderDTOPage.orderId}</td>
                            <td>${orderDTOPage.buyerName}</td>
                            <td>${orderDTOPage.buyerPhone}</td>
                            <td>${orderDTOPage.buyerAddress}</td>
                            <td>${orderDTOPage.orderAmount}</td>
                            <td>${orderDTOPage.getOrderStatusEnum().message}</td>
                            <td>${orderDTOPage.getPayStatusEnum().message}</td>
                            <td>${orderDTOPage.createTime}</td>
                            <td><a href="/sell/seller/order/detail?orderId=${orderDTOPage.orderId}">详情</a></td>
                            <td>
                                <#if orderDTOPage.getOrderStatusEnum().message=="新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTOPage.orderId}">取消</a>
                                </#if>
                            </td>
                        </tr>
                    </#list>

                    </tbody>
                </table>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <#if currentPage lte 1>
                            <li class="disabled">
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if index == currentPage>
                                <li class="active"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </#if>

                    </ul>
                </nav>
            </div>
        </div>
    </div>
</body>
</html>