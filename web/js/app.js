$(document).ready(function() {
    $("#anotherSection").hide(200);
    $("#myAjaxRequestForm").submit(function(e) {
        e.preventDefault();
    });

    $("#myButton").click(function(e) {
        $("#anotherSection").show(200);

        var text = $("input#lastname").val();
        var search = $("input[type=radio]:checked").val();

        $.ajax({
            type: "POST",
            url: "ContactSearch",
            data: {
                search: search,
                text: text
            },
            dataType: "json",
            success: function(data) {
                if (data.success) {
                    $("#ajaxResponse").html("");
                    var htmlContent = "<br/><table border=1 class='main_table'><tr><td>Фамилия</td><td>Имя</td><td>Отечество</td><td>Телефон</td><td>Улица</td><td>Дом</td><td></td><td></td></tr>";
                    for (var i = 0; i < data.contactInfo.length; i++) {
                        htmlContent += "<tr id='" + data.contactInfo[i].id + "'><td>" + data.contactInfo[i].lastname + "</td>\n\
                                                          <td>" + data.contactInfo[i].firstname + "</td>\n\
                                                          <td>" + data.contactInfo[i].fathername + "</td>\n\
                                                          <td>" + data.contactInfo[i].phone + "</td>\n\
                                                          <td>" + data.contactInfo[i].street_name + "</td>\n\
                                                          <td>" + data.contactInfo[i].flat + "</td>\n\
                                                          <td><a href='ShowContact?id=" + data.contactInfo[i].id + "'>Редактр</a></td>\n\
                                                          <td><input type='button' size='30' value='Удалить' onClick='deleteContact(" + data.contactInfo[i].id + ")'/></td></tr>";

                    }
                    htmlContent += "</table>";


                    $("#ajaxResponse").html(htmlContent);
                }

                else {
                    $("#ajaxResponse").html("<div><b>Lastname in Invalid!</b></div>");

                }
            }

        });
    });
    $("#response").hide(200);
    $("#exportForm").hide(200);
    $("#reportForm").submit(function(e) {
        e.preventDefault();
    });

    $("#reportStreet").change(function(e) {
        $("#response").show(200);
        $("#exportForm").show(200);
        var id = $("select#reportStreet").val();

        $.ajax({
            type: "POST",
            url: "ContactReport",
            data: {
                id: id
            },
            dataType: "json",
            success: function(data) {
                if (data.success) {
                    $("#response").html("");
                    var htmlContent = "<table border=1 class='main_table'><tr><td colspan=6>улица " + data.contactInfo[0].street_name + ": " + data.count + " абонент</td></tr><tr><td>Фамилия</td><td>Имя</td><td>Отечество</td><td>Телефон</td><td>Улица</td><td>Дом</td></tr>";
                    for (var i = 0; i < data.contactInfo.length; i++) {
                        htmlContent += "<tr id='" + data.contactInfo[i].id + "'><td>" + data.contactInfo[i].lastname + "</td>\n\
                                                          <td>" + data.contactInfo[i].firstname + "</td>\n\
                                                          <td>" + data.contactInfo[i].fathername + "</td>\n\
                                                          <td>" + data.contactInfo[i].phone + "</td>\n\
                                                          <td>" + data.contactInfo[i].street_name + "</td>\n\
                                                          <td>" + data.contactInfo[i].flat + "</td>\n\</tr>";

                    }
                    htmlContent += "</table>";


                    $("#response").html(htmlContent);
                }

                else {
                    $("#response").html("<div><b>Street in Invalid!</b></div>");
                    $("#exportForm").hide(200);
                }
            }

        });
    });

    $("#common").click(function(e) {
        $("#response").show(200);
        $("#exportForm").show(200);

        $.ajax({
            type: "POST",
            url: "CommonReport",
            dataType: "json",
            success: function(data) {
                if (data.success) {
                    $("#response").html("");
                    var htmlContent = "<table border=1 class='main_table'><tr><td>Фамилия</td><td>Имя</td><td>Отечество</td><td>Телефон</td><td>Улица</td><td>Дом</td></tr>";
                    for (var i = 0; i < data.contactInfo.length; i++) {
                        htmlContent += "<tr><td colspan=6><strong>улица " + data.contactInfo[i].name + ": " + data.contactInfo[i].contacts.length + " абонент</strong></td></tr>";
                        for (var j = 0; j < data.contactInfo[i].contacts.length; j++) {
                            htmlContent += "<tr><td>" + data.contactInfo[i].contacts[j].lastname + "</td>\n\
                                                          <td>" + data.contactInfo[i].contacts[j].firstname + "</td>\n\
                                                          <td>" + data.contactInfo[i].contacts[j].fathername + "</td>\n\
                                                          <td>" + data.contactInfo[i].contacts[j].phone + "</td>\n\
                                                          <td>" + data.contactInfo[i].contacts[j].street_name + "</td>\n\
                                                          <td>" + data.contactInfo[i].contacts[j].flat + "</td>\n\</tr>";
                        }


                    }
                    htmlContent += "</table>";


                    $("#response").html(htmlContent);
                }

                else {
                    $("#response").html("<div><b>Street in Invalid!</b></div>");
                    $("#exportForm").hide(200);
                }
            }

        });
    });

});

function deleteContact(el) {
    $.ajax({
        type: "POST",
        url: "ContactDelete",
        data: "id=" + el,
        dataType: "json",
        success: function(data) {
            if (data.success) {
                $("#delResponse").html("<div></div>");
            }
            else {
                $("#delResponse").html("<div><b>Incorrect!</b></div>");
            }
        }
    });
    $("#" + el + "").remove();
}

function gotoExcel(elemId, frmFldId)
{
    var obj = document.getElementById(elemId);
    var oFld = document.getElementById(frmFldId);
    oFld.value = obj.innerHTML;

}