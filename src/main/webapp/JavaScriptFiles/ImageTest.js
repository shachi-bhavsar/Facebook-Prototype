$(document).ready(function() {
	//alert("Inside Document.ready");
	//setTimeout(function (){alert("Inside Document.ready");}, 3*1000);
	var imgurl;
	var name;
	var mobile;
	var serverLocation = "images/";
	$.ajax({
		url : 'http://localhost:8080/facebook_v01/webapi/imageTest/getUserDetails',
		type : 'GET',
		dataType: 'json',
		async : false,
		success: function(data){
			imgurl = data[0]['imgurl'];
			mobile = data[0]['mobile'];
			name = data[0]['name'];
		}
	});
	document.getElementById("sampleImage").src = serverLocation + imgurl;
	document.getElementById("name").value = name;
	document.getElementById("mobile").value = mobile;
});

function changeModalImage(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#modalImage').attr('src', e.target.result);			
		};
		reader.readAsDataURL(input.files[0]);
	}
}

function changeImage() {
	var fd;
	var userid = document.getElementById("mobile").value;
	fd = new FormData($('#modalForm')[0]);
	if (fd) {
		$.ajax({
					type : 'POST',
					url : 'http://localhost:8080/facebook_v01/webapi/imageTest/pictureUpload',
					data : fd,
					enctype : 'multipart/form-data',
					async : false,
					processData : false, // Important!
					contentType : false,
					cache : false,
					complete : function(request, textStatus, errorThrown) {
					}
				});
	} 
	else
		alert("empty fd");
	wait(6000);
	$('#picturemodal').modal('toggle');
}

function wait(ms){
	alert("Please Wait. The Image is being uploaded. You will be automatically redirected.");
	var start = new Date().getTime();
	var end = start;
	while(end < start + ms) {
		end = new Date().getTime();
	}
}
