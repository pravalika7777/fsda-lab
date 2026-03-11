package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;

public class ClientDemo
{
public static void main(String args[])
{
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf=cfg.buildSessionFactory();
Session session=sf.openSession();

Transaction tx=session.beginTransaction();

Transport t1=new Transport("Bus","Active",new Date());
Transport t2=new Transport("Train","Inactive",new Date());

session.save(t1);
session.save(t2);

tx.commit();

Query q=session.createQuery("from Transport t where t.status=:status");
q.setParameter("status","Active");

List<Transport> list=q.list();

for(Transport t:list)
{
System.out.println(t.getId()+" "+t.getName()+" "+t.getStatus());
}

session.close();
sf.close();
}
}