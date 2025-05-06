$(function() {
	var contextPath = $('#contextPath').val();
	var acctApiUrl = $('#acctApiUrl').val();
	$("#custNo").focus();
	$('#acctMenu').addClass('selected-menu');
	$("#btnSearch").on("click", function(){
		var custNo = $('#custNo').val();
		if(custNo != "" && custNo != null){
			var acctData = {"custNo" : custNo};
			retrieveAcctList(acctData);
		}
		else{
			alert('고객번호를 입력해주세요.');
			$("#custNo").focus();
		}
	});
	

	function retrieveAcctList(acctData){
		var resMsg = "";
		$.ajax({
			type : 'POST',
			url : acctApiUrl+'/rest/v0.8/list',
			contentType: 'application/json',
			datatype : 'json',
			data : JSON.stringify(acctData),
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
				for(var i = 0; i < data.length; i++)
					appendAccountInfo(data[i].acno, 
							data[i].accoBal);
				
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
	
	function appendAccountInfo(acno, accoBal){
		var html = "<tr style='text-align:center;'>"
				+"<td>"+acno+"</td>"
				+"<td>"+accoBal+"</td>"
				"</tr>";
		$('#acctList').append(html);
	}
});