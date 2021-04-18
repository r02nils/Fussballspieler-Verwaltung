/**
 * spieler View
 * @author  Nils Rothenbühler
 */

/**
 * register listeners and load the spieler data
 */

$(document).ready(function (){
    loadSpieler();
});

/**
 *  loads the data of this spieler
 *
 */
function loadSpieler() {
    const queryString = window.location.search;
    var id = queryString;
    if (id !== null) {
        $
            .ajax({
                url: "./resource/spieler/readID" + id,
                dataType: "json",
                type: "GET"
            })
            .done(showSpieler)
            .fail(function (xhr, status, errorThrown) {
                if (xhr.status == 403) {
                    window.location.href = "./login.html";
                } else if (xhr.status == 404) {
                    $("#message").text("Kein Spieler gefunden!");
                } else {
                    window.location.href = "./spielerList.html";
                }
            })
    }

}

/**
 * shows the data of this spieler
 * @param  spieler data to be shown
 */
function showSpieler(spieler) {
    document.getElementById("id").innerText = spieler.spielerID;
    document.getElementById("name").innerText = spieler.name;
    document.getElementById("nation").innerText = spieler.nat.nat;
    document.getElementById("position").innerText = spieler.pos.pos;
    document.getElementById("team").innerText = spieler.team.team;
    document.getElementById("liga").innerText = spieler.team.liga.liga;
}
