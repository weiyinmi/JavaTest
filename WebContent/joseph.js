function calc() {

	if ($("#ipeoples").val() == "" || $("#istart").val() == ""
			|| $("#iinterval").val() == "") {
		alert("The input cann`t be null!");
		return false;
	} else if ($("#istart").val().search(/^[0-9]*[1-9][0-9]*$/) == -1) { // from 1
																			
		alert("Start shound be a number!");
	} else if ($("#iinterval").val().search(/^\d+$/) == -1) { // from 0
		alert("Interval shound be a number!");
	} else {
		
		var josephObj = {
			people : $("#ipeoples").val(),
			start : $("#istart").val(),
			interval : $("#iinterval").val()
		};

		var joseph = JSON.stringify(josephObj);

		$.ajax({
			url : "JsonServlet",
			type : "post",
			async : true,
			data : joseph,
			datatype : "application/json",
			contentType : "application/json",
			success : function(data1) {
				$("#last").html('The last one is:<p class="text">' + data1.lastPeople +'</p>');
			}
		});
	}
}
$(document).ready(function(){
	$("button").click(calc);
});
