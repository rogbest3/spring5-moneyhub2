"use strict"
var adm = adm || {}
adm =(()=>{
	const WHEN_ERR = '호출하는 js 파일을 찾지 못했습니다.'
	let _, js, css, img, navi_vue_js, navi_js, brd_vue_js
	let init=()=>{
/*		sessionStorage.setItem('ctx', '/web')
		_ = sessionStorage.getItem('ctx')}	*/
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		navi_vue_js = js + '/vue/navi_vue.js'
		navi_js = js + '/cmm/navi.js'
		brd_vue_js = js + '/vue/brd_vue.js'
	}
	
	let onCreate=()=>{
		init()
		alert('js : '+ js)
		$.when(
			$.getScript(navi_vue_js),
			$.getScript(navi_js),
			$.getScript(brd_vue_js)
		)
		.done(()=>{
			setContentView()
		})
		.fail(()=>{
			alert(WHEN_ERR)
		})
	}
	
	let setContentView=()=>{
	//	$('#login_form_id').remove()
	//	$('body').empty()
	//	$('head').html(brd_vue.brd_head())
		$('body').html('<div id="navi_id"></div>')
		.addClass('text-center')
		$(navi_vue.navi()).appendTo('#navi_id')
		$('#nav_scroller_id').remove()
		navi.onCreate()
		
		$('<main id="main" role="main" class="container">'+
		'<table id="tab">'+
		'  <tr>'+
		'  </tr>'+
		'</table>'+
		'</main>')  // key값 무조건 string이기 때문에 '' 생량가능 value는 생략 불가, json이기때문에 , 로 속성 추가							
		.appendTo('body')	// body에 오버로딩
		.css({ width : '100%', height : '100%'}) 
		$('#main table')
		.css({ width : '80%', height : '80%', border :'2px solid black', margin: '0 auto', 'margin-top' : '8%' }) 
		
		
		
		$.each(
			[{ id : 'left', width : '20%'}, 
			{ id : 'right', width : '80%'}], 
			(i, j)=>{
			$('<td id="'+ j.id +'"></td>')
			.css({border: '2px solid black', width: j.width, 'vertical-align': 'top'})
			.appendTo('#tab tr')
		})

		$.each([	// name을 주고 구분
			{txt : '웹크롤링', name : 'web_crawl'},
			{txt : '고객관리', name : 'cust_mgmt'}, 
			{txt : '상품등록', name : 'item_reg'}, 
			{txt : '상품조회', name : 'item_srch'}, 
			{txt : '상품수정', name : 'item_mod'}, 
			{txt : '상품삭제', name : 'item_del'}], 
			(i, j)=>{
				$('<div name="'+ j.name +'">'+ j.txt +'</div>')
				.appendTo('#left')
				.click(function(){
			//		let that = $(this).attr('name')
					$(this).addClass('active')
					$(this).siblings().removeClass('active')
					switch($(this).attr('name')){
					case 'web_crawl' :
							webCrawl()						
						break
					case 'cust_mgmt' : 
				
						break
					case 'item_reg' :
						
						break
					case 'item_srch' :
						
						break
					case 'item_mod' :
						
						break	
					case 'item_del' :
						
						break		
					}
			})
		})
		$('#left div').css({border: '2px solid black', margin: 'auto 0', 'line-height': '50px'})
	}
	let webCrawl =()=>{
		$('#right').empty()
		$('<form id="crawl_form_id" action="">'+
			'  <select name="site" size="1" >'+	//	multiple
			'  </select>'+
			'  <br>'+
/*			'  <button id="news_btn">이동</button>'+
			'</form>'+
			'<form class="form-inline my-2 my-lg-0">'+*/
			'  <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">'+
//			'  <button id="crawl_btn" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>'+
			'</form>')
		.addClass('form-inline my-2 my-lg-0')
		.appendTo('#right')
		$('#crawl_form_id').css({padding : '0 auto', 'padding-top' : '5%'  })	//'padding-top' : '5%' 
		$('#crawl_form_id select').css({ 'margin-left' : '20%' , 'margin-right' : '1%'})
		
		$.each(['naver.com', 'daum.net', 'google.com', 'youtube.com'], (i, j)=>{
			$('<option value="'+ j +'">'+ j +'</option>')
			.appendTo('#crawl_form_id select')
		})
		
		$('<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>')
		.appendTo('#crawl_form_id')
		.click(e=>{
			e.preventDefault()		
			let arr = [$('form#crawl_form_id select[name="site"]').val(),
						$('form#crawl_form_id input[type="text"]').val()]
			
			if(	!$.fn.nullChecker(arr) ){			
		//		alert(arr[0] + ', '+ arr[1])
				$.getJSON( _	+ '/tx/crawling/' + arr[0] +'/' + arr[1], d=>{		// form 태그의 id란 뜻
					alert(d.msg)
				})
			}
		})
	}
	return {onCreate}
})()