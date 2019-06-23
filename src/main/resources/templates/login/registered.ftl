<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <style>
        .body{
            display: flex;
            height: 500px;
            justify-content: center;
            align-items: center;
            flex-flow: column;
        }
        .form{
            width: 308px;
            height: 234px;
            /*background-color: #1a1a1a;*/
            display: flex;
            flex-flow: column;
        }

        .title{
            width: 250px;
            height: 100px;
        }

        .xy-center{
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .button{
            display: flex;
            justify-content: space-between;
        }

    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 body">
            <div>
                <h2 class=".col-md-6 .col-md-offset-3 title">
                    <#if sign=="0">
                        管理员注册
                        <#else>
                        管理员密码找回
                    </#if>
                </h2>
            </div>
            <div class="form">

                <form class="form-horizontal col-md-12"  method="post"
                      action="<#if sign=="0">
                                /sell/seller/login/registered
                                <#else>
                                /sell/seller/login/forget
                              </#if>">
                    <div class="form-group">
                        <label for="exampleInputName2">名称</label>
                        <input type="text" class="form-control" id="exampleInputName2" placeholder="username" name="username" value="${username!''}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputName2">手机号</label>
                        <input type="text" class="form-control" id="exampleInputName2" placeholder="mobile" name="phone" value="${phone!''}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputName2">密码</label>
                        <input type="password" class="form-control" id="exampleInputEmail2" placeholder="password" name="password" value="${password!''}">
                    </div>
                    <#--<#if sign!="0">-->
                        <#--<div class="form-group">-->
                            <#--<label for="exampleInputName2">再次确认密码</label>-->
                            <#--<input type="password" class="form-control" id="exampleInputEmail2" placeholder="password" name="password" value="${password!''}">-->
                        <#--</div>-->
                    <#--</#if>-->
                    <div class="form-group">
                        <label for="exampleInputName2">验证码</label>
                        <input type="text" class="form-control" id="exampleInputEmail2" placeholder="" name="code" value="${code!''}">
                    </div>
                    <div class="form-group button">
                        <button type="button"class="btn btn-default" onclick="phone">发送验证码</button>
                        <input type="submit" class="btn btn-default"
                               value="<#if sign=="0">
                                        Registered
                                        <#else>
                                        Submit
                                      </#if>">
                    </div>

                </form>

            </div>

        </div>
    </div>
</div>

<script>

    $(function () {
        $(":button").click(function () {
            var phoneValue = $(":text[name='phone']").val();
            // var passwordValue = $(":password[name='password']").val();
            // var sendData = {phone:phoneValue};
            $.post("/sell/seller/login/send",phoneValue,function (backData) {

            })
        })
    })



</script>
</body>
</html>