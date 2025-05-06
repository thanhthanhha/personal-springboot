$(function() {
	var contextPath = $('#contextPath').val();
	var trnsfApiUrl = $('#trnsfApiUrl').val();
	$("#acno").focus();
	$('#trnsfMenu').addClass('selected-menu');
	$("#btnSearch").on("click", function(){
		var acno = $('#acno').val();
		retrieveWthdrInfo(acno);
	});
	
	
	$("#btnTransfer").on("click", function(){
		
		var wthdrAcno = $('#acno').val();
		var custNo = $('#custNo').text();
		var rqerNm = $('#custNm').text();
		var dpstAcno = $('input[name="dpstAcno"]').val();
		var trnsfAmt = $('input[name="trnsfAmt"]').val();
		var dpstSumry = $('input[name="dpstSumry"]').val();
		var wthdrAccoSumry = $('input[name="wthdrAccoSumry"]').val();
		
		var transferData = {"wthdrAcno" : wthdrAcno,
						"custNo" : custNo,
						"dpstAcno" : dpstAcno,
						"trnsfAmt" : trnsfAmt,
						"dpstSumry" : dpstSumry,
						"wthdrAccoSumry" : wthdrAccoSumry,
						"rqerNm" : rqerNm};
		transferAcct(transferData);
	});
	

	function retrieveWthdrInfo(acno){
		var resMsg = "";
		$.ajax({
			type : 'GET',
			url : trnsfApiUrl+'/rest/v0.8/' + acno,
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
				$("td[name=d1TrnsfLimtAmt]").html(data.d1TrnsfLimtAmt);
				$("td[name=ti1TrnsfLimtAmt]").html(data.ti1TrnsfLimtAmt);
				
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
				$(location).attr("href", contextPath + "trnsf/view/transferAcct");
		    }
		});
		return resMsg;
	}
	
	function transferAcct(transferData){
		$.ajax({
			type : 'POST',
			url : trnsfApiUrl+'/rest/v0.8',
			contentType: 'application/json',
			datatype : 'json',
			data : JSON.stringify(transferData),
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
				sessionStorage.setItem('wthdrAcno', data.wthdrAcno);
				sessionStorage.setItem('dpstAcno', data.dpstAcno);
				sessionStorage.setItem('recvrNm', data.recvrNm);
				sessionStorage.setItem('trnsfAmt', data.trnsfAmt);
				sessionStorage.setItem('wthdraccoSumry', data.wthdraccoSumry);
				sessionStorage.setItem('dpstSumry', data.dpstSumry);
				$(location).attr("href", contextPath + "trnsf/view/transferAcctResult");
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	if(jqXHR.status == '417' || jqXHR.status == '500'){
			    	var responseText = jqXHR.responseText;
			    	var body = JSON.parse(responseText);
			    	alert(body.message);
		    	}
		    	else
		    		alert("[연결 오류]\n서버와 연결에 실패했습니다.");
				$(location).attr("href", contextPath + "trnsf/view/transferAcct");
		    }
		});
		
	}
});