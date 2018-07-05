<%@ Page Language="vb" AutoEventWireup="false" CodeBehind="WebForm1.aspx.vb" Inherits="WebApplication1.WebForm1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <title></title>
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  -->
  <style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}
#map {
  height: 400px;
  width: 100%;
 }
</style>
</head>
<body>
<div class="container">
  <div class="jumbotron">
    <center><h1>ACCINET</h1></center>
  </div>
  <br />
  
<br />

<div style="overflow-x:auto;">
  <table>
    <tr>
      <th>Description</th>
      <th>Email</th>
      <th>Id</th>
      <th>Landmark</th>
      <th>Location</th>
      <th>National highways</th>
      <th>Risk level</th>
    </tr>
    <tbody id="body1">

    </tbody>
  </table>
</div>
<input type="button" id="but" onclick="addStore()" />
<br />
    <script src="https://www.gstatic.com/firebasejs/4.12.0/firebase.js"></script>
<script>
    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyCGhI1Ct-gxObgDm8o8zXyW03pJUfgQVzA",
        authDomain: "abiaccinet-c3570.firebaseapp.com",
        databaseURL: "https://abiaccinet-c3570.firebaseio.com",
        projectId: "abiaccinet-c3570",
        storageBucket: "abiaccinet-c3570.appspot.com",
        messagingSenderId: "1015329437532"
    };
    firebase.initializeApp(config);
</script>
<script src="http://code.jquery.com/jquery-3.1.0.is"></script>
<script>
    var rootRef = firebase.database().ref().child("Complaints");
    rootRef.on("child_added",snap => {
    var Description=snap.child("description").val();
    var Email=snap.child("email").val();
    var Id=snap.child("id").val();
    var Landmark=snap.child("landmark").val();
    var Location=snap.child("location").val();
    var Nationalhighways=snap.child("nationalhighways").val();
    var Risklevel=snap.child("risklevel").val();
     var Status=snap.child("status").val();
    $("#body1").append("<tr><td>" + Description + "</td><td>" + Email + "</td><td>" + Id + "</td><td>" + Landmark + "</td><td>" + Location + "</td><td>" + Nationalhighways + "</td><td>" + Risklevel + "</td><td>" + Status + "</td></tr>");
    });
</script>
<script>
    function addStore() {
        var rootRef = firebase.database().ref().child("Complaints");
        rootRef.push({
            "description": "The danger of a single story",
            "email": "Chimamanda Ngozi Adichie",
            "id": "h",
            "landmark": "nearbynagpr",
            "location": "13.2333 18.23332",
            "nationalhighways":"NH5",
            "risklevel":"easy"
        });
    }
</script>
<p id="demo"></p>
</div>
</body>
</html>
