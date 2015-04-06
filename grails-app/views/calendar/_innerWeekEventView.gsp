<table>
    <g:each in="${pageModel.getHourTimeIntervals()}" var="timeInterval">
        <tr>
            <td id="td-inner-week-slice_${day.getCondensedDateString()}" class="td-inner-week-slice">
                <div id="hour-slice-day_${day.getCondensedDateString()}">

                </div>
            </td>
        </tr>
    </g:each>
</table>
