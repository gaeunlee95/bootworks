/* 댓글 구현 */

let replyObject = {
	
	init: function(){
		
		$("#btn-save-reply").click(() => {
			this.insertReply();  //this(클릭한 대상) - 필수입력
		});
	},
	
	insertReply: function(){
		alert("댓글 등록 요청됨");
		
		let boardId = $("#boardId").val();
		//documnet.getElementById(replyContent).value
		let replyContent = $("#replyContent").val();
		
		if(replyContent == ""){
			alert("댓글을 입력해 주세요");
			$("#replyContent").focus();
			return false;
		}
		console.log(reply);
		
		let reply = {
			content: replyContent  //content - 컨트롤러 넘겨주는 데이터
		}
		
		//403 forbidden
		let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');
		
		$.ajax({
			type: "POST",
			url: "/reply/" + boardId,
			//403 forbidden
			beforeSend: function(xhr){
	        xhr.setRequestHeader(header, token);
	    },
			data: JSON.stringify(reply),  //자바스크립트 객체를 문자화해서 json으로 변형
			contentType: "application/json; charset=utf-8"
		}).done(function(response){
			console.log(response);
			replyContent = "";
			
			location.href = "/board/" + boardId;
		}).fail(function(error){
			alert("에러 발생: " + error);
		});
	}
}

replyObject.init();  //init() 함수 호출