<g:if test="${exceptionMessage}">
    <div class="alert alert-danger">
        <h4>Error!</h4>
        Something went wrong: ${exceptionMessage}
    </div>
</g:if>

<g:if test="${flash.message}">
    <div class="alert alert-dismissable alert-danger">
        <button type="button" class="close" data-dismiss="alert">×</button>
        ${flash.message}
    </div>
</g:if>