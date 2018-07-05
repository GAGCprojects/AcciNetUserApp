<%@ Page Language="vb" AutoEventWireup="false" CodeBehind="WebForm3.aspx.vb" Inherits="WebApplication1.WebForm3" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
   <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  <title>Google Maps Multiple Markers</title> 
  <script src="http://maps.google.com/maps/api/js?key=AIzaSyCisyAM_Q6-EENiNr4ogXdIZwIeasQ9Ef0&callback=initMap" 
          type="text/javascript"></script>
</head>
<body>
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
<div id="map" style="width: 500px; height: 400px;"></div>
<p id="p"></p>
  <script type="text/javascript">
     var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 5,
          center: new google.maps.LatLng(18.1136745,75.2848973),
          mapTypeId: google.maps.MapTypeId.ROADMAP
      });
      var infowindow = new google.maps.InfoWindow();
      var marker;
      var rootRef = firebase.database().ref().child("Complaints");
       rootRef.on("child_added",snap => {
    var Loc=snap.child("location").val();
     var res = Loc.split(" ");
      marker = new google.maps.Marker({
              position: new google.maps.LatLng(res[0], res[1]),
              map: map
          });

          google.maps.event.addListener(marker, 'click', (function (marker, i) {
              return function () {
                  infowindow.setContent(i);
                  infowindow.open(map, marker);
              }
          })(marker, i));
    });
  </script>
</body>
</html>
