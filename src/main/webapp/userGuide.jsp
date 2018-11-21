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
    <h3 align="center">Constructing a Request</h3>
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
                    <td>dateTracker/services/</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Resource</td>
                    <td>events</td>
                    <td>Information returned from the request</td>
                </tr>
                <tr>
                    <td>Parameters 1</td>
                    <td><pre style="color: silver; background: black;">&userName={your event name}</pre> </td>
                    <td>User Name provided by Date Tracker Support.  Required for <u>all</u> event requests</td>
                </tr>
                <tr>
                    <td>Parameters 2</td>
                    <td><pre style="color: silver; background: black;">&eventName={your event name}</pre></td>
                    <td>Value you want to match to your event names.  Parameter only needed on event Name Requests </td>
                </tr>
            </table>
            <Br>
            <HR>
            <br>
            The following is an example requesting all upcoming events for a given user name:
            <pre style="color: silver; background: black;">http:999.999.999.99/dateTracker/services/events&userName=steveSmith</pre>
            <br>
            The following is an example requesting all upcoming events for a given user name AND event name:
            <pre style="color: silver; background: black;">http:999.999.999.99/dateTracker/services/events&userName=steveSmith&eventName=birthday</pre>
            <br>

        </div>

    </div>
</div>

<!-- Content here -->
</body>
</html>