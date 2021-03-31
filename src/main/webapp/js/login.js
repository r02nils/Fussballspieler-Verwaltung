
$(document).ready(function (){
    $("#loginForm").submit(sendLogin);
    $("#logoff").click(sendLogout);
});

function sendLogin(form){
    form.preventDefault();
    $
        .ajax({
            url: "./resource/user/login",
            type: "POST",
            data: $("#loginForm").serialize()
        })
        .done(function () {
            alert("Erfolgreich eingeloggt!")
            window.location.href = "./index.html";
        })
        .fail(function (xhr, status, errorThrown){
            if(xhr.status == 404){
                alert("Username oder Password unbekannt!");
            } else{
                alert("Es ist ein Fehler aufgetreten!");
            }
        })
}

function sendLogout(){
        $
            .ajax({
                url: "./resource/user/logout",
                dataType: "text",
                type: "DELETE",
            })
            .done(function () {
                alert("Erfolgreich ausgeloggt!");
                window.location.href = "./login.html";
            })
            .fail(function (xhr, status, errorThrown){

            })
}