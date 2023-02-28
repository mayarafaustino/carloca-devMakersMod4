package br.com.mayara.application;

import br.com.mayara.entities.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {
    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Cliente.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();


        readCliente(session,"Olivia");

    }

    public static void readCliente(Session session, String nome) {
        Cliente cliente = (Cliente) session.createQuery("from Cliente where nome = :nome")
                .setParameter("nome", nome)
                .getSingleResult();
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Id: " + cliente.getId());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("CNH: " + cliente.getCnh());
        System.out.println("Endereço Id: " + cliente.getIdEndereco());
        System.out.println();
    }
}
