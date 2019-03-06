function payment(){
    var data={};
    data.payer={};
    data.payer.email=$("#cust").val();
    data.transaction={};
    data.transaction.amount={};
    data.transaction.amount.value=$("#amnt").val();
    data.transaction.amount.currency=$("#curr").val();
    data.transaction.description=$("#desc").val()
    $.ajax({
        url: "/payment",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(data) {
            window.alert("Payment with " + data.id +" created. Check status payments on /status")
        },
        error: function (request, status, error) {
            switch(request.status){
            case 400:
                var message="";
                $.each(request.responseJSON.errors,function(index,value){
                    message+=value.defaultMessage+".";
                })
                window.alert(status+": " + message);
                break;
            case 402:
                window.alert("Sandbox internal syntax  error");
                break;
            case 401:
                window.alert("Authentication failed. Check client settings");
                break;
            case 415:
                window.alert("Unsupported media type");
                break;
            case 500:
                window.alert("TestPay server error");
                break;
            }
        }
    })
}