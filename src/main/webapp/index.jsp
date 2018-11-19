<!doctype html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
</head>
<body>
<!-- As a heading -->
<nav class="navbar justify-content-md-center navbar-dark bg-dark">
    <span class="navbar-brand mb-0 h1">Date Tracker Website</span>
</nav>

<div class="container">
    <!-- Content here -->
    <hr>
    <h3>Welcome to the Main Date Tracker Page!</h3>
    <br/>

    <h4>Links:</h4>
    <br/>
    <h5>
        <ul>
            <li><a href="searchEvent.jsp">Search Events</a></li>
            <br/>
            <li><a href="searchByName.jsp">Search By User Name</a></li>
            <br/>
            <li><a href="addEvent.jsp">Add Event</a></li>
            <br/>
            <li><a href="deleteEvent.jsp">Delete Event</a></li>
            <br/>
            <li><a href="updateEvent.jsp">Update Event</a></li>
        </ul>
    </h5>

    <hr>
    <figure class="figure">
        <img src="images/dates.jpg"  class="figure-img img-fluid rounded" alt="A generic square placeholder image with rounded corners in a figure.">
        <figcaption class="figure-caption"></figcaption>
    </figure>
</div>

</body>
</html>