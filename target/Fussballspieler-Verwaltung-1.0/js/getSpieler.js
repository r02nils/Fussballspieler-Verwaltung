$(document).ready(
    function (){
        loadSpieler();
    }
);

function loadSpieler() {
    $
        .ajax({
            url: "http://localhost:8080/Fussballspieler-Verwaltung-1.0/resource/spieler/list",
            type: "GET",
            dataType: "json"
        })

        .done(doIt)

        .fail(function (xhr, status, errorThrown) {
            alert("Error");
        })
}
function doIt(spielerData){
    var table = document.getElementById("tableBody");

    var row = table.insertRow(0);

    var cell1 = row.insertRow(0);
    var cell2 = row.insertRow(1);
    var cell3 = row.insertRow(2);
    var cell4 = row.insertRow(3);
    var cell5 = row.insertRow(4);
    var cell6 = row.insertRow(5);

    cell1.innerHTML = spielerData["spielerID"];
    cell2.innerHTML = spielerData["name"];
    cell3.innerHTML = spielerData["nat"];
    cell4.innerHTML = spielerData["pos"];
    cell5.innerHTML = spielerData["team"];
    cell6.innerHTML = spielerData["liga"];
}