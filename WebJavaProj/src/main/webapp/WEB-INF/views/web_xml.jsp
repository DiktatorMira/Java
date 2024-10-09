<%@ page contentType="text/html;charset=UTF-8" %>
<h1>Файл настроек <code>web.xml</code></h1>
<p>
	Файл <code>web.xml</code> позволяет настроить веб-сервер (Tomcat или другие)
	под данный проект.
	<%= request.getAttribute( "hash" )%>
</p>
<h2>Фильтры и их область действия</h2>
<p>
	Для фильтров <code>web.xml</code> особенно важен, поскольку гарантирует порядок
	выполнение фильтров (если их несколько). В области действия фильтров распространена практика
	шаблонных адресов типа <code>/*</code> или <code>/api/*</code>
</p>
<pre>
	 &lt;!-- Регистрация фильтров --&gt;
	 &lt;filter&gt;
		 &lt;filter-name&gt;charsetFilter&lt;/filter-name&gt;
		 &lt;filter-class&gt;itstep.learning.filters.CharsetFilter&lt;/filter-class&gt;
	 &lt;/filter&gt;
	 &lt;filter-mapping&gt;
		 &lt;filter-name&gt;charsetFilter&lt;/filter-name&gt;
		 &lt;url-pattern&gt;/*&lt;/url-pattern&gt;
	 &lt;/filter-mapping&gt;
</pre>
<h2>Сервлеты и маршрутизация</h2>
<p>
	До появления аннотаций типа <code>@WebServlet</code> сервлеты регистрировались
	в файле <code>web.xml</code> с указанием их маршрутов (роутинга).
</p>
<pre>
  &lt;!-- Регистрация сервлетов --&gt;
  &lt;servlet&gt;
    &lt;servlet-name&gt;webXmlServlet&lt;/servlet-name&gt;
    &lt;servlet-class&gt;itstep.learning.servlets.WebXmlServlet&lt;/servlet-class&gt;
      &lt;!-- webXmlServlet = new itstep.learning.servlets.WebXmlServlet() --&gt;
  &lt;/servlet&gt;
    &lt;!-- И их маршрутизация (mapping) --&gt;
  &lt;servlet-mapping&gt;
    &lt;servlet-name&gt;webXmlServlet&lt;/servlet-name&gt;
    &lt;url-pattern&gt;/web-xml&lt;/url-pattern&gt;
  &lt;/servlet-mapping&gt;
</pre>
<h2>Страницы ошибок</h2>
<p>
	В <code>web.xml</code> можно заложить адреса для всех типов ошибок:
	как по коду ошибки, так и по типу исключения, которое происходит при обработке.
</p>
<pre>
  &lt;error-page&gt;
    &lt;error-code&gt;404&lt;/error-code&gt;
    &lt;location&gt;/WEB-INF/views/_layout.jsp&lt;/location&gt;
  &lt;/error-page&gt;
</pre>