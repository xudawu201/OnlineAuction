package dao;

import impl.StampDaoImpl;

/**
* @author rhythm
* @date 2019年5月16日 上午1:13:32
*  相关说明 
*/
public class StampDaoFactory {
	public static StampDao getDaoInstance(){  
        return new StampDaoImpl() ;  
    }  
}