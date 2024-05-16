<html>
<head>

  <title>添加留言</title>


  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->

</head>

<body>
<s:form action="AddAction" method="post" theme="simple">
  <table border="1">
    <caption>添加留言</caption>
    <tr>
      <td>留言标题</td>
      <td>
        <s:textfield name="ly.title" />
      </td>
    </tr>
    <tr>
      <td>留言内容</td>
      <td>
        <s:textarea name="ly.content" rows="10" cols="20"></s:textarea>
      </td>
    </tr>
  </table>
  <s:submit value="添加" />
  <s:reset value="重置" />
</s:form>
</body>
</html>