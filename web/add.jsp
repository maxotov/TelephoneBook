<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Создание абонента</title>
    </head>
    <body>
        <div align="center">
            <h3 align="center">Форма создания абонента</h3>
            <div id="forForm">
            <form action="ContactAdd" method="post">
                                           <table border="0" cellpadding="2px">
                                      
                                           <tr>
                                               <td>Фамилия: </td>
                                               <td><input type="text" name="lastname" value=""/></td>
                                          </tr>
                                         <tr>
                                               <td>Имя: </td>
                                               <td><input type="text" name="firstname" value=""/></td>
                                          </tr>
                                          <tr>
                                               <td>Отечество: </td>
                                               <td><input type="text" name="fathername" value=""/></td>
                                          </tr>
                                          <tr>
                                               <td>Телефон: </td>
                                               <td><input type="text" name="phone" value="" placeholder="87774445566" /></td>
                                          </tr>
                                           <tr>
                                               <td>Улица: </td>
                                               <td>
                                                   <select name="street_id" style="width: 155px;">
                                                       <c:forEach items="${streets}" var="st">
                                                           <option value="${st.id}">${st.name}</option>
                                                       </c:forEach>
                                                   </select>
                                               </td>
                                          </tr>
                                          <tr>
                                               <td>Номер дома: </td>
                                               <td><input type="text" name="flat" value="" /></td>
                                          </tr>
                                        </table>
                                            <input type="submit" value="Добавить"/>
                                            </form>
            <p>${add}</p>
            <a href="index.jsp">назад</a>
            </div>
        </div>
    </body>
</html>
