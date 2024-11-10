<%@ taglib prefix="c" uri="http://jakarta.apache.org/taglibs/standard/core" %>
<%@ taglib prefix="fmt" uri="http://jakarta.apache.org/taglibs/standard/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Orders</title>
</head>
<body>
    <h1>Order Management</h1>

    <!-- Filter Form -->
    <h2>Filter Orders</h2>
    <form action="/admin/orders/filter" method="get">
        <label for="date">Date:</label>
        <input type="date" id="date" name="date">
        
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName">
        
        <label for="buyerName">Buyer Name:</label>
        <input type="text" id="buyerName" name="buyerName">
        
        <button type="submit">Filter</button>
    </form>

    <br>
    
    <!-- Display Orders Table -->
    <h2>All Orders</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Buyer Name</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Order Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.buyer.name}</td>
                    <td>${order.product.name}</td>
                    <td>${order.quantity}</td>
                    <td><fmt:formatDate value="${order.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>${order.status}</td>
                    <td>
                        <!-- Form to update order status -->
                        <form action="/admin/orders/${order.orderId}/status" method="post">
                            <select name="status" required>
                                <option value="Processing" ${order.status == 'Processing' ? 'selected' : ''}>Processing</option>
                                <option value="Shipped" ${order.status == 'Shipped' ? 'selected' : ''}>Shipped</option>
                                <option value="Delivered" ${order.status == 'Delivered' ? 'selected' : ''}>Delivered</option>
                                <option value="Canceled" ${order.status == 'Canceled' ? 'selected' : ''}>Canceled</option>
                            </select>
                            <button type="submit">Update Status</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
