<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Файл настроек <code>web.xml</code></h1>
<p>
    Файл <code>web.xml</code> позволяет настроить веб-сервер (Tomcat или другие)
    под данный проект.
</p>

<h2>Фильтры и их область действия</h2>
<p>
    Для фильтров <code>web.xml</code> особенно важен, поскольку гарантирует порядок
    выполнение фильтров (если их несколько). В области действия фильтров распространена практика
    шаблонных адресов типа <code>/*</code> или <code>/api/*</code>
</p>
<pre>
    &lt ;! -- Регистрация фильтров -- &gt;
    &lt; filter&gt;
    &lt; filter-name&gt; charsetFilter&lt;/filter-name&gt;
    &lt; filter-class&gt; itstep. learning. filters. CharsetFilter&lt;/filter-class&gt;
    &lt;/filter&gt;
    &lt; filter-mapping&gt;
    &lt; filter-name&gt; charsetFilter&lt;/filter-name&gt;
    &lt; url-pattern&gt; /*&lt; /url-pattern&gt;
    &lt;/filter-mapping&gt;
</pre>

<h2>Сервлеты и маршрутизация</h2>
<p>
    До появления аннотаций типа <code>@WebServlet</code> сервлеты регистрировались
    в файле <code>web.xml</code> с указанием их маршрутов (роутинга).
</p>
<pre>
    &lt;p&gt;
    Файл &lt;code&gt;web.xml&lt;/code&gt; позволяет донастроить веб-сервер (Tomcat или другие) под данный проект.
    &lt;/p&gt;
    &lt;h2&gt;Сервлеты и маршрутизация&lt;/h2&gt;
    &lt;p&gt;
    До появления аннотаций типа &lt;code&gt;@WebServlet&lt;/code&gt; сервлеты регистрировались
    в файле &lt;code&gt;web.xml&lt;/code&gt; с указанием их маршрутов (роутинга).
    &lt;/p&gt;
</pre>
<h2>Странички ошибок</h2>
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