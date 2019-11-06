"use strict"
var navi = navi || {}
navi =(()=>{
	const WHEN_ERR = '호출하는 js 파일을 찾지 못했습니다.'
	let _, js, css, img, brd_js, navi_vue_js, auth_js, $cid, app_js
	let init=()=>{
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		brd_js = js + '/brd/brd.js'
		navi_vue_js = js + '/vue/navi_vue.js'
		auth_js = js + '/cmm/auth.js'
		app_js = js + '/app.js'
		$cid = document.cookie
//		alert('navi - js : '+ js)
	}
	let onCreate=()=>{
		init()
		$.when(
			$.getScript(app_js),
			$.getScript(brd_js)
		)
		.done(()=>{
			setContentView()
	//		alert('navi oncre - _ : ' + _)
		})
		.fail(()=>{
			alert(WHEN_ERR)
		})
	}
	
	let setContentView=()=>{
//		alert('navi set - _ : ' + _)
		$('<a>', {
			href : '#',
			text : '글쓰기'
		})
		.addClass('nav-link')
		.appendTo('#go_write')	
		.click(e=>{
			e.preventDefault()
			alert('navi w c - _ : ' + _)
			brd.write()
		})
		
		$('<a>', {
			href : '#',
			text : '로그아웃'
		})
		.addClass('nav-link')
		.appendTo('#logout')	
		.click(e=>{
			e.preventDefault()
			alert('로그아웃 클릭')
			deleteCookie()
			app.run(_)
		})
	}
	return {onCreate}
})()