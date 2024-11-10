<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Category</title>
</head>
<body>
    <h1>Add Category to Products</h1>
    
    <form action="/admin/addCategory" method="post">
        <label for="category">New Category:</label>
        <input type="text" name="category" id="category" required>
        <button type="submit">Add Category</button>
    </form>
    
    <br>
    <a href="/admin">Back to Products List</a>
</body>
</html>
