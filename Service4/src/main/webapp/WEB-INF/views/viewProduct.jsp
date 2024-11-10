<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            color: #333;
        }
        .container {
            width: 60%;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        img {
            max-width: 100%;
            height: auto;
        }
        h1 {
            text-align: center;
        }
        p {
            line-height: 1.6;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Product Details</h1>
    
    <!-- Display Product details using JSTL and Spring EL -->
   <p><strong>Product ID:</strong> ${product.id}</p>
<p><strong>Name:</strong> ${product.name}</p>
<p><strong>Description:</strong> ${product.description}</p>
<p><strong>Price:</strong> $${product.price}</p>
<p><strong>Quantity:</strong> ${product.quantity}</p>
<p><strong>Category:</strong> ${product.category}</p>


    <!-- Display product image if available -->
    <c:if test="${not empty product.imageUrl}">
        <img src="${product.imageUrl}" alt="Product Image">
    </c:if>
    <c:if test="${empty product.imageUrl}">
        <p>No image available for this product.</p>
    </c:if>

    <br>
    <a href="/admin">Back to Admin Dashboard</a>
</div>

</body>
</html>
