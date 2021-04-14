/**
 * view-controller for bookshelf.html
 *
 * M133: Bookshelf
 *
 * @author  Marcel Suter
 */

/**
 * register listeners and load all books
 */
$(document).ready(function () {
    alert("1");
    loadBooks();

    /**
     * listener for buttons within shelfForm
     */
    $("#shelfForm").on("click", "button", function () {
        if (confirm("Wollen Sie dieses Buch wirklich löschen?")) {
            deleteBook(this.value);
        }
    });



});

function loadBooks() {
    alert("2");
    $
        .ajax({
            url: "http://localhost:8080/Fussballspieler-Verwaltung-1.0/resource/spieler/list",
            dataType: "json",
            type: "GET"
        })
        .done(showBooks)
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 403) {
                window.location.href = "./login.html";
            } else if (xhr.status == 404) {
                $("#message").text("keine Bücher vorhanden");
            }else {
                $("#message").text("Fehler beim Lesen der Bücher");
            }
        })

}

/**
 * shows all books as a table
 *
 * @param bookData all books as an array
 */
function showBooks(bookData) {

    let table = document.getElementById("tableBody");
    clearTable(table);

    $.each(bookData, function (id, spieler) {
        if (spieler.name) {
            alert("t")
            let row = table.insertRow(-1);

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
            cell.innerHTML = "<a href='./bookedit.html?uuid=" + 1 + "'>Bearbeiten</a>";

            cell = row.insertCell(-1);
            cell.innerHTML = "<button type='button' id='delete_" + 1 + "' value='" + 1 + "'>Löschen</button>";


        }
    });
}

function clearTable(table) {
    while (table.hasChildNodes()) {
        table.removeChild(table.firstChild);
    }
}


/**
 * send delete request for a book
 * @param bookUUID
 */
function deleteBook(bookUUID) {
    $
        .ajax({
            url: "./resource/book/delete?uuid=" + bookUUID,
            dataType: "text",
            type: "DELETE",
        })
        .done(function (data) {
            loadBooks();
            $("#message").text("Buch gelöscht");

        })
        .fail(function (xhr, status, errorThrown) {
            $("#message").text("Fehler beim Löschen des Buchs");
        })
}