package org.k2.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.k2.K2Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class K2ControllerTest {

    @Autowired
    K2Controller k2Controller;

    @Test
    public void testRegister() throws Exception {
        k2Controller.register("hello");
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
}
