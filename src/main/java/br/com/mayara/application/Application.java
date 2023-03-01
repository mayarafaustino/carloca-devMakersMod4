package br.com.mayara.application;

import br.com.mayara.entities.Carro;
import br.com.mayara.entities.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Cliente.class);
        config.addAnnotatedClass(Carro.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();


        readCliente(session,"Olivia");
        readCarrosDisponiveis(session, true);

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

    public static void readCarrosDisponiveis(Session session, boolean disponivel) {
        List<Carro> carros = session.createQuery("from Carro where disponivel = :disponivel")
                .setParameter("disponivel", disponivel)
                .getResultList();

        for (Carro carro: carros) {

            System.out.println("Id: " + carro.getId());
            System.out.println("Montadora: " + carro.getMontadora());
            System.out.println("Modelo: " + carro.getModelo());
//        System.out.println("Cor: " + carro.getCor().toString());
            System.out.println("Kilometragem: " + carro.getKilometragem());
            System.out.println("Categoria Id: " + carro.getIdCategoria());
            System.out.println();
        }

    }
}
