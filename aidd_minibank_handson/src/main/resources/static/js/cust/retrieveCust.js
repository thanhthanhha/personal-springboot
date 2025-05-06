$(function() {
	var contextPath = $('#contextPath').val();
	var custApiUrl = $('#custApiUrl').val();
	$("#custNo").focus();
	$('#custMenu').addClass('selected-menu');
	$("#btnSearch").on("click", function(){
		var custNo = $('#custNo').val();
		retrieveCust(custNo);
		/*
		if(custNo != "" && custNo != null){
			retrieveCust(custNo);
		}
		else{
			alert('고객번호를 입력해주세요.');
			$("#custNo").focus();
		}
		*/
	});
	
	$("#btnAcctCreate").on("click", function(){
		if(isValidInputValue() == true){
			var custNo = $('td[name="custNo"]').text();
			var custNm = $('td[name="custNm"]').text();
			sessionStorage.setItem('custNo', custNo);
			sessionStorage.setItem('custNm', custNm);
			$(location).attr("href", contextPath + "acct/view/registerAcct");
		}
	});
	
	$("#btnUserCntrCreate").on("click", function(){
		if(isValidInputValue() == true){
			var custNo = $('td[name="custNo"]').text();
			var custNm = $('td[name="custNm"]').text();
			sessionStorage.setItem('custNo', custNo);
			sessionStorage.setItem('custNm', custNm);
			$(location).attr("href", contextPath + "cntr/view/registerUserCntr");
		}
	});
	
	$("#btnUpdate").on("click", function(){
		if(isValidInputValue() == true){
			var custNo = $('td[name="custNo"]').text();
			var custNm = $('td[name="custNm"]').text();
			var custEnnm = $('td[name="custEnnm"]').text();
			var cpno = $('td[name="cpno"]').text();
			var emaddr = $('td[name="emaddr"]').text();
			sessionStorage.setItem('custNo', custNo);
			sessionStorage.setItem('custNm', custNm);
			sessionStorage.setItem('custEnnm', custEnnm);
			sessionStorage.setItem('cpno', cpno);
			sessionStorage.setItem('emaddr', emaddr);
			$(location).attr("href", contextPath + "cust/view/updateCust");
		}
	});
	
	$("#btnDelete").on("click", function(){
		var custNo = $('td[name="custNo"]').text();
		deleteCustomer(custNo);
	});
	
	function deleteCustomer(custNo){
		$.ajax({
			type : 'DELETE',
			url : custApiUrl+'/rest/v0.8/' + custNo,
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
				$(location).attr("href", contextPath + "cust/view/retrieveCust");
		    }
		});
		
	}
	
	function isValidInputValue(){
		var custNo = $('td[name="custNo"]').text();
		
		if(custNo == "" || custNo == null ){
			alert("고객를 조회해주세요.")
			$('#custNo').focus();
			return false;
		}
		return true;
	}

	function retrieveCust(custNo){
		var resMsg = "";
		$.ajax({
			type : 'GET',
			url : custApiUrl+'/rest/v0.8/' + custNo,
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
				$("#custNo").val(data.custNo);
				$("td[name=custNo]").html(data.custNo);
				$("td[name=custNm]").html(data.custNm);
				$("td[name=custEnnm]").html(data.custEnnm);
				$("td[name=cpno]").html(data.cpno);
				$("td[name=emaddr]").html(data.emaddr);
				
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
				$(location).attr("href", contextPath + "cust/view/retrieveCust");
		    }
		});
		return resMsg;
	}
});