<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../includes/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800">List Page</h1>

	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
			<select name='typeView' class="custom-select">
				<option value='' ${cri.type==""?"selected":"" }>--</option>
				<option value='T' ${cri.type=="T"?"selected":"" }>제목</option>
				<option value='C' ${cri.type=="C"?"selected":"" }>내용</option>
				<option value='W' ${cri.type=="W"?"selected":"" }>작가</option>
				<option value='TC'${cri.type=="TC"?"selected":"" }>제목+내용</option>
				<option value='TCW'${cri.type=="TCW"?"selected":"" }>제목+내용+작가</option>
			</select>
			<input type="text" name='keywordView' value="${cri.keyword }">
			<button class="btn btn-primary btn-search">Search</button>
			<!-- 여기서 name값으로 검색 값을 저장하고 jquery로 히든 값을 바꾼후 submit한다. -->
			
			<select class="opt custom-select">
				<!--  <option selected>Data per page</option> -->
				<option value="10" ${cri.amount==10?"selected":"" }>10</option>
				<option value="20" ${cri.amount==20?"selected":"" }>20</option>
				<option value="50" ${cri.amount==50?"selected":"" }>50</option>
				<option value="100" ${cri.amount==100?"selected":"" }>100</option>
			</select>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<td>bno</td>
							<td>title</td>
							<td>writer</td>
							<td>regdate</td>
						</tr>
					</thead>
					<tfoot>
						<c:forEach items="${ list}" var="vo">
							<tr>
								<td><c:out value="${vo.bno }" /></td>
								<td><a href='${vo.bno }' class="view"><c:out
											value="${vo.title }" /></a> <span>[ ${vo.replycnt} ]</span></td>
								<td><c:out value="${vo.writer }" /></td>
								<td><fmt:formatDate value="${vo.regdate}"
										pattern="yyyy/MM/dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tfoot>
				</table>
				<ul class="pagination">
					<!-- .disabled로 켜짐과 꺼짐을 만들수 있다. -->
					<li class='page-item ${pm.prev? "":"disabled"}'><a
						class="page-link" href='${pm.start-1 }'>Previous</a></li>
					<c:forEach begin="${pm.start }" end="${pm.end }" var="idx">
						<li class='page-item ${idx == pm.current? "active" : "" }'><a
							class="page-link" href='${idx }'>${idx }</a></li>
					</c:forEach>
					<li class='page-item ${pm.next? "":"disabled"}'><a
						class="page-link" href='${pm.end+1 }'>Next</a></li>
				</ul>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->
	<form id='actionForm' action="/board/list" method="get">
		<input type="hidden" name='page' value="${cri.page }">
		<!-- amount의 값은 위의 option에서 바뀐 amount값이 적용된다. -->
		<input type="hidden" name='amount' value="${cri.amount }">
		<!-- 검색을 하면 맵이 만들어져 크리테리아 맵 기준의 리스트를 만든다. -->
		<input type="hidden" name='type' value="${cri.type}">
		<!-- 액션폼은 히든으로 냄겨두자. -->	
		<input type="hidden" name='keyword' value="${cri.keyword}">
		
	</form>

</div>
<script>
	/*  서버에서 만들어진 문자열을 ' ' 에 둘러싸아 문자열로 클라이언트에 보내준다. 고로 ' '을 붙혀준다 */
	var flag = '${result}';
	if (flag === 'success') {
		alert("작업이 성공!");
	}

	/* 페이지별 나오는 데이터 정하는 이벤트 추가 select와 option*/
	/* 클링 이외에 change란 이벤트로 변화를 감지할수 있다.*/
	$('.opt').on('change', function(e) {
		var amountValue = this.value;
		/* self.location="/board/list?page=1&amount="+amountValue; */
		actionForm.find("input[name='page']").val(1);
		actionForm.find("input[name='amount']").val(amountValue);
		actionForm.submit();
	})

	var actionForm = $("#actionForm");
	/* actionForm을  submit하여 정보를 전달한다. 이동하는 a태그의 기능은 없앤다.*/
	$('.page-link').on("click", function(e) {
		e.preventDefault();
		var targetPage = $(this).attr("href");
		console.log("targetPage: " + targetPage);
		/* actionForm에 값을 추가한다. */
		actionForm.find("input[name='page']").val(targetPage);
		actionForm.submit();
	});

	$(".view").on("click",	function(e) {
						e.preventDefault();
						var targetBno = $(this).attr("href");
						console.log("targetBno: " + targetBno);
						actionForm.attr("action", "/board/read");
						actionForm.append("<input type='hidden' name='bno' value='"+targetBno+"'>");
						actionForm.submit();
					});
	
	
	$('.btn-search').on("click", function(e){
		var keyword = $("input[name='keywordView']");
		actionForm.find("input[name='page']").val(1);
		
		var keywordValue = keyword.val();
		if(keywordValue.trim().length == 0){
			alert("검색어 입력");
			return;
		}
		$("input[name='type']").val( $("select[name=typeView]").val());
		$("input[name='keyword']").val( keywordValue);
		actionForm.submit();
	})
</script>

<!-- End of Main Content -->
<%@include file="../includes/footer.jsp"%>



</body>
</html>