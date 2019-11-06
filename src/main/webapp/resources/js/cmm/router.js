"use strict"
function Session(x){	// 자바스크립트 
	sessionStorage.setItem('ctx', x);
	sessionStorage.setItem('js', x + '/resources/js');
	sessionStorage.setItem('css', x + '/resources/css');
	sessionStorage.setItem('img', x + '/resources/img');

	return{
		ctx : ()=>{ return sessionStorage.getItem('ctx');},
		js : ()=>{ return sessionStorage.getItem('js');},
		css : ()=>{ return sessionStorage.getItem('css');},
		img : ()=>{ return sessionStorage.getItem('img');}
	}
}


/*function User(x){
	sessionStorage.setItem('cid', x.cid)
	sessionStorage.setItem('pwd', x.pwd)
	sessionStorage.setItem('hubAccount', x.hubAccount)
	sessionStorage.setItem('reg', x.reg)
	return {
		cid : ()=> { return sessionStorage.getItem('cid')},
		pwd : ()=> { return sessionStorage.getItem('pwd')},
		hubAccount : ()=> { return sessionStorage.getItem('hubAccount')},
		reg : ()=> { return sessionStorage.getItem('reg')}
	}
}*/