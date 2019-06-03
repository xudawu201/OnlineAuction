package dao;

import impl.CommodityDaoImpl;

/**
* @author rhythm
* @date 2019年5月19日 下午6:02:59
*  相关说明 
*/
public class CommodityDaoFactory {
	public static CommodityDao getDaoInstance(){  
        return new CommodityDaoImpl() ;  
    }  
}
