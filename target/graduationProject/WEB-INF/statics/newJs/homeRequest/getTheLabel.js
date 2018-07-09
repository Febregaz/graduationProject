$(document).ready(function () {
    $.ajax({
        type: "POST",
        async: false,
        url: "topics/getTheLabel",
        dataType: "json",
        success: function (data, status) {
            console.log(data[0].niceTopic);
            console.log(data.length);
            var d = "";
            for(i=0;i<data.length;i++){
                if(data[i].countTopics!=0){
                    d+="<li>\n" +
                        "<a href=\"/617/newtype"+data[i].id+".617museum\">"+data[i].typesCategory.namee+"/"+data[i].name+"</a>\n" +
                        "</li>"
                }
            }
            $(".typeCateLabel").html(d);
        }
    })
});