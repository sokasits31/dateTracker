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
    <h3 align="center">Welcome to the Main Date Tracker Page!</h3>
    <br/>

    <div class="row">
        <div class="col-md-3">
            <h5>Access our Services:</h5>
            <ul>
                <li><a href="searchByName.jsp">Search By Name</a></li>
                <li><a href="addEvent.jsp">Add Event</a></li>
                <li><a href="deleteEvent.jsp">Delete Event</a></li>
            </ul>
            <h5>Other:</h5>
            <ul>
                <li><a href="userGuide.jsp">User Guide</a></li>
                <li><a href="pricing.jsp">Pricing</a></li>
            </ul>
        </div>

        <div class="col-md-9">
            <p>
                Now you can easily keep track and access those important dates via website or application.
                Our free service will allow you track "annual" and "special one time" events.
                <br>
                <img src="images/dates.jpg" style="float: right; padding: 15px 5px 10px 20px; width: 50%; height: auto" >
                <br>
            <h4>How it works:</h4>
            <dl>
                <dt><b>Step 1</b></dt>
                <dd>To get started, request an unique "User Name" from dateTracker2018@gmail.com.
                    Once you receive your unique user name(1 to 5 business days), you can begin using our
                    service to store and reference your upcoming events.
                    <br>
                <dt><b>Step 2</b></dt>
                <dd>Start adding events.  To do this, use our insert Service.  Events will need the following
                    required data:
                    <ul>
                        <li>Event Name</li>
                        <li>Event Date (should be the actual date of the event. For example, a birthday would have a date in the past)</li>
                        <li>Event Type ("annual" or "one time")</li>
                    </ul>
                <dt><b>Step 3</b></dt>
                <dd>Request your data.  To do this, provide your user name in HTTP path.  This request
                    will return a list of all upcoming events.  If an event is "annual" service will provide
                    event date for the upcoming year (i.e. look out request date + 365 days)
            </dl>

            </p>
        </div>

    </div>
</div>

<footer class="container-fluid text-center">
</footer>
</body>
</html>