function handleRedirection(xhr,status,args){
	if(status == 'success'){
		  if(args.isRedirection){
			  window.blur();
			  var url = document.getElementById('hidden_url').value;
			  var newWindow = window.open(url,'_blank').focus();
			  //newWindow.location= url;
			  //newWindow.focus();
			  document.getElementById('form_id:authentication_link').click();
		  }  
	  }
}