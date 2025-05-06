$(function() {
	var custApiUrl = $('#custApiUrl').val();
	var contextPath = $('#contextPath').val();
	$('#custMenu').addClass('selected-menu');
	$("#btnCreate").on("click", function(){
		if(isValidInputValue() == true){
			var custNm = $('#custNm').val();
			var custEnnm = $("#custEnnm").val();
			var cpno = $('#cpno').val();
			var emaddr = $('#emaddr').val();
			var custData = {"custNm" : custNm,
							"custEnnm" : custEnnm,
							"cpno" : cpno,
							"emaddr" : emaddr};
			registerCust(custData);
		}
	});
	function isValidInputValue(){
		/*
		var custNm = $('#custNm').val();
		
		if(custNm == "" || custNm == null ){
			alert("고객한글명을 입력해주세요.")
			$('#custNm').focus();
			return false;
		}
		*/
		return true;
	}
	function registerCust(custData){
		$.ajax({
			type : 'POST',
			url : custApiUrl+'/rest/v0.8',
			contentType: 'application/json',
			datatype : 'json',
			data : JSON.stringify(custData),
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
				$(location).attr("href", contextPath + "cust/view/registerCust");
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	if(jqXHR.status == '417' || jqXHR.status == '500'){
			    	var responseText = jqXHR.responseText;
			    	var body = JSON.parse(responseText);
			    	alert(body.message);
		    	}
		    	else
		    		alert("[연결 오류]\n서버와 연결에 실패했습니다.");
				$(location).attr("href", contextPath + "cust/view/registerCust");
		    }
		});
		
	}

});