<%-- 
    Document   : ValidationUserResponse
    Created on : Jun 16, 2023, 3:18:00 AM
    Author     : acer
--%>

<%@page import ="javax.servlet.http.HttpServlet"%>
<%@page import ="javax.servlet.http.HttpServletRequest"%>
<%@page import ="javax.servlet.http.HttpServletResponse"%>
<%@page import="java.util.*"%>
<%@page import="Enotes.dto.EnotesShowAllNotesdto"%>
<%
    List<EnotesShowAllNotesdto> list = (List<EnotesShowAllNotesdto>) request.getAttribute("list");
    for (int i = 0; i < list.size(); i++) {
        EnotesShowAllNotesdto obj = list.get(i);
        String title = obj.getTitle();
        String subject = obj.getSubject();
        String content = obj.getContent();
        Date d = obj.getD();
%>
<div class="card swiper-slide">
    <div class="card-content">
        <p class="name">Date: <span class="date_id"><%= d %></span></p>
        <input type="text" class="name title-input" disabled value="<%= title %>"><br>
        <input type="text" class="c_name subject-input" disabled value="<%= subject %>">
        <input type="text" class="description scrollable-container content-input" value="<%= content %>"disabled>
        <div class="div_btn">
            <button class="button div_btn1" onclick="editFun()">Edit</button>
            <button class="button div-btn2" onclick="deleteFun()">Delete</button>
            <button class="button div-btn2 div-btn3" onclick="closeAllNotesFun()">Close</button>
        </div>
    </div>
</div>
<%
    }
%>
