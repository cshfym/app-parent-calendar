
$(document).ready(function() {
  adjustCalendarHeight();
});

function adjustCalendarHeight() {
  var tableHeight = $(window).height() - 250;
  if (tableHeight < 500) { tableHeight = 500; }
  var weekCount = $("#weekCount").val();
  if(!weekCount) {
    weekCount = 5;
  }

  $(".calendar-day-container").height(tableHeight / weekCount);
}

function launchCreateCalendarEvent(id) {

    var millis = new Number(id.replace("td_",""));
    var eventDate = new Date(millis);

    var formatted = (eventDate.getMonth()+1) + "/" + eventDate.getDate() + "/" + eventDate.getFullYear();

    $("#eventDate").text(formatted);
    // $("#eventDate").val(date.toString());
    $("#createCalendarEventModal").modal("show");
}

function doCheckAllDayEvent(control) {
    var row = $("#rowTimeSelection");
    if(control.checked) {
        row.fadeOut("fast");
    } else {
        row.fadeIn("fast");
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
        eventDate: $("#eventDate").text(),
        eventDescription: $("#eventDescription").val(),
        eventCalendarId: $("#fromCalendar").val(),
        eventFromTime: $("#fromTime").val(),
        eventToTime: $("#toTime").val(),
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
