<!DOCTYPE html>

<html>

<head>
<link rel="stylesheet" href="/resources/bootstrap/3.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">
</head>

<body>
  <div id="wrap" class="container">
    <div id="header" class="page-header">
      <%@ include file="header.jsp"%>
    </div>

    <div id="body">
        <p>Your image is: </p>
        <img src="${ encodedImagePath }">
    </div>

    <div id="footer">
      <%@ include file="footer.jsp"%>
    </div>
  </div>
</body>

</html>