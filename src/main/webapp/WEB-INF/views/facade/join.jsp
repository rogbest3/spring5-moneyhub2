<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<div></div> <div>회원가입 화면</div> <div></div>
<div></div>
<div>
<form id ="join_form" >
<!-- playerId, teamId, playerName, ePlayerName,nickName, joinYYYY , position, backNo, nation, birthDate,solar,height, weight ;             -->
    <div id="join_div">
	    <div>아이디(사원번호):</div><div><input id="join_id" type="text" name="empno" /></div>
	    <div>비밀번호(사원이름):</div><div><input id="join_pw" type="password" name="ename" /></div>
	    <div>부서번호:</div><div><input type="text"  name="deptno"/></div>
	    <div>담당업무:</div><div><input type="text" name="job"/></div>
	    <div>매니저:</div><div><input  type="text" name="mgr" /></div>
	    <div>고용일:</div><div><input type="text" name="hiredate" /></div>
	    <div>연봉:</div><div><input type="text"  name="sal"/></div>
	    <div>커미션:</div><div><input type="text" name="comm"/></div>
        <input type="hidden"  name="action" value="join"/>
        <input type="hidden"  name="page" value=login/>
        <div></div>
        <div>
        	<input id="member_btn" type="button"  value="가입완료" style="height: 100%"/>
        </div>
    </div>
</form>
</div> <div></div>
<div></div> <div><h3><a id="back" href="#">뒤로가기</a></h3></div> <div></div>

<script>
app.init('${ctx}');
</script>