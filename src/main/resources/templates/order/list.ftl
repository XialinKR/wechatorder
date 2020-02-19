<!DOCTYPE html>
<html lang="en">
    <#include "../common/header.ftl">
<body>

    <div id="wrapper" class="toggled">

        <#--边栏sidebar-->
        <#include "../common/nav.ftl">

        <#--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span12">
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>订单编号</th>
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
                                <td>${orderDTOPage.createTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTOPage.orderId}">详情</a></td>
                                <td>
                                    <#if orderDTOPage.getOrderStatusEnum().message=="新下单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTOPage.orderId}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>

                    </div>
                    <div class="col-md-12 column">
                        <nav aria-label="Page navigation">
                            <ul class="pagination  pull-right">
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
                        <#if orderDTOPage.getTotalPages() lte 5>
                            <#list 1..orderDTOPage.getTotalPages() as index>
                                <#if index == currentPage>
                                <li class="active"><a href="#">${index}</a></li>
                                <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                </#if>
                            </#list>
                        <#else>
                            <#if currentPage gt 2 &&currentPage lt orderDTOPage.getTotalPages()-1>
                                <#if currentPage-2!=1><li class="disabled"><a href="#">...</a></li></#if>
                                <#list currentPage-2..currentPage+2 as index>
                                    <#if index == currentPage>
                                        <li class="active"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>
                                </#list>
                                <#if currentPage+2!=orderDTOPage.getTotalPages()><li class="disabled"><a href="#">...</a></li></#if>
                            <#else>
                                <#if currentPage==1 || currentPage==2>
                                    <#list 1..5 as index>
                                        <#if index == currentPage>
                                        <li class="active"><a href="#">${index}</a></li>
                                        <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                        </#if>
                                    </#list>
                                    <li class="disabled"><a href="#">...</a></li>
                                <#else>
                                    <li class="disabled"><a href="#">...</a></li>
                                    <#list orderDTOPage.getTotalPages()-4..orderDTOPage.getTotalPages() as index>
                                        <#if index == currentPage>
                                        <li class="active"><a href="#">${index}</a></li>
                                        <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                        </#if>
                                    </#list>
                                </#if>
                            </#if>
                        </#if>

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
        </div>

    </div>

    <#--弹窗-->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">
                        提醒
                    </h4>
                </div>
                <div class="modal-body">
                    你有新的订单
                </div>
                <div class="modal-footer">
                    <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
                </div>
            </div>
        </div>
    </div>

    <#--播放音乐-->
    <audio id="notice" loop="loop">
        <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
    </audio>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
        var websocket = null;
        if('WebSocket' in window) {
            // websocket = new WebSocket('ws://wcs.natapp1.cc/sell/webSocket');
            websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
        }else {
            alert('该浏览器不支持websocket!');
        }

        websocket.onopen = function (event) {
            console.log('建立连接');
        }

        websocket.onclose = function (event) {
            console.log('连接关闭');
        }

        websocket.onmessage = function (event) {
            console.log('收到消息:' + event.data)
            //弹窗提醒, 播放音乐
            $('#myModal').modal('show');

            document.getElementById('notice').play();
        }

        websocket.onerror = function () {
            alert('websocket通信发生错误！');
        }

        window.onbeforeunload = function () {
            websocket.close();
        }

    </script>
</body>


</html>