<%@ Page Language="vb" AutoEventWireup="false" CodeBehind="WebForm1.aspx.vb" Inherits="WebApplication1.WebForm1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>//
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <script src="http://maps.google.com/maps/api/js?key=AIzaSyCisyAM_Q6-EENiNr4ogXdIZwIeasQ9Ef0&callback=initMap" 
          type="text/javascript"></script>

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
</style>

</head>
<body>
<div class="container">
  <div class="jumbotron">
    <center><h1 style="font-family:Copperplate Gothic Bold;">ACCINET</h1></center>
  </div>

  <!--<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" id="menu1" type="button" data-toggle="dropdown">Display
    <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">NHI</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">WHIA</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">HNHI</a></li>
      <li role="presentation" class="divider"></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>    
    </ul>
  </div><br />
  <div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" id="Button1" type="button" data-toggle="dropdown">Problem Risk level
    <span class="caret"></span></button>
  </div><br />-->
  <div style="overflow-x:auto;">
  <table class="table table-striped table-bordered">
  <thead>
    <tr>
      <th>Aadhar</th>
      <th>Name</th>
      <th>Phone number</th>
      <th>Email</th>
      <th>Problem Type</th>
    </tr>
    </thead>
    <tbody id="body">
    
    </tbody>
  </table>
</div>
<br />
<!--<input type="text" id="t1" /><input type="text" id="t2" />
<input type="button" onclick="report()" value="Report" />
<p id="ppp"></p>-->
<center><h2 style="font-family:Times New Roman;">Complaints Details</h2></center>
<br />
<script>
var rootRef = firebase.database().ref().child("users");
    rootRef.on("child_added",snap => {
    var Aadhar=snap.child("aadhar").val();
    var Name=snap.child("name").val();
    var Phno=snap.child("phno").val();
    var Email=snap.child("em").val();
    $("#body").append("<select onchange=area('"+Email+"')><option value=rural>Rural</option> <option value=urban>Urban</option></select>");
    });
    

</script>
<br />
<div style="overflow-x:auto;">
  <table class="table table-striped table-bordered">
  <thead>
    <tr>
      <th>Date</th>
      <th>Time</th>
      <th>Area Type</th>
      <th>Accident Type</th>
      <th>Vehicle Type</th>
      <th>Construction</th>
      <th>Category</th>
      <th>Nationalhighways</th>
      <th>Landmark</th>
    </tr>
    </thead>
    <tbody id="Tbody2">
    
    </tbody>
  </table>
</div>
<p id="pp"></p>
<br />
<center><h2 style="font-family:Times New Roman;">Procedure View</h2></center>
<br />
<div class="jumbotron" style="background-color:lightblue">
<p id="pa" style="font-family:Times New Roman;" style="font-size:160%;"></p>
</div>
<br />
</div>
<br />
<center><h2 style="font-family:Copperplate Gothic Bold;">REPORT LOCATIONS</h2></center>
<br />
    <center><div id="map" style="width: 1200px; height: 800px;"></div></center>
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
    var rootRef = firebase.database().ref().child("users");
    rootRef.on("child_added",snap => {
    var Aadhar=snap.child("aadhar").val();
    var Name=snap.child("name").val();
    var Phno=snap.child("phno").val();
    var Email=snap.child("em").val();
    $("#body").append("<tr><td>" + Aadhar + "</td><td>" + Name + "</td><td>" + Phno + "</td><td>"+ Email +"</td> <td> <button onclick=callme1('"+Email+"')>Logical view</button><button onclick=callme('"+Email+"')>Procedure View</button></td></tr>");
    });
