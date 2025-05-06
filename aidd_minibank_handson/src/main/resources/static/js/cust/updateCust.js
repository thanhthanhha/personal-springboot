$(function() {
	var contextPath = $('#contextPath').val();
	var custApiUrl = $('#custApiUrl').val();
	$('#custMenu').addClass('selected-menu');
	
	$("td[name=custNo]").html(sessionStorage.getItem('custNo'));
	$('#custNm').val(sessionStorage.getItem('custNm'));
	$('#custEnnm').val(sessionStorage.getItem('custEnnm'));
	$('#cpno').val(sessionStorage.getItem('cpno'));
	$('#emaddr').val(sessionStorage.getItem('emaddr'));
	
	$("#btnUpdate").on("click", function(){
		var custNo = $('#custNo').text();
		var custNm = $('#custNm').val();
		var custEnnm = $("#custEnnm").val();
		var cpno = $('#cpno').val();
		var emaddr = $('#emaddr').val();
		var custData = {"custNo" : custNo,
						"custNm" : custNm,
						"custEnnm" : custEnnm,
						"cpno" : cpno,
						"emaddr" : emaddr};
		updateCust(custData);
	});
	
	function updateCust(custData){
		$.ajax({
			type : 'PUT',
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
				alert("성공적으로 수정하였습니다.");
				$(location).attr("href", contextPath + "cust/view/retrieveCust");
		    },
		    error: function (jqXHR, textStatus, errorThrown) {
		    	if(jqXHR.status == '417' || jqXHR.status == '500'){
			    	var responseText = jqXHR.responseText;
			    	var body = JSON.parse(responseText);
			    	alert(body.message);
		    	}
		    	else
		    		alert("[연결 오류]\n서버와 연결에 실패했습니다.");
				$(location).attr("href", contextPath + "cust/view/updateCust");
		    }
		});
		
	}
});