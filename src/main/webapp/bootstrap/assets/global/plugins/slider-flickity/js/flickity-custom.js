//ÍÆ¼öÊËÑù
$("document").ready(function () {
    var $items = $(".gallery-cell .row .item");
    $items.click(function () {
        $items.removeClass("sel");
        $(this).addClass("sel");
        var selID = $(".gallery-cell .row .sel").attr("id");
        $("#selVal").val(selID);
        //alert($("#selVal").val());
    })
})