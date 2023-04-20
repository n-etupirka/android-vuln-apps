<!DOCTYPE html>
<html>
<head>
    <title>Notifications</title>
</head>

<body>
<h1>Notifications</h1>

<?php
$token = $_SERVER['HTTP_TOKEN'];
$db = new SQLite3('db/users.db');
$stmt = $db->prepare('SELECT email FROM users WHERE sessionid = :sessionid');
$stmt->bindValue(':sessionid', $token, SQLITE3_TEXT);
$result = $stmt->execute();
if ($result === false) {
    http_response_code(500);
    echo "Error: DB error";
    exit();
}

$arr = $result->fetchArray();

if (count($arr) !== 2) {
    http_response_code(500);
    echo "Error: Authentication error";
    exit();
}

echo "<p>Welcome, " . $arr['email'] . "!</p>";
echo "<p>This is your own notifications.</p>";
echo "<p>No notification to you.</p>";
?>

</body>

</html>
