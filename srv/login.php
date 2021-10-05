<?php
$body = file_get_contents('php://input');
if (empty($body)) {
    http_response_code(500);
    echo "Error: no data (JSON)";
    exit();
}
$json = json_decode($body, true);
if (is_null($json)) {
    http_response_code(500);
    echo "Error: JSON parse error";
    exit();
}
$email = $json['email'];
$password = $json['password'];

$db = new SQLite3('db/users.db');
$stmt = $db->prepare('SELECT email FROM users WHERE email = :email and password = :password');
$stmt->bindValue(':email', $email, SQLITE3_TEXT);
$stmt->bindValue(':password', $password, SQLITE3_TEXT);
$result = $stmt->execute();
if ($result === false) {
    http_response_code(500);
    echo "Error: DB error";
    exit();
}
$arr = $result->fetchArray();
if (count($arr) !== 2) {
    http_response_code(500);
    exit();
}

$sessionid = md5(mt_rand());
$stmt = $db->prepare('UPDATE users SET sessionid = :sessionid WHERE email = :email');
$stmt->bindValue(':sessionid', $sessionid, SQLITE3_TEXT);
$stmt->bindValue(':email', $email, SQLITE3_TEXT);
$result = $stmt->execute();
if ($result === false) {
    http_response_code(500);
    echo "Error: DB error";
    exit();
}

echo $sessionid;
exit();
?>
