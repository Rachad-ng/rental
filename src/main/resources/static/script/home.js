// Synchronisation du model avec la marque lors d'un changement du marque.

$("#mark").change(function () {
    sychronizeModels(this.value);
});

// Fonction de synchronisation de model avec la marque.
function sychronizeModels(idMark){

    var selectedMark = idMark;
    var token = $("meta[name='_csrf']").attr("content");

    if(isFinite(selectedMark)){

        if(selectedMark == 0)
            selectedMark = 8;

        $.post('/syncModelWithMark?_csrf=' + token,
            {idMark: selectedMark},
            function (data, status) {

                if(status === "success" && data !== 'null'){

                    var modelDtos = JSON.parse(data);

                    // Vider la liste des series et la recharger avec les nouveux elements
                    $('#model').empty();

                    $('#model').append($('<option>', {
                        value: 0,
                        text: ""
                    }));

                    // Charger la liste
                    $.each(modelDtos, function(i, modelDto) {

                        $('#model').append($('<option>', {
                            value: modelDto.id,
                            text: modelDto.libelle
                        }));
                    });

                }else {
                    alert("La recupération des modèles est échoué.!!");
                    location.reload();
                }
            }
        )
    }else {
        alert("La marque que vous séléctionner n'est pas valide");
        location.reload();
    }
}

/* ***************************** Car Creation Script End *******************************/