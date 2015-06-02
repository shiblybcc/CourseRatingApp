$(function(){
	$('.comment-expand').click(function(){
		   $(this).siblings('.comment-collapse').toggleClass('hide');
		});
});

$(function(){
	$('.reply-comment').click(function(){
		   $(this).siblings('.comment-area-collapse').toggleClass('hide');
		});
});

$(function() {
	$("#tabs").tabs();
});

$(function() {
	$('#tabs').removeClass('ui-widget-content');
});

$(function() {
	var that = this;
	$("#jRate_1").jRate({
		rating : 3.5,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		readOnly: true,
		onChange : function(rating) {
//			$('#jRate_value_1').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#jRate_2").jRate({
		rating : 3,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		readOnly: true,
		onChange : function(rating) {
//			$('#jRate_value_2').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#jRate_3").jRate({
		rating : 2.3,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		readOnly: true,
		onChange : function(rating) {
//			$('#jRate_value_3').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#jRate_4").jRate({
		rating : 1.8,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		readOnly: true,
		onChange : function(rating) {
//			$('#jRate_value_4').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#jRate_5").jRate({
		rating : 2.6,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		readOnly: true,
		onChange : function(rating) {
//			$('#jRate_value_5').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#rating_cat_1").jRate({
		rating : 1,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		onChange : function(rating) {
			$('#rating_value_1').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#rating_cat_2").jRate({
		rating : 1,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		onChange : function(rating) {
			$('#rating_value_2').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#rating_cat_3").jRate({
		rating : 1,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		onChange : function(rating) {
			$('#rating_value_3').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#rating_cat_4").jRate({
		rating : 1,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		onChange : function(rating) {
			$('#rating_value_4').text("Your Rating: " + rating);
		}
	});

});

$(function() {
	var that = this;
	$("#rating_cat_5").jRate({
		rating : 1,
		strokeColor : 'black',
		width : 60,
		height : 35,
		precision : 0.1,
		minSelected : 1,
		onChange : function(rating) {
			$('#rating_value_5').text("Your Rating: " + rating);
		}
	});

});
