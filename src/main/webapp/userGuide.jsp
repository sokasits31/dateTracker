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
    <h3 align="center">User Instructions</h3>
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
            <B>How to make an request</B>
            <p>
                To receive a data via a request use the following elements:
                <br>
                <table border="1" cellpadding="2">
                <tr>
                    <th style="width:20%;">Element</th>
                    <th style="width:40%;">Value/Example</th>
                    <th style="width:40%;">Description</th>
                </tr>
                <tr>
                    <td>Base URL</td>
                    <td>www.dateTracker.com</td>
                    <td>Uses HTTP GET</td>
                </tr>
                <tr>
                    <td>Path</td>
                    <td>dateTracker/services/searchbyName/{userName}</td>
                    <td>Where {userName} is your assigned User Name form www.dateTracker.com</td>
                </tr>
                </table>
                <br>
                <b>Examples</b>
                HTTP Request: <pre style="color: silver; background: black;">http://localhost:8080/dateTracker/services/events/searchbyName/steveSmith</pre>
                <br>
                JSON Response:  <pre style="color: silver; background: black;">
                { <br />
                  "eventName": "Steve's Birthday"  <br />
                  "eventType": "annual"  <br />
                  "eventDate": "1972-09-06"    <br />
                  "timeUntil": "9 Months and 17 Days"   <br />
                  "eventLength": "46 Years, 2 Months and 14 Days"    <br />
                  "nextUpcomingEventDate": "2019-09-06"  <br />
                } </pre>
                Notes:
                <ul>
                    <li>Only future events(request date >= current date) will be displayed</li>
                    <li>For annual events, request will display next event for the upcoming year.</li>
                </ul>
            </p>
            <hr>
            <b>Remove a event</b>
            <p>
            To remove an event, please use our "Delete Event" form.  One form enter the following:
            <ul>
                <li>Assigned userName</li>
                <li>Exact text of event you want to remove</li>
            </ul>
            </p>
            <hr>
            <b>Add an event</b>
            <p>
            To add an event, pleae use our "Add Event" form.  On form enter the following info:
            <ul>
                <li>Assigned userName</li>
                <li>Event Name</li>
                <li>Event Type - must be "annual" or "oneTime"</li>
                <li>Event Date - must be in CCYY-MM-DD formate</li>
            </ul>
            </p>
        </div>
    </div>
</div>

<!-- Content here -->
</body>
</html>