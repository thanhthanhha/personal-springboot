$(function() {
	var contextPath = $('#contextPath').val();
	var acctApiUrl = $('#acctApiUrl').val();
	$('#acctMenu').addClass('selected-menu');
	$("td[name=acno]").html(sessionStorage.getItem('acno'));
	
	$("#btnUpdate").on("click", function(){
		if(isValidUpdateInputValue() == true){
			var acno = $('td[name="acno"]').text();
			var accoPswd = $('#accoPswd').val();
			var acctData = {"acno" : acno,
							"accoPswd" : accoPswd};
			updateAcct(acctData);
		}
	});
	
	function isValidUpdateInputValue(){
		var acno = $('td[name="acno"]').text();
		var accoPswd = $('#accoPswd').val();
		
		if(acno == "" || acno == null ){
			alert("계좌를 조회해주세요.")
			$('#acno').focus();
			return false;
		}
		if(accoPswd == "" || accoPswd == null ){
			alert("비밀번호를 입력해주세요.")
			$('#accoPswd').focus();
			return false;
		}
		return true;
	}
	
	function updateAcct(acctData){
		$.ajax({
			type : 'PUT',
			url : acctApiUrl+'/rest/v0.8',
			contentType: 'application/json',
			datatype : 'json',
			data : JSON.stringify(acctData),
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
				alert("성공적으로 수정하였습니다.");
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
});