<%--
  Created by IntelliJ IDEA.
  User: viennguyenthi
  Date: 2024/07/26
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label{
            margin-bottom: 10px;
        }
        input[type="text"],
        input[type="number"],
        input[type="checkbox"] {
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {

            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 6px 12px;
            text-align: center;
            display: inline-block;
            border-radius: 10px;
            font-weight: 800;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #7D8ABC;
            color: white;
        }
        .btn-group{
            margin-bottom: 20px;

        }
        .btn-group label{
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 6px 12px;
            text-align: center;
            display: inline-block;
            border-radius: 10px;
            font-weight: 800;
            font-size: 16px;
        }
        .btn-group label:hover{
            background-color: #7D8ABC;
            color: white;
        }
        label{
            color: #304463;
            font-size: 18px;
            font-weight: 700;
        }
        .btn-group p{
            color: #304463;
            font-size: 18px;
            font-weight: 700;
        }
        .message {
            text-align: center;
            margin-top: 20px;
            color: green;
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
        .link:hover{
            background-color: #7D8ABC;
            color: white;
        }

        #category{
            padding: 8px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Create New Product</h2>
    <form action="/product" method="post">
        <input type="hidden" name="action" value="add"/>
        <input type="hidden" name="id">

        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="image">Product Image (URL):</label>
        <input type="text" id="image" name="image" required>

        <label for="category">Category:</label>
        <select id="category" name="categoryId" required>
            <c:forEach var="category" items="${categoriesList}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" required>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>

        <label for="producer">Producer:</label>
        <input type="text" id="producer" name="producer" required>

        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" required>

        <div class="btn-group">
            <p>Status:</p>
            <label for="active">
                <input type="radio" name="status" id="active" value="true" required style="display: none"> Active
            </label>
            <label for="inactive">
                <input type="radio" name="status" id="inactive" value="false" required style="display: none"> Inactive
            </label>
        </div>
        <input type="submit" value="add" name="action">
    </form>

</div>
<a href="/product" class="link"><< Back</a>
</body>
</html>
