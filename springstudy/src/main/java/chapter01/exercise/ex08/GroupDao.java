package chapter01.exercise.ex08;

import chapter01.exercise.ex08.ConnectionMaker;

public class GroupDao {
	
	ConnectionMaker connectionMaker;
	
	GroupDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

}
