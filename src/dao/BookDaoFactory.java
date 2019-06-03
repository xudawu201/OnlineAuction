package dao;

import impl.BookDaoImpl;

/**
* @author rhythm
* @date 2019年5月8日 下午5:41:44
*  相关说明 
*  在没有DAO工厂类的情况下，必须通过创建DAO实现类的实例才能完成数据库的操作。
*  这时要求必须知道具体的实现子类，对于后期的修改十分不便。如后期需要创建一个该DAO接口的oracle实现类。
*  这时就必须修改所有使用DAO实现类的代码。
*  如果使用DAO工厂类的一个静态方法(不需要创建对象即可调用)来获取DAO实现类实例，
*  这时替换DAO实现类，只需修改DAO工厂类中的方法代码，而不需要修改所有的调用DAO实现的代码。

DAO工厂类是一个单例模式，这样避免的数据库的不一致。
*/
public class BookDaoFactory {
	public static BookDao getBookDaoInstance(){  
        return new BookDaoImpl() ;  
    }  
}
