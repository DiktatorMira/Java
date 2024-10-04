<%@ page contentType="text/html;charset=UTF-8" %>
<% String contextPath = request.getContextPath(); %>
<h1>Регистрация пользователя</h1>
<form class="card-panel grey lighten-5"
      id="signup-form"
      action="<%=contextPath%>/signup"
      enctype="multipart/form-data"
      method="post">
	<div class="row">
		<div class="input-field col s6">
			<i class="material-icons prefix">badge</i>
			<input id="user-name" name="user-name" type="text" class="validate">
			<label for="user-name">Имя</label>
		</div>
		<div class="input-field col s6">
			<i class="material-icons prefix">cake</i>
			<input id="user-birthdate" name="user-birthdate" type="date" class="validate">
			<label for="user-birthdate">День рожденияя</label>
		</div>
	</div>
	<div class="row">
		<div class="input-field col s6">
			<i class="material-icons prefix">alternate_email</i>
			<input id="user-email" name="user-email" type="email" class="validate">
			<label for="user-email">E-mail</label>
		</div>
		<div class="file-field input-field col s6">
			<div class="btn light-blue">
				<i class="material-icons">account_circle</i>
				<input type="file" name="user-avatar">
			</div>
			<div class="file-path-wrapper">
				<input class="file-path validate" type="text">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="input-field col s6">
			<i class="material-icons prefix">lock</i>
			<input id="user-password" name="user-password" type="password" class="validate">
			<label for="user-password">Пароль</label>
		</div>
		<div class="input-field col s6">
			<i class="material-icons prefix">lock_open</i>
			<input id="user-repeat" name="user-repeat" type="password" class="validate">
			<label for="user-repeat">Повтор</label>
		</div>
	</div>
	<div class="row">
		<button class="btn waves-effect waves-light  light-blue darken-2 right" type="submit">Регистрация
			<i class="material-icons right">send</i>
		</button>
	</div>
</form>
<div style="height: 40px"></div>
<h2>Разбор данных форм</h2>
<p>
	Формы передаются двумя видами представлений:
	<code>application/x-www-form-urlencoded</code> или
	<code>multipart/form-data</code>.
	Первый включает только поля (ключ=значение) и может быть как в query-параметрах,
	да и в теле пакета.
	Второй может передавать файлы и имеет более сложную структуру:
	multipart - состоящий из нескольких частей, каждая из которых - это
	самостоятельный НТТР пакет, только без статус-строки. Каждое поле формы передается
	отдельной частью, которая своими заголовками определяет что это – файл или поле.
</p>
<pre>
    POST /JavaWeb/signup HTTP/1.1\r\n
    Host: localhost:8080\r\n
    Connection: close\r\n
    Content-Type: application/x-www-form-urlencoded; charset=utf-8\r\n
    \r\n
    user-name=%D0%9F%D0%B5%D1%82%D1%80%D0%BE%D0%B2%D0%B8%D1%87&user-email=user@i.ua
    (user-name=Петрович&user-email=user@i.ua)

    POST /JavaWeb/signup HTTP/1.1
    Host: localhost:8080
    Connection: close
    Delimiter: 1234
    Content-Type: multipart/form-data; charset=utf-8

    1234--
    Content-Type: text/plain; charset=utf-8
    Content-Disposition: form-field; name=user-name

    Петрович
    1234--
    Content-Type: text/plain; charset=utf-8
    Content-Disposition: form-field; name=user-email

    user@i.ua
    1234--
    Content-Type: image/png
    Content-Disposition: attachment; filename=photo.png

    PNG1l;jnvo[im3perindb'k,
    --1234--
</pre>
<div style="height: 40px"></div>