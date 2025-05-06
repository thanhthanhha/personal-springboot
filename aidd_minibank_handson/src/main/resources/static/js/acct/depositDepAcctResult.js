$(function() {
	var contextPath = $('#contextPath').val();
	$("#acno").focus();
	$('#acctMenu').addClass('selected-menu');
	$("td[name=acno]").html(sessionStorage.getItem('acno'));
	$("td[name=txAmt]").html(sessionStorage.getItem('txAmt'));
	$("td[name=preAccoBal]").html(sessionStorage.getItem('preAccoBal'));
	$("td[name=accoBal]").html(sessionStorage.getItem('accoBal'));
	
	$("#btnClose").on("click", function(){
		$(location).attr("href", contextPath + "acct/view/depositAcct");
	});
});