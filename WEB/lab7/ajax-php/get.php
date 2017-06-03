<?php
require_once("dbcontroller.php");
$db_handle = new DBController();
$sql = "Select * from recipes WHERE  type = ".$_GET["type"];
$result = $db_handle->runSelectQuery($sql);

?>
<?php
if(!empty($result)) {
    foreach($result as $k=>$v) {
        echo $result
        ?>

        <tr class="table-row" id="table-row-'<?php echo $result[$k]["id"]; ?>' ">
            <td contenteditable="true" onBlur="saveToDatabase('+this+','+<?php echo $result[$k]["author"];?>+ ','+ <?php echo $result[$k]["id"]; ?>')" onClick="editRow('+this+');">'<?php echo $result[0]["author"]; ?>'</td>
            <td contenteditable="true" onBlur="saveToDatabase('+this+','<?php echo $result[$k]["name"]; ?>','<?php echo $result[$k]["id"]; ?>')" onClick="editRow('+this+');">'<?php echo $result[0]["name"]; ?>'</td>
            <td contenteditable="true" onBlur="saveToDatabase('+this+','<?php echo $result[$k]["type"]; ?>','<?php echo $result[$k]["id"]; ?>')" onClick="editRow('+this+');">'<?php echo $result[0]["type"]; ?>'</td>
            <td contenteditable="true" onBlur="saveToDatabase('+this+','<?php echo $result[$k]["recipe"]; ?>','<?php echo $result[$k]["id"]; ?>')" onClick="editRow('+this+');">'<?php echo $result[0]["recipe"]; ?>'</td>
            <td><a class="ajax-action-links" onclick="deleteRecord('+id+');">Delete</a></td>
        </tr>


        <?php
    }
}
?>

