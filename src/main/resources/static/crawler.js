$(document).ready(function(){

    $("button#Get").click(function(){
        var input = $("#link_text").val();
        $("#msg").html("Crawling...");

        $.ajax({
            url: "http://localhost:8080/wikiCrawler/api/crawl?input="+input,
            error: function (jqXHR, textStatus, errorThrown) {
                $("#msg").html("Status: "+jqXHR.status+"  "+jqXHR.responseText);
            },
            success: function (data, status) {
                $("#msg").html(data.message);
            },
        });
    });

    $("button#Reset").click(function(){
            $("#link_text").val("");
            $("#msg").html("");
        });


});