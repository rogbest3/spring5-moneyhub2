"use strict"
var app = app || {};
app = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.';
    let _, js, css, img, auth_js;            
    let run =x=> $.getScript(x + '/resources/js/cmm/router.js',()=>{
    		$.extend(new Session(x));
    	//	alert('컨텍스트 : ' + $.ctx());
    		onCreate();
    })
    let init =()=>{
    	_ = $.ctx();
    	js = $.js();
    	css = $.css()
    	img = $.img()
    	auth_js = js + '/cmm/auth.js';
    //	alert('authjs 컨텍스트 값 : ' + authjs);
    }
    let onCreate =()=>{
    	init()
    					// when() - ~할때 성공하면 done(), 실패하면 fail() 실행
    	$.when(
    		$.getScript(auth_js)	//	authjs 뒤에 , 후 기능 없으면 불러오기만 함
    	)	
    	.done(()=>{	//	콜백함수
    		auth.onCreate()
    	})
    	.fail(()=>{
    		alert(WHEN_ERR)
    	})	
    	
    }
	return {run}
})();
