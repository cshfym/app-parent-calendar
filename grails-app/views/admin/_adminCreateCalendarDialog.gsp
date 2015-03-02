<div class="modal fade" id="createCalendarModal" tabindex="-1" role="dialog" aria-labelledby="createCalendarModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <span class="glyphicon glyphicon-remove-sign"></span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Create Calendar</h4>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <table class="table">
                        <tr>
                            <td>
                                <label for="selectUser" class="control-label">User</label>
                            </td>
                            <td>
                                <g:select id="selectUser" name="selectUser" class="form-control"
                                          from="${users}"
                                          optionKey="id"
                                          optionValue="username">
                                </g:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="calendarDescription" class="control-label">Description</label>
                            </td>
                            <td>
                                <g:textField name="calendarDescription" class="form-control"></g:textField>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="createCalendarForSelectedUser()">
                    Save
                </button>
            </div>
        </div>
    </div>
</div>