package com.studyboom.dtos;

public interface Constants {

	Integer STATUS_FAILED = 0;
	Integer STATUS_OK = 1;

	enum USER_TYPE {
		PUBLISHER, STUDENT, ADMIN
	}

	enum REQUEST_STATUS {
		NOT_STARTED, ACCEPTED, NOT_ACCEPTED
	}
	
	String[] reportsColumns = new String[] {
			"Test Name", "Average Ratings"
	};
	
	int reportDefaultColumnSize = 20;
}
