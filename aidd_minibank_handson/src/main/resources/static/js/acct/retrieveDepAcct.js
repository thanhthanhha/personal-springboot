$(function() {
	var contextPath = $('#contextPath').val();
	var acctApiUrl = $('#acctApiUrl').val();
	$("#acno").focus();
	$('#acctMenu').addClass('selected-menu');
	$("#btnSearch").on("click", function(){
		var acno = $('#acno').val();
		if(acno != "" && acno != null){
			retrieveAcct(acno);
		}
		else{
			alert('계좌번호를 입력해주세요.');
			$("#acno").focus();
		}
	});
	
	
	$("#btnUpdate").on("click", function(){
		if(isValidUpdateInputValue() == true){
			var acno = $('#acno').val();
			sessionStorage.setItem('acno', acno);
			$(location).attr("href", contextPath + "acct/view/updateAcct");
		}
		/*if(isValidUpdateInputValue() == true){
			var acno = $('td[name="acno"]').text();
			var accoPswd = $('#accoPswd').val();
			var acctData = {"acno" : acno,
							"accoPswd" : accoPswd};
			updateAcct(acctData);
		}*/
	});
	
	function isValidUpdateInputValue(){
		var acno = $('td[name="acno"]').text();
		
		if(acno == "" || acno == null ){
			alert("계좌를 조회해주세요.")
			$('#acno').focus();
			return false;
		}
		return true;
	}
	
	$("#btnDelete").on("click", function(){
		if(isValidDeleteInputValue() == true){
			var acno = $('td[name="acno"]').text();
			deleteAcct(acno);
		}
	});
	
	function isValidDeleteInputValue(){
		var acno = $('td[name="acno"]').text();
		
		if(acno == "" || acno == null ){
			alert("계좌를 조회해주세요.")
			$('#acno').focus();
			return false;
		}
		return true;
	}
	
	function deleteAcct(acno){
		$.ajax({
			type : 'DELETE',
			url : acctApiUrl+'/rest/v0.8/' + acno,
			contentType: 'application/json',
			datatype : 'json',
			xhrFields : {
				withCredentials : true
			},
			crossDmain: true,
			beforeSend : function(xhr){
		        $('.wrap-loading').removeClass('display-none');
			},
			complete:function(){
		        $('.wrap-loading').addClass('display-none');
			},
			success: function(data, textStatus, jqXHR){
				alert("성공적으로 삭제하였습니다.");
				$(location).attr("href", contextPath + "acct/view/retrieveAcct");
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	if(jqXHR.status == '417' || jqXHR.status == '500'){
			    	var responseText = jqXHR.responseText;
			    	var body = JSON.parse(responseText);
			    	alert(body.message);
		    	}
		    	else
		    		alert("[연결 오류]\n서버와 연결에 실패했습니다.");
				$(location).attr("href", contextPath + "acct/view/retrieveAcct");
		    }
		});
		
	}
	

	function retrieveAcct(acno){
		var resMsg = "";
		$.ajax({
			type : 'GET',
			url : acctApiUrl+'/rest/v0.8/' + acno,
			contentType: 'application/json',
			datatype : 'json',
			xhrFields : {
				withCredentials : true
			},
			crossDmain: true,
			beforeSend : function(){
		        $('.wrap-loading').removeClass('display-none');
			},
			complete:function(){
		        $('.wrap-loading').addClass('display-none');
			},
			success : function(data) {
				$("#acno").val(data.acno);
				$("td[name=acno]").html(data.acno);
				$("td[name=accoBal]").html(data.accoBal);
				$("#accoPswd").val(data.accoPswd);
				
			},
		    error: function (jqXHR, textStatus, errorThrown) {
		    	if(jqXHR.status == '417' || jqXHR.status == '500'){
			    	var responseText = jqXHR.responseText;
			    	var body = JSON.parse(responseText);
			    	alert(body.message);
		    	}
		    	else {
		    		alert("[연결 오류]\n서버와 연결에 실패했습니다.");
		    	}
				$(location).attr("href", contextPath + "acct/view/retrieveAcct");
		    }
		});
		return resMsg;
	}
});