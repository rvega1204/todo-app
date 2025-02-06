<%@ include file="/WEB-INF/jsp/fragments/header.jspf" %>
  <%@ include file="/WEB-INF/jsp/fragments/nav.jspf" %>
    <div class="container">
        <h1> Welcome ${name} </h1>
        <a href="list-todos">Manage</a> your todos.
    </div>
    <%@ include file="/WEB-INF/jsp/fragments/footer.jspf" %>