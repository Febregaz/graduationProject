$(document).ready(function () {
    $.ajax({
        type : "POST",
        async : false,
        url : "topics/getTheNewestTopics",
        dataType : "json",
        success : function(data, status) {
            console.log(data[0].niceTopic);
            console.log(data.length);
            //window.location.reload();
            var d = "";
            for(i=0;i<data.length;i++){
                var date = new Date(data[i].topicTime);
                Y = date.getFullYear() + '-';
                M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                D = date.getDate() + ' ';
                h = date.getHours() + ':';
                m = date.getMinutes() + ':';
                s = date.getSeconds();
                d+="<div style=\"border:1px solid #666666;background:#eef3f7\"><article class=\"post post-1\">\n" +
                    "                        <header class=\"entry-header\">\n" +
                    "                            <h1 class=\"entry-title\">\n" +
                    "                                <a href=\"#\">"+data[i].title+"</a>\n" +
                    "                            </h1>\n" +
                    "                            <div class=\"entry-meta\">\n" +
                    "                                <span class=\"post-category\"><a href=\"#\">"+data[i].topicsType.typesCategory.namee+"/"+data[i].topicsType.name+"</a></span>\n" +
                    "                                <span class=\"post-date\"><a href=\"#\"><time class=\"entry-date\"\n" +
                    "                                                                          datetime=\"2012-11-09T23:15:57+00:00\">"+Y+M+D+h+m+s+"</time></a></span>\n" +
                    "                                <span class=\"post-author\"><a href=\"#\">"+data[i].topicsUser.nickname+"</a></span>\n" +
                    "                                <span class=\"comments-link\"><a href=\"#\"></a></span>\n" +
                    "                                <span class=\"views-count\"><a href=\"#\">文章价值"+data[i].integral+"金币</a></span>\n" +
                    "                            </div>\n" +
                    "                        </header>\n" +
                    "                        <div class=\"entry-content clearfix\">\n" +
                    "                            <p></p>\n" +
                    "                            <div class=\"read-more cl-effect-14\">\n" +
                    "                                <a href=\"/617/newDetail"+data[i].id+".617museum\" class=\"more-link\">继续阅读 <span class=\"meta-nav\">→</span></a>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </article></div>";
            }
            $(".newest").html(d);
        }
    });
    $('#back-top').hide();
    $('#back-bottom').hide();

    //当滚动条的垂直位置距顶部100像素一下时，跳转链接出现，否则消失
    $(window).scroll(function() {
        if ($(window).scrollTop() > 100) {
            $('#back-top').fadeIn(1000);
            $('#back-bottom').fadeIn(1000);
        } else {
            $("#back-top").fadeOut(1000);
            $("#back-bottom").fadeOut(1000);
        }
    });

    //点击跳转链接，滚动条跳到0的位置，页面移动速度是1000
    $("#back-top").click(function() {
        $("html,body").animate({
            scrollTop: '0'
        }, 1000);
        return false; //防止默认事件行为
    })

    $("#back-bottom").click(function() {
        $("html,body").animate({
            scrollTop: document.body.clientHeight
        }, 1000);
        return false; //防止默认事件行为
    })
})