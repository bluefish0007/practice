package com.bluefish.domain;

import com.bluefish.domain.inter.IArticleOperation;
import com.bluefish.domain.inter.IUserOperation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Set;

/**
 * Created by elaine on 2016/4/3.
 */
public class Tester {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try {
            reader = Resources.getResourceAsReader("conf.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            User user = (User) session.selectOne("com.bluefish.mapping.userMapper.getUserById", 1);
//            System.out.println(user);
//        } finally {
//            session.close();
//        }
        try{
            /*IUserOperation mapper = session.getMapper(IUserOperation.class);
            User user = mapper.getUserById(1);

            IUserOperation mapper = session.getMapper(IUserOperation.class);
            int repeatCount = 1000000;
            long start = System.currentTimeMillis();
            for(int i = 0 ; i < repeatCount ; ++ i){
                Set<User> allUser = mapper.getAllUser();
            }
            System.out.println((System.currentTimeMillis() - start) + "ms");
            System.out.println();
            User newUser = new User("tianzh22",3);
            mapper.insertUser(newUser);
            session.commit();
            allUser = mapper.getAllUser();
            System.out.print(allUser);
            System.out.println();

            user = mapper.getUserById(1);
            user.setAge(user.getAge() + 1);
            mapper.updateUser(user);
            session.commit();
            allUser = mapper.getAllUser();
            System.out.print(allUser);
            System.out.println();

            mapper.deleteUser(user);
            allUser = mapper.getAllUser();
            System.out.print(allUser);
            System.out.println();*/
            IArticleOperation article = session.getMapper(IArticleOperation.class);
            System.out.println(article.getArticleByUserId(1));

        }finally {
            session.close();
        }

    }
}
