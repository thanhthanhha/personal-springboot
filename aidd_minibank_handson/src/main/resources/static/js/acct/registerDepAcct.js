$(function() {
	var contextPath = $('#contextPath').val();
	var acctApiUrl = $('#acctApiUrl').val();
	$("td[name=custNo]").html(sessionStorage.getItem('custNo'));
	$("td[name=custNm]").html(sessionStorage.getItem('custNm'));
	
	$("#btnRegister").on("click", function(){
		if(isValidInputValue() == true){
			var custNo = $('#custNo').text();
			var acno = $('#acno').val();;
			var accoPswd = $('#accoPswd').val();
			var acctData = {"acno" : acno,
							"custNo" : custNo,
							"accoBal" : 0,
							"accoPswd" : accoPswd};
			registerAcct(acctData);
		}
	});
	
	function isValidInputValue(){
		// var custNo = $('td[name="custNo"]').text(); 고객조회랑 연동시 변경해야함
		var custNo = $('#custNo').text();
		var acno = $('#acno').val();
		var accoPswd = $('#accoPswd').val();
		
		if(custNo == "" || custNo == null ){
			alert("고객번호를 입력해주세요.")
			$('#custNo').focus();
			return false;
		}
		
		if(acno == "" || acno == null ){
			alert("계좌번호를 입력해주세요.")
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
	
	function registerAcct(acctData){
		$.ajax({
			type : 'POST',
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
				alert("성공적으로 저장하였습니다.");
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