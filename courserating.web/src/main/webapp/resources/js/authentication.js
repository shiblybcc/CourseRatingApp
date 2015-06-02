function handleRedirection(xhr,status,args){
	if(status == 'success'){
		  if(args.isRedirection){
			  window.blur();
			  var url = args.url;
			  var newWindow = window.open(url,'_blank').focus();
			  document.getElementById('form_id:hidden_link').click();
		  }  
	  }
}