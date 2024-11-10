<%@ page import="java.util.List" %>
<%@ page import="com.example.entity.Buyer" %>
<%@ page import="com.example.entity.Seller" %>
<%@ page import="com.example.entity.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7fc;
            color: #333;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
            color: #333;
        }

        h2 {
            margin-top: 40px;
            color: #333;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        td {
            background-color: #f9f9f9;
        }

        button {
            padding: 8px 16px;
            font-size: 14px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            opacity: 0.9;
        }

        .active {
            background-color: #28a745;
            color: white;
        }

        .deactivated {
            background-color: #dc3545;
            color: white;
        }

        form {
            display: inline-block;
        }
    </style>
    <script>
        // Function to confirm before removing a product
        function confirmRemoval() {
            return confirm("Are you sure you want to remove this product?");
        }
    </script>
</head>
<body>
    <h1>Admin Dashboard</h1>

    <!-- Buyers Table -->
    <h2>Buyers</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Buyer> buyers = (List<Buyer>) request.getAttribute("buyers");
                for (Buyer buyer : buyers) {
            %>
                <tr>
                    <td><%= buyer.getId() %></td>
                    <td><%= buyer.getName() %></td>
                    <td><%= buyer.getEmail() %></td>
                    <td><%= buyer.getMobileNumber() %></td>
                    <td><%= buyer.isAccountStatus() ? "Active" : "Deactivated" %></td>
                    <td>
                        <form action="/admin/updateBuyerStatus" method="POST">
                            <input type="hidden" name="id" value="<%= buyer.getId() %>">
                            <input type="hidden" name="accountStatus" value="<%= buyer.isAccountStatus() ? "false" : "true" %>">
                            <button type="submit" class="<%= buyer.isAccountStatus() ? "deactivated" : "active" %>">
                                <%= buyer.isAccountStatus() ? "Deactivate" : "Activate" %>
                            </button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <!-- Sellers Table -->
    <h2>Sellers</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Seller> sellers = (List<Seller>) request.getAttribute("sellers");
                for (Seller seller : sellers) {
            %>
                <tr>
                    <td><%= seller.getId() %></td>
                    <td><%= seller.getName() %></td>
                    <td><%= seller.getEmail() %></td>
                    <td><%= seller.getMobileNumber() %></td>
                    <td><%= seller.isAccountStatus() ? "Active" : "Deactivated" %></td>
                    <td>
                        <form action="/admin/updateSellerStatus" method="POST">
                            <input type="hidden" name="id" value="<%= seller.getId() %>">
                            <input type="hidden" name="accountStatus" value="<%= seller.isAccountStatus() ? "false" : "true" %>">
                            <button type="submit" class="<%= seller.isAccountStatus() ? "deactivated" : "active" %>">
                                <%= seller.isAccountStatus() ? "Deactivate" : "Activate" %>
                            </button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>

<!-- Products Table -->
<h2>Products</h2>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <% 
            List<Product> products = (List<Product>) request.getAttribute("products");
            for (Product product : products) {
        %>
            <tr>
                <td><%= product.getId() %></td>
                <td><%= product.getName() %></td>
                <td><%= product.getDescription() %></td>
                <td>
                    <!-- Remove Product Form with Confirmation -->
                    <form action="/admin/removeProduct" method="POST" style="display:inline;" onsubmit="return confirmRemoval();">
                        <input type="hidden" name="productId" value="<%= product.getId() %>">
                        <button type="submit" class="deactivated">Remove</button>
                    </form>
                    
                    <!-- View Product Form -->
                    <form action="/admin/viewProduct/<%= product.getId() %>" method="GET" style="display:inline;">
                        <button type="submit" class="active">View</button>
                    </form>
                </td>
            </tr>
        <% } %>
    </tbody>
</table>

</body>
</html>
