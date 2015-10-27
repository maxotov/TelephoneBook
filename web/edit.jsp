<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Страница редактирования</title>
    </head>
    <body>
        <div align="center">
            <h3 align="center">Форма редактирования абонента</h3>
            <div id="forForm">
            <form action="ContactEdit" method="post">
                                           <table border="0" cellpadding="2px">
                                      <input type="hidden" name="id" value="${c.id}"/>
                                           <tr>
                                               <td>Фамилия: </td>
                                               <td><input type="text" name="lastname" value="${c.lastname}"/></td>
                                          </tr>
                                         <tr>
                                               <td>Имя: </td>
                                               <td><input type="text" name="firstname" value="${c.firstname}"/></td>
                                          </tr>
                                          <tr>
                                               <td>Отчество: </td>
                                               <td><input type="text" name="fathername" value="${c.fathername}"/></td>
                                          </tr>
                                          <tr>
                                               <td>Телефон: </td>
                                               <td><input type="text" name="phone" value="${c.phone}" /></td>
                                          </tr>
                                           <tr>
                                               <td>Улица: </td>
                                               <td>
                                                   <select name="street_id" style="width: 155px;">
                                                       <c:forEach items="${streets}" var="st">
                                                           <c:if test="${c.street_id==st.id}">
                                                               <option value="${st.id}" selected>${st.name}</option>
                                                           </c:if>
                                                           <c:if test="${c.street_id!=st.id}">
                                                               <option value="${st.id}">${st.name}</option>
                                                           </c:if>
                                                       </c:forEach>
                                                   </select>
                                               </td>
                                          </tr>
                                          <tr>
                                               <td>Номер дома: </td>
                                               <td><input type="text" name="flat" value="${c.flat}" /></td>
                                          </tr>
                                        </table>
                                            <input type="submit" value="Редактировать"/>
                                            </form>
            <p>${edit}</p>
            <a href="index.jsp">назад</a>
            </div>
        </div>
    </body>
</html>
