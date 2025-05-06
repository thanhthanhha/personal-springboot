$(function() {
	var contextPath = $('#contextPath').val();
	var acctApiUrl = $('#acctApiUrl').val();
	var txApiUrl = $('#txApiUrl').val();
	$("#acno").focus();
	$('#acctMenu').addClass('selected-menu');
	$("#btnSearch").on("click", function(){
		var acno = $('#acno').val();
		retrieveAcctDetail(acno);
	});
	
	
	$("#btnDeposit").on("click", function(){
		
		var acno = $('#acno').val();
		var txAmt = $('input[name="txAmt"]').val();
		var depositData = {"acno" : acno,
						"txAmt" : txAmt};
		depositAcct(depositData);
	});
	

	function retrieveAcctDetail(acno){
		var resMsg = "";
		$.ajax({
			type : 'GET',
			url : acctApiUrl+'/rest/v0.8/detail/' + acno,
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
				$("td[name=custNo]").html(data.custNo);
				$("td[name=custNm]").html(data.custNm);
				$("td[name=accoBal]").html(data.accoBal);
				
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
				$(location).attr("href", contextPath + "acct/view/depositAcct");
		    }
		});
		return resMsg;
	}
	
	function depositAcct(depositData){
		$.ajax({
			type : 'POST',
			url : txApiUrl+'/rest/v0.8/deposit',
			contentType: 'application/json',
			datatype : 'json',
			data : JSON.stringify(depositData),
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
				sessionStorage.setItem('acno', data.acno);
				sessionStorage.setItem('txAmt', data.txAmt);
				sessionStorage.setItem('preAccoBal', data.preAccoBal);
				sessionStorage.setItem('accoBal', data.accoBal);
				$(location).attr("href", contextPath + "acct/view/depositAcctResult");
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	if(jqXHR.status == '417' || jqXHR.status == '500'){
			    	var responseText = jqXHR.responseText;
			    	var body = JSON.parse(responseText);
			    	alert(body.message);
		    	}
		    	else
		    		alert("[연결 오류]\n서버와 연결에 실패했습니다.");
				$(location).attr("href", contextPath + "acct/view/depositAcct");
		    }
		});
		
	}
});