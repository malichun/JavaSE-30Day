package github.service;
/**
  * 
  * @Description 自定义异常类
  * @author subei Email:subei@163.com
  * @version
  * @date 2020年5月6日下午8:29:54
  *
 */
public class TeamException extends Exception{
	static final long serialVersionUID = -33875169124229948L;
	
	public TeamException(){
		super();
	}
	
	public TeamException(String img){
		super(img);
	}
}
