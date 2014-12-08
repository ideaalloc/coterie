package org.coterie.repository.dao.impl;

import org.coterie.datasource.config.DataSourceConfig;
import org.coterie.repository.config.RepositoryConfig;
import org.coterie.repository.dao.UserDao;
import org.coterie.repository.po.UserPo;
import org.coterie.repository.pojo.UserPojo;
import org.coterie.repository.repository.UserRepository;
import org.coterie.security.config.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DataSourceConfig.class, RepositoryConfig.class, SecurityConfig.class, UserDaoImpl.class})
public class UserDaoImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImplTest.class);

    @Qualifier("userDaoImpl")
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private List<UserPojo> users;

    @Before
    public void setUp() throws Exception {
        loadDataFromYaml();
    }

    @Test
    public void testCreate() throws Exception {
        assertNotNull("users not initialized yet.", users);
        for (UserPojo userPojo : users) {
            // Encoding password
            userPojo.setPassword(passwordEncoder.encode(userPojo.getPassword()));
            UserPojo returned = userDao.create(userPojo);
            LOGGER.info(returned.toString() + "\n");
        }
    }

    @Test
    public void testUpdate() throws Exception {
        assertNotNull("users not initialized yet.", users);
        List<UserPo> pos = userRepository.findAll();
        int counter = 0;
        for (UserPojo userPojo : users) {
            userPojo.setActivated(false);
            userPojo.setId(pos.get(counter).getId());
            // Encoding password
            userPojo.setPassword(passwordEncoder.encode(userPojo.getPassword()));
            LOGGER.info(userDao.update(userPojo).toString() + "\n");
            counter++;
        }
    }

    private void loadDataFromYaml() {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("user.yaml");
        Constructor constructor = new Constructor(UserHolder.class);
        TypeDescription userHolderDescription = new TypeDescription(UserHolder.class);
        userHolderDescription.putListPropertyType("users", UserPojo.class);
        constructor.addTypeDescription(userHolderDescription);
        Yaml yaml = new Yaml(constructor);
        UserHolder userHolder = (UserHolder) yaml.load(input);
        users = userHolder.getUsers();
    }

    public static class UserHolder {
        private List<UserPojo> users;

        public List<UserPojo> getUsers() {
            return users;
        }

        public void setUsers(List<UserPojo> users) {
            this.users = users;
        }
    }
}