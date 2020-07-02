package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbsTractDAO<E> implements GenericDAO<E> {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/newservlet";
//			String user = "root";
//			String password = "1234";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <E> List<E> query(String sql, RowMapper<E> rowMapper, Object... parameters) {
		List<E> results = new ArrayList<E>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			setParameters(ps, parameters);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void setParameters(PreparedStatement ps, Object... parameters) {
		try {
			for (int paraindex = 0; paraindex < parameters.length; paraindex++) {
				Object parameter = parameters[paraindex];
				int index = paraindex + 1;
				if (parameter instanceof Long) {
					ps.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					ps.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					ps.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					ps.setTimestamp(index, (Timestamp) parameter);
				} else if (parameter == null) {
					ps.setNull(index, Types.NULL);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			setParameters(ps, parameters);
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Long id = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(ps, parameters);
			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			int count = 0;
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			setParameters(ps, parameters);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
