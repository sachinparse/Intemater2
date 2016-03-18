//Accepts only alphabets i.e. a-z, A-Z
$.validator.addMethod('onlyalphabet', function (value) { 
    return /^[a-zA-Z]*$/.test(value); 
}, 'This field should not contain special characters and numbers.');

//Accepts only alphabets i.e. a-z, A-Z
$.validator.addMethod('onlyalphabetwithspace', function (value) { 
    return /^[a-z A-Z]*$/.test(value); 
}, 'This field should not contain special characters and numbers.');

//Accepts only alphabets i.e. a-z, A-Z, space and numbers i.e 0-9
$.validator.addMethod('alphanumeric', function (value) { 
    return /^[a-zA-Z 0-9]*$/.test(value); 
}, 'This field should not contain special characters.');
//Accepts only alphabets i.e. a-z, A-Z, and numbers i.e 0-9
$.validator.addMethod('username', function (value) { 
    return /^[_a-zA-Z0-9]+$/.test(value); 
}, 'This field should not contain special characters except \'_\'.');

//Accepts only alphabets i.e. a-z, A-Z, and numbers i.e 0-9 and special characters _!@#$*-
$.validator.addMethod('password', function (value) { 
    return /^[a-zA-Z0-9_!@#$*-]+$/.test(value); 
}, 'This field should not contain special characters except \'_\'.');


$.validator.addMethod('aptrule', function (value) { 
    return /^[\/.a-zA-Z 0-9]*$/.test(value); 
}, 'This field should not contain special characters except . and /.');
		
$.validator.addMethod('addressrule', function (value) { 
    return /^[\/.()#"",a-zA-Z 0-9]*$/.test(value); 
}, 'This field should not contain special characters except /.()@"\,.');
	
$.validator.addMethod('flname', function (value) { 
    return /^[.a-zA-Z]*$/.test(value); 
}, 'This field should not contain special characters except / and . and numbers.');

$.validator.addMethod('foldernamerule', function (value) { 
    return /^[ .()#"",a-zA-Z 0-9]*$/.test(value); 
}, 'This field should not contain special characters except .()#",. and space');


function reset_Form(form) {
    form.find('input:text, input:password, input:file, select, textarea').val('');
    form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
}


$.validator.addMethod('username_alphanum', function (value) { 
    return /^(?![0-9]+$)[a-zA-Z 0-9_.]+$/.test(value); 
	}, 'User name should be alphabetic or Alphanumeric and may contain . and _.');
			
$.validator.addMethod('alphanum', function (value) { 
    return /^(?![a-zA-Z]+$)(?![0-9]+$)[a-zA-Z 0-9]+$/.test(value); 
	}, 'Password should be Alphanumeric.');

$.validator.addMethod('isdepartmentselect', function (value) { 
    if(value<=0 || value==null){
    	return false;
    }else{
    	return true;
    } 
	}, 'Please select Department');

//String can be a alphabetic or alphanumeric and may contain - and _ but must not start with space and end with space or allowed special characters.
$.validator.addMethod('groupname', function (value) { 
    return /^[-_a-zA-Z0-9]+[- _a-zA-Z0-9]*[a-zA-Z0-9]*$/.test(value); 
}, 'This field should not contain special characters except \_ - and space');
