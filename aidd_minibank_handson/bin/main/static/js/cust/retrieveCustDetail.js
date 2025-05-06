$(function() {
	var contextPath = $('#contextPath').val();
	var custinfoApiUrl = $('#custinfoApiUrl').val();
	$("#custNo").focus();
	$('#custMenu').addClass('selected-menu');
	$("#btnSearch").on("click", function(){
		var custNo = $('#custNo').val();
		var custData = {"custNo" : custNo};
		alert(custNo);
		retrieveCustDetail(custData);
	});

	function retrieveCustDetail(custData){
		var resMsg = "";
		alert(custData.custNo);
		
		$.ajax({
			type : 'POST',
			url : custinfoApiUrl+'/rest/v0.8',
			contentType: 'application/json',
			datatype : 'json',
			data : JSON.stringify(custData),
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
				$("#custNo").val(data.custNo);
				$("td[name=custNo]").html(data.custNo);
				$("td[name=custNm]").html(data.custNm);
				$("td[name=custEnnm]").html(data.custEnnm);
				$("td[name=cpno]").html(data.cpno);
				$("td[name=emaddr]").html(data.emaddr);
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
				$(location).attr("href", contextPath + "cust/view/retrieveCustDetail");
		    }
		});
		return resMsg;
	}
});