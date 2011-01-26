package org.springframework.data.jpa.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.example.domain.User;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


/**
 * Class with the implementation of the custom repository code. Uses JDBC in
 * this case. For basic programatic setup see {@link UserRepositoryImpl} for
 * examples.
 * <p>
 * As you need to hand the instance a {@link javax.sql.DataSource} or
 * {@link org.springframework.jdbc.core.simple.SimpleJdbcTemplate} you manually
 * need to declare it as Spring bean:
 * 
 * <pre>
 * &lt;jpa:repository base-package=&quot;com.acme.repository&quot; /&gt;
 * 
 * &lt;bean id=&quot;userRepositoryImpl&quot; class=&quot;...UserRepositoryJdbcImpl&quot;&gt;
 *   &lt;property name=&quot;dataSource&quot; ref=&quot;dataSource&quot; /&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * Using {@code userRepositoryImpl} will cause the repository instance get this
 * bean injected for custom repository logic as the default postfix for custom
 * DAO instances is {@code Impl}.
 * 
 * @author Oliver Gierke
 */
class UserRepositoryJdbcImpl extends JdbcDaoSupport implements
UserRepositoryCustom {

    private static final String COMPLICATED_SQL = "SELECT * FROM User";


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.jpa.sample.repository.UserRepositoryCustom#
     * myCustomBatchOperation()
     */
    public List<User> myCustomBatchOperation() {

        return getJdbcTemplate().query(COMPLICATED_SQL, new UserRowMapper());
    }

    private static class UserRowMapper implements ParameterizedRowMapper<User> {

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.springframework.jdbc.core.simple.ParameterizedRowMapper#mapRow
         * (java.sql.ResultSet, int)
         */
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setLastname(rs.getString("lastname"));
            user.setFirstname(rs.getString("firstname"));

            return user;
        }
    }
}