</script>
<script>
function callme1(str) {
    $("#Tbody2").html("");
        var s1 = str;
        var count=0;
        var rootRef = firebase.database().ref().child("Complaints");
        rootRef.on("child_added",snap => {
        var s2 = snap.child("email").val();
        var Description=snap.child("description").val();
    var Id=snap.child("id").val();
    var Landmark=snap.child("landmark").val();
    var Location=snap.child("location").val();
    var Nationalhighways=snap.child("nationalhighways").val();
    var Risklevel=snap.child("risklevel").val();
    var AccTypeT=snap.child("accTypeT").val();
    var AreaT=snap.child("areaT").val();
    var Categ=snap.child("categ").val();
    var Const=snap.child("constructionT").val();
    var Date=snap.child("date").val();
    var RoadTypeT=snap.child("roadTypeT").val();
    var Time=snap.child("time").val();
    var Vehile=snap.child("vehicleT").val();
    var Status=snap.child("status").val();
        var n = s1.localeCompare(s2);
        var m = AreaT.localeCompare('rural');
         if (n==0) {
         count++;
              $("#Tbody2").append("<tr><td>" + Date + "</td><td>" + Time + "</td><td>" + AreaT + "</td><td>" + AccTypeT + "</td><td>" + Vehile + "</td><td>" + Const + "</td><td>" + Categ + "</td><td>" + Nationalhighways + "</td><td>" + Landmark + "</td></tr>");
          } 
        });
            $("#Tbody2").append("<tr><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td></tr>");
          
    }
    function area(str) {
    $("#Tbody2").html("");
        var s1 = str;
        var count=0;
        var rootRef = firebase.database().ref().child("Complaints");
        rootRef.on("child_added",snap => {
        var s2 = snap.child("email").val();
        var Description=snap.child("description").val();
    var Id=snap.child("id").val();
    var Landmark=snap.child("landmark").val();
    var Location=snap.child("location").val();
    var Nationalhighways=snap.child("nationalhighways").val();
    var Risklevel=snap.child("risklevel").val();
    var AccTypeT=snap.child("accTypeT").val();
    var AreaT=snap.child("areaT").val();
    var Categ=snap.child("categ").val();
    var Const=snap.child("constructionT").val();
    var Date=snap.child("date").val();
    var RoadTypeT=snap.child("roadTypeT").val();
    var Time=snap.child("time").val();
    var Vehile=snap.child("vehicleT").val();
    var Status=snap.child("status").val();
        var n = s1.localeCompare(s2);
        var m = AreaT.localeCompare('rural');
         //if (n==0) {
         count++;
              $("#Tbody2").append("<tr><td>" + Date + "</td><td>" + Time + "</td><td>" + AreaT + "</td><td>" + AccTypeT + "</td><td>" + Vehile + "</td><td>" + Const + "</td><td>" + Categ + "</td><td>" + Nationalhighways + "</td><td>" + Landmark + "</td></tr>");
         // } 
        });
            $("#Tbody2").append("<tr><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td></tr>");
          
    }
   /* function report() {
    
        var s1 = document.getElementById('t1').value;
        var s2 = document.getElementById('t2').value;
        var ss1 = s1.split("-");
        var ss2 = s2.split("-");
        //alert(ss1[0]+ " " + ss2[0]);
 
        var rcount=0;
        var ucount=0;
        var fcount=0;
        var tcount=0;
        var rootRef = firebase.database().ref().child("Complaints");
        rootRef.on("child_added",snap => {
        
        var s2 = snap.child("email").val();
        var Description=snap.child("description").val();
    var Id=snap.child("id").val();
    var Landmark=snap.child("landmark").val();
    var Location=snap.child("location").val();
    var Nationalhighways=snap.child("nationalhighways").val();
    var Risklevel=snap.child("risklevel").val();
    var AccTypeT=snap.child("accTypeT").val();
    var AreaT=snap.child("areaT").val();
    var Categ=snap.child("categ").val();
    var Const=snap.child("constructionT").val();
    var Date=snap.child("date").val();
    var RoadTypeT=snap.child("roadTypeT").val();
    var Time=snap.child("time").val();
    var Vehile=snap.child("vehicleT").val();
    var Status=snap.child("status").val();
    var n = AreaT.localeCompare('4 wheeler');
    var p = AreaT.localeCompare('2 wheeler');
        var m = Vehile.localeCompare('rural');
        if(p==0){
        tcount++;
        }
        if(n==0){
        fcount++;
        }
         if (m==0) {
         rcount++;
              } 
          else
          {
          ucount++;
          }
          $("#ppp").html("");
          $("#ppp").append("From: " + s1 + "To: " + s2);
          $("#ppp").append("<br />accidents occured by 2 wheeler " + tcount);
          $("#ppp").append("<br />accidents occured by 4 wheeler " + fcount);
          $("#ppp").append("<br />accidents in Rural " + rcount);
          $("#ppp").append("<br />accidents in Urban " + ucount);
        });
            $("#ppp").append("<tr><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td><td>" + count + "</td></tr>");
    }*/
