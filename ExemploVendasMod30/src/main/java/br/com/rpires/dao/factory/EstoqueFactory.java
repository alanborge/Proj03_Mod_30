package br.com.rpires.dao.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rpires.domain.Estoque;

public class EstoqueFactory {
	
	public static Estoque convert(ResultSet rs)throws SQLException{
		Estoque estoque = new Estoque();
		estoque.setId(rs.getLong("ID_ESTOQUE"));
		estoque.setId_produto_fk(rs.getLong("ID_PRODUTO_FK"));
		estoque.setCodigo(rs.getLong("CODIGO"));
		estoque.setQuantidade_atual(rs.getInt("QUANTIDADE_ATUAL"));
		estoque.setQuantidade_entrada(rs.getInt("QUANTIDADE_ENTRADA"));
		estoque.setData_entrada(rs.getDate("DATA_ENTRADA"));
		return estoque;
	}

}
