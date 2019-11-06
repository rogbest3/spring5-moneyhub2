"use strict"
var auth = auth || {};
auth =(()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.';
	let _, js, css, img, auth_vue_js, brd_js, brd_vue_js, router_js, cookie_js, adm_js, app_js, navi_js, navi_vue_js
	let init =()=>{
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		auth_vue_js = js + '/vue/auth_vue.js'
		brd_js = js + '/brd/brd.js'
		router_js = js + '/cmm/router.js'
		cookie_js = js + '/cmm/cookie.js'
		adm_js = js + '/adm/adm.js'
		app_js = js + '/app.js'
		navi_js = js + '/cmm/navi.js'
		navi_vue_js = js + '/vue/navi_vue.js'
	}
	let onCreate =()=>{
		init()
		$.when(
    		$.getScript(auth_vue_js),	//	authjs 뒤에 , 후 기능 없으면 불러오기만 함
    		$.getScript(brd_js),
    		$.getScript(router_js),
    		$.getScript(cookie_js),
    		$.getScript(app_js),
    		$.getScript(adm_js),
    		$.getScript(navi_js),
    		$.getScript(navi_vue_js)
    	)	
    	.done(()=>{
			setContentView()
			$('#login_img_btn').click(e=>{
				e.preventDefault()
				loginPage()
				login()
				access()
		    		   
			})	
		}).fail(()=>{alert(WHEN_ERR)})
	}
	let setContentView =()=>{	// 첫화면
	/*	$('head')
		.html( auth_vue.login_head({ css:$.css(), img:$.img() }) )*/
		$('body').html( auth_vue.main_form({ css:$.css(), img:$.img() }) ) 
	}

	let loginPage =()=>{
		$('head')
		.html( auth_vue.login_head({ css:$.css(), img:$.img() }) )
		$('body').html('<div id="navi_id"></div>')
		.addClass('text-center')
		.append( auth_vue.login_body({ css:$.css(), img:$.img() }) )
		$(navi_vue.navi()).appendTo('#navi_id')
		$('#nav_scroller_id').remove()
		
		$('#cid').val('1')	//	input에 값 직접입력
		$('#pwd').val('1')	
	}
	let login =()=>{
		//<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		$('<button>', {
		//	type : 'submit',
			text : '로그인',
			click : e=>{
				e.preventDefault()
				$.ajax({
					url : _+'/clients/' + $('#cid').val() + '/login',
					type : 'POST',
					dataType : 'json',
					data : JSON.stringify({ 
						cid : $('#cid').val(), 
						pwd : $('#pwd').val()
					}),
					contentType : 'application/json',
					success : d =>{
						alert(d.hubAccount + '(계좌번호)님 환영합니다.')
						
						setCookie("CLIENTID", d.cid)
						alert('저장된 쿠기 : ' + getCookie("CLIENTID"))
						brd.onCreate()
						
						/*	$.when(
								$.getScript(router_js, ()=>{
									$.extend(new User(d)) })	
							).done(()=>{
								brd.onCreate({_:_, js:js, css:css, img:img})
							}).fail(()=>{
								alert(WHEN_ERR)
							})
								
							$.getScript(router_js, ()=>{
								$.extend(new User(d)) })
							.done(()=>{
								brd.onCreate()
							}).fail(()=>{alert(WHEN_ERR)})*/
						
					},
					error : e=>{
						alert('AJAX 실패')
					}
				})
			}
		})
		.addClass('btn btn-lg btn-primary btn-block')
		.appendTo('#btn_login')
		
		$('#a_go_join').click(e=>{
			e.preventDefault()
			$('head').html( auth_vue.join_head() )
			$('body').html( auth_vue.join_body() ) 
			$('#cid').keyup(()=>{
				if($('#cid').val().length > 2){
					$.ajax({
						url : _+'/clients/'+$('#cid').val()+'/exist',
						contentType : 'application/json',
						success : d => {
							if(d.msg === 'Success'){	// 중복 X
								$('#dupl_check')
								.val('사용가능한 ID 입니다')	// input 안에라 text가 아니라 val, text는 빈곳 
								.css('color','blue')
							}		
							else{
								$('#dupl_check')
								.val('이미 사용한 ID 입니다')
								.css('color','red')
							}
						}, 
						error : e =>{
							alert('existId AJAX 실패')
						}
					})
				}	
			})
			$('<button>', {
				text : '회원가입',	//	값을 주면 세터가 됨.
				href : '#',
//					type : 'submit',
				click : e=>{
					e.preventDefault();	//	form tag 무력화시킴 form은 SOAP방식이기 때문에 AJAX 안먹힘
					join()	
				}
			})
			.addClass('btn btn-primary btn-lg btn-block')
	    	.appendTo('#join_btn')	
		})
	}
	let mypage =()=>{
		let x = { css:$.css(), img:$.img() }
		$('body').html( auth_vue.mypage_form(x) ) 
		
	}
	let join =()=>{
		$.ajax({
			url : _+'/clients/',
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify({ 
				cid : $('#cid').val(), 
				pwd : $('#pwd').val(),
				hubAccount : $('#hubAccount').val(),
				reg : $('#reg').val()
				}),
			contentType : 'application/json',
			success : d => {	// sender, d가 자바에서 map, d.cid map의 키값
			//	alert('AJAX 성공 아이디 : '+ d.msg);
				if(d.msg === 'Success'){
					loginPage()
					login()
					access()
				}else{
					alert('회원가입 실패')
				}
				
			},	
			error : e => {		// receiver, 
				alert('join AJAX 실패');
			}		
		})
	}
	let access =()=>{								// alert   - 리턴 void
		$('#a_go_admin').click(()=>{
			adm.onCreate()
		/*	let ok = confirm('사원입니까?')				// confirm - boolean 리턴
			if(ok){
				let aid = prompt('아이디를 입력하시오')
			//	alert('입력한 사번 : ' + aid)
				$.ajax({
					url : _+'/admins/' + aid + '/access',
					type : 'POST',					// GET이 맞지만 비번 보안 때문에 POST 사용
					data : JSON.stringify({
						aid : aid,
						pwd : prompt('비밀번호를 입력하시오')
					}),
					dataType : 'json',
					contentType : 'application/json',
					success : d =>{
						if(d.msg === 'SUCCESS'){
							$.getScript(adm_js, ()=>{
								adm.onCreate()
							})
						}
						else{
							alert('접근권한이 없습니다. app_js : ' + app_js)			
					//		app.run()
						}
					},
					error : e =>{
						alert('ajax 실패')
					}
				})
				
			}*/
		})
	}
	return{ onCreate, join, login }	// app에서 auth.onCreate() 호출했기 때문에 return에 onCreate 사용
})();

























