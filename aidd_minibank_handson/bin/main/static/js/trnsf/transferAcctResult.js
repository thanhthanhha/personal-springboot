$(function() {
	var contextPath = $('#contextPath').val();
	$("#acno").focus();
	$('#trnsfMenu').addClass('selected-menu');
	$("td[name=wthdrAcno]").html(sessionStorage.getItem('wthdrAcno'));
	$("td[name=dpstAcno]").html(sessionStorage.getItem('dpstAcno'));
	$("td[name=recvrNm]").html(sessionStorage.getItem('recvrNm'));
	$("td[name=trnsfAmt]").html(sessionStorage.getItem('trnsfAmt'));
	$("td[name=wthdraccoSumry]").html(sessionStorage.getItem('wthdraccoSumry'));
	$("td[name=dpstSumry]").html(sessionStorage.getItem('dpstSumry'));
	
	$("#btnClose").on("click", function(){
		$(location).attr("href", contextPath + "trnsf/view/transferAcct");
	});
});