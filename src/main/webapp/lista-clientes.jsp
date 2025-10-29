<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Lista de Clientes</h1>

        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/clientes?acao=novo" class="btn btn-primary">+ Novo Cliente</a>
        </div>

        <c:choose>
            <c:when test="${empty clientes}">
                <div class="empty-message">
                    Nenhum cliente cadastrado. Clique em "Novo Cliente" para adicionar.
                </div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Telefone</th>
                            <th>CPF</th>
                            <th>Endereço</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${clientes}">
                            <tr>
                                <td>${cliente.id}</td>
                                <td>${cliente.nome}</td>
                                <td>${cliente.email}</td>
                                <td>${cliente.telefone}</td>
                                <td>${cliente.cpf}</td>
                                <td>${cliente.endereco}</td>
                                <td>
                                    <div class="actions">
                                        <a href="${pageContext.request.contextPath}/clientes?acao=editar&id=${cliente.id}" class="btn btn-edit">Editar</a>
                                        <a href="${pageContext.request.contextPath}/clientes?acao=deletar&id=${cliente.id}"
                                           class="btn btn-delete"
                                           onclick="return confirm('Tem certeza que deseja excluir este cliente?')">Excluir</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
