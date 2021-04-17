/**
 *
 * @author  Nils Rothenbühler
 */

/**
 * register listeners and load the spieler data
 */
$(document).ready(function (){
    loadSpieler();

    /**
     * listener for submitting the form
     */
    $("#spielerEdit").submit(saveSpieler);

    /**
     * listener for button [abbrechen]
     */
    $("#cancel").click(function () {
        window.location.href = "./spielerList.html";
    });

    $("#id").change(function () {
        loadSpieler();
    });


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
                    $("#message").text("Kein Buch gefunden");
                } else {
                    window.location.href = "./spielerList.html";
                }
            })
    }

}

/**
 * shows the data of this spieler
 * @param  book  the spieler data to be shown
 */
function showSpieler(spieler) {
    $("#id").val(spieler.spielerID);
    $("#name").val(spieler.name);
    $("#nation").val(spieler.nat.nat);
    $("#position").val(spieler.pos.pos);
    $("#team").val(spieler.team.team);
    $("#liga").val(spieler.team.liga.liga);

}

/**
 * sends the book data to the webservice
 * @param form the form being submitted
 */
function saveSpieler(form) {
    form.preventDefault();

    let url = "./resource/spieler/";
    let type;

    let id = $("#id").val();
    if (id != 0) {
        type= "PUT";
        url += "update"
    } else {
        type = "POST";
        url += "create";
    }

    $
        .ajax({
            url: url,
            dataType: "text",
            type: type,
            data: $("#spielerEdit").serialize()
        })
        .done(function (jsonData) {
            window.location.href = "./spielerList.html";
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Dieser Spieler existiert nicht");
            } else {
                $("#message").text("Fehler beim Speichern des Spielers");
            }
        })
}
