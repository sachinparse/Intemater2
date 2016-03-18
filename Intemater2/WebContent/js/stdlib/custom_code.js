$(document).ready(function(){
//var width=new Array(468,464,224,522,595,614,145,509,104,176,307,287,304,248,715,440,440,225);
//var left_x = new Array(65,65,584,65,65,65,584,65,165,584,65,165,65,165,65,400,400,400);
  var left_x = new Array(0,65,584,65,315,65,584,65,150,584,110,190,190,150,190,65,400,400,400);                     // min =0 max=316
   var top_y = new Array(0,49,49,49,365,49,49,49,49,49,49,565,565,565,565,149,249,565,565);                         // min =0 max=365

var textboxes = $('#form1 input,#form1 select'); //gets all the textboxes         
//        images = $("img.classforimg");   //gets all the images
var divs=$('div[id^="divn"]');   //gets all the divs having id divn
var xscroll,yscroll;
var imagewidth=$('#baseimg').css('width').replace(/[^-\d\.]/g, '');
//alert(imagewidth);
var imageheight=$('#baseimg').css('height').replace(/[^-\d\.]/g, '');
//alert(imageheight);
//    images.hide(); //hide all of them except the first one
divs.hide();
	//alert(textboxes.length);
//	alert(divs.length);
    textboxes.each(function (i){
									var j = i;
							        $(this).focus(function (){
																	//images.hide(); //hide all of them except the first one
																	divs.hide(); //hide all of them except the first one
																	$("#baseid").show();
																	//alert(divs.eq(j).css('left').replace(/[^-\d\.]/g, ''));
																	//alert(divs.eq(j).css('top').replace(/[^-\d\.]/g, ''));
																	//alert(divs.eq(j).css('width').replace(/[^-\d\.]/g, ''));
																	//alert(divs.eq(j).css('height').replace(/[^-\d\.]/g, ''));
																	var ans=(divs.eq(j).css('left').replace(/[^-\d\.]/g, ''))-50;
																	if(ans>(imagewidth-$("div.test1").css('width').replace(/[^-\d\.]/g, ''))){
																		xscroll=imagewidth-$("div.test1").css('width').replace(/[^-\d\.]/g, '');
																	}
																	else
																	{
																		xscroll=ans;
																	}
																	ans=(divs.eq(j).css('top').replace(/[^-\d\.]/g, ''))-50;
																	if(ans>(imageheight-$("div.test1").css('height').replace(/[^-\d\.]/g, ''))){
																		yscroll=imageheight-$("div.test1").css('height').replace(/[^-\d\.]/g, '');
																	}
																	else
																	{
																		yscroll=ans;
																	}
																	
																	$("#div1").scrollLeft(xscroll);
																	$("#div1").scrollTop(yscroll);
																	//$("#div1").scrollLeft(left_x[j]);
																	//$("#div1").scrollTop(top_y[j]);
																	
																	
																	//images.eq(j).show();
																	divs.eq(j).show();
															 });
							   });


	 $("#form1").validate({
	rules: {
			formnum: {
				formnumrule: true
			},

			title: {
				required: true
			},
			gender: {
				required: true
			},
	
			fname: {
				specialchar: true
			}, 
			lname: {
				specialchar: true			
			},
			address: {
				specialchar: true			
			},
			apt: {
				specialchar: true
			},
			city: {
				specialchar: true
			},
			state: {
				specialchar: true
			},
			zip: {
				number: true
			},
			homeph: {
				number: true
			},
			businessph: {
				number: true
			},
			cellph: {
				number: true,
				maxlength: 10,
				minlength: 10
			},
			account_no: {
				number: true,
				maxlength: 17,
				minlength: 4
			},
			cardno: {
				maxlength: 17,
				minlength: 4			
			},
			signdate:{
				date: true
			},
			birthdate: {
				date: true
			},
			email: {
				email: true
			}

		},
	messages: {
	
		formnum: {
			formnumrule: "<br> Form Number should not contain special characters."
		},
		
		title: {
				required: "<br> Please select the Title."
		},
		gender: {
			required: "<br> Please select the Gender."
		},
	
		fname: {
					specialchar: "<br> First Name should not contain special character and numbers."
			   },
		lname: {
					specialchar: "<br> Last Name should not contain special character and numbers."
		       },
		address: {
			specialchar: "<br> WORKING or NOT check it out."			
		},
		apt: {
				specialchar: "<br> This field should not contain special character and numbers."
			 } ,
		city: {
			specialchar: "<br> City should not contain special character."
		},
		state: {
			specialchar: "<br> State should not contain special character."
		},
		zip: {
			number : "<br> Zip code should not contain special characters or alphabets."
		},
		homeph: {
			number : "<br> Phone number shold be a numner."
		},
		businessph: {
			number : "<br> Phone number shold be a numner."
		},
		cellph: {
			number : "<br> Cell phone number should be a number",
			minlength: "<br> Cell phone number should have ten digit.",
			maxlength: "<br> Cell phone number should have ten digit"
		},
		account_no: {
			number : "<br> Account number should not contain alphabets and special characters.",
			minlength: "<br> Account number should be minimum 4 digits in length.",
			maxlength: "<br> Account number should be maximum 17 digits in length."
		},
		card_no: {
			minlength: "<br> Credit Card number should be minimum 16 digits in length.",
			maxlength: "<br> Credit Card number should be maximum 16 digits in length."		
		},
		sign_date:{
			date: "<br> Please enter valid date."
		},
		birthdate :"<br> Please enter date in proper format.",
		email: "<br> Please enter a valid email address"
		},
		
		errorClass: 'style1',

		errorPlacement: function(error, element) {
			if ( element.is(":radio") )
				error.insertAfter( element.parent().next() );
			else 
				error.insertAfter( element);
		}
		
	
	});
		

	$.validator.addMethod('specialchar', function (value) { 
    return /^[a-zA-Z]*$/.test(value); 
	}, 'This field should not contain special characters and numbers.');
	
	$.validator.addMethod('formnumrule', function (value) { 
    return /^[a-zA-Z 0-9]*$/.test(value); 
	}, 'This field should not contain special characters.');
	
	$('#input_5').keyfilter(/[a-zA-Z]/);
});
