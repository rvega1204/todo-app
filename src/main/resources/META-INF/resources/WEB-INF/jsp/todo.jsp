<%@ include file="/WEB-INF/jsp/fragments/header.jspf" %>
  <%@ include file="/WEB-INF/jsp/fragments/nav.jspf" %>

    <div class="container">
      <h1>Enter Todo Details</h1>

      <form:form method="post" cssClass="row g-3" modelAttribute="todo">
        <fieldset class="mb-3">
          <form:label path="description" cssClass="form-label">Description</form:label>
          <form:input type="text" path="description" required="required" cssClass="form-control" style="width: 50%;" />
          <form:errors path="description" cssClass="text-warning" />
        </fieldset>
        <fieldset class="mb-3">
          <form:label path="targetDate" cssClass="form-label">Target Date</form:label>
          <form:input type="text" path="targetDate" required="required" cssClass="form-control" style="width: 20%;" />
          <form:errors path="targetDate" cssClass="text-warning" />
        </fieldset>

        <form:input type="hidden" path="done" />
        <form:input type="hidden" path="id" />
        <input type="submit" class="btn btn-success" value="Add Todo" style="width: 15%;" />
      </form:form>
    </div>
    <%@ include file="/WEB-INF/jsp/fragments/footer.jspf" %>
      <script type="text/javascript">
        $("#targetDate").datepicker({
          format: "yyyy-mm-dd",
        });
      </script>