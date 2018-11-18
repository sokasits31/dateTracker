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
<h3>Welcome to the Search Page!</h3>
<hr>

<!-- form action="/dateTracker/services/events/search/events" method="get" -->
    <form action="searchEvent" method="get">
<div class="form-group">
    <label for="userName">User name: </label>
    <input type="text" class="form-control" id="userName" name="userName" aria-describedby="userName" required>

    <label for="eventName">Search Event: </label>
    <input type="text" class="form-control" id="eventName" name="eventName" aria-describedby="eventName">
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

</body>
</html>