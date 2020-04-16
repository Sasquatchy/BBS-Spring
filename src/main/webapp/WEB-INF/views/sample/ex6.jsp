<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirected sample 6</title>
</head>
<body>

<script >

/* ex5용 */
/* var result = '${param.result}'; */
/* ex5A용 */
var result = '${result}';
if(result === 'SUCCESS'){
	alert('success!');
}
</script>
</body>
</html>