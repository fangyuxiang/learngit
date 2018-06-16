1：入参实现：（完成的ipml实现，接口名.class）
   (参考网址：https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484243&idx=1&sn=972cbe6cdb578256e4d4771e7ca25de3&chksm=ebd74252dca0cb44419903758e8ca52d9ab287562f80be9365e305d6dcc2deaa45b40f9fd2e9#rd)
	public class DaoFactory {
		// 获取DaoFactory对象
		private static DaoFactory factory = new DaoFactory();
		// 无参构造方法
		private DaoFactory() {}

		public static DaoFactory getInstance() {
			return factory;
		}

		/*
		 * 专门创建Dao的工厂
		 * 参数1： 完成的ipmi实现
		 * 参数2:  接口名.class
		 */
		public <T> t createDao (String className, Class<T> clazz) {
			try{
				T t = (T) Class.forName(className).newInstance();
				return t;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}


	// 使用示例
	CategoryDao categoryDao = DaoFactory.getInstance.createDao("fyx.dao.impl.CategoryDAOImpl", CategoryDao.class) 

2：配置文件实现：
	参考网址：https://blog.csdn.net/nuowei_senlin/article/details/57073655
	a): UserDao.java接口
		public interface UserDao {
			public void add ();
			public void delete();
		}

	b): UserDaoImpl.java UserDao的实现：
		public class UserDaoImpl implements UserDao{
			public void add() {
				Systetm.out.println("add方法");
			}

			public void delete(){
				Systetm.out.println("delete方法");
			}
		}

	c): daofactory.properties配置文件，daofactory会根据此配置文件的信息构造出Dao的实现类
		UserDao=fyx.test.dao.ipml.UserDaoImpl

	d): 专门创建Dao的工厂	
		public class DaoFactory() {
			/* -----------读取daofactory.properties配置文件 -----------*/
			private static Properties prop = new Properties();
			static{
				try {
					prop.load(DaoFactory.class.getClassLoader().getResourceAsStream("daofactory.properties"))
				} catch (IOExecption e) {
					throw new RuntimeException(e);
				}
			}

			/* ----------------将DaoFactorys设为单例模式----------------*/
			private static DaoFactory factory = new DaoFactory();
			private DaoFactory() {}
			public static DaoFactory getInstance() {
				return factory;
			}

			/* ----------------dao工厂,生产dao的实现类 -----------------*/
			public <T> t createDao(Class<T> interfaceClass) {
				String simpleName = interfaceClass.getSimpleName(); 
				System.out.println("简单类名称是:"+simpleName);   //UserDao
				String classname = prop.getProperty(simpleName); //从配置文件中得到接口的实现类

				try{
					return (T) Class.forName(classname).newInstance();
				} catch (InstantiationExeption e) {
					e.prinStackTrace();
				} catch (IllegalAccessException e) {
					e.prinStackTrace();
				}

				return null;
			}
		}