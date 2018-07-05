<%@ Page Language="vb" AutoEventWireup="false" CodeBehind="WebForm5.aspx.vb" Inherits="WebApplication1.WebForm5" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
<input type="button" value="click" onclick="addStore()" />
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

<!--<script>
    function update() {
        var ref = firebase.database().ref('Complaints/-L8q_ym1kEf2hbMv76w2');
        ref.once("child_added",snap => {
        var s2 = snap.child("email").val();
        var Description=snap.child("description").val();
    var id=snap.child("id").val();
    var landmark=snap.child("landmark").val();
    var location=snap.child("location").val();
    var nationalhighways=snap.child("nationalhighways").val();
    var risklevel=snap.child("risklevel").val();
    var accTypeT=snap.child("accTypeT").val();
    var areaT=snap.child("areaT").val();
    var categ=snap.child("categ").val();
    var constructionT=snap.child("constructionT").val();
    var date=snap.child("date").val();
    var roadTypeT=snap.child("roadTypeT").val();
    var time=snap.child("time").val();
    var vehileT=snap.child("vehileT").val();
    var status=snap.child("status").val();
    var data = {accType,areaT,categ,constructionT,date,description,email,id,landmark,location,nationalhighways,roadTypeT,status,time,vehicleT}
        ref.update(data);
        //{ accType: AccType,areaT:AreaT ,categ:Categ ,constructionT:Const ,date:Date ,description:Description ,email:s2 ,id:Id ,landmark:Landmark ,location:Location ,nationalhighways:Nationalhighways ,roadTypeT:RoadTypeT ,status:Status ,time:Time ,vehicleT:Vehilel  }
        console.log("Success");
        });
        ref.remove().then(function () {
            console.log("removed ");
        });
        
    }
</script>-->
<script>
    function addStore() {
        var rootRef = firebase.database().ref().child("Complaints");
        rootRef.push({
            "description": "The danger of a single story",
            "email": "Chimamanda Ngozi Adichie",
            "id": "h",
            "landmark": "nearbynagpr",
            "location": "13.2333 18.23332",
            "nationalhighways": "NH5",
            "risklevel": "easy"
        });
    }
</script>
</body>
</html>
