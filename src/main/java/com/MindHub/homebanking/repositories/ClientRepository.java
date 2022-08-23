package com.MindHub.homebanking.repositories;

import com.MindHub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String emailClient);
}

// repository va a heredar (extends) los metodos de JpaRepository