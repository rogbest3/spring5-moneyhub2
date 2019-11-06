"use strict"
$.prototype.nullChecker =x=>{	//	x - 배열, input 여러개
	let flag = false
	let i = 0
	for ( i in x ){
		if( x[i] === ''){
			flag = true
		}
	}
	return flag
}