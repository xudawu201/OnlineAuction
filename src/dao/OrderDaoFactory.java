package dao;

import impl.OrderDaoImpl;

/**
* @author rhythm
* @date 2019年5月17日 下午7:50:43
*  相关说明 
*/
public class OrderDaoFactory {
	public static OrderDao getDaoInstance(){  
        return new OrderDaoImpl() ;  
    }  
}
