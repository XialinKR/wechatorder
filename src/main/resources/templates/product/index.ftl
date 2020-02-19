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
                            <label>图片</label>
                            <input id="productIcon" name="productIcon" type="text" hidden="hidden" value="${(productInfo.productIcon)!''}"/>

                            <div class="">
                                <input id="input-id" type="file">
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                            </div>
                        </div>
                        <input type="hidden" value="${(productInfo.productId)!''}" name="productId">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/fileinput.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>
<script>

    $(function () {
        var initialPreview = [];
        if ('${(productInfo.productIcon)!""}' != '') {
            initialPreview = "<img class='kv-preview-data file-preview-image' src='${(productInfo.productIcon)!""}'>"
        }

        $("#input-id").fileinput({
            uploadUrl: '/sell/image/upload',
            language: 'zh',
            browseClass: "btn btn-primary btn-block",
            showCaption: false,
            showRemove: false,
            showUpload: false,
            allowedFileExtensions: [ 'jpg', 'jpeg', 'png', 'gif' ],
            maxFileSize: 1024,
            autoReplace: true,
            overwriteInitial: true,
            maxFileCount: 1,
            initialPreview: initialPreview,
            // deleteUrl: '/sell/image/delete',
        });
    });
    //上传完成设置表单内容
    $('#input-id').on('fileuploaded', function(event, data, previewId, index) {
        if (data.response.code != 0) {
            alert(data.response.msg)
            return
        }
        $('#productIcon').val(data.response.data.fileName)
    });
    // //移除按钮触发事件
    // $('#input-id').on('filecleared', function() {
    //     // var testVal = $("#del_name").val();
    //     var url=$("#productIcon").val();
    //     console.log(url);
    //     // if (testVal){
    //         $.get("/sell/image/delete",{"fileName":fileName},function (data) {
    //             if(data.code == 200){
    //                 // $("#del_name").val('');
    //                 console.log("code:"+data.code+",message:"+data.message);
    //             }else {
    //                 console.log("code:"+data.code+",message:"+data.message);
    //             }
    //         })
    //     // }
    // });
</script>
</body>

</html>