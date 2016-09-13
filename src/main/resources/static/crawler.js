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

                var list = "";
                if(data.links){
                list += "<ul>";
                    for(i=0; i<data.links.length; i++){
                        list +="<a href='"+data.links[i]+"'><li>"+data.links[i]+"</li>";
                    }
                 list += "</ul>";
                }

                $("#links").html(list);
            },
        });
    });

    $("button#Reset").click(function(){
            $("#link_text").val("");
            $("#msg").html("");
            $("#links").html("");
        });

    $("button#ResetToExample").click(function(){
           $("#link_text").val("https://en.wikipedia.org/wiki/Paraguay");
           $("#msg").html("");
           $("#links").html("");
    });


});