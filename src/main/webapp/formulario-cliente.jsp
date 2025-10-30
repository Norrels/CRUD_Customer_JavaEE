<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:choose>
        <c:when test="${not empty cliente}">
            <title>Editar Cliente</title>
        </c:when>
        <c:otherwise>
            <title>Novo Cliente</title>
        </c:otherwise>
    </c:choose>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${not empty cliente}">
                <h1>Editar Cliente</h1>
            </c:when>
            <c:otherwise>
                <h1>Novo Cliente</h1>
            </c:otherwise>
        </c:choose>

        <form action="${pageContext.request.contextPath}/clientes" method="post">
            <c:choose>
                <c:when test="${not empty cliente}">
                    <input type="hidden" name="acao" value="atualizar">
                    <input type="hidden" name="id" value="${cliente.id}">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="acao" value="inserir">
                </c:otherwise>
            </c:choose>

            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="${cliente.nome}" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${cliente.email}" required>
            </div>

            <div class="form-group">
                <label for="telefone">Telefone:</label>
                <input type="text" id="telefone" name="telefone" value="${cliente.telefone}" required>
            </div>

            <div class="form-group">
                <label for="cpf">CPF:</label>
                <input type="text" id="cpf" name="cpf" value="${cliente.cpf}" required>
            </div>

            <div class="form-group">
                <label for="endereco">Endereço:</label>
                <input type="text" id="endereco" name="endereco" value="${cliente.endereco}" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <a href="${pageContext.request.contextPath}/clientes?acao=listar" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>
