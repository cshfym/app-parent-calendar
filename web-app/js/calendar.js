
$(document).ready(function() {
  adjustCalendarHeight();
});

function adjustCalendarHeight() {
  var tableHeight = $(window).height() - 200;
  if (tableHeight < 500) { tableHeight = 500; }
  var weekCount = $("#weekCount").val();
  if(!weekCount) {
    weekCount = 5;
  }

  $(".calendar-day-container").height(tableHeight / weekCount);
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
            adjustCalendarHeight();
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
      adjustCalendarHeight();
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
            adjustCalendarHeight();
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
      adjustCalendarHeight();
    },
    error: function(request, status, error) {
      alert(error);
    },
    complete: function() { }
  });
}

function selectDay(id) {
    var link = "/app-parent-calendar/calendar/selectCalendarDay";
    var parameters = { selectedDay: id.replace("day_","") };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarHeight();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function switchView(view) {
    var link = "/app-parent-calendar/calendar/switchView";
    var parameters = { viewType: view};
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-wrapper").html(data);
            adjustCalendarHeight();
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
            adjustCalendarHeight();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() {
            $("#createCalendarEventModal").modal("hide");
        }
    });

}
