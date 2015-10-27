<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
        <title>Телефонный справочник</title>
    </head>
    <body>
        <div align="center">
            <%request.setCharacterEncoding("utf-8");%>
            <%response.setCharacterEncoding("utf-8");%>
            <form id="myAjaxRequestForm">
                <fieldset style="width: 668px;">
                    <legend>Поиск абонентов...</legend>

                    <p>
                        <input type="radio" name="search" checked="checked" value="bylastname"/>по фамилии
                        <input type="radio" name="search" value="byphone"/>по телефону
                        <input id="lastname" type="text" name="searchtext" />
                        <input id="myButton" type="button" value="Просмотр" />
                        <a href="ContactAddPage?page=add">Создать контакт</a>
                        <a href="ContactAddPage?page=report">Создать отчет</a>
                    </p>
                </fieldset>
            </form>
            <div id="anotherSection">
                <div id="ajaxResponse"></div>
                <div id="response"></div>
                <div id="delResponse"></div>
            </div>
        </div>
    </body>
</html>
