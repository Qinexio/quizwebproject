<!DOCTYPE html>
<html lang="en">
<head>
	<title>Quiz Site</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
	/* Remove the navbar's default margin-bottom and rounded borders */ 
	.navbar {
	  margin-bottom: 0;
	  border-radius: 0;
	}
	
	/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
	.row.content {height: 450px}
	
	/* Set gray background color and 100% height */
	.sidenav {
	  padding-top: 20px;
	  background-color: #f1f1f1;
	  height: 100%;
	}
	
	/* colour change
	/* !important overrides certain elements without messing with the rest */
	.navbar-inverse {
     background-color: #222; !important
     border-color: #080808; !important
}
	
	/* Set black background color, white text and some padding */
	footer {
	  background-color: #555;
	  color: white;
	  padding: 20px;
	}
	
	/* On small screens, set height to 'auto' for sidenav and grid */
	@media screen and (max-width: 767px) {
	  .sidenav {
		height: auto;
		padding: 15px;
	  }
	  .row.content {height:auto;} 
	}
  </style>
</head>
<body>
<!-- Part of the firstmost grid, navbar-inverse is akin to a dark version, could create a different theme for light theme -->
<nav class="navbar navbar-dark navbar-expand-lg bg-dark">
	<!-- Smaller Screen Toggle for Menu -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navBar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- Sends back to the homepage, Logo, always displays on the left due to the class definition -->
		<a class="navbar-brand" href="#">Logo</a>
		<!-- The actual menu, displays horizontaly, toggled horizontaly when the page is small enough via the navbar-toggle button -->
		<div class="collapse navbar-collapse" id="navBar">
		<!-- mr-auto is to specify the items to be moved to the rightmost margin -->
		<ul class="nav navbar-nav mr-auto">
		<!-- Active webpage, looks real nice, even though it's only an illusion of interactivity -->
			<li class="nav-item active"><a class="nav-link" href="#"><span class= "fa fa-home"></span> Home</a></li>
			<li class="nav-item"><a class="nav-link" href="#"><span class= "fa fa-list"></span> Quizes</a></li>
			<li class="nav-item"><a class="nav-link" href="#"><span class= "fa fa-address-book"></span> Users</a></li>
			<li class="nav-item"><a class="nav-link" href="#"><span class= "fa fa-question-circle"></span> About</a></li>
	  </ul>
	 <!-- ml-auto is to specify the items to be moved to the leftmost margin -->
	  <ul class="navbar-nav ml-auto">
	  <!-- Login Button, dropdowns a form on click for authentification, dissapears upon the presence of a logged in user -->
	   <li class="nav-item dropdown" id="login">
	   <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="navdrop1"><span class= "fas fa-sign-in-alt"></span> Login</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="#">Username:</a>
				<a class="dropdown-item" href="#">Password:</a>
				<a class="dropdown-item" href="#">Enter</a>
				<a class="dropdown-item" href="#">Forgot Password</a>
			</div>
		</li>
		
		<li class="nav-item" id="register">
		<a class="nav-link" href="#"><span class="far fa-file-alt"></span> Register</a>
		</li>
		
		<!-- Profile Button, dropdowns a form on click for logout and settings, dissapears upon the absence of an user -->
		<li class="nav-item dropdown" id="profile">
		<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" class="dropdown-toggle" id="navdrop2"><span class= "fa fa-user"></span> Profile</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="#"><span class= "fa fa-wrench"></span> Settings</a>
				<a class="dropdown-item"href="#"><span class= "fa fa-arrow-alt-circle-down"></span> Logout</a>
			</div>
		</li>
		
	  </ul>
	</div>
</nav>
 <!-- Main section, covers the content of the current page, uses a grid system to arrange elements and resize if the browser screen is too small -->
<div class="container-fluid text-center">    
  <div class="row content">
  <!-- First element, displays on top or to the left if the page is big enough -->
	<div class="col-sm-2 sidenav">
	  <p><a href="#">Link</a></p>
	  <p><a href="#">Link</a></p>
	  <p><a href="#">Link</a></p>
	</div>
<!-- Middle element, displays in the middle -->
	<div class="col-sm-8 text-left"> 
	  <h1>Welcome</h1>
	  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
	  <hr>
	  <h3>Test</h3>
	  <p>Lorem ipsum...</p>
	</div>
<!-- Rightmost or bottom element -->
	<div class="col-sm-2 sidenav">
	  <div class="well">
		<p>ADS</p>
	  </div>
	  <div class="well">
		<p>ADS</p>
	  </div>
	</div>
  </div>
</div>

<!-- Footer, displays information at the bottom of the page, a smaller, secondary grid, static -->
<footer class="page-footer pt-4 fill">
	<div class="container-fluid text-center text-md-left">
	  <div class="row">
		<div class="col-md-6 mt-md-0 mt-3">
		  <h5 class="text-uppercase">Message of the day</h5>
		  <p id="MOTD">If you see this, you took a wrong path</p>
		</div>
		<div class="col-md-3 mb-md-0 mb-3">
			<h5 class="text-uppercase">Website made by</h5>
			<ul class="list-unstyled">
			  <li>
				<p>Velt Alexandru</p>
			  </li>
			  <li>
				<p>Group 10LF252</p>
			  </li>
			  
			</ul>
		  </div>
		<div class="col-md-3 mb-md-0 mb-3">
			<h5 class="text-uppercase">Coordinator</h5>
			<ul class="list-unstyled">
			  <li>
				<p>Livia Sangeorzan</p>
			  </li>
			</ul>
		  </div>

	  </div>
	</div>
  </footer>

</body>
</html>