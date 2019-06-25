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
            width: 300px;
            height: 100px;
        }

        .xy-center{
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .button{
            display: flex;
            /*justify-content: space-between;*/
        }

        .body-title{
            display: flex;
            flex-flow: column;
        }


    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 body">
            <div class="xy-center body-title">
                <h1><svg t="1561169734062" class="icon xy-center" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1939" data-spm-anchor-id="a313x.7781069.0.i0" width="32" height="32"><path d="M739.7 145a26.1 26.1 0 0 0 25.9-26.4c0-14.6-11.6-26.5-25.9-26.5H300.1c-14.3 0-25.8 11.9-25.8 26.5s11.5 26.4 25.8 26.4z m0 0M806.5 192.4c17.9 0 39.2 13 47.4 28.9l123.5 239.5c13.8 26.6 0.6 48.2-29.3 48.2 0 0-40.3 15.5-158.8-7.9-37.2-7.4-55.7 35.8-123.3 35.8-116.3 0-167.8-47.4-237.4-49.5-72.4-2.2-114.9 8.8-136.7 18.1-26.6 11.4-75.5 35.4-103.7 35.4-69.7 0-111.4-31.9-111.4-31.9-34.8-4.9-43.1-21.6-29.4-48.2l123.5-239.5c8.3-15.9 29.5-28.9 47.5-28.9z m-8.6 243.4a15.8 15.8 0 1 0 29.3-11.8l-63.1-158.3a15.8 15.8 0 1 0-29.3 11.8zM198.4 424a15.8 15.8 0 1 0 29.3 11.8l63.1-158.3a15.9 15.9 0 0 0-8.8-20.6 15.7 15.7 0 0 0-20.5 8.8z m440.9 9a15.8 15.8 0 1 0 30.9-6.2l-31.5-158.3a15.8 15.8 0 1 0-31 6.2z m-284-6.2a15.8 15.8 0 1 0 31 6.2l31.5-158.3a15.8 15.8 0 1 0-30.9-6.2z m141.7 3.1a15.8 15.8 0 0 0 31.6 0V271.6a15.8 15.8 0 1 0-31.6 0z m0 0" p-id="1940"></path><path d="M812.5 931.5a15.9 15.9 0 0 1-15.8 15.9H228.9a16 16 0 0 1-15.9-15.9V504.1h-63.1v427.4a79.1 79.1 0 0 0 79 79.2h567.8c43.5 0 78.9-35.5 78.9-79.2V504.1h-63.1z m0 0" p-id="1941"></path><path d="M433.9 931.1v29.4H497V461.7h-63.1z m0 0M312.2 679.4a27.1 27.1 0 1 0 27-27.1 27.1 27.1 0 0 0-27 27.1z m0 0M324.5 776.2a14.8 14.8 0 1 0 14.7-14.8 14.7 14.7 0 0 0-14.7 14.8z m0 0" p-id="1942"></path><path d="M682.8 19m17.5 0l11.5 0q17.5 0 17.5 17.5l0 79.71q0 17.5-17.5 17.5l-11.5 0q-17.5 0-17.5-17.5l0-79.71q0-17.5 17.5-17.5Z" p-id="1943"></path><path d="M594.2 47.5m17.5 0l11.5 0q17.5 0 17.5 17.5l0 46.33q0 17.5-17.5 17.5l-11.5 0q-17.5 0-17.5-17.5l0-46.33q0-17.5 17.5-17.5Z" p-id="1944"></path></svg></h1>
                <h2 class=".col-md-6 .col-md-offset-3 title xy-center">卖家后端管理系统</h2>
            </div>
            <div class="form">

                <form class="form-horizontal col-md-12" action="/sell/seller/login/vali" method="post">
                    <div class="form-group">
                        <label for="exampleInputName2">管理员</label>
                        <input type="text" class="form-control" id="exampleInputName2" placeholder="mobile" name="phone" value="${phone!''}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputName2">密码</label>
                        <input type="password" class="form-control" id="exampleInputEmail2" placeholder="password" name="password" value="${password!''}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputName2">验证码</label> <label for="exampleInputName2" class="col-md-offset-5"><a href="/sell/seller/login/create?sign='1'">Forget password ?</a></label>
                    </div>
                    <div class="form-group button">
                        <input type="text" class="form-control" id="exampleInputEmail2" placeholder="" name="code" value="${code!''}">
                        <button type="button"class="btn btn-default" onclick="phone" name="send">发送验证码</button>
                    </div>
                    <div class="xy-center form-group">
                        <input type="submit" class="form-control" value="Sign in"  style="background-color: #28A745">
                        <#--<button type="button" class="btn btn-default"><a href="/sell/seller/login/create">Registered</a></button>-->
                    </div>
                    <div class="form-group xy-center">
                        New to Sell?<a href="/sell/seller/login/create">&nbsp;Create an account.</a>
                    </div>

                </form>

            </div>

        </div>
    </div>
</div>

<script>

        $(function () {
            $(":button[name='send']").click(function () {
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