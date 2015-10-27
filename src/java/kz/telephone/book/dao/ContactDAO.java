package kz.telephone.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kz.telephone.book.entity.Contact;
import kz.telephone.book.entity.Street;

public class ContactDAO {

    public List<Street> findAll() {
        List<Street> streets = new ArrayList<Street>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql;

        try {
            conn = getConnection();

            sql = "select * from streets";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Street street = new Street();
                street.setId(rs.getInt("id"));
                street.setName(rs.getString("name"));
                streets.add(street);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return streets;

    }

    public Street findStreetById(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = null;
        Street street = new Street();
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "select * from streets where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                street.setId(rs.getInt("id"));
                street.setName(rs.getString("name"));

            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return street;

    }

    public List<Contact> getInfoByLastname(String lastname) {

        List<Contact> contacts = new ArrayList<Contact>();
        Contact cont = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "SELECT *, streets.name FROM contacts JOIN streets ON contacts.street_id=streets.id  WHERE lastname LIKE '%" + lastname + "%'";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cont = new Contact();
                cont.setId(rs.getInt("id"));
                cont.setFirstname(rs.getString("firstname").trim());
                cont.setLastname(rs.getString("lastname").trim());
                cont.setFathername(rs.getString("fathername").trim());
                cont.setPhone(rs.getString("phone").trim());
                cont.setStreet_id(rs.getInt("street_id"));
                cont.setStreet_name(rs.getString("streets.name"));
                cont.setFlat(rs.getInt("flat"));
                contacts.add(cont);

            }

            rs.close();
            stmt.close();
            stmt = null;


            conn.close();
            conn = null;

        } catch (Exception e) {
            System.out.println(e);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    System.err.println(sqlex);
                }

                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                }

                conn = null;
            }
        }

        return contacts;

    }

    public List<Contact> getInfoByPhone(String phone) {

        List<Contact> contacts = new ArrayList<Contact>();
        Contact cont = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "SELECT *, streets.name FROM contacts JOIN streets ON contacts.street_id=streets.id WHERE phone LIKE '%" + phone + "%'";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cont = new Contact();
                cont.setId(rs.getInt("id"));
                cont.setFirstname(rs.getString("firstname").trim());
                cont.setLastname(rs.getString("lastname").trim());
                cont.setFathername(rs.getString("fathername").trim());
                cont.setPhone(rs.getString("phone").trim());
                cont.setStreet_id(rs.getInt("street_id"));
                cont.setStreet_name(rs.getString("streets.name"));
                cont.setFlat(rs.getInt("flat"));
                contacts.add(cont);

            }

            rs.close();
            stmt.close();
            stmt = null;


            conn.close();
            conn = null;

        } catch (Exception e) {
            System.out.println(e);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here           
                }

                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here     
                }

                conn = null;
            }
        }

        return contacts;

    }

    public Contact getContactById(int id) {

        Contact cont = new Contact();
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "SELECT *, streets.name FROM contacts JOIN streets ON contacts.street_id=streets.id WHERE contacts.id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cont.setId(rs.getInt("id"));
                cont.setFirstname(rs.getString("firstname").trim());
                cont.setLastname(rs.getString("lastname").trim());
                cont.setFathername(rs.getString("fathername").trim());
                cont.setPhone(rs.getString("phone").trim());
                cont.setStreet_id(rs.getInt("street_id"));
                cont.setStreet_name(rs.getString("streets.name"));
                cont.setFlat(rs.getInt("flat"));


            }

            rs.close();
            stmt.close();
            stmt = null;


            conn.close();
            conn = null;

        } catch (Exception e) {
            System.out.println(e);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here           
                }

                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here     
                }

                conn = null;
            }
        }

        return cont;

    }

    public boolean updateContact(String firstname, String lastname, String fathername, String phone, int st_id, int flat, int id) {
        boolean val = false;
        Connection conn = null;
        PreparedStatement st = null;
        String sql = null;

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "update contacts set lastname=?, firstname=?, fathername=?, phone=?, street_id=?, flat=? where id=?";
            st = conn.prepareStatement(sql);
            st.setString(1, lastname);
            st.setString(2, firstname);
            st.setString(3, fathername);
            st.setString(4, phone);
            st.setInt(5, st_id);
            st.setInt(6, flat);
            st.setInt(7, id);
            st.executeUpdate();
            val = true;

        } catch (Exception e) {
        } finally {
            try {
                if (st != null) {
                    st.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return val;

    }

    public boolean deleteContact(int id) {
        boolean val = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "DELETE FROM contacts WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            val = true;


            stmt.close();
            stmt = null;


            conn.close();
            conn = null;

        } catch (Exception e) {
            System.out.println(e);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here           
                }

                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here     
                }

                conn = null;
            }
        }

        return val;

    }

    public List<Contact> getInfoByStreet(int id_street) {

        List<Contact> contacts = new ArrayList<Contact>();
        Contact cont = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "SELECT *, streets.name FROM contacts JOIN streets ON contacts.street_id=streets.id where contacts.street_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_street);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cont = new Contact();
                cont.setId(rs.getInt("id"));
                cont.setFirstname(rs.getString("firstname").trim());
                cont.setLastname(rs.getString("lastname").trim());
                cont.setFathername(rs.getString("fathername").trim());
                cont.setPhone(rs.getString("phone").trim());
                cont.setStreet_id(rs.getInt("street_id"));
                cont.setStreet_name(rs.getString("streets.name"));
                cont.setFlat(rs.getInt("flat"));
                contacts.add(cont);

            }

            rs.close();
            stmt.close();
            stmt = null;


            conn.close();
            conn = null;

        } catch (Exception e) {
            System.out.println(e);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here           
                }

                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here     
                }

                conn = null;
            }
        }

        return contacts;

    }

    public boolean insertContact(String firstname, String lastname, String fathername, String phone, int st_id, int flat) {
        boolean val = false;
        Connection conn = null;
        PreparedStatement st = null;
        String sql = null;

        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            sql = "insert into contacts(lastname, firstname, fathername, phone, street_id, flat) values(?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, lastname);
            st.setString(2, firstname);
            st.setString(3, fathername);
            st.setString(4, phone);
            st.setInt(5, st_id);
            st.setInt(6, flat);
            st.executeUpdate();
            val = true;

        } catch (Exception e) {
        } finally {
            try {
                if (st != null) {
                    st.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return val;

    }

    private Connection getConnection() throws SQLException, NamingException {
        Connection conn;
        Context ctx = (Context) new InitialContext().lookup("java:comp/env");
        conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();
        return conn;
    }
}
