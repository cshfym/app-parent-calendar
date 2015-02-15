
$(document).ready(function() {
  adjustCalendarHeight();
});

function adjustCalendarHeight() {
  var tableHeight = $(window).height() - 350;
  var weekCount = $("#weekCount").val();
  if(!weekCount) {
    weekCount = 5;
  }

  $(".calendar-day-container").height(tableHeight / weekCount);
}

function changeCalendarMonth(days) {
  var link = "${createLink(controller:'calendar',action:'changeCalendarMonth')}"
  //var link = "/app-parent-calendar/calendar/changeCalendarMonth";
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

function changeCalendarToday() {
  var link = "${createLink(controller:'calendar',action:'changeCalendarToday')}"
  //var link = "/app-parent-calendar/calendar/changeCalendarToday";
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

function changeNextYear() {
  var link = "${createLink(controller:'calendar',action:'changeNextYear')}"
  //var link = "/app-parent-calendar/calendar/changeNextYear";
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
