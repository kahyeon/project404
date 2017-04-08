<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section>
	<header class="major">
		<h2>회원가입</h2>
	</header>
	<form name="joinform" action="/www/join_ok.do" method="get">
		<table>
			<thead>
				<tr>
					<td>아이디</td>
					<th><input type="text" name="id" /></th>
				</tr>
				<tr>
					<td>비밀번호</td>
					<th><input type="password" name="password" /></th>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<th><input type="password" name="password2" /></th>
				</tr>
				<tr>
					<td>성명</td>
					<th><input type="text" name="name" /></th>
				</tr>
				<tr>
					<td>휴대폰번호</td>
					<th><input type="text" name="telephone" /></th>
				</tr>
				<tr>
					<td>이메일</td>
					<th><input type="text" name="email" /></th>
				</tr>
			</thead>
		</table>
		<input type="submit" value="회원가입">
	</form>
</section>