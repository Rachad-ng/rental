
$(function(){

    // Sign-in form script Start    ************************************************

    const signupForm = $("#signupForm");
    const role = $("input:radio[name='role']");
    const agentData = $(".agent-data");
    const agentFormItems = $(".agent-form-item");

    // Cacher et désactiver le formulaire des informations spécifique aux agents
    if($("#client").is(':checked')){
        agentData.hide();
        agentFormItems.prop("disabled", true);
    }
    else
        signupForm.attr("action", "/signupAgent");   // Adapter action de formulaire en cas d'erreur d'un agent

    // Adapter le formulaire selon le type du compte souhaité (Agent ou client)
    role.change(function (){
        if (this.value === 'ROLE_AGENT') {
            agentData.show();
            signupForm.attr("action", "/signupAgent");
            agentFormItems.prop("disabled", false);
        }
        else if (this.value === 'ROLE_CLIENT') {
            agentData.hide();
            signupForm.attr("action", "/signupClient");
            agentFormItems.prop("disabled", true);
        }
    });

    // Sign-in form script End    ************************************************
    
    
    /* ***************************** Car Creation Script Start *******************************/

    // Synchronisation du model avec la marque lors d'un changement du marque.
    $("#mark").change(function () {
        sychronizeModels(this.value);
    });

    // Fonction de synchronisation de model avec la marque.
    function sychronizeModels(idMark){
        let selectedMark = idMark;
        let token = $("meta[name='_csrf']").attr("content");

        if(isFinite(selectedMark)){
            $.post('/syncModelWithMark?_csrf=' + token,
                {idMark: selectedMark},
                function (data, status) {

                    if(status === "success" && data !== 'null'){

                        var modelDtos = JSON.parse(data);

                        // Vider la liste des series et la recharger avec les nouveux elements
                        $('#model').empty();

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

});