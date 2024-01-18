package com.MindHub.HomeBanking.testRepository;

import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.RolType;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static com.MindHub.HomeBanking.models.Client.passwordValidator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTest {

        @Autowired
        ClientRepositories clientRepositories;

        @Test
        public void existClients(){
            List<Client> clients = clientRepositories.findAll();
            assertThat(clients,is(not(empty())));

        }
        @Test
        public void existType(){
        List<Client> clients = clientRepositories.findAll();
        assertThat(clients, hasItem(hasProperty("rol", is(RolType.CLIENT))));

        }

        @Test
        public void existType2(){
                Client clients = new Client("pepe","lopez","pepe@","5454564");
                assertEquals(RolType.CLIENT, clients.getRol());

        }

        @Test
        public void passwordValidatorUpperCase(){
                assertThrowsExactly(IllegalArgumentException.class, ()
                        -> passwordValidator
                        ("hola123*"),"La contraseña debe contener al menos una letra mayuscula");
        }

        @Test
        public void passwordValidatorLowerCase(){
                assertThrowsExactly(IllegalArgumentException.class, ()
                        -> passwordValidator
                        ("HOLA123*"),"La contraseña debe contener letras minusculas");
        }

        @Test
        public void passwordValidatorNumber(){
                assertThrowsExactly(IllegalArgumentException.class, ()
                        -> passwordValidator
                        ("Hola*"),"La contraseña debe contener al menos un numero");
        }

        @Test
        public void passwordValidatorCharacter(){
                assertThrowsExactly(IllegalArgumentException.class, ()
                        -> passwordValidator
                        ("Hola123"),"La contraseña debe contener al menos un caracter especial");
        }

}
