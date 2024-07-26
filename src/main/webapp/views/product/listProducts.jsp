<%--
  Created by IntelliJ IDEA.
  User: viennguyenthi
  Date: 2024/07/26
  Time: 8:17
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product List</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #EEEDEB}

        th {
            background-color: #FFC7ED;
            color: #2F3645;
            font-weight: 700;
            font-size: 18px;
        }

        input[type=text] {
            width: 300px;
            box-sizing: border-box;
            border: 1px solid #2F3645;
            border-radius: 10px;
            font-size: 16px;
            background-color: white;
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            transition: width 0.4s ease;
        }

        form {
            margin-top: 30px;
            margin-bottom: 20px;
        }
        h2{
            text-align: center;
            color: #2F3645;
        }
        .link{
            text-decoration: none;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 10px 12px;
            text-align: center;
            display: inline-block;
            border-radius: 3px;
            font-weight: 800;
            font-size: 20px;

        }
        .link.back{
            margin-top: 200px;
        }
        .link:hover{
            background-color: #7D8ABC;
            color: white;
        }

        .link{
            text-decoration: none;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 10px 12px;
            text-align: center;
            display: inline-block;
            border-radius: 3px;
            font-weight: 800;
            font-size: 20px;
        }
        .link:hover {
            background-color: #7D8ABC;
            color: white;
        }
        .item {
            text-decoration: none;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 8px 10px;
            text-align: center;
            display: inline-block;
            border-radius: 3px;
            font-weight: 800;
            font-size: 14px;
        }
        .item:hover{
            background-color: #7D8ABC;
            color: white;
        }
        .content{
            display: flex;
            justify-content: space-around;
            align-items: center;
        }
        .category-image {
            max-width: 100px;
            height: auto;
        }

    </style>
    <script>
        function confirmDelete(id) {
            if (confirm('Bạn có chắc chắn muốn xóa san pham này không?')) {
                window.location.href = 'product?action=delete&id=' + id;
            }
        }
    </script>

</head>
<body>
<div class="content">
    <div><a href="/product?action=add" class="link" > +Add Product</a></div>
    <form action="/product" method="get">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="name" placeholder="Search by name"/>
        <button type="submit" class="link">Search</button>
    </form>
</div>

<div class="container">
    <h2>Product List</h2>
    <div class="message">
        <c:if test="${not empty param.message}">
            <p>${param.message}</p>
        </c:if>
    </div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Image</th>
            <th>Category</th>
            <th>Price</th>
            <th>Description</th>
            <th>Producer</th>
            <th>Stock</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="product" items="${product}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td><img src="${product.image}" alt="${product.name}" class="category-image"></td>
                <td>${product.categories.getName()}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td>${product.producer}</td>
                <td>${product.stock}</td>
                <td>${product.status ? "Active" : "Inactive"}</td>
                <td>
                    <a href="product?action=edit&id=${product.id} " class="item">Edit</a>
                    <a href="javascript:confirmDelete(${product.id})" class="item">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/index.jsp" class="link back"><< Home</a>
</body>
</html>
