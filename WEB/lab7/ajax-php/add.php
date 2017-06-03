<?php
require_once("dbcontroller.php");
$db_handle = new DBController();

if(!empty($_POST["name"])) {
    $conn = $db_handle->conn;
	$author = $conn->real_escape_string(strip_tags($_POST["author"]));
    $name = $conn->real_escape_string(strip_tags($_POST["name"]));
    $type = $conn->real_escape_string(strip_tags($_POST["type"]));
	$recipe = $conn->real_escape_string(strip_tags($_POST["recipe"]));

  $sql = "INSERT INTO recipes (author,name,type,recipe) VALUES ('" . $author . "','" . $name . "','" . $type . "','" . $recipe . "')";
  $id = $db_handle->executeInsert($sql);
	if(!empty($id)) {
		$sql = "SELECT * from recipes WHERE id = '$id' ";
		$recipes = $db_handle->runSelectQuery($sql);
	}
?>
<tr class="table-row" id="table-row-<?php echo $recipes[0]["id"]; ?>">
    <td
            onClick="showRow('<?php echo $recipes[0]["id"]; ?>',
                    '<?php echo $recipes[0]["author"]; ?>',
                    '<?php echo $recipes[0]["name"]; ?>',
                    '<?php echo $recipes[0]["type"]; ?>',
                    '<?php echo $recipes[0]["recipe"]; ?>');"><?php echo $recipes[0]["name"]; ?>
    </td>
</tr>
<?php } ?>