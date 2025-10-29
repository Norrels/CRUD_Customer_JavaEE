<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${cliente != null ? 'Editar' : 'Novo'} Cliente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>${cliente != null ? 'Editar' : 'Novo'} Cliente</h1>

        <form action="${pageContext.request.contextPath}/clientes" method="post">
            <input type="hidden" name="acao" value="${cliente != null ? 'atualizar' : 'inserir'}">

            <c:if test="${cliente != null}">
                <input type="hidden" name="id" value="${cliente.id}">
            </c:if>

            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="${cliente != null ? cliente.nome : ''}" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${cliente != null ? cliente.email : ''}" required>
            </div>

            <div class="form-group">
                <label for="telefone">Telefone:</label>
                <input type="text" id="telefone" name="telefone" value="${cliente != null ? cliente.telefone : ''}" required>
            </div>

            <div class="form-group">
                <label for="cpf">CPF:</label>
                <input type="text" id="cpf" name="cpf" value="${cliente != null ? cliente.cpf : ''}" required>
            </div>

            <div class="form-group">
                <label for="endereco">Endereço:</label>
                <input type="text" id="endereco" name="endereco" value="${cliente != null ? cliente.endereco : ''}" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <a href="${pageContext.request.contextPath}/clientes?acao=listar" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>
