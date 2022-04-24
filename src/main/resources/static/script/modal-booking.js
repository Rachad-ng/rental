
$(function () {

    $(".booking-car").click(function () {

        const idBooking = $(this).find("a")[1].classList[0];
        const token = $("meta[name='_csrf']").attr("content");
        let message;

        if(idBooking && isFinite(idBooking) && idBooking > 0){

            $.get("/agent/booking-car-info?_csrf=" + token, {idBooking: idBooking}, function (data, status){

                if(status === 'success'){
                    let result = JSON.parse(data);
                    let bookingCarDto = result.bookingCarDto;
                    if(bookingCarDto != null)
                        showCarInfo(bookingCarDto);
                    else if(result.errorMessage !== undefined)
                        showErrorMessage(result.errorMessage);

                }else{
                    $(".message-error").text("Aucune information pour cette véhicule");
                }

            });

        }else
            message = "Aucune information pour cette véhicule";

        $(".message-error").text(message);
    });

    $(".booking-user").click(function () {

        const idBooking = $(this).find("a")[0].classList[0];
        const token = $("meta[name='_csrf']").attr("content");
        let message;

        if(idBooking && isFinite(idBooking) && idBooking > 0){

            $.get("/agent/booking-client-info?_csrf=" + token, {idBooking: idBooking}, function (data, status){

                if(status === 'success'){
                    let result = JSON.parse(data);
                    let clientDto = result.clientDto;
                    let errorMessage = result.errorMessage
                    if(clientDto != null)
                        showClientInfo(clientDto);
                    else if(errorMessage !== undefined)
                        showErrorMessage(errorMessage);

                }else{
                    $(".message-error").text("Aucune information pour cette réservation");
                }

            });

        }else
            message = "Aucune information pour cette reservation";

        $(".message-error").text(message);
    });

    $(".booking-manager a").click(function () {

        const idBooking = this.classList[0];
        const token = $("meta[name='_csrf']").attr("content");
        let message;

        if(idBooking && isFinite(idBooking) && idBooking > 0){
            $(".btn-add ").attr("id", idBooking);
            showBookingManager();
        }else
            message = "Aucune information pour cette reservation";

        $(".message-error").text(message);
    });


    function showClientInfo(clientDto){

        const errorItem = $(".message-error");
        errorItem.attr("class", "message-error text-danger text-center d-none");

        $("#firstname").text(clientDto.firstname);
        $("#lastname").text(clientDto.lastname);
        $("#phone-number").text(clientDto.tel);
        $("#email").text(clientDto.email);

        const date = new Date(clientDto.registrationDate.date.year, clientDto.registrationDate.date.month, clientDto.registrationDate.date.day);
        $("#registration-date").text(formatDate(date));

        $("#modal-client-content").css("display", "block");
        $(".modal-list").css("display", "block");
    }

    function showCarInfo(carData){

        const errorItem = $(".message-error");
        errorItem.attr("class", "message-error text-danger text-center d-none");

        $("#mark").text(carData.model.mark.libelle);
        $("#model").text(carData.model.libelle);
        $("#registration-number").text(carData.registrationNumber);
        $("#price").text(carData.pricePerDay);
        $("#mileage").text(carData.mileage);
        $("#oil-change-mileage").text(carData.oilChange.maxMileage);

        var date = new Date(carData.assurance.dueDate.date.year, carData.assurance.dueDate.date.month, carData.assurance.dueDate.date.day);
        $("#assurance").text(formatDate(date));

        date = new Date(carData.technicalVisit.dueDate.date.year, carData.technicalVisit.dueDate.date.month, carData.technicalVisit.dueDate.date.day);
        $("#technical-visit").text(formatDate(date));

        date = new Date(carData.carSticker.dueDate.date.year, carData.carSticker.dueDate.date.month, carData.carSticker.dueDate.date.day);
        $("#car-sticker").text(formatDate(date));


        $("#modal-car-content").css("display", "block");
        $(".modal-list").css("display", "block");
    }

    function showBookingManager(){

        const errorItem = $(".message-error");
        errorItem.attr("class", "message-error text-danger text-center d-none");

        const messageSuccesItem = $(".message-success");
        messageSuccesItem.attr("class", "message-success text-success text-center my-5 d-none");

        $("#modal-booking-manager-form").css("display", "block");
        $(".btn-add").css("display", "block");
    }

    function showErrorMessage(message){

        $(".btn-add").css("display", "none");
        $(".modal-list").css("display", "none");
        $("#modal-booking-manager-form").css("display", "none");

        const errorItem = $(".message-error");
        errorItem.text(message);
        errorItem.attr("class", "message-error text-danger text-center my-5");
    }


    /* Format date functions start */

    function formatDate(date) {
        return [
            padTo2Digits(date.getDate()),
            padTo2Digits(date.getMonth() + 1),
            date.getFullYear(),
        ].join('/');
    }
    function padTo2Digits(num) {
        return num.toString().padStart(2, '0');
    }

    /* Format date functions end */


    // Gérer une reservation (Confirmer, archiver ou annuler )
    $(".btn-add").click(function (){

        const token = $("meta[name='_csrf']").attr("content");
        const idBooking = this.id;

        const confirmOperation = $("input:radio[id='confirm-booking']");
        const startOperation = $("input:radio[id='start-location']");
        const cancelOperation = $("input:radio[id='cancel-booking']");

        if(confirmOperation.is(':checked')){
            $.ajax({
                url: '/agent/confirm-booking?_csrf=' + token,
                type: 'PUT',
                data: {idBooking: idBooking},
                success: function (result){

                    const message = JSON.parse(result);
                    if(message.successMessage){
                        const successMessage = message.successMessage;
                        const stateBoard = $("#state-" + idBooking);
                        stateBoard.attr("class", "confirmed");
                        stateBoard.text('Confirmé');
                        showSuccessMessage(successMessage);
                    }else{
                        const errorMessage = message.errorMessage;
                        showErrorMessage(errorMessage);
                    }
                }
            });
        }else if(cancelOperation.is(':checked')){
            // Annuler une reservation
            if(confirm("Cette opération est irréversible ! Etes-vous sur ?")){
                $.ajax({
                    url: '/delete-booking?_csrf=' + token,
                    type: 'DELETE',
                    data: {idBooking: idBooking},
                    success: function (){
                        const successMessage = "La opération est effectuée avec success";
                        showSuccessMessage(successMessage);
                        location.reload();
                    }
                });
            }
        }else if(startOperation.is(':checked')){

        }
    });


    function showSuccessMessage(message){
        const messageSuccesItem = $(".message-success");
        messageSuccesItem.text(message);

        $("#modal-booking-manager-form").css("display", "none");
        $(".btn-add").css("display", "none");

        messageSuccesItem.attr("class", "message-success text-success text-center my-5");
    }


    $(".booking-item").hover(function(){
        $(this).css("background-color", "white");
        $(this).find("*").css("color", "#0aa5cd");
    }, function(){
        $(this).css("background-color", "#0aa5cd");
        $(this).find("*").css("color", "white");
    });

});