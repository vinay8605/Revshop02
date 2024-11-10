<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products in Category</title>
</head>
<body>
    <h1>Products in Category: ${category}</h1>
    
    <ul>
        <c:forEach var="product" items="${products}">
            <li>${product.name} - ${product.price} - ${product.quantity}</li>
        </c:forEach>
    </ul>
    
    <br>
    <a href="/admin/addCategory">Add New Category</a>
    <br>
    <a href="/admin/productsByCategory?category=all">Back to All Products</a>
</body>
</html>
