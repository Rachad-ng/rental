
$(function(){

    // Sign-in form script Start    ************************************************

    const signinForm = $("#signinForm");
    const role = $("input:radio[name='role']");
    const agentData = $(".agent-data");
    const agentFormItems = $(".agent-form-item");

    // Cacher et désactiver le formulaire des informations spécifique aux agents
    if($("#client").is(':checked')){
        agentData.hide();
        agentFormItems.prop("disabled", true);
    }
    else
        signinForm.attr("action", "/signinAgent");   // Adapter action de formulaire en cas d'erreur d'un agent

    // Adapter le formulaire selon le type du compte souhaité (Agent ou client)
    role.change(function (){
        if (this.value === 'ROLE_AGENT') {
            agentData.show();
            signinForm.attr("action", "/signinAgent");
            agentFormItems.prop("disabled", false);
        }
        else if (this.value === 'ROLE_CLIENT') {
            agentData.hide();
            signinForm.attr("action", "/signinClient");
            agentFormItems.prop("disabled", true);
        }
    });

    // Sign-in form script End    ************************************************

});