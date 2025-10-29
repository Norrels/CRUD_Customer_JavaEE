package gestao.servlet;

import gestao.model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientes")
public class ClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listarClientes(req, resp);
                break;
            case "edit":
                // Lógica para editar cliente
                break;
            case "delete":
                // Lógica para deletar cliente
                break;
            case "new":
                // Lógica para novo cliente
                break;
            default:
                // Lógica padrão
                break;
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cliente client1 = new Cliente(1, "João Silva", "joao@email.com", "112223333", "123.456.789-00", "Rua A, 123");
        Cliente client2 = new Cliente(2, "Maria Santos", "maria@email.com", "987654321", "987.654.321-00", "Rua B, 456");

        List<Cliente> clientes = List.of(client1, client2);

        request.setAttribute("clientes", clientes);

        request.getRequestDispatcher("/lista-clientes.jsp").forward(request, response);
    }
}
