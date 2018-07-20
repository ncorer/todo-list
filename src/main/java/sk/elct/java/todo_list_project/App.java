package sk.elct.java.todo_list_project;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

    	ActivityDao dao = DaoFactory.INSTANCE.getActivityDao();
    	
    	Category sport = new Category();
    	sport.setName("sport");
    	
//    	dao.add(sport);
    	
 //   	sport.setName("physical");
    	
//    	dao.update(sport);
 //   	dao.delete(sport.getId());
    	
    	
    	List<Activity> mylist = dao.getAll();
    	
    	for(Activity act:mylist) 
{
    			System.out.println(act);
    		
    	}
    	
  //  	Category nieco = dao.getById(3);
    	
   // 	System.out.println(nieco.getName());
    	
    	
    }
    }


