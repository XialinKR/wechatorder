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
                <div class="span12 col-xs-4">
                    <form method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>商品名称</label>
                            <input type="text" class="form-control" placeholder="请输入" value="${(productInfo.productName)!''}" name="productName" >
                        </div>
                        <div class="form-group">
                            <label>单价</label>
                            <input type="text" class="form-control" placeholder="请输入" value="${(productInfo.productPrice)!''}" name="productPrice">
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input type="text" class="form-control" placeholder="请输入" value="${(productInfo.productStock)!''}" name="productStock">
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#if !(productInfo.categoryType)??>
                                    <option>请选择</option>
                                </#if>
                                <#list productCategories as category>
                                    <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)?? && category.categoryType==productInfo.categoryType>
                                            selected
                                        </#if>
                                    >
                                        ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input type="text" class="form-control" placeholder="请输入" value="${(productInfo.productDescription)!''}" name="productDescription">
                        </div>
                        <div class="form-group">
                            <label>图片</label><br>
                            <img src="${(productInfo.productIcon)!''}" style="background-position: center" height="100px" width="100px" alt="">
                            <input type="text" class="form-control" placeholder="请输入" value="${(productInfo.productIcon)!''}" name="productIcon">
                        </div>
                        <input type="hidden" value="${(productInfo.productId)!''}" name="productId">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>