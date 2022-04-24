$(function () {

    /* Cost modal script start */

    $(".cost-btn-add").click(function () {

        let token = $("meta[name='_csrf']").attr("content");
        let idCar, dateString, date, amount, costType, message, operation;

        idCar = this.id;
        dateString = $("#datepicker1").val(); 
        date = moment(dateString, 'DD/MM/YYYY', true);
        amount = $("#cost-amount").val();

        if($("input:radio[id='assurance']").is(":checked")){
            costType = "ASSURANCE";
            operation = "Assurance";
        }
        else if($("input:radio[id='technical-visit']").is(":checked")){
            costType = "TECHNICAL_VISIT";
            operation = "Visite Technique";
        }
        else if($("input:radio[id='car-sticker']").is(":checked")){
            costType = "CAR_STICKER";
            operation = "Vignette";
        }

        let costErrorMessageItem = $(".cost-message-error");

        if(date !== undefined && amount !== undefined && costType !== undefined) {
            if(date.isValid() && date > moment()){
                if(amount && isFinite(amount) && amount >= 0){

                    var costTypes = ["ASSURANCE", "TECHNICAL_VISIT", "CAR_STICKER"];
                    if(costTypes.includes(costType)){

                        $.post("/agent/add-cost?_csrf=" + token, {date: dateString, amount: amount, operation: costType, idCar: idCar}, function (data, status) {

                            if(status === 'success'){
                                costErrorMessageItem.text("");

                                let result = JSON.parse(data);

                                if(result.status === 'success'){
                                    switch (costType){
                                        case "ASSURANCE" :
                                            $("#assurance-due-date").text(dateString);
                                            break;

                                        case "TECHNICAL_VISIT" :
                                            $("#technical-visit-due-date").text(dateString);
                                            break;

                                        case "CAR_STICKER" :
                                            $("#car-sticker-due-date").text(dateString);
                                            break;
                                    }

                                    showCostDetails(result, operation, dateString, amount);
                                }
                                else
                                    costErrorMessageItem.text(result.serverMessage);

                            }else
                                costErrorMessageItem.text("Le traitement est échouée.!! Ressayez ...");
                        } );
                        
                    }else
                        message = "L'operation est invalid.!! Ressayez..";
                }else
                    message = "Le montant est invalid.!! Ressayez..";
            }else
                message = "La date est invalid.!! Ressayez..";
        }else
            message = "Error d'envoie.!! Ressayez..";

        $(".message-success").text("");
        // Message d'erreur
        costErrorMessageItem.text(message);
    });

    function showCostDetails(result, operation, dateString, amount){
        $(".message-success").text(result.serverMessage);
        $("#cost-result-type").text(operation);
        $("#cost-result-due-date").text(dateString);
        $("#cost-result-amount").text(amount + " (DH)");

        $("#modal-cost-form-body").hide();
        $(".btn-add").hide();

        $("#modal-cost-result-body").attr("class", "text-center modal-cost-result-body");
    }

    // Initialize cost form if previous result exists
    $(".new-cost-btn").click(function (){
        initializeCostModalForm();
    });

    function initializeCostModalForm(){
        $(".btn-add").show();
        $("#modal-cost-result-body").attr("class", "d-none");
        $("#modal-cost-form-body").show();
        $("#cost-amount").val("");
        $("#datepicker1").val("");
    }
    /* Cost modal script end */


    /* Maintenance modal script start */

    const oilChangeRadio = $("#oil-change");

    $(".maintenance-btn-add").click(function () {

        let token = $("meta[name='_csrf']").attr("content");
        let idCar, dateString, date, amount, maintenanceType, message, operation, comment, maxMileage;

        idCar = this.id;
        dateString = $("#datepicker3").val();
        date = moment(dateString, 'DD/MM/YYYY', true);
        amount = $("#maintenance-amount").val();

        const carRepairRadio = $("#car-repair");

        if(oilChangeRadio.is(":checked")){
            maintenanceType = oilChangeRadio.val();
            operation = "Vidange";
        }
        else if(carRepairRadio.is(":checked")){
            maintenanceType = carRepairRadio.val();
            operation = "Reparation";
        }

        let maintenanceErrorMessageItem = $(".maintenance-message-error");

        if(date !== undefined && amount !== undefined && maintenanceType !== undefined) {
            if(date.isValid()){
                if(amount && isFinite(amount) && amount >= 0){

                    let maintenanceTypes = ["OIL_CHANGE", "CAR_REPAIR"];
                    if(maintenanceTypes.includes(maintenanceType)){

                        if(maintenanceType === "CAR_REPAIR"){
                            comment = $("#maintenance-comment").val();

                            if(comment){
                                $.post("/agent/add-car-repair?_csrf=" + token, {date: dateString, amount: amount, comment: comment, idCar: idCar}, function (data, status) {

                                    if(status === 'success'){
                                        maintenanceErrorMessageItem.text("");

                                        let result = JSON.parse(data);

                                        if(result.status === 'success')
                                            showCarRepairDetails(result, comment, dateString, amount);
                                        else
                                            maintenanceErrorMessageItem.text(result.serverMessage);

                                    }else
                                        maintenanceErrorMessageItem.text("Le traitement est échouée.!! Ressayez ...");
                                } );

                            }else
                                message = "Vous devez saisir un commentaire.!! Ressayez..";

                        }else{

                            maxMileage = $("#maintenance-max-mileage").val();
                            if(maxMileage && isFinite(maxMileage)){
                                $.post("/agent/add-oil-change?_csrf=" + token, {date: dateString, amount: amount, maxMileage: maxMileage, idCar: idCar}, function (data, status) {

                                    if(status === 'success'){
                                        maintenanceErrorMessageItem.text("");

                                        let result = JSON.parse(data);

                                        if(result.status === 'success'){
                                            $("#oil-change-max-mileage").text(maxMileage);
                                            showOilChangeDetails(result, maxMileage, dateString, amount);
                                        }
                                        else
                                            maintenanceErrorMessageItem.text(result.serverMessage);

                                    }else
                                        maintenanceErrorMessageItem.text("Le traitement est échouée.!! Ressayez ...");
                                } );
                            }else
                                message = "Vous devez saisir le kilometrage maximal.!! Ressayez..";

                        }

                    }else
                        message = "L'operation est invalid.!! Ressayez..";
                }else
                    message = "Le montant est invalid.!! Ressatez..";
            }else
                message = "La date est invalid.!! Ressayez..";
        }else
            message = "Error d'envoie.!! Ressayez..";

        $(".message-success").text("");
        // Message d'erreur
        maintenanceErrorMessageItem.text(message);
    });

    /* Maintenance Type script start */
    let commentBlock = $(".maintenance-comment-block");
    let maxMileageBlock = $(".maintenance-max-mileage");
    maxMileageBlock.toggle();

    $("input:radio[name='maintenance-type']").change(function (){
        if(oilChangeRadio.is(":checked"))
            commentBlock.toggle("slow", "linear", maxMileageBlock.toggle("slow"));
        else
            commentBlock.toggle("slow", "linear", maxMileageBlock.toggle());
    });
    /* Maintenance Type script end */

    function showOilChangeDetails(result, maxMileage, dateString, amount){

        $(".message-success").text(result.serverMessage);
        $("#maintenance-result-type").text("Vidange");
        $("#maintenance-result-date").text(dateString);
        $("#maintenance-result-amount").text(amount + " (DH)");
        $("#maintenance-result-max-mileage").text(maxMileage + " (Km)");
        $("#maintenance-result-max-mileage").show();

        $("#modal-maintenance-form-body").hide();
        $(".maintenance-result-comment").hide();
        $(".btn-add").hide();

        $("#modal-maintenance-result-body").attr("class", "text-center modal-cost-result-body");
    }

    function showCarRepairDetails(result, comment, dateString, amount){
        $(".message-success").text(result.serverMessage);
        $("#maintenance-result-type").text("Réparation");
        $("#maintenance-result-date").text(dateString);
        $("#maintenance-result-amount").text(amount + " (DH)");
        $("#maintenance-result-comment").text(comment);
        $("#maintenance-result-comment").show();

        $("#modal-maintenance-form-body").hide();
        $(".maintenance-result-max-mileage").hide();
        $(".btn-add").hide();

        $("#modal-maintenance-result-body").attr("class", "text-center modal-cost-result-body");
    }

    // Initialize maintenance form if previous result exists
    $(".new-maintenance-btn").click(function (){
        initializeMaintenanceModalForm();
    });

    function initializeMaintenanceModalForm(){
        $(".btn-add").show();
        $("#modal-maintenance-result-body").attr("class", "d-none");
        $("#modal-maintenance-form-body").show();
        $("#maintenance-amount").val("");
        $("#datepicker3").val("");
        $("#maintenance-max-mileage").val("");
        $("#maintenance-comment").val("");
    }

    /* Maintenance modal script start */

});