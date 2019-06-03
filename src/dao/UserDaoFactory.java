package dao;

import impl.UserDaoImpl;

/**
* @author rhythm
* @date 2019年5月12日 下午5:51:50
*  相关说明 
*/
public class UserDaoFactory {
	public static UserDao getDaoInstance(){  
        return new UserDaoImpl() ;  
    }  
}
