<g:if test="${exceptionMessage}">
    <div class="alert alert-dismissible alert-danger">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <h4>Error!</h4>
        Something went wrong: ${exceptionMessage}
    </div>
</g:if>