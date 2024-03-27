
$(document).ready(function() {

	$('.deleteHouse').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let houseId = $(this).val();
		deleteHouse(houseId);

	});


	function deleteHouse(houseId) {
		if (confirm('Are you sure you want to delete the House with following ID : ' + houseId + '?')) {
			$.ajax({
				type: "DELETE",
				url: '/profile/dashboard/houses/delete/' + houseId,
				success: function(response) {
					alert(response);
					location.reload();
				},
				error: function(error) {
					alert('Error : ' + error.responseText);
				}
			})
		}
	}

	$('#updateHouse').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let houseId = $('#houseId').val();
		let json = { "id": houseId, "house": $('#house').val() };
		sendHouseData("PUT", "/profile/dashboard/houses/update/" + houseId, json, "Updating : [");

	});
	
	$('#createHouse').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		
		let json = {"house": $('#house').val() };
		sendHouseData("POST", "/profile/dashboard/houses/create", json, "Creating : [");

	});

	function sendHouseData(type, url, jsonData, successMsg) {
		$.ajax({
			type: type,
			url: url,
			data: JSON.stringify(jsonData),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				let respContent = "<div class='successAlert alert alert-success'>" + "<span class='success'>" + successMsg + "<b>" + data.house + "</b>] - Success! </span></div>";
				setTimeout(function() {
					window.location.href = "/profile/dashboard/houses"
				}, 2000);
				$("#successAlert").html(respContent).show();
			},
			error: function(xhr) {
				let respContent = "<div class='errorAlert alert alert-danger'>" +
					"<span class='error'>" + xhr.responseText + "</span></div>";
				setTimeout(function() {
					$('#saveHouse').prop('disabled', false);
					$('#updateHouse').prop('disabled', false);
					$("#errorAlert").hide();
				}, 2000);
				$("#errorAlert").html(respContent).show();
				console.log("Error updating house", xhr.responseText);
			}
		});
	}
});
