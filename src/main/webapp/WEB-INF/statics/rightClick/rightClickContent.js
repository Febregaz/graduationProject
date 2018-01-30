/*设置右键内容*/
/*右键出现的上下文菜单的每一项都是由一个有着固定键值的对象创建的，
这个对象只支持三个关键键值：text, func, data，
分别表示菜单文字，点击菜单的方法，菜单对应的二级菜单数据*/
var imageMenuData = [
    [{
        text: "新建目录",
        func: function () {
            console.log("new");
        }
    }, {
        text: "修改目录",
        func: function() {
            $(this).css("padding", "10px");
        }
    }, {
        text: "删除目录",
        func: function() {
            $(this).css("background-color", "#beceeb");
        }
    }, {
        text: "新建元模型",
        func: function() {
            $(this).css("padding", "10px");
        }
    }, {
        text: "修改元模型",
        func: function() {
            $(this).css("padding", "10px");
        }
    }, {
        text: "作废元模型",
        func: function() {
            $(this).css("padding", "10px");
        }
    }, {
        text: "删除元模型",
        func: function() {
            $(this).css("padding", "10px");
        }
    }, {
        text: "导出模型",
        func: function() {
            $(this).css("padding", "10px");
        }
    }, {
        text: "查看历史纪录",
        func: function() {
            $(this).css("padding", "10px");
        }
    }]
];
$("#test").smartMenu(imageMenuData);
$("#test1").smartMenu(imageMenuData);