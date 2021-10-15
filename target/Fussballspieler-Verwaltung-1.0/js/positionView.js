/**
 *
 * @author  Nils Rothenbühler
 */

/**
 * register listeners and load the pos data
 */
$(document).ready(function (){
    loadPosition();
});

function loadPosition() {
    const queryString = window.location.search;
    var id = queryString;
    if (id !== null) {
        $
            .ajax({
                url: "./resource/position/readID" + id,
                dataType: "json",
                type: "GET"
            })
            .done(showPosition)
            .fail(function (xhr, status, errorThrown) {
                if (xhr.status == 403) {
                    window.location.href = "./login.html";
                } else if (xhr.status == 404) {
                    $("#message").text("Keine Position gefunden!");
                } else {
                    window.location.href = "./positionList.html";
                }
            })
    }

}

function showPosition(pos) {
    document.getElementById("id").innerText = pos.posID;
    document.getElementById("name").innerText = pos.pos;
}
