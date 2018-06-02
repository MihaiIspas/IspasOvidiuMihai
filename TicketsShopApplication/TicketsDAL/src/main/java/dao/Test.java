package dao;

import java.util.ArrayList;
import java.util.List;

import entities.FestivalObj;
import entities.UserObj;

public class Test {

	public static void main(String[] args) {

		UserDAO dao=new UserDAO();
		List<UserObj> list=new ArrayList<UserObj>();
		
		list=dao.selectAll();
		
		System.out.println(list.toString());

	}

}
