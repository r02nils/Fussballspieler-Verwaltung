/**
 * get Spieler
 * @author  Nils Rothenbühler
 */

/**
 * register listeners and load all spieler
 */
$(document).ready(function () {
    loadSpielers();

    /**
     * listener for buttons within shelfForm
     */
    $("#shelfForm").on("click", "button", function () {
        if (confirm("Wollen diesen Spieler wirklich löschen")) {
            deleteSpieler(this.value);
        }
    });



});

function loadSpielers() {
    $
        .ajax({
            url: "http://localhost:8080/Fussballspieler-Verwaltung-1.0/resource/spieler/list",
            dataType: "json",
            type: "GET"
        })
        .done(showSpieler)
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 403) {
                window.location.href = "./login.html";
            } else if (xhr.status == 404) {
                $("#message").text("keine Spieler vorhanden");
            }else {
                $("#message").text("Fehler beim Lesen der Spieler");
            }
        })

}

/**
 * shows all spieler as a table
 *
 * @param showSpieler all spieler as an array
 */
function showSpieler(SpielerData) {

    let table = document.getElementById("tableBody");
    clearTable(table);
    const cookie = document.cookie;

    $.each(SpielerData, function (id, spieler) {
        if (spieler.name) {
            let row = table.insertRow(-1);


            if(cookie == "userRole=admin"){

                let cell = row.insertCell(-1);
                cell.innerHTML = "<a href='./spielerView.html?id=" + spieler.spielerID + "'>"+spieler.name+"</a>";

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.nat.nat;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.pos.pos;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.team.team;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.team.liga.liga;

                cell = row.insertCell(-1);
                cell.innerHTML = "<a href='./spielerEdit.html?id=" + spieler.spielerID + "'>Bearbeiten</a>";

                cell = row.insertCell(-1);
                cell.innerHTML = "<button type='button' id='delete_" + spieler.spielerID + "' value='" + spieler.spielerID + "'>Löschen</button>";
            }
            else if(cookie == "userRole=read"){

                let cell = row.insertCell(-1);
                cell.innerHTML = "<a href='./spielerView.html?id=" + spieler.spielerID + "'>"+spieler.name+"</a>";

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.nat.nat;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.pos.pos;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.team.team;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.team.liga.liga;

                cell = row.insertCell(-1);
                cell.innerHTML = "Bearbeiten";

                cell = row.insertCell(-1);
                cell.innerHTML = "<button disabled type='button' id='delete_" + spieler.spielerID + "' value='" + spieler.spielerID + "'>Löschen</button>";
            }
            else{
                let cell = row.insertCell(-1);
                cell.innerHTML = spieler.name;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.nat.nat;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.pos.pos;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.team.team;

                cell = row.insertCell(-1);
                cell.innerHTML = spieler.team.liga.liga;

                cell = row.insertCell(-1);
                cell.innerHTML = "Bearbeiten";

                cell = row.insertCell(-1);
                cell.innerHTML = "<button disabled type='button' id='delete_" + spieler.spielerID + "' value='" + spieler.spielerID + "'>Löschen</button>";
            }

        }
    });
}

function clearTable(table) {
    while (table.hasChildNodes()) {
        table.removeChild(table.firstChild);
    }
}


/**
 * send delete request for a spieler
 * @param id
 */
function deleteSpieler(id) {
    $
        .ajax({
            url: "./resource/spieler/delete?id=" + id,
            dataType: "text",
            type: "DELETE",
        })
        .done(function (data) {
            loadSpielers();
            $("#message").text("Spieler gelöscht");

        })
        .fail(function (xhr, status, errorThrown) {
            $("#message").text("Fehler beim Löschen des Spielers");
        })
}