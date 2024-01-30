package br.com.rpires;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;

import br.com.rpires.dao.ClienteDAO;
import br.com.rpires.dao.EstoqueDAO;
import br.com.rpires.dao.IEstoqueDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.dao.VendaDAO;
import br.com.rpires.domain.Cliente;
import br.com.rpires.domain.Estoque;
import br.com.rpires.domain.Produto;
import br.com.rpires.domain.ProdutoQuantidade;
import br.com.rpires.domain.Venda;
import br.com.rpires.domain.Venda.Status;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.MaisDeUmRegistroException;
import br.com.rpires.exceptions.TableException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

public class EstoqueDAOTest {

	private IEstoqueDAO estoqueDAO;
	
	public EstoqueDAOTest() {
		estoqueDAO = new EstoqueDAO();
	}
	
	public void end()throws DAOException{
		Collection<Estoque>list = estoqueDAO.buscarTodos();
		list.forEach(est -> {
			try {
				estoqueDAO.excluir(est.getCodigo());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	@Test
	public void pesquisarEstoque()throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException{
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		Produto produto = new Produto();
		cliente.setCpf(1531L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		clienteDAO.cadastrar(cliente);
		
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produto.setCodigo("1535");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produto.setEstoque(8);
		produtoDAO.cadastrar(produto);
		
		Venda venda = new Venda();
		VendaDAO vendaDAO = new VendaDAO();
		venda.setCodigo("0152");
		venda.setDataVenda(Instant.now());
		venda.setCliente(cliente);
		venda.setStatus(Status.INICIADA);
		venda.adicionarProduto(produto, 2);
		vendaDAO.cadastrar(venda);
		
		Estoque estoque = new Estoque();
		estoque.setCodigo(12312312L);
		estoque.setId_produto_fk(produto.getId());
		estoque.setQuantidade_atual(06);
		estoque.setQuantidade_entrada(10);
		Date dataentrada = new Date(0);
		estoque.setData_entrada(dataentrada);
		estoqueDAO.cadastrar(estoque);
		
		Estoque estoqueConsultado = estoqueDAO.consultar(estoque.getCodigo());
		Assert.assertNotNull(estoqueConsultado);
		estoqueDAO.excluir(estoque.getCodigo());
			
		Venda vendaConsultada = vendaDAO.consultar(venda.getCodigo());
		Assert.assertNotNull(vendaConsultada);
		vendaDAO.excluir(venda.getCodigo());
		
		Produto produtoConsultado = produtoDAO.consultar(produto.getCodigo());
		Assert.assertNotNull(produtoConsultado);
		produtoDAO.excluir(produto.getCodigo());
		
		Cliente clienteConsultado = clienteDAO.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
		clienteDAO.excluir(cliente.getCpf());
			
		
	}
}
