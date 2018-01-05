package com.van.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.van.entity.UserEntity;
import java.util.concurrent.Semaphore;

public class VanUtil {
	
	private final   int varTest1;
	
	public  VanUtil(TraxConstant tc){
		varTest1=2;
		System.out.println(varTest1);
		if(tc==TraxConstant.ONE){
			System.out.println("one");
		}
	}
	
	public static void main(String args[]) {
		try {
			
			/*String strDate = "2015-09-07";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date convertedCurrentDate = sdf.parse(strDate);
			
			byte myByte1=1;
			float f1=3f;
			double d1=2d;
			
			System.out.println(convertedCurrentDate);
			System.out.println(myByte1);*/
			ServiceLocator locator=new ServiceLocator();
			//locator.getVanLogicManager().getUserFromUsername("lukasp");
			List<?> list=locator.getVanLogicManager().findDynamicQuery("select user from UserEntity user");
			System.out.println("Size->"+list.size());
			UserEntity user=new UserEntity(null,"sam21","pass1","Sam Joseph",new Long(23),new Date());
			//locator.getVanLogicManager().create(user);
			System.out.println("**done**");
			//UserEntity user = getUserFromUsername("lukasp");
		/*	Set<String> set1=new HashSet();
			set1.add("one");
			set1.add("two");
			set1.add("one");
			SortedSet<String> set2=new TreeSet<String>();
			set2.add("one");
			set2.add("two");
			set2.add("three");
			System.out.println(set1);
			System.out.println(set2);
			UserEntity ue1=new UserEntity(new Long(2),"abc","pass1","sam1",new Long(23),new Date());
			System.out.println(ue1);
			UserEntity ue2=ue1;
			changeVal(ue1);
			System.out.println(ue1);
			System.out.println(ue2);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void changeVal(UserEntity uex){
		uex.setName("sam2");
	}
}
