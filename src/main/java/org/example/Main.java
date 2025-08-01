package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AcademiaPU");
        EntityManager em = emf.createEntityManager();
    }
}