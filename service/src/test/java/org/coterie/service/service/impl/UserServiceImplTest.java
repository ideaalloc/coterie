package org.coterie.service.service.impl;

import org.coterie.datasource.config.DataSourceConfig;
import org.coterie.repository.config.RepositoryConfig;
import org.coterie.repository.dao.impl.UserDaoImpl;
import org.coterie.security.config.SecurityConfig;
import org.coterie.service.bo.UserBo;
import org.coterie.service.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DataSourceConfig.class, RepositoryConfig.class, SecurityConfig.class, UserDaoImpl.class, UserServiceImpl.class})
public class UserServiceImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class);

    private List<UserBo> users;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        loadDataFromYaml();
    }

    @Test
    public void testAddUser() throws Exception {
        assertNotNull("not yet initialized.", users);
        for (UserBo userBo : users) {
            // Encoding password
            userBo.setPassword(passwordEncoder.encode(userBo.getPassword()));
            UserBo returned = userService.addUser(userBo);
            LOGGER.info(returned.toString() + "\n");
        }
    }

    private void loadDataFromYaml() {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("user.yaml");
        Constructor constructor = new Constructor(UserHolder.class);
        TypeDescription userHolderDescription = new TypeDescription(UserHolder.class);
        userHolderDescription.putListPropertyType("users", UserBo.class);
        constructor.addTypeDescription(userHolderDescription);
        Yaml yaml = new Yaml(constructor);
        UserHolder userHolder = (UserHolder) yaml.load(input);
        users = userHolder.getUsers();
    }

    public static class UserHolder {
        private List<UserBo> users;

        public List<UserBo> getUsers() {
            return users;
        }

        public void setUsers(List<UserBo> users) {
            this.users = users;
        }
    }
}