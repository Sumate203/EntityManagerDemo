/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Address;
import model.Customer;
import java.util.*;
/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     //createData();
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter City");
     String city = sc.nextLine();
     printCustomerByCity(city);
     printAllCustomer();
     
    }
  
    public static void createData(){
        Customer customer1 = new Customer(1L, "john", "Henry", "jh@mail.com"); 
        Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH"); 
         Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com"); 
        Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
         Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com"); 
        Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
         Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com"); 
        Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");
        address1.setCustomerFk(customer1);
        customer1.setAddressId(address1);
         address2.setCustomerFk(customer2);
        customer2.setAddressId(address2);
         address3.setCustomerFk(customer3);
        customer3.setAddressId(address3);
         address4.setCustomerFk(customer4);
        customer4.setAddressId(address4);
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(address1);
            em.persist(customer1);
            em.persist(address2);
            em.persist(customer2);
            em.persist(address3);
            em.persist(customer3);
            em.persist(address4);
            em.persist(customer4);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
        public static void showData(List<Address> customerList) {
            
        
        for(Address a : customerList) {
            
            Customer c = findCustomerById(a.getCustomerFk());
            System.out.println("First Name:"+c.getFirstname() + " ");
            System.out.println("Last Name:"+c.getLastname() + " ");
            System.out.println("City:"+c.getEmail()+ " ");
            System.out.println("Street:"+a.getStreet() + " ");
            System.out.println("City:"+a.getCity()+ " ");
            System.out.println("Country:"+a.getCountry() + " ");
            System.out.println("Zip Code:"+a.getZipcode() + " ");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
        }
    }
        
        
    public static void printAllCustomer(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.findAll");
        List<Address> addressList = (List<Address>) query.getResultList();
        if (addressList != null) {
            showData(addressList);
        }
    }
    
    public static void printCustomerByCity(String city){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Query queryAddress = em.createNamedQuery("Address.findByCity");
        queryAddress.setParameter("city", city);
        List<Address> AddressList = (List<Address>) queryAddress.getResultList();
        if (AddressList != null) {
            showData(AddressList);
        }
       
    }
    
    public static Customer findCustomerById(Customer id) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
          Customer customer = em.find(Customer.class,id.getId());
            em.close();
            return customer;

    }
    
}
