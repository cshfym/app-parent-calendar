
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

function highlightDay(id) {
  $("#" + id).attr("class", "highlight-day");
}
function unHighlightDay(id) {
  $("#" + id).attr("class", "");
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

function changeNextYear() {
  var link = "/app-parent-calendar/calendar/changeNextYear";
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
