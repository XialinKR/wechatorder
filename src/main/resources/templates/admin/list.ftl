<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <#if admin.roleId == 0>
                    <div class="col-md-12 column">

                        <div class="btn-group" role="group" aria-label="...">
                            <a href="/sell/seller/admin/index" type="button" class="btn btn-default btn-primary">添加</a>
                        </div>
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>名字</th>
                                <th>手机号</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th>角色</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list sellerInfoPage.content as sellerInfo>
                                <tr>
                                    <td>${sellerInfo.sellerId}</td>
                                    <td>${sellerInfo.username}</td>
                                    <td>${sellerInfo.phone}</td>
                                    <td>${sellerInfo.createTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                                    <td>${sellerInfo.updateTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                                    <td>
                                        <#list roleInfoList as roleInfo>
                                            <#if (sellerInfo.roleId)?? && roleInfo.roleId==sellerInfo.roleId>
                                                ${roleInfo.name}
                                            </#if>
                                        </#list>
                                    </td>
                                    <td>
                                        <#if admin.sellerId == sellerInfo.sellerId || sellerInfo.roleId == 1>
                                            <a href="/sell/seller/admin/index?sellerId=${sellerInfo.sellerId}">修改</a>
                                        </#if>
                                    </td>
                                    <td>
                                        <#if admin.phone?index_of("15522667037")!=-1 || sellerInfo.roleId == 1>
                                            <a href="/sell/seller/admin/delete?sellerId=${sellerInfo.sellerId}">删除</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <#if currentPage lte 1>
                                <li class="disabled"><a href="#">上一页</a></li>
                            <#else>
                                <li><a href="/sell/seller/admin/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>

                            <#list 1..sellerInfoPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="active"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/admin/list?page=${index}&size=${size}">${index}</a></li>
                                </#if>
                            </#list>

                            <#if currentPage gte sellerInfoPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/sell/seller/admin/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                            </#if>
                        </ul>
                    </div>
                </#if>
                <#if admin.roleId != 0>
                    <div class="col-md-12 column admin">
                        当前用户没有权限管理
                    </div>
                </#if>
            </div>
        </div>
    </div>

</div>
</body>
<style>
    .admin{
        display: flex;
        height: 500px;
        justify-content: center;
        align-items: center;
        flex-flow: column;
        font-size: 30px;
    }
</style>
</html>