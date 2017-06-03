<?php
require_once("dbcontroller.php");
$db_handle = new DBController();

$sql = "SELECT * from recipes";
$recipes = $db_handle->runSelectQuery($sql);

$sql = "SELECT distinct type from recipes";
$types = $db_handle->runSelectQuery($sql);
?>
<script src="https://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>
<script>
    var nameList = [];
function createNew() {
	$("#add-more").hide();
	var data =   '<table id="table-add" class="tbl">'+
        '<thead> <tr>'+
        '<th class="table-header">Author</th>'+
        '<th class="table-header">Name</th>'+
        '<th class="table-header">Type</th>'+
        '<th class="table-header">Recipe</th>'+
        '<th class="table-header">Actions</th>'+
        '</tr>'+
        '</thead>'+
            '<tbody>'+
        '<tr class="table-row" id="new_row_ajax">' +
	'<td contenteditable="true" id="txt_author" onBlur="addToHiddenField(this,\'author\')" onClick="editRow(this);"></td>' +
	'<td contenteditable="true" id="txt_name" onBlur="addToHiddenField(this,\'name\')" onClick="editRow(this);"></td>' +
    '<td contenteditable="true" id="txt_type" onBlur="addToHiddenField(this,\'type\')" onClick="editRow(this);"></td>' +
    '<td contenteditable="true" id="txt_recipe" onBlur="addToHiddenField(this,\'recipe\')" onClick="editRow(this);"></td>' +
    '<td>' +
        '<input type="hidden" id="author" />' +
        '<input type="hidden" id="name" />' +
        '<input type="hidden" id="type" />' +
        '<input type="hidden" id="recipe" />' +
        '<span id="confirmAdd">' +
        '<a onClick="addToDatabase()" class="ajax-action-links">Save</a> / <a onclick="cancelAdd();" class="ajax-action-links">Cancel</a></span></td>' +
	'</tr>'+
        '</tbody></table>';

	console.log("TOADD: ",data);
  $("#row-content").html(data);
}
function cancelAdd() {
	$("#add-more").show();
	$("#new_row_ajax").remove();
	$("#table-add").remove();
}
function editRow(editableObj) {
  $(editableObj).css("background","#FFF");
}

function saveToDatabase(editableObj,column,id) {
    console.log("col",column,"obj=",editableObj,"id=",id);
  $.ajax({
    url: "edit.php",
    type: "POST",
    data:'column='+column+'&editval='+$(editableObj).text()+'&id='+id,
    success: function(data){
      $(editableObj).css("background","#FDFDFD");
    }
  });
}
function addToDatabase() {
  var author = $("#author").val();
  var name = $("#name").val();
  var type = $("#type").val();
  var recipe = $("#recipe").val();
  console.log('author='+author+'&name='+name+'&type='+type+'&recipe='+recipe);
    $.ajax({
		url: "add.php",
		type: "POST",
		data:'author='+author+'&name='+name+'&type='+type+'&recipe='+recipe,
		success: function(data){
		    console.log("data:",data);
		  $("#new_row_ajax").remove();
		  $("#table-add").remove();
		  $("#add-more").show();		  
		  $("#table-body").append(data);
		},
        error: function(err){
		    console.log("Err: ",err);
        }
	  });

}
function addToHiddenField(addColumn,hiddenField) {
	var columnValue = $(addColumn).text();
	$("#"+hiddenField).val(columnValue);
}

