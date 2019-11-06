<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div></div>
 <div>
 	<img style="margin: 0 auto" id="moneyhub_logo" src="${img}/mh_logo.jpg" alt="" />
</div>
<div></div>
<div></div>
 <div>
 <form id="login_form">
	<div id="login_tab" >
		<div id="login_tab_item_1" > 
			USERNAME
		</div>
		<div>
			<input id="username" class="input_text" type="text" name="empno"/>
		</div>
		<div id="login_tab_item_2">
			PASSWORD
		</div>
		<div>
			<input id="ename" class="input_text" type="text" name="ename">
		</div>
		<div id="login_tab_item_3">
			DNAME
		</div>
		<div>
			<input id="dname" class="input_text" type="text" name="dname">
		</div>
		<div>
			<input id ="join_btn" type="button" value="회원가입"/>
		</div>
		<div>
			<input id="login_btn" type="button" value="로그인" />
		</div>
		<input type="hidden" name="action" value="login" />
		<input type="hidden" name="page" value="mypage" />
			
 	</div>
 </form>
</div>
<div></div>
<div></div><div></div><div></div>
<script>
app.init('${ctx}');
</script>	