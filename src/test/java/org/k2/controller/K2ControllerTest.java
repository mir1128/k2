package org.k2.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.k2.exception.ConflictException;
import org.k2.exception.NotFoundException;
import org.k2.model.User;
import org.k2.service.UserRepository;
import org.k2.viewmodel.BoardInfo;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class K2ControllerTest implements ApplicationContextAware {

    @Autowired
    private K2Controller k2Controller;

    private ApplicationContext context = null;

    private UserRepository userRepository = null;

    private final String helloUser = "hello";

    private BoardInfo boardInfo;

    @Before
    public void createHelloUser() throws ConflictException {
        boardInfo = k2Controller.register(helloUser);
    }

    @After
    public void deleteHellUser() {
        User user = userRepository.findByName(helloUser);
        userRepository.delete(user);
    }

    @Test
    public void should_register_success() throws Exception {
        assertEquals(boardInfo.getName(), helloUser);
    }

    @Test
    public void should_be_able_to_reset_board() throws Exception {
        BoardInfo resetBoardInfo = k2Controller.reset(helloUser);
        assertEquals(resetBoardInfo.getName(), helloUser);
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
