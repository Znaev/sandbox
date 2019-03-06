$(document).ready(function(){
    $.ajax({
            url: "/payments",
            type: "GET",
            contentType: "application/json",
            success: function(data) {
                $.each(data,function(index,value){
                    $('.container-status')
                    .append("<div class='status'>"
                    +"<span><h4>ID: "+value.testPayPayment.id+ "</h4></span>"
                    +"<span><h4>Payer: "+value.sandboxPayment.payer.email+"</h4></span>"
                    +"<span><h4>Value: "+value.sandboxPayment.transaction.amount.value+"</h4></span>"
                    +"<span><h4>Currency: "+value.sandboxPayment.transaction.amount.currency+"</h4></span>"
                    +"<span><h4>Status: "+value.testPayPayment.state+"</h4></span>"
                    +"</div>")
                })
            }
    });
})