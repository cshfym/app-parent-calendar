<div class="modal fade" id="createCalendarEventModal" tabindex="-1" role="dialog" aria-labelledby="createCalendarEventModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <span class="glyphicon glyphicon-remove-sign"></span>
                </button>
                <h4 class="modal-title" id="lblCreateEvent">Create Event</h4>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <table>
                        <tr>
                            <td>
                                <label class="col-lg-2 control-label">From</label>
                            </td>
                            <td>
                                <div class="input-group">
                                    <label id="fromDate" name="fromDate" class="form-control"></label>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">
                                            <span class="glyphicon glyphicon-calendar" style="font-size: 12px; color: #008CBA;"></span>
                                        </button>
                                    </span>
                                </div>
                            </td>
                            <td>&nbsp;</td>
                            <td>
                                <g:select class="form-control" id="fromTime" name="fromTime"
                                          from="${pageModel.eventTimeIntervals}" style="width: 125;"
                                          value="${pageModel.nextEventStartTime}">
                                </g:select>
                            </td>
                        </tr>
                        <tr><td colspan="4">&nbsp;</td></tr>
                        <tr>
                            <td>
                                <label class="col-lg-2 control-label">To</label>
                            </td>
                            <td>
                                <div class="input-group">
                                    <label id="toDate" name="toDate" class="form-control"></label>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">
                                            <span class="glyphicon glyphicon-calendar" style="font-size: 12px; color: #008CBA;"></span>
                                        </button>
                                    </span>
                                </div>
                            </td>
                            <td>&nbsp;</td>
                            <td>
                                <g:select class="form-control" id="toTime" name="toTime"
                                          from="${pageModel.eventTimeIntervals}" style="width: 125;"
                                          value="${pageModel.nextEventStartTime}">
                                </g:select>
                            </td>
                        </tr>
                        <tr><td colspan="4">&nbsp;</td></tr>
                        <tr>
                            <td>
                                <label for="eventDescription" class="col-lg-2 control-label">Description</label>
                            </td>
                            <td colspan="3">
                                <g:textField id="eventDescription" name="eventDescription" class="form-control" style="width: 300px;" placeholder="Untitled Event"></g:textField>
                            </td>
                        </tr>
                        <tr><td colspan="4">&nbsp;</td></tr>
                        <tr>
                            <td><label class="col-lg-2 control-label">Calendar</label></td>
                            <td colspan="3">
                                <g:select class="form-control" id="fromCalendar" name="fromCalendar" from="${pageModel.userCalendars}"
                                    optionKey="id" optionValue="description" style="width: 125;"></g:select>
                            </td>
                        </tr>
                        <tr><td colspan="4">&nbsp;</td></tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <div class="checkbox">
                                    <label>
                                        <input id="ckAllDayEvent" name="ckAllDayEvent" type="checkbox" onclick="doCheckAllDayEvent(this);"> All-Day Event
                                    </label>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createCalendarEvent()">
                    <span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;&nbsp;Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>