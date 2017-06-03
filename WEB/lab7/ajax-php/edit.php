<?php
require_once("dbcontroller.php");
$db_handle = new DBController();
$sql = "UPDATE recipes set " . $_POST["column"] . " = '".$_POST["editval"]."' WHERE  id=".$_POST["id"];
$result = $db_handle->executeUpdate($sql);
?>
