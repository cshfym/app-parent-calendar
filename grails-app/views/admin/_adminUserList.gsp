<div id="user-list-wrapper">
    <table class="table table-striped table-hover">
        <thead><td><h3>Users</h3></td></thead>
        <g:each in="${users}" var="user">
            <tr>
                <td>${user}</td>
                <td>
                    <a class="btn btn-xs btn-success" href="#" onclick="createCalendarForUser(${user.id})">Create Calendar</a>
                </td>
                <td>
                    <a class="btn btn-xs btn-danger" href="#" onclick="deleteUser(${user.id})">Delete</a>
                </td>
            </tr>
        </g:each>
    </table>
</div>