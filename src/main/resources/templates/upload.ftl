<html>
<head>
    <title>我是标题</title>
    <meta>
</head>
<body>
<input type="file" name="file" id="file">
</body>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="/jquery.uploadifive.js"></script>
<script>
$(function(){
    console.log('init  --1');
    $("#file").uploadifive({
        height        : 30,
        //swf           : '/uploadify/uploadify.swf',
        uploadScript      : '/upload',
        width         : 120
    });
})
</script>
</html>