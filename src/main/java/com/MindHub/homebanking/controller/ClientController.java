package com.MindHub.homebanking.controller;

import com.MindHub.homebanking.dtos.CardDTO;
import com.MindHub.homebanking.dtos.ClientDTO;
import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.models.Client;
import com.MindHub.homebanking.services.AccountService;
import com.MindHub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
        @Autowired
        private ClientService clientService;

        @Autowired
        private AccountService accountService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @GetMapping("/clients")
        public List<ClientDTO> getClientDTO() {
            return clientService.getClientsDTO();
        }

        @GetMapping("clients/{id}")
        public ClientDTO getClient(@PathVariable Long id){
                return clientService.getClientDTO(id);
        }


        @PostMapping("/clients")
        public ResponseEntity<Object> register(

                @RequestParam String firstName, @RequestParam String lastName,

                @RequestParam String email, @RequestParam String password) {

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
                }

                if (clientService.getClientByEmail(email) != null) {
                        return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
                }

                Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
                clientService.saveClient(client);

                Account account = new Account(client,"VIN-" + this.getRandomNumber(150,76348129), LocalDateTime.now(),0);
                accountService.saveAccount(account);

                return new ResponseEntity<>(HttpStatus.CREATED);
        }

        public int getRandomNumber(int min, int max) {
                return (int) ((Math.random() * (max - min)) + min);
        }

        @GetMapping("/clients/current")
        public ClientDTO getAll(Authentication authentication) {

                return new ClientDTO(clientService.getClientCurrent(authentication));
        }

        @GetMapping("/clients/current/cards")
        public Set<CardDTO> getCardsDTO(Authentication authentication) {

                Client client = clientService.getClientCurrent(authentication);

                return client.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
        }

}
