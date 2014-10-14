/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utcluj.sd.assign1.backend.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Eniko
 */
public class ConnectionManager {
    private static final int SIZE = 10;
	private final List<Connection> pool;
	private static ConnectionManager instance;

	private ConnectionManager() {
		pool = new LinkedList<>();
		initializePool();
	}

	public synchronized static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public synchronized Connection getConnection() {
		Connection con = null;
		if (pool.size() > 0) {
			con = pool.get(0);
			pool.remove(0);
		}
		if (con == null) {
			System.out.println("No connections in pool");
		}
		return con;
	}

	public synchronized void returnConnection(final Connection con) {
		if (pool.size() < SIZE) {
			pool.add(con);
		}
	}

	private void initializePool() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for (int i = 0; i < SIZE; i++) {
				pool.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/ds-assign1","root","admin"));
			}
		} catch (final SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
			System.out.println("Connection pool initialization failed");
		
		}
	}

}
