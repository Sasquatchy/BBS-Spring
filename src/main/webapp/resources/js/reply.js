var replyService = (function (){
    //자바스크립트에서 가장 많이 쓰는 클로저 패턴이다.

    var idx =0;

    //상수
    const localhost = "/replies/";

    //예도 클로저다. 클로저라 전역변수를 안쓰고
    // 내부의 idx에 접근할수 있다. idx는 계속 숨어있게 된다.
    function countUp() {
        return ++idx;
    }
    
    //수정하는 함수
    function modifyReply(obj, callback){
    	$.ajax({
    		type:"put",
    		url:localhost+"modify",
    		data:JSON.stringify(obj),
            //아래 415에러를 일으킬수도 있다.
            contentType:"application/json;charset=UTF-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
    	})
    }
    
    
    //삭제하는 함수
    function removeReply(rno, callback){
        $.ajax({
        	//지운다다는 정보와 함께 rno을 지목
        	type:"delete",
            url: localhost+rno,
            success: function(){
                if(callback){
                    callback();
                }
            }
        })
    }

    //댓글 추가하는 함수를 추가하자
    function addReply(obj, callback) {
        console.log(obj);
        $.ajax({
            type:"post",
            url: localhost+"new",
            data:JSON.stringify(obj),
            //아래 415에러를 일으킬수도 있다.
            contentType:"application/json;charset=UTF-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        })
    }

    //댓글을 서버에서 받는 함수를 추가하자
    //콜백에서 arr 배열이 나오는데 getJSON끝나고 나온거다.
    function getList(bno, callback) {
        $.getJSON(localhost+bno+"/1.json", null ,function(arr){
            if(callback){
                callback(arr);
            }
        })
    }
    
    //댓글 하나만 보기
    function getReply(rno, callback) {
        $.getJSON(localhost+"/"+rno+".json", null ,function(arr){
            if(callback){
                callback(arr);
            }
        })
    }
    //replyService안에 이런 기능이 있다고 추가한다.
    return {
    	modifyReply:modifyReply,
        countUp:countUp,
        addReply:addReply,
        getList:getList,
        getReply:getReply,
        removeReply:removeReply,
    }



})();