package dao;

import impl.WatchDaoImpl;

/**
* @author rhythm
* @date 2019年5月9日 下午9:09:07
*  相关说明 
*/
public class WatchDaoFactory {
	public static WatchDao getWatchDaoInstance(){  
        return new WatchDaoImpl() ;  
    }  
}
