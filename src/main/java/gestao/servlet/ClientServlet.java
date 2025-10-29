package gestao.servlet;

import gestao.model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientes")
public class ClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private List<Cliente> clientes = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("acao");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "listar":
                listarClientes(req, resp);
                break;
            case "edit":
                // Lógica para editar cliente
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
        //        atualizarCliente(req, response);
                break;
            default:
          //      listarClientes(request, response);
                break;
        }

    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("clientes", clientes);

        request.getRequestDispatcher("/lista-clientes.jsp").forward(request, response);
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

        int id = clientes.isEmpty() ? 1 : clientes.get(clientes.size() - 1).getId() + 1;
        Cliente cliente = new Cliente(id, nome, email, telefone, cpf, endereco);

        clientes.add(cliente);

        response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
    }

    private void deletarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                clientes.removeIf(cliente -> cliente.getId() == id);
            } catch (NumberFormatException e) {
                // Log do erro se necessário
            }
        }

        response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
    }
}
