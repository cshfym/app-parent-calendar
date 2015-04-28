
var currentView = "week";
var weekHourConstraint = 24;
var calendarHeight = 500;
var hourSliceHeight = 41;
var halfHourSliceHeight = 20;

$(document).ready(function() {
    switchView(currentView);
    adjustCalendarVisuals();
});

function adjustCalendarVisuals() {
    adjustCalendarHeight();
    adjustWeeklyViewHourSlices();
    showWeeklyEventRow();
    adjustNavigationControls();
}

function adjustNavigationControls() {

    if(currentView == "week") {
        $("#weekly-constraint-group").show();
    } else {
        $("#weekly-constraint-group").hide();
    }

    $("#weeklyConstraint12Hours").attr("disabled", true);
    $("#weeklyConstraint18Hours").attr("disabled", true);
    $("#weeklyConstraint24Hours").attr("disabled", true);

    if (weekHourConstraint == 24) {
        $("#weeklyConstraint18Hours").removeAttr('disabled');
        $("#weeklyConstraint12Hours").removeAttr('disabled');
    }
    if (weekHourConstraint == 18) {
        $("#weeklyConstraint24Hours").removeAttr('disabled');
        $("#weeklyConstraint12Hours").removeAttr('disabled');
    }
    if (weekHourConstraint == 12) {
        $("#weeklyConstraint18Hours").removeAttr('disabled');
        $("#weeklyConstraint24Hours").removeAttr('disabled');
    }
}

function adjustCalendarHeight() {

    if (currentView == "month") { calendarHeight = $(window).height() - 200; }
    //else { calendarHeight = 1000; } // Week view.

    if (calendarHeight < 500) { calendarHeight = 500; }
    var weekCount = $("#weekCount").val();
    if(!weekCount) {
        weekCount = 5;
    }

    /* Adjust height of day containers for month/week view. */
    $(".calendar-day-container").height(calendarHeight / weekCount);

    //$(".week-calendar-day-container").height(calendarHeight / weekCount);

    //$(".calendar-hours-container").height(calendarHeight / weekCount);
}

function adjustWeeklyViewHourSlices() {

    /* Adjust height and width of each hour slice in weekly view. */

    // Legend
    var legendHourSlices = $('[id^="hour-slice-legend_"]');
    $.each(legendHourSlices, function(idx, val) {
        $(val).height(hourSliceHeight);
        $(val).width("42px");
    });
    // All hour slices in weekly view.
    var hourSlices1 = $('[id^="half-hour-slice-day-1_"]');
    $.each(hourSlices1, function(idx, val) {
        $(val).height(halfHourSliceHeight);
        $(val).width("100px"); // Apply width inside the TD.
    });
    var hourSlices2 = $('[id^="half-hour-slice-day-2_"]');
    $.each(hourSlices2, function(idx, val) {
        $(val).height(halfHourSliceHeight);
        $(val).width("100px"); // Apply width inside the TD.
    });
}

function showWeeklyEventRow() {

    if($("#weekAllDayEventCount").val() > 0) {
        $("#tr-all-day-events").show();
    } else {
        $("#tr-all-day-events").hide();
    }
}

function launchCreateCalendarEvent(id) {

    var millis = new Number(id.replace("td_",""));
    var fromDate = new Date(millis);

    var formatted = (fromDate.getMonth()+1) + "/" + fromDate.getDate() + "/" + fromDate.getFullYear();

    $("#fromDate").text(formatted);
    $("#toDate").text(formatted);

    $("#createCalendarEventModal").modal("show");
}

function doCheckAllDayEvent(control) {
    if(control.checked) {
        $("#fromTime").fadeOut("fast");
        $("#toTime").fadeOut("fast");
    } else {
        $("#fromTime").fadeIn("fast");
        $("#toTime").fadeIn("fast");
    }
}

/*
function highlightDay(id) {
    $("#" + id).attr("class", "calendar-day-container highlight-day");
}
function unHighlightDay(id) {
    $("#" + id).attr("class", "calendar-day-container");
}
function highlightToday(id) {
    $("#" + id).attr("class", "calendar-day-container highlight-today");
}
function unHighlightToday(id) {
    $("#" + id).attr("class", "calendar-day-container today");
}
*/

function changeCalendarYear(years) {
    var link = "/app-parent-calendar/calendar/changeCalendarYear";
    var parameters = { adjust: years };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function changeCalendarMonth(days) {
    var link = "/app-parent-calendar/calendar/changeCalendarMonth";
    var parameters = { adjust: days };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function changeCalendarWeek(weeks) {
    var link = "/app-parent-calendar/calendar/changeCalendarWeek";
    var parameters = { adjust: weeks };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function changeCalendarToday() {
    var link = "/app-parent-calendar/calendar/changeCalendarToday";
    var parameters = { };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function selectDay(elementId, duration) {

    var link = "/app-parent-calendar/calendar/selectCalendarDay";
    var parameters = { selectedDay: elementId.replace(duration + "-day_","") };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function createCalendarEvent() {

    var link = "/app-parent-calendar/calendar/createCalendarEvent";
    var parameters = {
        fromDate: $("#fromDate").text(),
        toDate: $("#toDate").text(),
        description: $("#eventDescription").val(),
        calendarId: $("#fromCalendar").val(),
        fromTime: $("#fromTime").val(),
        toTime: $("#toTime").val(),
        allDayEvent: $("#ckAllDayEvent").is(':checked')
    };

    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() {
            $("#createCalendarEventModal").modal("hide");
        }
    });
}

function switchView(view) {

    currentView = view
    var link = "/app-parent-calendar/calendar/switchView";
    var parameters = { viewType: view};
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function setWeeklyVisibleHours(hours) {

    weekHourConstraint = hours
    var link = "/app-parent-calendar/calendar/setWeeklyVisibleHours";
    var parameters = { weeklyVisibleHours: hours};
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}
