<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="alert alert-error">
                    <h4>
                        错误!
                    </h4> <strong>${msg}</strong> <a href="${url}" class="alert-link">3秒后自动跳转</a>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    setTimeout('location.href="${url}"',3000)
</script>
</html>