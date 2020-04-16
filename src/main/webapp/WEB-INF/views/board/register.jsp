<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800">Board Register Page</h1>

	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Register</h6>
		</div>
		<div class="card-body">
			<form class="user" action="/board/register" method="post">
				<div class="form-group">
					<input type="text" name="title"
						class="form-control form-control-user" placeholder="Title">
				</div>
				<div class="form-group">
					<input type="text" name="content"
						class="form-control form-control-user" placeholder="content">
				</div>
				<div class="form-group">
					<input type="text" name="writer"
						class="form-control form-control-user" placeholder="writer">
				</div>
				<button class="btn btn-primary ">Submit</button>
				<button class="btn btn-secondary ">Reset</button>

				<hr>

			</form>
			<a href="/board/list" class="btn btn-primary btn-user btn-block">
				Go to List </a>

		</div>

	</div>
	<!-- /.container-fluid -->

</div>
<script>
	/*  서버에서 만들어진 문자열을 ' ' 에 둘러싸아 문자열로 클라이언트에 보내준다. 고로 ' '을 붙혀준다 */
	var flag = '${result}';
	if (flag === 'success') {
		alert("작업이 성공!");
	}
</script>

<!-- End of Main Content -->
<%@include file="../includes/footer.jsp"%>



</body>
</html>