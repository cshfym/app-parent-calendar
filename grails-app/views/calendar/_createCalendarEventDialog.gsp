<div class="modal fade" id="createCalendarEventModal" tabindex="-1" role="dialog" aria-labelledby="createCalendarEventModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <span class="glyphicon glyphicon-remove-sign"></span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Create Event</h4>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <table>
                        <tr>
                            <td>
                                <label class="col-lg-2 control-label">Date</label>
                            </td>
                            <td colspan="3">
                                <div class="input-group">
                                    <label id="eventDate" name="eventDate" class="form-control"></label>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">
                                            <span class="glyphicon glyphicon-calendar" style="font-size: 12px; color: #008CBA;"></span>
                                        </button>
                                    </span>
                                </div>
                            </td>
                            <!--
                            <td>&nbsp;</td>
                            <td>
                                <a href="#" class="btn btn-xs" onclick="" title="Last Month">
                                    <span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
                                </a>
                                <a href="#" class="btn btn-xs" onclick="" title="Yesterday">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                </a>
                                <a href="#" class="btn btn-xs" onclick="" title="Tomorrow">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                </a>
                                <a href="#" class="btn btn-xs" onclick="" title="Next Month">
                                    <span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
                                </a>
                            </td>
                            -->
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr>
                            <td>
                                <label for="eventDescription" class="col-lg-2 control-label">Description</label>
                            </td>
                            <td colspan="3">
                                <g:textField id="eventDescription" name="eventDescription" class="form-control" style="width: 300px;" placeholder="Untitled Event"></g:textField>
                            </td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr>
                            <td><label class="col-lg-2 control-label">Calendar</label></td>
                            <td colspan="3">
                                <g:select class="form-control" id="fromCalendar" name="fromCalendar" from="${pageModel.userCalendars}"
                                    optionKey="id" optionValue="description" style="width: 125;"></g:select>
                            </td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr id="rowTimeSelection">
                            <td><label class="col-lg-2 control-label">From</label></td>
                            <td>
                                <g:select class="form-control" id="fromTime" name="fromTime"
                                          from="${pageModel.eventTimeIntervals}" style="width: 125;"
                                          value="${pageModel.nextEventStartTime}">
                                </g:select>
                            </td>
                            <td><label class="col-lg-2 control-label">To</label></td>
                            <td>
                                <g:select class="form-control" id="toTime" name="toTime"
                                          from="${pageModel.eventTimeIntervals}" style="width: 125;"
                                          value="${pageModel.nextEventFinishTime}">

                                          </g:select>
                            </td>
                            <td style="padding-left: 10px;">
                                <!-- Display Interval Here -->
                            </td>
                        </tr>
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