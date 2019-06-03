package dao;

import impl.WineDaoImpl;

/**
* @author rhythm
* @date 2019年5月26日 上午10:02:27
*  相关说明 
*/
public class WineDaoFactory {
	public static WineDao getDaoInstance(){  
        return new WineDaoImpl() ;  
    }  
}
