package github.service;

import github.domain.Architect;
import github.domain.Designer;
import github.domain.Employee;
import github.domain.Programer;
/**
  * 
  * @Description 关于开发团队成员的管理：添加、删除等。
  * @author subei Email:subei@163.com
  * @version
  * @date 2020年5月6日下午9:14:19
  *
 */
public class TeamService {

	private static int counter = 1;	//为开发团队新增成员自动生成团队中的唯一ID
	private final int MAX_MEMBER = 5;	//限定开发团队的人数
	private Programer[] team = new Programer[MAX_MEMBER];	//用来保存当前团队中的各成员对象
	private static int total = 0;	//记录团队成员的实际人数
	
	public TeamService() {
		super();
	}

	/**
	  * 
	  * @Description 获取当前团队的所有对象,即返回team中所有程序员构成的数组
	  * @author subei
	  * @date 2020年5月6日下午9:19:18
	  * @return
	 */
	public Programer[] getTeam() {
		Programer[] team = new Programer[total];

        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
		return team;
	}
	
	/**
	  * 
	  * @Description 向团队中添加成员,即将指定员工添加到开发团队中
	  * @author subei
	  * @date 2020年5月6日下午9:23:06
	  * @param e
	 * @throws TeamException 
	 */
	public void addMember(Employee e) throws TeamException{
//		失败信息包含以下几种：
//		– 成员已满，无法添加
		if(total >= MAX_MEMBER){
			throw new TeamException("成员已满，无法添加");
		}
//		– 该成员不是开发人员，无法添加
		if(!(e instanceof Programer)){
			throw new TeamException("该成员不是开发人员，无法添加");
		}
				
//		– 该员工已在本开发团队中
		if(isExist(e)){
			throw new TeamException("该员工已在本开发团队中");
		}
//		– 该员工已是某团队成员
//		– 该员正在休假，无法添加
		Programer p = (Programer)e;	//一定不会出现类型转化异常:ClassCaseException
//		if("BUSY".equalsIgnoreCase(p.getStatus().getNAME())){
//			throw new TeamException("该员工已是某团队成员");
//		}else if("VOCATION".equalsIgnoreCase(p.getStatus().getNAME())){
//			throw new TeamException("该员正在休假，无法添加");
//		}

		switch (p.getStatus()){	////byte\short\char\int\String\枚举类对象
			case BUSY:
				throw new TeamException("该员工已是某团队成员");
			case VOCATION:
				throw new TeamException("该员正在休假，无法添加");
		}

//		– 团队中至多只能有一名架构师
//		– 团队中至多只能有两名设计师
//		– 团队中至多只能有三名程序员	
		
		//获取team已有成员中架构师，设计师，程序员的人数
		int numOfArch = 0,numOfDes = 0,numOfPro = 0;
		for(int i = 0;i < total;i++){
			if(team[i] instanceof Architect){
				numOfArch++;
			}else if(team[i] instanceof Designer){
				numOfDes++;
			}else if(team[i] instanceof Programer){
				numOfPro++;
			}
		}
		
		//正确的写法
		if(p instanceof Architect){
			if(numOfArch >= 1){
				throw new TeamException("团队中至多只能有一名架构师");
			}
		}else if(p instanceof Designer){
			if(numOfDes >= 2){
				throw new TeamException("团队中至多只能有两名设计师");
			}
		}else if(p instanceof Programer){
			if(numOfPro >= 3){
				throw new TeamException("团队中至多只能有三名程序员");
			}
		}
		
		//错误的写法
//		if(p instanceof Architect && numOfArch >= 1){
//			throw new TeamException("团队中至多只能有一名架构师");
//		}else if(p instanceof Designer && numOfDes >= 2){
//			throw new TeamException("团队中至多只能有两名设计师");
//		}else if(p instanceof Programer && numOfPro >= 3){
//			throw new TeamException("团队中至多只能有三名程序员");
//		}
		
		//p的属性赋值
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
		//将p添加到现有的team中
        team[total++] = p;
	}
	
	private boolean isExist(Employee e) {
		for(int i = 0;i < total;i++){
			return team[i].getId() == e.getId();
		}
		return false;
	}

	/**
	  * 
	  * @Description 从团队中删除成员,即删除指定memberId的程序员
	  * @author subei
	  * @date 2020年5月6日下午9:23:44
	  * @param memberId
	 * @throws TeamException 
	 */
	public void removeMember(int memberId) throws TeamException{
		int n = 0;
        //找到指定memberId的员工，并删除
        for (n = 0; n < total; n++) {
            if (team[n].getMemberId() == memberId) {
                team[n].setStatus(Status.FREE);
                break;
            }
        }
        //如果遍历一遍，都找不到，则报异常
        if (n == total)
            throw new TeamException("找不到该成员，无法删除");
        //后面的元素覆盖前面的元素
        for (int i = n + 1; i < total; i++) {
            team[i - 1] = team[i];
        }
        
        //写法一
//        team[total - 1] = null;
//        total --;
        
        //写法二
        team[--total] = null;       
	}
}
