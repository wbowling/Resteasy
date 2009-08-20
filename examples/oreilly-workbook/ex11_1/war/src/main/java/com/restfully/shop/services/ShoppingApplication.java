package com.restfully.shop.services;


import javax.naming.InitialContext;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ShoppingApplication extends Application
{
   private Set<Class<?>> classes = new HashSet<Class<?>>();

   public ShoppingApplication()
   {
      classes.add(EntityNotFoundExceptionMapper.class);
      classes.add(EJBExceptionMapper.class);
   }

   public Set<Class<?>> getClasses()
   {
      return classes;
   }

   public Set<Object> getSingletons()
   {
      HashSet<Object> set = new HashSet();
      try
      {
         InitialContext ctx = new InitialContext();
         Object obj = ctx.lookup(
                 "java:comp/env/ejb/CustomerResource");
         set.add(obj);

         obj = ctx.lookup(
                 "java:comp/env/ejb/OrderResource");
         set.add(obj);

         obj = ctx.lookup(
                 "java:comp/env/ejb/ProductResource");
         set.add(obj);

         obj = ctx.lookup(
                 "java:comp/env/ejb/StoreResource");
         set.add(obj);

      }
      catch (Exception ex)
      {
         throw new RuntimeException(ex);
      }
      return set;
   }
}
