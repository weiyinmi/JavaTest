function append() {
	var a = $("#info").val();
	var li = document.createElement('li');
	li.innerText = a;
	$("#info").attr("value", "");
	$("#peoplesList").append(li);
	
	$("#peoplesList li").hover(function() {
		$("#peoplesList li").css("background-color", "white");
		$("#selected").attr("id", "");
		$(this).css("background-color", "#E9E9E4"); // The selected display this
		// color when mouse over
		$(this).attr("id", "selected"); // Setting a id for the selected
		$(".menu").css("display", "block");
	}, function() {
		/* $(".menu").css("display", "none"); */
	});

	console.log("Append: " + li.innerText); // print log in browser console
	console.log("Peoples are:" + $("#peoplesList").text());
};

function insertPeople() { // Insert a person
	var a = $("#info").val();
	var li = document.createElement('li');
	li.innerText = a;
	console.log('Select ' + $("#selected").text() + ' in insertPeople');
	$("#selected").before(li);
	/*
	 * $("#insert").click(function() { console.log('Select ' +
	 * $("#selected").text() + ' in event'); $("#selected").before(li);
	 * console.log("Insert "+li.innerText + + ' in event'); });
	 */
	$(".menu").css("display", "none");

	console.log("Insert " + li.innerText + +' in insertPeople');
	console.log("Peoples are:" + $("#peoplesList").text());
};

function deletePeople() { // Delete a person
	$("#selected").remove();
	$(".menu").css("display", "none");

	console.log($("#selected").text() + " remove!");
	console.log("Peoples are:" + $("#peoplesList").text());
};

function moveUpPeople() { // MoveUp
	$.each($("#selected"), function() {
		var onthis = $(this);
		var getUp = onthis.prev();
		$(onthis).after(getUp);
		$(".menu").css("display", "none");
	});

	console.log($("#selected").text() + " move up!");
	console.log("Peoples are:" + $("#peoplesList").text());
};

function moveDownPeople() { // MoveDown
	$.each($("#selected"), function() {
		var onthis = $(this);
		var getdown = onthis.next();
		$(getdown).after(onthis);
		$(".menu").css("display", "none");
	});

	console.log($("#selected").text() + " move down!");
	console.log("Peoples are:" + $("#peoplesList").text());
};

function RenamePeople() { // Rename for a person
	var a = $("#info").val();
	var li = document.createElement('li');
	li.innerText = a;
	$("#selected").replaceWith(li);
	$(".menu").css("display", "none");

	console.log("Rename : " + $("#selected").text());
	console.log("Peoples are:" + $("#peoplesList").text());
};

function clear() {
	/*
	 * $("#peoplesList").empty(); $(":text").val(""); $("#last").empty();
	 * $("#myMenu").hide();
	 */
	location.reload();
};

function validateStart(value) {
	if (value == "") {
		return false;
	} else {
		if (Number.isInteger(Number(value))) {
			if (Number(value) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
};

function validateInterval(value) {
	if (value == "") {
		return false;
	} else {
		if (Number.isInteger(Number(value))) {
			if (Number(value) >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
};

function setEmpty()
{
	$("#info").val("");
};

function calc() {

	// ($("#istart").val().search(/^[0-9]*[1-9][0-9]*$/) == -1) // from 1

	if (validateStart($("#istart").val())) {

		// ($("#iinterval").val().search(/^\d+$/) == -1) // from 0
		if (validateInterval($("#iinterval").val())) {

			/*
			 * var josephObj = { // Create a json object for inputs people :
			 * peopleArr, start : $("#istart").val(), interval :
			 * $("#iinterval").val() };
			 * 
			 * var joseph = JSON.stringify(josephObj);
			 */
			if ($("#peoplesList").children().text() != "") {

				var peopleArr = [];
				$("#peoplesList").each(function() { // Peoples array
					$(this).children().each(function() {
						peopleArr.push($(this).text());
					});
				});
				var circleobj = { // Create a json object for inputs
					start : $("#istart").val(),
					interval : $("#iinterval").val(),
					persons : peopleArr
				};
			} else {
				alert("Peoples cann`t be null!");
				console.log("Peoples cann`t be null!");
			}
		} else {
			alert("Interval shound be a non-negative integer !");
			console.log("Interval shound be a non-negative integer!");
		}
	} else {
		alert("Start shound be a positive integer!");
		console.log("Start shound be a positive integer!");
	}

	var josephObj = {
		circle : circleobj
	};
	var joseph = JSON.stringify(josephObj);
	console.log("The json is:" + joseph);

	$.ajax({ // Send request
		// url : "JsonServlet",
		url : "ReflectionServlet",
		type : "post",
		async : true,
		data : joseph,
		datatype : "application/json",
		contentType : "application/json",
		success : function(data1) {
			$("#last").html(
					'The last one is:<p class="text">' + data1.lastPeople
							+ '</p>');

			console.log("The last people:" + data1.lastPeople);
		}
	});

}
$(document).ready(function() {
	$("#submit").click(calc);
	$("#clear").click(clear);
});
