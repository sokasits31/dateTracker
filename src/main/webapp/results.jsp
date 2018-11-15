<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results" />
<%@include file="head.jsp"%>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#userTable').DataTable();
    } );
</script>

<html>
<body>

<div class="container-fluid">
    <h2>Search Results: </h2>
    <table id="eventTable" class="display" cellspacing="0" width="100%">
        <thead>
        <th>Event Name</th>
        <th>Event Type</th>
        <th>Event Date</th>
        </thead>
        <tbody>
        <c:forEach var="event" items="${events}">
            <tr>
                <td>${event.eventName}</td>
                <td>${event.eventType}</td>
                <td>${event.eventDate}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
