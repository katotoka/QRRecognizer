<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
	<link rel="stylesheet" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>

<body>
  <div id="wrap" class="container">
    <div id="header" class="page-header">
      <%@ include file="header.jsp"%>
    </div>

    <div id="body">
        <p>Your text is: ${ decodedText }</p>
    </div>

    <div id="footer">
      <%@ include file="footer.jsp"%>
    </div>
  </div>
</body>

</html>