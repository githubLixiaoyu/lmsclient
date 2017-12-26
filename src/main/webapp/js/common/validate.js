utils.validate={
		tipvalidate:function(options){
			var form1 = $('#' + options.ctrlName);
			options = $.extend(true, {
				errorElement: 'span', //default input error message container
		        errorClass: 'help-block help-block-error', // default input error message class
		        focusInvalid: false, // do not focus the last invalid input
		        ignore: "",  // validate all fields including form hidden input
		        invalidHandler: function (event, validator) { //display error alert on form submit              
		            return false;
		        },
		        errorPlacement: function (error, element) { // render error placement for each input type
		            var icon = $(element).parent('.input-icon').children('i');
		            icon.removeClass('fa-check').addClass("fa-warning");  
		            icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
		        },

		        highlight: function (element) { // hightlight error inputs
		            $(element)
		                .closest('.formgroup').removeClass("has-success").addClass('has-error'); // set error class to the control group   
		        },

		        unhighlight: function (element) { // revert the change done by hightlight
		            
		        },

		        success: function (label, element) {
		            var icon = $(element).parent('.input-icon').children('i');
		            $(element).closest('.formgroup').removeClass('has-error').addClass('has-success'); // set success class to the control group
		            icon.removeClass("fa-warning").addClass("fa-check");
		        },

		        submitHandler: function (form) {
		        	if(utils.tools.isFunction(options.callback)){
		        		options.callback();
		    		}
		            return false;
		        }
			},options);
		    form1.validate(options);
		},
		blockvalidate:function(options){
			var form1 = $('#' + options.ctrlName);
			options = $.extend(true, {
				errorElement: 'span', //default input error message container
		        errorClass: 'help-inline help-block help-block-error', // default input error message class
		        focusInvalid: false, // do not focus the last invalid input
		        ignore: "",  // validate all fields including form hidden input
		       

		        invalidHandler: function (event, validator) { //display error alert on form submit              
		            return false;
		        },
		        highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.formgroup').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.formgroup').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.formgroup').removeClass('has-error'); // set success class to the control group
                },

		        submitHandler: function (form) {
		        	if(utils.tools.isFunction(options.callback)){
		        		options.callback();
		    		}
		            return false;
		        }
			},options);
		    form1.validate(options);
		}
}
		