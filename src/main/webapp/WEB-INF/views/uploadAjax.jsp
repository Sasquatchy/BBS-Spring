<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>

#bigPicture {
	display: flex;
	width:100vw;
	heigth:auto;
	z-index:100;
	position: absolute;
	duration: 1s;
}


</style>


<h1>upload ajax page</h1>

<div>
	<input type="file" name="uploadFile" multiple>

</div>
<button id='uploadBtn'> upload </button>
<ul id="uploadResult">

</ul>
<div id="bigPicture" class='hide'>

</div>




<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	  crossorigin="anonymous"></script>

<script>
$(document).ready(function(){
	
	var uploadResult = $("#uploadResult");
	
	var bigPicture = $("#bigPicture");
	
	bigPicture.hide();
	
	uploadResult.on("click", "img", function(e){
		var originName = $(this).attr("data-origin");
		
		bigPicture.html("<img src='/viewImage?file="+originName+"'>");
		
		bigPicture.show();
		
	});
	
	$("#uploadBtn").click(function(e){
		//input중 유일하게 무조건 ReadOnly는 파일이다. 파일을 바꿀수 있다면 해킹도 가능
		var inputFile = $("input[name='uploadFile']");	
		
		console.log(inputFile);
		//파일 추가하기
		var formData = new FormData();
		var files = inputFile[0].files;
		
		//다중의 파일에 대해서 콘솔에 나온다.
		console.log(files);
		
		for(var i = 0; i < files.length; i++){
			formData.append("uploadFile", files[i]);
			
		}
		$.ajax({
			url:'/uploadAjaxAction',
			processData: false,
			contentType: false,
			data: formData,
			dataType:'json',
			type:'post',
			success: function(result){
				//success:는 ajax에 이미 있는것으로 끝나고 콜백 함수를 발동한다. result는 서버에서 결과로 받은 값.
				alert("upload...");
				console.log(result);
				
				for(var i = 0; i < result.length; i++){
					var originName = result[i].uuid+"_"+result[i].fileName;
					var thumbName = "s_"+originName;
					
					uploadResult.append("<li><img data-origin='"+originName+"' src='/viewImage?file="+thumbName+"'>'</li>");
				}//end for
			}//end success
		});//end $.ajax
	});//end #uploadBtn event
});//end document
</script>

</body>
</html>