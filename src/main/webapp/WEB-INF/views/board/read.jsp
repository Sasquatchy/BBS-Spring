<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../includes/header.jsp"%>
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800">Read Page ${vo.bno}</h1>

	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Read</h6>
		</div>
		<div class="card-body">
			<div class="form-group">
				<input type="number" name="bno"
					class="form-control form-control-user" readonly="readonly"
					value='<c:out value="${vo.bno}"/>'>
			</div>
			<div class="form-group">
				<input type="text" name="title"
					class="form-control form-control-user" readonly="readonly"
					value='<c:out value="${vo.title }"/>'>
			</div>
			<div class="form-group">
				<input type="text" name="content"
					class="form-control form-control-user" readonly="readonly"
					value="<c:out value='${vo.content }'/>">
			</div>
			<div class="form-group">
				<input type="text" name="writer"
					class="form-control form-control-user" readonly="readonly"
					value="<c:out value='${vo.writer }'/>">
			</div>
			<button class="btn btn-danger btn-mod">Modify/Delete</button>
			<button class="btn btn-primary btn-addReply">Add Reply</button>

			<hr>

			<button class="btn btn-primary btn-user btn-block btn-list">
				Go to List</button>

		</div>
		<div class="card shadow mb-4">
			<div class="card-body">
				<ul class="replyList list-group">

				</ul>
			</div>

		</div>

	</div>

	<form id='actionForm' action="/board/modify" method="get">
		<input type="hidden" name='bno' value="${ cri.bno}"> <input
			type="hidden" name='page' value="${cri.page }"> <input
			type="hidden" name='amount' value="${cri.amount }"> <input
			type="hidden" name='type' value="${cri.type}"> <input
			type="hidden" name='keyword' value="${cri.keyword}">
	</form>
	<!-- Logout Modal-->
	<div class="modal fade" id="replyModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Reply</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="text" class="form-control mbno" name="bno"	value="${vo.bno }" readonly="readonly" /> 
					<input type="text" class="form-control mrno" name="rno"	value="" readonly="readonly" /> 					
					<input type="text" class="form-control" name="reply" value="" /> 
					<input type="text" class="form-control" name="replyer" value="" readonly="readonly" />

				</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<Button class="btn btn-primary replyBtn">Add Reply</Button>
					<Button class="btn btn-info modifyBtn hide">Modify</Button>
					<Button class="btn btn-danger deleteBtn hide">Delete</Button>
				</div>
			</div>
		</div>
	</div>


</div>

<style>
	.modal-footer .hide{
		display:none;
	}
</style>


<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>

	//replyService에 함수가 재대로 들어갔는지 확인
	console.log(replyService);
	var bno = ${vo.bno};
	
	var replyList = $(".replyList")
	
	replyList.on("click", "li", function(e){
		var rno = $(this).attr("data-rno");
		replyService.getReply(rno, function(reply){
			$(".mrno").val(reply.rno);
			$("input[name='reply']").val(reply.reply);
			$("input[name='replyer']").val(reply.replyer);
			$("#replyModal").modal("show");
			
			$(".modifyBtn").removeClass("hide");
			$(".replyBtn").addClass("hide");
			$(".deleteBtn").removeClass("hide");
			
		})
	});


	showPage();
	function showPage(){
        replyList.html("");
        replyService.getList(bno, function (arr) {
            //비 구조화 분해 문법, 구조 분해 할당 문법, 탬플릿 문법, 백틱

            var str='';
            for(var i = 0; i < arr.length;i++){
                var {rno, bno, reply, replyer, replyDate} = arr[i];
                var temp = "<li class='list-group-item' data-rno="+rno+">"+
                            "<div class='rno'>"+rno+"</div>"+
                            "<div class='reply'>"+reply+"</div>"+
                            "<div class='replyer'>"+replyer+"</div>"+
                        "</li>"; //백틱
                str += temp;
            }
            replyList.append(str)
        });
    }
	
	
	//리플 버튼을 누르면 추가할수 있는 모달이 켜진다.	
	$(".btn-addReply").on("click", function(e) {
		$("#replyModal").modal("show");
		
		$(".modifyBtn").addClass("hide");
		$(".replyBtn").removeClass("hide");
		$(".deleteBtn").addClass("hide");
		
	});

	$(".replyBtn").click(function(e) {
		let obj = {
			bno : $("input[name='bno']").val(),
			reply : $("input[name='reply']").val(),
			replyer : $("input[name='replyer']").val(),
		};

		replyService.addReply(obj, function(e) {
			alert("댓글 등록 성공");
			$("#replyModal").modal('hide');
			showPage();
		});
	});
	
	/* 뎃글 수정*/
	$('.modifyBtn').on("click", function(e){
		let obj = {
				rno : $(".mrno").val(),
				reply : $("input[name='reply']").val(),
			};

			replyService.modifyReply(obj, function(e){
				alert("댓글 수정 성공");
				$("#replyModal").modal('hide');
				showPage();
			})
	})
	
	
	/* 삭제 */
	$(".deleteBtn").on("click", function(){
		var rno =  $("input[name='rno']").val();
		replyService.removeReply(rno, function(){
		$("#replyModal").modal('hide');
		alert("지워짐");
		showPage();
		});
	});
	/*  서버에서 만들어진 문자열을 ' ' 에 둘러싸아 문자열로 클라이언트에 보내준다. 고로 ' '을 붙혀준다 */
	var flag = '${result}';
	if (flag === 'success') {
		alert("작업이 성공!");
	}
	var actionForm = $("#actionForm");

	$('.btn-mod').on("click", function(e) {
		actionForm.submit();
	});
	$('.btn-list').on("click", function(e) {
		actionForm.find("input[name=bno]").remove();
		actionForm.attr('action', "/board/list").submit();
	});
</script>

<!-- End of Main Content -->
<%@include file="../includes/footer.jsp"%>



</body>
</html>