function deleteRecord(id) {
	if(confirm("Are you sure you want to delete this row?")) {
		$.ajax({
			url: "delete.php",
			type: "POST",
			data:'id='+id,
			success: function(data){
			  $("#table-row-"+id).remove();
			}
		});
		back();
	}
}
function showRow(id,author,name,type,recipe){
    $('#table-content').addClass('hidden');
    nameList.push(name);
    var content = '<table class="tbl">'+
  '<thead> <tr>'+
	  '<th class="table-header">Author</th>'+
      '<th class="table-header">Name</th>'+
      '<th class="table-header">Type</th>'+
	  '<th class="table-header">Recipe</th>'+
	  '<th class="table-header">Actions</th>'+
	'</tr>'+
  '</thead>'+
  '<tbody id="table-body" class="row-table-body">'+
	  '<tr class="table-row" id="table-row-"+id >'+
		'<td contenteditable="true" onBlur="saveToDatabase('+this+','+author+','+id+')" onClick="editRow('+this+');">'+author+'</td>'+
		'<td contenteditable="true" onBlur="saveToDatabase('+this+','+name+','+id+')" onClick="editRow('+this+');">'+name+'</td>'+
		'<td contenteditable="true" onBlur="saveToDatabase('+this+','+type+','+id+')" onClick="editRow('+this+');">'+type+'</td>'+
		'<td contenteditable="true" onBlur="saveToDatabase('+this+','+recipe+','+id+')" onClick="editRow('+this+');">'+recipe+'</td>'+
        '<td><a class="ajax-action-links" onclick="deleteRecord('+id+');">Delete</a></td>'+
	  '</tr>'+

  '</tbody>'+
'</table>'+
'<div  class="ajax-action-button" onclick="back()">Back</div>';

    $('#history-table-body').append("<tr><td>"+name+"</td></tr>");
    $('#row-content').html(content);
}
function back(){
    $('#row-content').html("");
    $('#table-content').removeClass('hidden');
}
function showType(type){
    var rows = '';
    $.ajax({
        url: "get.php",
        type: "GET",
        data:'type='+type,
        success: function(data){

            $('#table-content').addClass('hidden');
            nameList.push(name);
            var content = '<table class="tbl">'+
                '<thead> <tr>'+
                '<th class="table-header">Author</th>'+
                '<th class="table-header">Name</th>'+
                '<th class="table-header">Type</th>'+
                '<th class="table-header">Recipe</th>'+
                '<th class="table-header">Actions</th>'+
                '</tr>'+
                '</thead>'+
                '<tbody id="table-body" class="row-table-body">'+
                '<tr class="table-row" id="table-row-"+id >'+
                '<td contenteditable="true" onBlur="saveToDatabase('+this+','+author+','+id+')" onClick="editRow('+this+');">'+author+'</td>'+
                '<td contenteditable="true" onBlur="saveToDatabase('+this+','+name+','+id+')" onClick="editRow('+this+');">'+name+'</td>'+
                '<td contenteditable="true" onBlur="saveToDatabase('+this+','+type+','+id+')" onClick="editRow('+this+');">'+type+'</td>'+
                '<td contenteditable="true" onBlur="saveToDatabase('+this+','+recipe+','+id+')" onClick="editRow('+this+');">'+recipe+'</td>'+
                '<td><a class="ajax-action-links" onclick="deleteRecord('+id+');">Delete</a></td>'+
                '</tr>'+
                rows+
                '</tbody>'+
                '</table>'+
                '<div  class="ajax-action-button" onclick="back()">Back</div>';

            $('#history-table-body').append("<tr><td>"+name+"</td></tr>");
            $('#row-content').html(content);
        },
        error:function(err){
            console.log(err);
        }
    });

}
</script>

<style>
body{width:615px;}
#table-content .tbl{ width: 20%;font-size:0.9em;background-color: #f5f5f5;}
#row-content .tbl{width: 98%;font-size:0.9em;background-color: #f5f5f5;}
.tbl th.table-header {padding: 5px;text-align: left;padding:10px;}
.tbl .table-row td {padding:10px;background-color: #FDFDFD;}
.ajax-action-links {color: #09F; margin: 10px 0px;cursor:pointer;}
.ajax-action-button {border:#094 1px solid;color: #09F; margin: 10px 0px;cursor:pointer;display: inline-block;padding: 10px 20px;}
#history-table { position:fixed; width:20%; background-color: #f5f5f5; top:15px;right:45px; }
.hidden { display: none;}
</style>
<div id="table-content">
<table class="tbl">
  <thead>
	<tr>
	  <th class="table-header">Name</th>
      </tr>
  </thead>
  <tbody id="table-body">
	<?php
	if(!empty($types)) {
	foreach($types as $k=>$v) {
	  ?>
	  <tr class="table-row" id="table-row-<?php echo $types[$k]; ?>">
		<td
            onClick="showType('<?php echo $types[$k]["type"]; ?>')">
            <?php echo $recipes[$k]['type']; ?>
        </td>
      </tr>

        <?php
	}
	}
	?>
  </tbody>
</table >
<div class="ajax-action-button" id="add-more" onClick="createNew();">Add More</div>

<table id="history-table">
    <thead>
        <th>History</th>
    </thead>
    <tbody id="history-table-body">

    </tbody>
</table>
</div>
<div id="row-content"></div>
