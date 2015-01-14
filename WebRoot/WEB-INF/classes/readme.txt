------------数据库设计文档-----------
	库名：ATP_DATA
	 表：user
	 
	 
	 SQL:
	 
	 	create database ATP_DATA;
	 	use ATP_DATA;
	 	create table user(name varchar(60),password varchar(60),admin varchar(30) default 'no');
	 	
	 	insert into user(name,password) values("zhangsan","zhangsan"),("lisi","lisi");
	 	
	 	
--------配置文件-------------

	配置文件中具体的字段分为必须和可选：
		
		必选：1.必须匹配。
			  2.必选的值不一定，有几种情况  有可能是特有的，有可能是包含特定字段。
			  	有可能是多个值，有可能是单值。
			  	需要考虑。
		
		可选： 1.可选的值不一定需要在配置文件中进行配置
			  2.可选的值一旦配置，分两种情况，如果包中携带该字段，则值必须正确，如果包中没有携带该字段，
			  	则显示未携带，若携带但值不正确，则显示错误。
			  3.又可能包含特定字段。同必须一样。
			  
		解决方案：
				此处处理必选项，分为几种情况：  1.无法判断的信息，直接输出      ----通过把值设置成为OutPut来判定
		        						2.能够判断的固定值   -----直接写值就好了  
		        							  多选值通过值中间的空格区分
		        						3. 特有的值，需要从特殊的哦配置文件中读取   
		        							 -----通过把值设置成为 PrivateProp
		        						4. 判断是否包含字段，只用输出即可，无需判断值    
		        							--------通过增加属性subField="true"来判断。然后遍历子标签
		        							
		        							
	----------------------------------------------------------------------------------------------------------------------------------	        							
		    
		每导入一个包，则 对应一个线程，对应一个配置文件，然后根据配置文件中的packet的个数，
		 	  从导入的包中选择合适的frame，然后核对packet中的每一项。
		 	  用一张表记录所有的检查表
		 	
		 	表1    （共用，所有的包都存在这个表中）
		 	
		 		  表名：用户名
		 		  
		 		  		packet_name     			   					check_result
		 		  		
		 		  爱立信_华为_5_1_1_1   						 0(通过)  1(未通过)   2(未导入配置文件)
		 	
		 				此表的建立实在登录成功的时候建立，数据的插入分为两个时期，
		 						1 	用户导入的包，没有问题的都在此表中保存。
		 						2   测试完毕，检查测试结果的时候更新TestResult字段。
		 	
		 	表2		（共用）
		 	
		 		 表名：Frame2Pcap_<username>
		 		 
		 				frameName					packet_name      exits
		 				
		  		Frame1_爱立信_华为_5_1_1_1	爱立信_华为_5_1_1_1
		  		
		  		
		  	表3		（私有的）
		  	
		  		表名：Frame1_爱立信_华为_5_1_1_1_<username>
		  		
		  		AVPName  		 可选/必选(mustOrNot) 		预置值 (preset_value)       			实际值(actual_value)     			final_result
		  		
		    CC-Request-Type   		必选		initial_request		initial_request		  yes
		    
		    
		    create table IF NOT EXISTS "+tableName+" (packet_name varchar(100),check_result varchar(30) default '0')
		    create table if not exists "+tableName+"(AVPName varchar(100),mustOrNot varchar(30) default '必选',preset_value varchar(100),actual_value varchar(100),final_result varchar(30));
		        							
		        			
		        			
		        			
---------------------------------------注意事项-------------------------------------------------------

		1.   不要导入相同的包
		2.   操作完成请点击" 清除操作记录 " 按钮，清除所有的操作记录！
		
		
		
		
--------------------待办事项-------------------------------------
	
		
			
		3. 线程的规范化		
		4. 查看详情 和  收起			
		        							