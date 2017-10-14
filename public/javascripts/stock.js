$(document).ready(function() {

    // チェックボックスをクリックしたらチェックが入る
    $('input[type="checkbox"]').click(function() {
        var c= $(this)
        if(c.prop('checked'))
            c.prop('checked', '');
        else
            c.prop('checked', 'checked');
    });

    // テーブルの列をクリックしてもチェックが入る
    $('table tr').click(function() {
      var c = $(this).children('td').children('input[type=checkbox]');
        if(c.prop('checked'))
            c.prop('checked', '');
        else
            c.prop('checked', 'checked');
    });



    context.init({preventDoubleContext: false});


    // チェックの入っている列のCODEを取得する
    function getSelectedIds() {
        var ids = [];
        var rows = $('input[type=checkbox]:checked').parents("tr")
        $.each(rows, function(){
            ids.push($(this).children('td[class=code]').text());
        });
        return ids;
    }
});
