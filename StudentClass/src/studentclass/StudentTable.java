/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclass;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author siraphat
 */
public class StudentTable {
      public static void insertEmployee(Student emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentClassPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void updateEmployee(Student emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, emp.getId());
        fromDb.setName(emp.getName());
        fromDb.setGpa(emp.getGpa());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static Student findEmployeeById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Student emp = em.find(Student.class, id);
        em.close();
        return emp;
    }
    public static List<Student> findAllEmployee() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        List<Student> empList = (List<Student>) em.createNamedQuery("Employee.findAll").getResultList();
        em.close();
        return empList;
    }
    public static List<Student> findEmployeeByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Employee.findByName");
        query.setParameter("name", name);
        List<Student> empList = (List<Student>) query.getResultList();
        em.close();
        return empList;
    }
    public static void removeEmployee(Student emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
                
    }
}
