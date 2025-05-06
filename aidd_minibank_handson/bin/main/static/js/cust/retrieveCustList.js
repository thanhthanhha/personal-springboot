$(function() {
	var contextPath = $('#contextPath').val();
	var custApiUrl = $('#custApiUrl').val();
	$('#custMenu').addClass('selected-menu');
	
	$("#btnSearch").on("click", function(){
	    $('#custList td').remove();
		var custNm = $('#custNm').val();
		var custData = {"custNm" : custNm};
		retrieveCustomerList(custData);
	});

	function retrieveCustomerList(custData){
		var resMsg = "";
		$.ajax({
			type : 'POST',
			url : custApiUrl+'/rest/v0.8/list',
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
				for(var i = 0; i < data.length; i++)
					appendCustInfo(data[i].custNo,  
							data[i].custEnnm, 
							data[i].cpno,
							data[i].emaddr);
				
				/*
				$("td[name=oneDyTrnfLmt]").html(data.oneDyTrnfLmt);
				$("td[name=oneTmTrnfLmt]").html(data.oneTmTrnfLmt);
				
				for(var i = 0; i < data.accounts.length; i++)
					appendAccountInfo(data.accounts[i].acntNm, 
							data.accounts[i].acno, 
							data.accounts[i].newDtm,
							data.accounts[i].acntBlnc);
							*/
				
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
				$(location).attr("href", contextPath + "cust/view/retrieveCustList");
		    }
		});
		return resMsg;
	}
	
	function appendCustInfo(custNo, custEnnm, cpno, emaddr){
		var html = "<tr style='text-align:center;'>"
				+"<td>"+custNo+"</td>"
				+"<td>"+custEnnm+"</td>"
				+"<td>"+cpno+"</td>"
				+"<td>"+emaddr+"</td>"
				"</tr>";
		$('#custList').append(html);
	}
	
});