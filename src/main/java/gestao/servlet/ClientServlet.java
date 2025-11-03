package gestao.servlet;

import gestao.dao.ClienteDAO;
import gestao.model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/clientes")
public class ClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("acao");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "listar":
                listarClientes(req, resp);
                break;
            case "editar":
                mostrarFormularioEditar(req, resp);
                break;
            case "deletar":
                deletarCliente(req, resp);
                break;
            case "novo":
                mostrarFormularioNovo(req, resp);
                break;
            default:
                // Lógica padrão
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if (acao == null) {
            acao = "inserir";
        }

        switch (acao) {
            case "inserir":
                inserirCliente(req, resp);
                break;
            case "atualizar":
                atualizarCliente(req, resp);
                break;
            default:
          //      listarClientes(request, response);
                break;
        }

    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Cliente> clientes = clienteDAO.listarTodos();
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("/lista-clientes.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar clientes", e);
        }
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/formulario-cliente.jsp").forward(request, response);
    }

    private void inserirCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco);

        try {
            clienteDAO.inserir(cliente);
            response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
        } catch (SQLException e) {
            throw new ServletException("Erro ao inserir cliente", e);
        }
    }

    private void deletarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                clienteDAO.deletar(id);
            } catch (NumberFormatException e) {
            } catch (SQLException e) {
                throw new ServletException("Erro ao deletar cliente", e);
            }
        }

        response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Cliente cliente = clienteDAO.buscarPorId(id);

                if (cliente != null) {
                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("/formulario-cliente.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
            } catch (SQLException e) {
                throw new ServletException("Erro ao buscar cliente", e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
        }
    }

    private void atualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);

                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setTelefone(telefone);
                cliente.setCpf(cpf);
                cliente.setEndereco(endereco);

                clienteDAO.atualizar(cliente);
            } catch (NumberFormatException e) {
                // Log do erro se necessário
            } catch (SQLException e) {
                throw new ServletException("Erro ao atualizar cliente", e);
            }
        }

        response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
    }
}
