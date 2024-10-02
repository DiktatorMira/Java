<%@ page contentType="text/html;charset=UTF-8"  %>
<h1>Java web. JSP</h1>
<img src="./images/java.png" alt="logo" style="height: 200px">
<p>
    JSP - Java Server Pages - технология веб-разработки с динамическим
    формированием HTML страниц. Аналогично PHP, ранних ASP являются
    надстройкой над HTML, что расширяет его добавляя
</p>
<ul>
    <li>Выражения</li>
    <li>Переменные</Li>
    <li>Алгоритмические конструкции (условия, циклы)</li>
    <li>Взаимодействие с другими файлами-страницами</li>
</ul>
<p>
    Основу JSP составляют специализированные тэги &lt;% %&gt; и &lt;%= %&gt; <br/>
    Тег &lt;% %&gt; включает в себя Java код, тег &lt;%= %&gt; выводит
    результат (является сокращенной формой оператора <code>print()</code>).
</p>
<p>
    Выражения чаще всего задаются выводящим тегом, в котором может быть
    произвольное выражение (корректное для Java). Вывод результата осуществляется
    в том месте, где находится тег: <br/>
    &lt; %= 2 + 3 %&gt; =< %= 2+3%>
</p>
<h2>Переменные</h2>
<p>
    Переменные, их объявления и назначения (без вывода результата)
    оформляется в блоке &lt;% %&gt;
    <%
        String str = "Hello, World!";
        double[] prices = { 10.0, 20.0, 30.0, 40.0 };
    %>
<pre>
    &lt;%
        String str = "Hello, World!";
        double[] prices = { 10.0, 20.0, 30.0, 40.0 };
    %&gt;
</pre>
<p>
    Вывод значений переменных - снова тег <br/>
    &lt;%= str %&gt; &rarr; <%= str %>
</p>
<h2>Алгоритмические конструкции</h2>
<pre>
    &lt;% for (int i = 0; i < prices.length; i++) { %&gt;
        &lt;i&gt; &lt;%= prices[i] %&gt;&lt;/i&gt;&amp; emsp;
    &lt;% } %&gt;
</pre>
&rarr; <% for (int i = 0; i < prices. length; i++) { %>
<i><%= prices[i] %></i>&emsp;
<% } %>
<h2>Взаимодействие с файлами</h2>
&lt;jsp:include page="WEB-INF/fragment.jsp" /&gt; &rarr;
<jsp:include page="../fragment.jsp" />
<h2>Таблица цен</h2>
<table border="1">
    <thead>
    <tr>
        <th>№</th>
        <th>Цена</th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < prices.length; i++) {  %>
        <tr>
            <td><%= i + 1 %></td>
            <td><%= prices[i] %></td>
        </tr>
    <%}%>
    </tbody>
</table>
<h1>Генерация случайных имен файлов</h1>
<p>Имя файла по умолчанию: <strong>${fileNameDefault}</strong></p>
<p>Имя файла длиной 16 символов: <strong>${fileNameCustomLength}</strong></p>