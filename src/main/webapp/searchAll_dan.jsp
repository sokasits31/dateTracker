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

<form action="/dateTracker/services/events/search/all" method="post">
<div class="form-group">
<label for="userName">User name: </label>
<input type="text" class="form-control" id="userName" name="userName" aria-describedby="searchUserName" required>

<label for="searchTerm">Search value: </label>
<input type="text" class="form-control" id="searchTerm" name="searchTerm" aria-describedby="searchTermHelp">
</div>

<div class="input-group">
<div class="input-group-prepend">
<span class="input-group-text" id="">Begin and End Date</span>
</div>
<input type="text" class="form-control" name="beginDt">
<input type="text" class="form-control" name="endDt">
<div class="input-group-append">
<!--button class="btn btn-primary" type="button">Search by Date</button-->
</div>
</div>
<hr>
    <button type="submit" name="searchevent" value="searchEvent" class="btn btn-primary">Search by Event</button>
    <button type="submit" name="searchdate" value="searchDate" class="btn btn-primary">Search by Date</button>
    <button type="submit" name="viewall" value="view/All" class="btn btn-primary">View all</button>
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