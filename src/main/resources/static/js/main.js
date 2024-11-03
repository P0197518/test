$(document).ready(function() {

    $("#frontend-technology").datagrid({
        url: "/json/frontend-technology.json",
        method: "get",
        striped: true,
        fitColumns: true,
        singleSelect: true,
        height: 200,
        columns: [[
            {field: "name", title: "技术", align: "center", width: 100},
            {field: "note", title: "说明", align: "center", width: 100},
            {field: "href", title: "官网", align: "center", width: 100
                , formatter: function(value) {
                    return "<a href='" + value + "'>" + value + "</a>";
                }
            }
        ]]
    });

    $("#backend-technology").datagrid({
        url: "/json/backend-technology.json",
        method: "get",
        striped: true,
        fitColumns: true,
        singleSelect: true,
        height: 300,
        columns: [[
            {field: "name", title: "技术", align: "center", width: 100},
            {field: "note", title: "说明", align: "center", width: 100},
            {field: "href", title: "官网", align: "center", width: 100
                , formatter: function(value) {
                    return "<a href='" + value + "'>" + value + "</a>";
                }
            }
        ]]
    });

});