<!doctype html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
</head>

<body>
<!-- As a heading -->
<nav class="navbar justify-content-md-center navbar-dark bg-dark">
    <span class="navbar-brand mb-0 h1"><a href="index.jsp">Date Tracker Website</a></span>
</nav>

<div class="container">
    <hr>
    <h3>Welcome Add Event Page!</h3>
    <hr>

    <form action="/dateTracker/services/events/add" method="post">
        <div class="form-group">
            <label>Username *</label>
            <input type="text" class="form-control" id="userName" name="userName" maxlength="40" required Title="Username is required."/>
        </div>

        <div class="form-group">
            <label>Event Name *</label>
            <input type="text" class="form-control" id="eventName" name="eventName" maxlength="100" required Title="Event name is required."/>
        </div>

        <div class="form-group">
            <label>Event Date</label>
            <input type="text" class="form-control" name="eventDate" id="eventDate" placeholder="YYYY-MM-DD" maxlength="10" size="50" required pattern="\d{1,4}-\d{1,2}-\d{2}" Title="Event Date yyyy-mm-dd is required."/>
        </div>

        <label>Event Type *</label>
        <div>
            <label class = "checkbox-inline">
                <input type = "checkbox" id = "inlineCheckbox1" name="eventType" value = "annual"> Annual
            </label>

            <label class = "checkbox-inline">
                <input type = "checkbox" id = "inlineCheckbox2" name="eventType" value = "one-time"> One-time
            </label>

        </div>

        <hr>
        <button type="submit" name="submit" value="submit" class="btn btn-primary">Submit</button>
        <button type="reset" name="reset" value="reset" class="btn btn-primary">Reset</button>

    </form>

    <hr>
    <figure class="figure">
        <img src="images/dates.jpg"  class="figure-img img-fluid rounded" alt="A generic square placeholder image with rounded corners in a figure.">
        <figcaption class="figure-caption"></figcaption>
    </figure>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>