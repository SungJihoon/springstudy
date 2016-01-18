package chapter01.exercise.ex08;

import chapter01.exercise.ex08.ConnectionMaker;

public class CompanyDao {
	
	ConnectionMaker connectionMaker;
	
	CompanyDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

}
