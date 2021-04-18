/**
 *
 * @author  Nils Rothenbühler
 */

/**
 * register listeners and load the pos data
 */
$(document).ready(function (){
    loadPosition();

    /**
     * listener for submitting the form
     */
    $("#posEdit").submit(savePosition);

    /**
     * listener for button [abbrechen]
     */
    $("#cancel").click(function () {
        window.location.href = "./positionList.html";
    });

    $("#id").change(function () {
        loadPosition();
    });


});

/**
 *  loads the data of this pos
 *
 */
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

/**
 * shows the data of this pos
 * @param  book  the pos data to be shown
 */
function showPosition(pos) {
    $("#id").val(pos.posID);
    $("#name").val(pos.pos);
}

/**
 * sends the book data to the webservice
 * @param form the form being submitted
 */
function savePosition(form) {
    form.preventDefault();

    let url = "./resource/position/";
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
            data: $("#posEdit").serialize()
        })
        .done(function (jsonData) {
            window.location.href = "./positionList.html";
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Diese Position existiert nicht");
            } else {
                $("#message").text("Fehler beim Speichern der Position");
            }
        })
}
