$(document).ready(
    function (){
        loadSpieler();
    }
);

function loadSpieler() {
    $
        .ajax({
            url: "http://localhost:8080/Fussballspieler-Verwaltung-1.0/resource/spieler/readID?id=2",
            type: "GET",
            dataType: "json"
        })

        .done(doIt)

        .fail(function (xhr, status, errorThrown) {
            alert("Error");
        })
}
function doIt(spielerData){
    document.getElementById("spieler").innerHTML = spielerData['name'];
}