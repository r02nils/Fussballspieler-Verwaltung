/**
 *
 * @author  Nils Rothenbühler
 */

/**
 * register listeners and load all spieler
 */
$(document).ready(function () {
    loadPositions();

    /**
     * listener for buttons within shelfForm
     */
    $("#shelfFormPos").on("click", "button", function () {
        if (confirm("Wollen diese Position wirklich löschen")) {
            deletePosition(this.value);
        }
    });



});

function loadPositions() {
    $
        .ajax({
            url: "http://localhost:8080/Fussballspieler-Verwaltung-1.0/resource/position/list",
            dataType: "json",
            type: "GET"
        })
        .done(showPosition)
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
 * shows all books as a table
 *
 * @param showPosition all books as an array
 */
function showPosition(positionData) {

    let table = document.getElementById("tableBodyPos");
    clearTable(table);
    const cookie = document.cookie;

    $.each(positionData, function (id, pos) {
        if (pos.pos) {
            let row = table.insertRow(-1);

            if(cookie == "userRole=admin"){
                cell = row.insertCell(-1);
                cell.innerHTML = "<a href='./positionView.html?id=" + pos.posID + "'>"+pos.pos+"</a>";

                cell = row.insertCell(-1);
                cell.innerHTML = "<a href='./positionEdit.html?id=" + pos.posID + "'>Bearbeiten</a>";

                cell = row.insertCell(-1);
                cell.innerHTML = "<button type='button' id='delete_" + pos.posID + "' value='" + pos.posID + "'>Löschen</button>";
            }
            else if(cookie == "userRole=read"){
                cell = row.insertCell(-1);
                cell.innerHTML = "<a href='./positionView.html?id=" + pos.posID + "'>"+pos.pos+"</a>";

                cell = row.insertCell(-1);
                cell.innerHTML = "Bearbeiten";

                cell = row.insertCell(-1);
                cell.innerHTML = "<button disabled type='button' id='delete_" + pos.posID + "' value='" + pos.posID + "'>Löschen</button>";
            }
            else{
                cell = row.insertCell(-1);
                cell.innerHTML = pos.pos;

                cell = row.insertCell(-1);
                cell.innerHTML = "Bearbeiten";

                cell = row.insertCell(-1);
                cell.innerHTML = "<button disabled type='button' id='delete_" + pos.posID + "' value='" + pos.posID + "'>Löschen</button>";
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
 * send delete request for a position
 * @param id
 */
function deletePosition(id) {
    $
        .ajax({
            url: "./resource/position/delete?id=" + id,
            dataType: "text",
            type: "DELETE",
        })
        .done(function (data) {
            loadPositions();
            $("#message").text("Position gelöscht");

        })
        .fail(function (xhr, status, errorThrown) {
            $("#message").text("Fehler beim Löschen der Position");
        })
}