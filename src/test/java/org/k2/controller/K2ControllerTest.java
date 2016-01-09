package org.k2.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.k2.K2Controller;
import org.k2.model.User;
import org.k2.service.UserRepository;
import org.k2.service.UserService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class K2ControllerTest implements ApplicationContextAware {

    @Autowired
    private K2Controller k2Controller;

    private ApplicationContext context = null;

    private UserRepository userRepository = null;

    @Test
    public void should_register_success() throws Exception {
        String who = "hello";

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity =  k2Controller.register(who);
            assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        } catch (Exception e) {
            assertFalse(true);
        } finally {
            User user = userRepository.findByName(who);
            userRepository.delete(user);
        }
    }

    @Test
    public void testMove() throws Exception {

    }

    @Test
    public void testGetScore() throws Exception {

    }

    @Test
    public void testGetScores() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        userRepository = (UserRepository) this.context.getBean("userRepository");
    }
}
