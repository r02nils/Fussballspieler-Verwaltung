/**
 * checkCookie
 * @author  Nils Rothenbühler
 */
$(document).ready(function (){
    const cookie = document.cookie;

    if(cookie == "userRole=admin"){
        document.getElementById("logon").style.display = "none";
        document.getElementById("logoff").style.display = "block";
    }
    else if(cookie == "userRole=read"){
        document.getElementById("logon").style.display = "none";
        document.getElementById("logoff").style.display = "block";
    }
    else{
        document.getElementById("logon").style.display = "block";
        document.getElementById("logoff").style.display = "none";
    }
});