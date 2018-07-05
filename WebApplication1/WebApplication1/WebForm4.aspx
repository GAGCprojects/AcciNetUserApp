<%@ Page Language="vb" AutoEventWireup="false" CodeBehind="WebForm4.aspx.vb" Inherits="WebApplication1.WebForm4" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    
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
<script>
    function addStore() {
        var rootRef = firebase.database().ref();
        var storesRef = rootRef.child('Complients');
        var newStoreRef = storesRef.push();
        newStoreRef.set({
            description: "Cars",
            //"pageId": "23",
        });
    }
    </script>
    <form id="form1" runat="server">
    <div>
    <input type="text" id="a" />
    <input type="text" id="b" />
    <input type="button" id="bu" onclick="addStore()" />
    </div>
    </form>
    <script language="javascript">
        function fun(a) {
            var sum = parseInt(a);
            alert(sum);
        }
    </script>
</body>
</html>
