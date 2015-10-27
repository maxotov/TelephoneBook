<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
        <title>Страница отчета</title>
    </head>
    <body>
        <div align="center">
        <form id="reportForm">
                <fieldset style="width: 668px;">
                    <legend>Создание отчета...</legend>

                    <p>
                        <label for="street">По улицам:</label>
                        <select id="reportStreet" name="street">
                        <c:forEach items="${streets}" var="st">
                            <option value="${st.id}">${st.name}</option>
                        </c:forEach>
                        </select>
                        <a href="index.jsp">назад</a>
                        <a id="common" href="#">общий отчет</a>
                    </p>
                </fieldset>
            </form>
            <div id="response" class="layer"></div>
            <form id="exportForm" action="Export" method="post">
                             <input type="hidden" id="tableWORD" name="tableHTML" value="" />
                             <input type="hidden" name="type" value="word" />
                             <input type="submit" onclick="gotoExcel('response', 'tableWORD');" value="экспорт(WORD)" />  
                        </form>
                </div>
    </body>
</html>
