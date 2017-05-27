
$( document ).ready(function() {

    console.log('ready');
    $('tfoot tr').click(function(e){

      console.log('ok: ',e.toElement.cellIndex);
      var index=e.toElement.cellIndex;

      moveColumn($('table'), index,index+1);


    });

    $('th').click(function(){
        var table = $(this).parents('table').eq(0)

        var rows = table.find('tr').slice(1,6).toArray().sort(comparer($(this).index()))
        this.asc = !this.asc
        if (!this.asc){rows = rows.reverse()}
        for (var i = 0; i < rows.length; i++){table.append(rows[i])}
    })



    function comparer(index) {
          console.log('index: ',index);
        return function(a,b) {

            console.log("a:",a,", b:", b);
            var valA = getCellValue(a, index), valB = getCellValue(b, index)
            return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB)
        }
    }
    function getCellValue(row, index){ return $(row).children('td').eq(index).html() }

        function moveColumn(table, index,index2) {
          var cols;
          $('tr', table).each(function () {
          cols = $(this).children('th, td');
          if(index!==3){

            cols.eq(index).detach().insertBefore(cols.eq(index2));
            cols.eq(index2).detach().insertBefore(cols.eq(index));
          }else{
            cols.eq(0).detach().insertBefore(cols.eq(3));
            cols.eq(3).detach().insertBefore(cols.eq(1));
          }

          });
        }








});
