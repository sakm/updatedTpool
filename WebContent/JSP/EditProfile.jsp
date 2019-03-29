<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="validation.js"></script>
    
<title>Edit Profile</title>
<style>
	body{
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	background: #34495e;
}
.star {
   color: red; /* Make it red */
   font-size: 70%; /* Make it twice as large */
}
   
header {
	background :#191919;
	margin:0; 
	padding: 0;
	width: 100%;
	font-family: sans-serif;
	height: 10vh;
	background-size: cover;
	background-position: center;
 }
header h1{
	
	color: white;
	font-weight: 500;
	display: inline-block;
	text-align: center;
	width: 100%;
	
}
.box{
	width: 500px;
	padding: 40px;
	position: relative;
	top: 40%;
	background: #191919;
	text-align: center;
	border-radius: 15px 15px 15px 15px;
	left: 35%; 
	color: white;
	margin-top: 25px;
	margin-bottom: 30px;
	margin-left:-40px;
}
.box input[type = "text"],.box input[type ="password"],.box input[type="email"]{
	border: 0;
	background: none;
	display: inline-block;
	margin : 20px auto;
	text-align: center;
	border: 2px solid #3498db;
	padding: 14px 10px;
	width: 200px;
    outline: none; 
	color : white;
	border-radius: 24px;
	transition: 0.25s;
}
.box input[type = "submit"]{
	border: 0;
	background : none;
	display: block;
	margin: 20px auto;
	text-align: center;
	border: 2px solid #2ecc71;
	padding: 14px 40px;
	outline: none;
	color: white;
	border-radius: 24px;
	transition: 0.25s;
	cursor: pointer;
}
.box input[type = "submit"]:hover{
	background: #2ecc71;
}
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #191919 ;
   color: white;
   text-align: center;
   height: 20px;
   font-size: small;
   font-family: sans-serif;
   
}   
.box input[type = "text"]:focus,.box input[type ="password"]:focus,.box input[type="text"]:hover,.box input[type = "password"]:hover,.box input[type="email"]:hover{
	width: 220px;
	border-color : #2ecc71;
}
   
</style>
<script src="../javaScriptValidation/validation.js">

</script>
</head>
<body>

	<header>
		<div class="main">
			<h1 align="center">eTrainer Pool Management</h1>
		</div>
	</header>
	<form  class="box" id="box" action="../Register">
		<h1>Edit Profile</h1>
		
		<label style="padding-left: 20px">First Name<sup><span class="star">&#x2605;</span></sup></label>
		<input type="text" name="FirstName" placeholder="Firstname" required style="margin-left: 50px" id="FirstName"><br>

		<label style="padding-left: 20px">Last Name<sup><span class="star">&#x2605;</span></sup></label>
		<input type="text" name="LastName" placeholder="Lastname"  required style="margin-left: 50px" id="LastName"><br>

		<label style="padding-left: 30px">Age<sup><span class="star">&#x2605;</span></sup></label>
		<input type="text" name="Age" placeholder="Age" required style="margin-left: 82px" id="Age"><br>

		<label style="padding-right:  8em">Gender<sup><span class="star">&#x2605;</span></sup></label>
		<input type="radio" name="gender"  required value="Male" style="margin-left: 0px">Male &nbsp &nbsp <input type ="radio" required name="gender" value="Female" style="margin-left: 0px">Female <br><br>

		<label style="padding-left: 30px">Contact No<sup><span class="star">&#x2605;</span></sup></label>
		<input type="text" name="Contact" placeholder="Contact" required style="margin-left: 42px" id="Contact"><br>

		<label style="padding-left: 30px">Email ID<sup><span class="star">&#x2605;</span></sup></label>
		<input type="email" name="EmailID" placeholder="Email" required style="margin-left: 75px" id="EmailID"><br>
		
		
		
		<input type="submit" name="Action" value="Save" onclick="validation()">
		
		 
	</form>
	
	<div class="footer">
  		<p>Copyright wali line</p>
	</div>
</body>
</html>
