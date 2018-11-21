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
    <h3>Welcome to the Delete Event Page!</h3>
    <hr>

    <form action="/dateTracker/services/events/delete" method="post">
        <div class="form-group">
            <label>Username *</label>
            <input type="text" class="form-control" id="userName" name="userName" maxlength="10" required Title="Username is required."/>
        </div>

        <div class="form-group">
            <label>Event Name *</label>
            <input type="text" class="form-control" id="eventName" name="eventName" maxlength="100" />
        </div>

</div>

<hr>
<button type="submit" name="submit" value="delete" class="btn btn-primary">Delete</button>
<button type="reset" name="reset" value="reset" class="btn btn-primary">Reset</button>

</form>

<hr>
<figure class="figure">
    <img src="images/dates.jpg"  class="figure-img img-fluid rounded" alt="A generic square placeholder image with rounded corners in a figure.">
    <figcaption class="figure-caption"></figcaption>
</figure>
</div>

</body>
</html>