</script>
<script type="text/javascript">
     var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 7,
          center: new google.maps.LatLng(21.176551426760852 ,79.06132936477661),
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
<script type="text/javascript">
    function callme(str) {
    $("#pa").html("");
        var s1 = str;
        var rootRef = firebase.database().ref().child("Complaints");
        rootRef.on("child_added",snap => {
        var s2 = snap.child("email").val();
        var Description=snap.child("description").val();
    var Id=snap.child("id").val();
    var Landmark=snap.child("landmark").val();
    var Location=snap.child("location").val();
    var Nationalhighways=snap.child("nationalhighways").val();
    var Risklevel=snap.child("risklevel").val();
    var AccTypeT=snap.child("accTypeT").val();
    var AreaT=snap.child("areaT").val();
    var Categ=snap.child("categ").val();
    var Const=snap.child("constructionT").val();
    var Date=snap.child("date").val();
    var RoadTypeT=snap.child("roadTypeT").val();
    var Time=snap.child("time").val();
    var Vehile=snap.child("vehileT").val();
    var Status=snap.child("status").val();
        var n = s1.localeCompare(s2);
        var m = AreaT.localeCompare('rural');
         if (n==0&&m==0) {
              $("#pa").append("<b>Accident Type :</b>"+AccTypeT+"<br /><b>Area Type :</b>"+AreaT+"<br /><b>Category :</b>"+Categ+"<br /><b>Construction :</b>"+Const+"<br /><b>Date :</b>"+Date+"<br /><b>Email :</b>"+s2+"     <br /><b>Description :</b>"+Description+"     <br /><b>Id : </b>"+Id+"<br /><b>Landmark : </b>"+Landmark+"     <br /><b>Location : </b>"+Location+"     <br /><b>Nationalhighways : </b>"+Nationalhighways+"     <br /><b>Risklevel : </b>"+Risklevel+"<br /><b>Road Type : </b>"+RoadTypeT+"<br /><b>Time :</b>"+Time+"<br /><b>Vehicle Type :</b>"+Vehile+"<br /><b>Status : </b>"+Status+"<br /><input type=text id=state /><input type=button id=bu value=update onclick=updateData() /><br /><br />");
          } 
        });
    }
</script>
<script>
    var fb_db = firebase.database().ref()
    //alert("a");
function updateData(){
Status = document.getElementById('state').value;
//data = {Status}
fb_db.child("complaints/status").update(Status)
}
</script>
<!--<script type="text/javascript">
    function mystate() {
        var Sta = document.getElementById('state').value;
        var ID = document.getElementById('as').value;
        var dbFirebase = new Firebase("Compliants/ID");
        dbFirebase.once('value', function (nameSnapshot) {
        var val = nameSnapshot.val();
        alert(val);
        //console.log(val);
        // val now contains 'Fred'
    });
    }
         /*   var rootRef = firebase.database().ref().child("Complaints");
            alert(ID);
          // rootRef.on("child_added",snap => {
          // var Id=snap.child("id").val();
           //    var n;
           //  alert(Id);
           // rootRef.update({
           //alert("Sta")
           //   if(n==0){
           //   "status" : Sta;
           }
           // });
          // });*/
</script>-->
</body>
</html>
