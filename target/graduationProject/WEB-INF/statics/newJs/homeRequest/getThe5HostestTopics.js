$(document).ready(function () {
    $.ajax({
        type: "POST",
        async: false,
        url: "topics/getTheNicestTopics",
        dataType: "json",
        success: function (data, status) {
            console.log(data[0].niceTopic);
            console.log(data.length);
            var d = "";
            for(i=0;i<data.length;i++){
                d+= "<li>\n" +
                    "<a href=\"/617/newDetail"+data[i].id+".617museum\">"+data[i].title+"</a>\n" +
                    "</li>";
            }
            $(".nicest").html(d);
        }
    })
});